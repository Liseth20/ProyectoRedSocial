<%-- 
    Document   : RegistroAdmin
    Created on : 30-jun-2018, 12:56:01
    Author     : Rodrigo Moreno S
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Aministrador</title>
    </head>
    <body>

        <form action="LAdministradores" method="POST">

            <input type="hidden" name="Accion" value="Ingresar Admin">
            <input type="hidden" name="idUsuario" value="${id}">

            <div class="col-md-5">

                <div class="form-group">
                    <label for="fecha">Fecha de contratación</label>
                    <input type="date" class="form-control" name="fecha" required>
                </div>

                <input type= "submit" class="boton" value="Registrarse como administrador">

            </div>

        </form>

    </body>
</html>
