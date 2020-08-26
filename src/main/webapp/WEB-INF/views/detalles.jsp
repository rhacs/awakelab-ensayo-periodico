<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Codificación de caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración de ancho y escala inicial -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Título -->
        <title>Periódico Electrónico</title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha512-MoRNloxbStBcD8z3M/2BmnT+rg4IsMxPkXaGh2zD6LGNNFE80W3onsAhRcMAMrSoyWL9xD7Ert0men7vR8LUZg==" crossorigin="anonymous" />
    </head>

    <body>
        <!-- Navegación -->
        <nav class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/posts">Periódico Electrónico</a>
            </div>
        </nav>
        <!-- /Navegación -->

        <!-- Contenido -->
        <div class="container my-5">
            <div class="row">
                <div class="col-12">
                    <a href="${pageContext.request.contextPath}/posts">&laquo; Volver</a>

                    <div class="card mt-3">
                        <div class="card-header">${post.getUsuario().getName()} <em>(${post.getUsuario().getEmail()})</em></div>

                        <div class="card-body">
                            <h5 class="card-title">${post.getTitle()}</h5>

                            <p class="card-text">${post.getBody()}</p>
                        </div>
                    </div>

                    <h3 class="border-bottom my-4 pb-2">Comentarios</h3>
                </div>

                <core:choose>
                    <core:when test="${post.getComentarios() != null && post.getComentarios().size() > 0}">
                        <core:forEach items="${post.getComentarios()}" var="comentario">
                            <div class="col-md-6">
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <h5 class="card-title">#${comentario.getId()} ${comentario.getName()} <em class="text-muted">(${comentario.getEmail()})</em></h5>

                                        <p class="card-text">${fn:substring(comentario.getBody(), 0, 20)}...</p>
                                    </div>
                                </div>
                            </div>
                        </core:forEach>
                    </core:when>

                    <core:otherwise>
                        <div class="col-12">
                            <div class="alert alert-warning">Esta publicación no tiene comentarios</div>
                        </div>
                    </core:otherwise>
                </core:choose>
            </div>
        </div>
        <!-- /Contenido -->

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js" integrity="sha512-ubuT8Z88WxezgSqf3RLuNi5lmjstiJcyezx34yIU2gAHonIi27Na7atqzUZCOoY4CExaoFumzOsFQ2Ch+I/HCw==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha512-M5KW3ztuIICmVIhjSqXe01oV2bpe248gOxqmlcYrEzAvws7Pw3z6BK0iGbrwvdrUQUhi3eXgtxp5I8PDo9YfjQ==" crossorigin="anonymous"></script>
    </body>
</html>
