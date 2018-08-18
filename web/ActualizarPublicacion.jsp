
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
        <div class="form-group col-md-8">
            <form action = "LNoticias" method="post">
                <input type="hidden" name="idUsuario" value="${id}">
                <input type="hidden" name="Accion" value="Actualizar">
                <input type="hidden" name="CodigoNoticia" value="${PublicacionActualizar.getIdNoticias()}">

                <div class="form-group">
                    <label for="titulo">Título</label>
                    <input type="text" class="form-control" value="${PublicacionActualizar.getTitulo()}" name="titulo" required="">
                </div> 
                <div class="form-group">
                    <label for="contenido">Contenido</label>
                    <input type="text" class="form-control" value="${PublicacionActualizar.getContenido()}" name="contenido" required="">
                </div> 

                <input type="submit" class="btn-primary" name = "btnActualizar" id="button" value="Actualizar">


            </form>
        </div>
    </body>
</html>
