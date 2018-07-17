<%-- 
    Document   : ActualizarGeneros
    Created on : Jul 13, 2018, 5:32:06 PM
    Author     : darian
--%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Generos</title>
    </head>
    <body>

        <h1>Actualizar genero</h1>

        <h3>Cambia los datos que deseas actualizar</h3>

        <form action = "LGenero" method="post">

            <input type="hidden" name="Accion" value="Actualizar">
            <input type="hidden" name="CodigoGenero" value="${GeneroActualizar.getIdGenero_musical()}">

            Nombre:
            <br> 
            <input type="text" name="nombre" value="${GeneroActualizar.getNombre()}" required="">
            <br>
            <br>


            Descripción:
            <br> 
         
            <input type="text" size="50" name="descripcion" value="${GeneroActualizar.getDescripcion()}" required="">

            <br>
            <br>

            <input type="submit" name = "btnActualizar" id="button" value="Actualizar">


        </form>


    </body>
</html>
