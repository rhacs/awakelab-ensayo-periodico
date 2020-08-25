package io.github.rhacs.periodico;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene los registros de los {@link Usuario}s
     */
    public static final String TABLA_USUARIOS = "users";

    /**
     * Nombre de la tabla que contiene los registros de los {@link Post}s
     */
    public static final String TABLA_POSTS = "posts";

    /**
     * Nombre de la tabla que contiene los registros de los {@link Comentario}s
     */
    public static final String TABLA_COMENTARIOS = "comments";

    // Columnas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_USUARIOS}
     */
    public static final String USUARIO_ID = "id";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_POSTS}
     */
    public static final String POST_ID = "id";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_COMENTARIOS}
     */
    public static final String COMENTARIO_ID = "id";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
