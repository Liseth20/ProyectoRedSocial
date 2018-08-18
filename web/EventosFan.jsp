
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>Eventos</title>

    </head>
    <body>
        <c:url var="salir" value="PaginaInicio.jsp"></c:url>

            <h1>Eventos</h1>

            <%--  <h3>¡Hoy es un buen día para escribir un nuevo evento!</h3>--%>
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

     
    </body>
</html>
