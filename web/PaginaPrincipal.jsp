<%-- 
    Document   : PaginaPrincipal
    Created on : 28-jun-2018, 13:48:15
    Author     : Rodrigo Moreno S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página principal</title>
    </head>
    <body>
        <h1>Página principal</h1>
        <form action = "LUsuarios" method="post">
            <input type="hidden" name="idUsuario" value="${id}">
            <input type="hidden" name="Accion" value="CapturarDatos">

            <input type="submit" value="Actualizar perfil">

        </form>
        <br>
        <br>
        <form action = "LUsuarios" method="post">
            <input type="hidden" name="idUsuario" value="${id}">
            <input type="hidden" name="Accion" value="Eliminar">
            <input type="submit" value="Eliminar cuenta">

        </form>
        <br>
        <br>

        <%-- Formulario para ingresar al muro --%>

        <form action = "LNoticias" method="get">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="Accion" value="Visualizar">
            <input type="submit" value="Ingresar al muro">

        </form>

        <br>
        <br>

        <%-- Formulario para ingresar al ALBUM--%>

        <form action = "LAlbum" method="get">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="Accion" value="Ver">
            <input type="submit" value="Album">

        </form>

    </body>
</html>
