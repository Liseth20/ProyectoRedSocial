<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/Auxiliar.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="artista">
        <%-- ----------------------------------LINKS---------------------------------- --%>
        <c:url var="linkEditar" value="LAlbum">
            <c:param name="Accion" value="Mostrar"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>

        <c:url var="linkNoticias" value="LNoticias">
            <c:param name="Accion" value="ir"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>  

        <c:url var="linkVisualizar" value="LNoticias">
            <c:param name="Accion" value="Visualizar"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>
        <c:url var="salir" value="PaginaInicio.jsp">

        </c:url>
        <c:url var="irArtista" value="LUsuarios">
            <c:param name="Accion" value="SaberNombre"></c:param>
            <c:param name="idUsuario" value="${id}"></c:param>
        </c:url>

        <%-- ----------------------------------NAVBAR---------------------------------- --%>

        <ul class="nav nav-tabs navbar-inverse" id="formtabs">
            <!-- Tab nav -->
            <li class="active"><a href="${irArtista}"  data-toggle="tab">Inicio</a></li>
            <li class=""><a href="${linkEditar}" data-toggle="tab">Albumes</a></li>
            <li class=""><a href="${linkVisualizar}" data-toggle="tab">Noticias</a></li>   
            <li class=""><a href="${salir}" data-toggle="tab">Logout</a></li> 
        </ul>

        <%-- ----------------------------------COLUMNA DERECHA---------------------------------- --%>

        <div class="col-md-2">

        </div>
        <div id="espacio" class="col-md-2" > 
            <br>
            <div class="form-group">
                <form action = "LUsuarios" method="post">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="CapturarDatos">

                    <input type="submit" class=" btn btn-primary" value="Actualizar perfil">

                </form>
            </div>

            <div class="form-group">
                <form action = "LArtistas" method="post">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="EliminarArtista">
                    <input type="submit" class=" btn btn-primary" value="Eliminar cuenta">

                </form>
            </div>
        </div>



        <%-- ----------------------------------COLUMNA CENTRAL---------------------------------- --%>

        <div  class="col-md-10" >  
            <div id="" class="col-md-11" > 
                <h1> Bienvenido ${nombre}</h1>
                <h3>¿Qué deseas hacer hoy?</h3>
            </div>




            <div id="aa" class="form-group col-md-2">
                <form action = "LAlbum" method="get">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="Mostrar">
                    <input type="submit" class=" btn btn-primary" value="Editar Albumes">
                </form>
            </div>

            <div class="form-group col-md-2">
                <form action = "LNoticias" method="post">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="ir">
                    <input type="submit" class=" btn btn-primary" value="Ingresar nueva noticia">
                </form>
            </div>

            <div class="form-group col-md-2">
                <form action = "LNoticias" method="get">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="Visualizar">
                    <input type="submit" class=" btn btn-primary" value="Ver noticias">
                </form>
            </div>
        </div>




    </body>
</html>
