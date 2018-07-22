
<%@page import="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar publicación</title>
    </head>
    <body>

        <header>  
            <h2>Editar publicación</h2>
        </header>


        <h3>Escribe en los campos que desees actualizar</h3>

        <form action = "LNoticias" method="post">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="Accion" value="Actualizar">
            <input type="hidden" name="CodigoNoticia" value="${PublicacionActualizar.getIdNoticias()}">

            Título:
            <br> 
            <input type="text" name="titulo" value="${PublicacionActualizar.getTitulo()}" required="">
            <br>
            <br>


            Contenido:
            <br> 
            <input type="text" size="50" name="contenido" value="${PublicacionActualizar.getContenido()}" required="">
            <br>
            <br>

            <input type="submit" name = "btnActualizar" id="button" value="Actualizar">


        </form>

    </body>
</html>
