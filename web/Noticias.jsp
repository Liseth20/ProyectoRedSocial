<%-- 
    Document   : DatosNoticia
    Created on : Jun 26, 2018, 2:53:08 PM
    Author     : darian
--%>

<%@page import="java.util.Date"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/Auxiliar.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noticia</title>
    </head>
    <body>



        <%-- ----------------------------------LINKS---------------------------------- --%>
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


        <%-- ----------------------------------NAVBAR---------------------------------- --%>

        <ul class="nav nav-tabs navbar-inverse" id="formtabs">
            <!-- Tab nav -->
            <li class=""><a href="${irArtista}"  data-toggle="tab">Inicio</a></li>
            <li class=""><a href="${linkEditar}" data-toggle="tab">Albumes</a></li>
            <li class="active"><a href="${linkVisualizar}" data-toggle="tab">Noticias</a></li>
            <li class=""><a href="${salir}" data-toggle="tab">Logout</a></li>        
        </ul>


        <%-- ----------------------------------BARRA---------------------------------- --%>

        <div class="col-md-1">
        </div>

        <div id="espacio" class="col-md-1" ></div>





        <div class="form-group col-md-8">

            <h1> Crea una nueva publicación </h1>
            <h3>¿Qué estás pensando hoy?, escribe unas palabras para tus seguidores</h3>
            <form action = "LNoticias" method="post">  

                <input type="hidden" name="idUsuario" value="${id}">
                <input type="hidden" name="Accion" value="Escribir">



                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" class="form-control" value="${PublicacionActualizar.getTitulo()}" name="titulo" required="">
                </div> 
                Escribe algo para tus seguidores:

                <br/>  
                <textarea name="contenido" rows="4" cols="20" id="contenido" required="">
                </textarea>
                <br/>
                <br/>

                <input type="submit" name = "btnPublicar" id="button" value="Publicar"/>

            </form>
        </div>


    </body>
</html>
