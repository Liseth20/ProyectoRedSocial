<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Logica.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%-- Fuentes de google--%>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type=text/css>
        <%-- Iconos--%>


        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
        <%-- CSS--%>
        <link rel="stylesheet" href="css/style.css"/>

        <title>Muro de publicaciones</title>

    </head>
    <body>
        <c:url var="linkEditar" value="LAlbum">
            <c:param name="Accion" value="Mostrar"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>
        <c:url var="irArtista" value="LUsuarios">
            <c:param name="Accion" value="SaberNombreArtistas"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>
         <c:url var="linkVisualizar" value="LNoticias">
            <c:param name="Accion" value="Visualizar"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url> 
        <c:url var="salir" value="PaginaInicio.jsp">

        </c:url>

        <%-- -----------------------------------NAVBAR------------------------------------- --%>

        <ul class="nav nav-tabs navbar-inverse" id="formtabs">
            <!-- Tab nav -->
            <li class=""><a href="${irArtista}"  data-toggle="tab">Inicio</a></li>
            <li class=""><a href="${linkEditar}" data-toggle="tab">Albumes</a></li>
            <li class="active"><a href="${linkVisualizar}" data-toggle="tab">Noticias</a></li> 
            <li class=""><a href="${salir}" data-toggle="tab">Logout</a></li> 
        </ul>

        <h1> Muro de publicaciones </h1> 
        <div>
            <c:forEach var="tempNoticia" items="${Publicaciones}">

                <%-- Link actualizador para cada genero utilizando el campo clave --%>
                <c:url var="linkCargar" value="LNoticias">

                    <c:param name="idUsuario" value="${id}"></c:param>
                    <c:param name="Accion" value="Cargar"></c:param>
                    <c:param name="idNoticia" value="${tempNoticia.getIdNoticias()}"></c:param>

                </c:url>

                <%-- Link para eliminar cada genero utilizando el campo clave --%>

                <c:url var="linkEliminar" value="LNoticias">

                    <c:param name="idUsuario" value="${id}"></c:param>           
                    <c:param name="Accion" value="Eliminar"></c:param>
                    <c:param name="idNoticia" value="${tempNoticia.getIdNoticias()}"></c:param>

                </c:url>

                <%-- Cajas de publicaciones --%>
                <div class="comments-container">

                    <ul id="comments-list" class="comments-list">

                        <li>
                            <%-- Contenedor avatar--%>             
                            <div class="comment-avatar">
                                <img src="Imagenes/avatar.png" alt="avatar"/>
                            </div>

                            <%-- Contenedor noticia--%>
                            <div class="comment-box">

                                <%-- Contenedor cabecera--%>
                                <div class="comment-head">
                                    <h6 class="comment-name">${tempNoticia.getTitulo() }</h6>
                                    <span> ${tempNoticia.getFecha() } </span>
                                    <a href="${linkEliminar}"><i class="fas fa-trash"></i></a> 
                                    <a href="${linkCargar}"><i class="fas fa-pencil-alt"></i></a> 
                                </div>

                                <%-- Contenedor de la descripcion--%>
                                <div class="comment-content">
                                    ${tempNoticia.getContenido() }
                                </div>

                            </div>
                        </li>
                    </ul>
                </div>
            </c:forEach>
        </div>
        <br> 

        <%-- Boton para escribir publicación --%>
        <form action = "LNoticias" method="post">  

            <input type="hidden" name="idUsuario" value="${id}">

            <input type="hidden" name="Accion" value="ir">


            <input class="button-style" type="submit" name = "btnPublicar" id="button" value="Escribir noticia"/>




        </form>

    </body>
</html>
