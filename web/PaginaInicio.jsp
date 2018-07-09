<%-- 
    Document   : PaginaInicio
    Created on : 25-jun-2018, 15:19:44
    Author     : Rodrigo Moreno S
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>

        <form action="LUsuarios" method="post">
            
            <input type="hidden" name="Accion" value="Verificar">
            <input type="hidden" name="idUsuario" value="0">
            
            <label for="txtUsuario">Usuario:  </label>
            <input type="text" name="txtUsuario" placeholder="Ingrese su usuario" required>
            <br>
            <br>
            <label for="txtPass">Contraseña: </label>
            <input type="text" name="txtPass" placeholder="Ingrese su contraseña" required>
            <br>
            
            <br>
            <input type="submit" name="btnTipo" value="Ingresar">       
            <br>
            <br>
            <input type="text" value="${Er}" readonly="">

        </form>
        <br>
        <br>
       ¿No posees una cuenta? <a href= "FormularioRegistro.html" onclick="">Registrate aquí</a>
    </body>
</html>
