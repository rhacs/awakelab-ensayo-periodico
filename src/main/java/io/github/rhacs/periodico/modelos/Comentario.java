package io.github.rhacs.periodico.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.periodico.Constantes;

@Entity
@Table(name = Constantes.TABLA_COMENTARIOS)
public class Comentario {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Comentario}
     */
    @Id
    @Column(name = Constantes.COMENTARIO_ID)
    private Long id;

    /**
     * Nombre del autor del {@link Comentario}
     */
    @NotEmpty
    @Size(max = 250)
    private String name;

    /**
     * Dirección de correo electrónico del autor del {@link Comentario}
     */
    @NotEmpty
    @Email
    @Size(max = 100)
    private String email;

    /**
     * Contenido del {@link Comentario}
     */
    @NotEmpty
    @Size(max = 1000)
    private String body;

    /**
     * {@link Post} al que pertenece el {@link Comentario}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.POST_ID, nullable = false)
    private Post post;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Comentario}
     */
    public Comentario() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Comentario}
     * 
     * @param id    identificador numérico
     * @param name  nombre del autor
     * @param email dirección de correo electrónico del autor
     * @param body  cuerpo
     * @param post  {@link Post} al que pertenece
     */
    public Comentario(Long id, String name, String email, String body, Post post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.post = post;
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
     * @return el nombre del autor
     */
    public String getName() {
        return name;
    }

    /**
     * @return la dirección de correo electrónico del autor
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return el cuerpo
     */
    public String getBody() {
        return body;
    }

    /**
     * @return el {@link Post} al que pertenece
     */
    public Post getPost() {
        return post;
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
     * @param name el nombre del autor a establecer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email la dirección de correo electrónico a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param body el cuerpo a establecer
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @param post el {@link Post} a establecer
     */
    public void setPost(Post post) {
        this.post = post;
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

        Comentario other = (Comentario) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Comentario [id=%s, name=%s, email=%s, body=%s]", id, name, email, body);
    }

}
