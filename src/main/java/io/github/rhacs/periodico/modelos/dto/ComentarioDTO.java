package io.github.rhacs.periodico.modelos.dto;

public class ComentarioDTO {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public ComentarioDTO() {

    }

    public ComentarioDTO(Long id, String name, String email, String body, Long postId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.postId = postId;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public Long getPostId() {
        return postId;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ComentarioDTO [id=%s, name=%s, email=%s, body=%s, postId=%s]", id, name, email, body,
                postId);
    }

}
