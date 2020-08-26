package io.github.rhacs.periodico.modelos.dto;

public class PostDTO {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Long id;
    private String title;
    private String body;
    private Long userId;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public PostDTO() {

    }

    public PostDTO(Long id, String title, String body, Long userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("PostDTO [id=%s, title=%s, body=%s, userId=%s]", id, title, body, userId);
    }

}
