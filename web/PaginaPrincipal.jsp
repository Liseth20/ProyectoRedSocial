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
        
        <%-- Formulario para escribir una noticia--%>

          <form action = "LNoticias" method="post">
            <input type="hidden" name="idUsuario" value="${id}">
            <input type="hidden" name="Accion" value="Iniciar">
            <input type="submit" value="Escribir publicación">

        </form>
         
    </body>
</html>
