package io.github.rhacs.periodico.modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.periodico.Constantes;

@Entity
@Table(name = Constantes.TABLA_POSTS)
public class Post {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Post}
     */
    @Id
    @Column(name = Constantes.POST_ID)
    private Long id;

    /**
     * Título del {@link Post}
     */
    @NotEmpty
    @Size(max = 250)
    private String title;

    /**
     * Cuerpo del {@link Post}
     */
    @NotEmpty
    @Size(max = 1000)
    private String body;

    /**
     * {@link Usuario} autor del {@link Post}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private Usuario usuario;

    /**
     * Listado de {@link Comentario}s asociados al {@link Post}
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private Set<Comentario> comentarios;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Post}
     */
    public Post() {
        comentarios = new HashSet<>();
    }

    /**
     * Crea una nueva instancia de la entidad {@link Post}
     * 
     * @param id          identificador numérico
     * @param title       título
     * @param body        cuerpo
     * @param usuario     el {@link Usuario} autor
     * @param comentarios litado de {@link Comentario}s asociados
     */
    public Post(Long id, String title, String body, Usuario usuario, Set<Comentario> comentarios) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.usuario = usuario;
        this.comentarios = comentarios;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public Long getId() {
        return id;
    }

    /**
     * @return el título
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return el cuerpo
     */
    public String getBody() {
        return body;
    }

    /**
     * @return el {@link Usuario}
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return el listado de {@link Comentario}s asociados
     */
    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param title el título a establecer
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param body el cuerpo a establecer
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @param usuario el {@link Usuario} a establecer
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @param comentarios el listado de {@link Comentario}s a establecer
     */
    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Post other = (Post) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Post [id=%s, title=%s, body=%s, usuario=%s, comentarios=%s]", id, title, body, usuario,
                comentarios);
    }

}
