package io.github.rhacs.periodico.controladores;

import java.util.Arrays;
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
import io.github.rhacs.periodico.repositorios.ComentariosRepositorio;
import io.github.rhacs.periodico.repositorios.PostsRepositorio;

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

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Efectúa la carga de los datos a los repositorios correspondientes
     */
    private void cargarDatos() {
        // Limpiar los repositorios
        postsRepositorio.deleteAll();
        comentariosRepositorio.deleteAll();

        // Inicializar RestTemplate
        RestTemplate rest = new RestTemplate();

        // Efectuar la petición a la API de Posts y capturar la respuesta
        ResponseEntity<Post[]> respuesta = rest.getForEntity(Constantes.API_POSTS, Post[].class);

        // Extraer datos
        List<Post> posts = Arrays.asList(respuesta.getBody());

        // Guardar posts en el repositorio
        posts.forEach(post -> postsRepositorio.save(post));

        // Efectuar petición a la API de Comentarios y capturar respuesta
        ResponseEntity<Comentario[]> resp = rest.getForEntity(Constantes.API_COMENTARIOS, Comentario[].class);

        // Extraer los datos
        List<Comentario> comentarios = Arrays.asList(resp.getBody());

        // Almacenar los registros en el repositorio
        comentarios.forEach(comentario -> comentariosRepositorio.save(comentario));
    }

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de publicaciones disponibles en el repositorio
     * 
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping
    public String listarPublicaciones(Model modelo) {
        // Cargar los datos
        cargarDatos();

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
    @GetMapping(path = "/post/{id:^[0-9]+$}")
    public String verDetalles(@PathVariable Long id, Model modelo) {
        // Buscar información del Post
        Optional<Post> post = postsRepositorio.findById(id);

        // Verificar si existe
        if (post.isPresent()) {
            // Agregar post al modelo
            modelo.addAttribute("post", post);

            // Devolver vista
            return "detalles";
        }

        // Redireccionar
        return "redirect:/?noid=" + id;
    }

}
