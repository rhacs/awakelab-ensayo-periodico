<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Periódico Electrónico</a>
            </div>
        </nav>
        <!-- /Navegación -->

        <!-- Contenido -->
        <div class="container my-5">
            <div class="row">
                <div class="col-12">
                    <h1 class="border-bottom pb-2 mb-4">Listado de Posts</h1>

                    <core:if test="${not empty param.noid}">
                        <div class="alert alert-warning">No existe el registro con el identificador numérico <strong>${param.noid}</strong></div>
                    </core:if>

                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-nowrap">#</th>
                                    <th scope="col" class="text-nowrap">Título</th>
                                    <th scope="col" class="text-nowrap">Autor</th>
                                    <th scope="col" class="text-nowrap">Comentarios</th>
                                </tr>
                            </thead>

                            <tbody>
                                <core:choose>
                                    <core:when test="${posts != null && posts.size() > 0}">
                                        <core:forEach items="${posts}" var="post">
                                            <tr role="button" data-id="${post.getId()}">
                                                <th scope="row" class="text-nowrap">${post.getId()}</th>
                                                <td>${post.getTitle()}</td>
                                                <td class="text-nowrap">${post.getUsuario().getName()}</td>
                                                <td class="text-nowrap text-right">${post.getComentarios().size()}</td>
                                            </tr>
                                        </core:forEach>
                                    </core:when>

                                    <core:otherwise>
                                        <tr>
                                            <th scope="row" class="text-center">No hay registros para mostrar</th>
                                        </tr>
                                    </core:otherwise>
                                </core:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js" integrity="sha512-ubuT8Z88WxezgSqf3RLuNi5lmjstiJcyezx34yIU2gAHonIi27Na7atqzUZCOoY4CExaoFumzOsFQ2Ch+I/HCw==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha512-M5KW3ztuIICmVIhjSqXe01oV2bpe248gOxqmlcYrEzAvws7Pw3z6BK0iGbrwvdrUQUhi3eXgtxp5I8PDo9YfjQ==" crossorigin="anonymous"></script>
    </body>
</html>
