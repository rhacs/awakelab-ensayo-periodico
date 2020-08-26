package io.github.rhacs.periodico.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import io.github.rhacs.periodico.Constantes;
import io.github.rhacs.periodico.modelos.Comentario;
import io.github.rhacs.periodico.modelos.Post;
import io.github.rhacs.periodico.modelos.Usuario;
import io.github.rhacs.periodico.modelos.dto.ComentarioDTO;
import io.github.rhacs.periodico.modelos.dto.PostDTO;
import io.github.rhacs.periodico.repositorios.ComentariosRepositorio;
import io.github.rhacs.periodico.repositorios.PostsRepositorio;
import io.github.rhacs.periodico.repositorios.UsuariosRepositorio;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ComentariosRepositorio} que contiene los métodos de
     * manipulación y consulta relativos a los registros de {@link Comentario}s
     */
    @Autowired
    private ComentariosRepositorio comentariosRepositorio;

    /**
     * Objeto {@link PostsRepositorio} que contiene los métodos de manipulación y
     * consulta relativos a los registros de {@link Post}s
     */
    @Autowired
    private PostsRepositorio postsRepositorio;

    /**
     * Objeto {@link UsuariosRepositorio} que contiene los métodos de manipulación y
     * consulta relativos a los registros de {@link Usuario}s
     */
    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Efectúa la carga de los Posts al repositorio
     */
    private void cargarPosts() {
        // Inicializar RestTemplate
        RestTemplate rest = new RestTemplate();

        // Efectuar la petición a la API de Posts y capturar la respuesta
        ResponseEntity<PostDTO[]> respuesta = rest.getForEntity(Constantes.API_POSTS, PostDTO[].class);

        // Extraer datos
        List<PostDTO> posts = Arrays.asList(respuesta.getBody());
        List<Post> ps = new ArrayList<>();

        // Buscar todos los usuarios
        List<Usuario> usuarios = usuariosRepositorio.findAll();

        // Convertir los datos y guardar en repositorio
        posts.forEach(post -> {
            // Inicializar usuario
            Usuario usuario = null;

            // Buscar usuario
            for (Usuario u : usuarios) {
                if (u.getId().equals(post.getUserId())) {
                    usuario = u;
                    break;
                }
            }

            // Crear nuevo Post
            Post p = new Post(post.getId(), post.getTitle(), post.getBody(), usuario, new HashSet<>());

            // Agregar al listado
            ps.add(p);
        });

        // Guardar por lote
        postsRepositorio.saveAll(ps);
    }

    private void cargarComentarios() {
        // Iniciar RestTemplate
        RestTemplate rest = new RestTemplate();

        // Efectuar petición a la API y capturar respuesta
        ResponseEntity<ComentarioDTO[]> respuesta = rest.getForEntity(Constantes.API_COMENTARIOS, ComentarioDTO[].class);

        // Extraer datos
        List<ComentarioDTO> comentarios = Arrays.asList(respuesta.getBody());

        // Inicializar listado de Comentarios
        List<Comentario> comms = new ArrayList<>();

        // Buscar todos los posts
        List<Post> posts = postsRepositorio.findAll();

        // Convertir datos obtenidos
        comentarios.forEach(comentario -> {
            // Buscar post
            for(Post p : posts) {
                if(p.getId().equals(comentario.getPostId())) {
                    // Crear nuevo comentario
                    Comentario c = new Comentario(comentario.getId(), comentario.getName(), comentario.getEmail(), comentario.getBody(), p);

                    // Agregar al listado
                    comms.add(c);

                    break;
                }
            }
        });

        // Guardar registros por lote
        comentariosRepositorio.saveAll(comms);
    }

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Efectura la carga de datos y redirecciona a la página principal
     * 
     * @return un objeto {@link String} con el nombre de la vista
     */
    @GetMapping
    public String cargarDatos() {
        // Cargar datos
        cargarPosts();
        cargarComentarios();

        // Redireccionar
        return "redirect:/posts";
    }

    /**
     * Muestra el listado de publicaciones almacenadas en el repositorio
     * 
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping(path = "/posts")
    public String verListado(Model modelo) {
        // Buscar listado de publicaciones
        List<Post> posts = postsRepositorio.findAll(Sort.by(Direction.DESC, "id"));

        // Agregar listado al modelo
        modelo.addAttribute("posts", posts);

        // Devolver vista
        return "home";
    }

    /**
     * Muestra el detalle de un {@link Post}
     * 
     * @param id     identificador numérico del {@link Post}
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping(path = "/posts/{id:^[0-9]+$}")
    public String verDetalles(@PathVariable Long id, Model modelo) {
        // Buscar información del Post
        Optional<Post> post = postsRepositorio.findById(id);

        // Verificar si existe
        if (post.isPresent()) {
            // Agregar post al modelo
            modelo.addAttribute("post", post.get());

            // Devolver vista
            return "detalles";
        }

        // Redireccionar
        return "redirect:/posts?noid=" + id;
    }

}
