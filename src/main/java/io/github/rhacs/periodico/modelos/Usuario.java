package io.github.rhacs.periodico.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.periodico.Constantes;

@Entity
@Table(name = Constantes.TABLA_USUARIOS)
public class Usuario {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Usuario}
     */
    @Id
    @Column(name = Constantes.USUARIO_ID)
    private Long id;

    /**
     * Nombre del {@link Usuario}
     */
    @NotEmpty
    @Size(max = 50)
    private String name;

    /**
     * Alias del {@link Usuario}
     */
    @NotEmpty
    @Size(max = 20)
    private String username;

    /**
     * Dirección de correo electrónico del {@link Usuario}
     */
    @Email
    @NotEmpty
    @Size(max = 50)
    private String email;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Usuario}
     */
    public Usuario() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Usuario}
     * 
     * @param id       identificador numérico
     * @param name     nombre
     * @param username alias
     * @param email    dirección de correo electrónico
     */
    public Usuario(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
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
     * @return el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @return el alias
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return la dirección de correo electrónico
     */
    public String getEmail() {
        return email;
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
     * @param name el nombre a establecer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param username el alias a establecer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param email la dirección de correo electrónico a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(email, id, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Usuario other = (Usuario) obj;

        return Objects.equals(email, other.email) || Objects.equals(id, other.id)
                || Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return String.format("Usuario [id=%s, name=%s, username=%s, email=%s]", id, name, username, email);
    }

}
