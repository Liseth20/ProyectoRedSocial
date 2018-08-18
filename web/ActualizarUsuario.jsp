<%-- 
    Document   : ActualizarUsuario
    Created on : 27-jun-2018, 13:18:08
    Author     : Rodrigo Moreno S
--%>


<%@page import="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <title>Atualizar usuario</title>
    </head>
    <body>

        <h1>Actualizar datos</h1>

        <h3>Cambia los datos que deseas actualizar</h3>
        <div class="col-md-7">
            <form action = "LUsuarios" method="post">

                <input type="hidden" name="idUsuario" value="${id}">
                <input type="hidden" name="Accion" value="Actualizar">

                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="${Nombre}" required="">
                </div> 

                <div class="form-group">
                    <label for="apellido1">Apellidos</label>
                    <input type="text" class="form-control" name="apellido1" value="${Ape1}" required="">&nbsp;&nbsp;
                    <input type="text" class="form-control" name="apellido2" value="${Ape2}" required="">
                </div> 

                <div class="form-group">
                    <label for="fechanaci">Fecha de nacimiento</label>
                    <input type = "text" class="form-control" name = "fechanaci" value="${Fecha_naci}" required="">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" name="email" required="" value="${Email}">
                </div>

                <div class="form-group">
                    <label for="usuario">Nombre de usuario</label>
                    <input type="text" class="form-control"name="usuario" required="" value="${Nombre_usuario}">
                </div>

                <div class="form-group">
                    <label for="contrasena">Contraseña</label>
                    <input type="password" name="contrasena" class="form-control"  value="${Contrasena}" required="">
                </div>

                <div class="form-group">

                    Género
                    <br>
                    <label class="checkbox-inline" >
                        <input type="radio" name="sexo" id="sexo_01" value="Femenino" required="">  Famenino
                    </label>
                    <label class="checkbox-inline" >
                        <input type="radio" name="sexo" id="sexo_02" value="Masculino" required=""> Masculino
                    </label>
                </div>


                <div class="form-group">
                    <label for="pais">País de nacimiento </label>
                    <select name="pais" class="form-control">
                        <option>Costa Rica</option>
                        <option>México</option>
                        <option>Colombia</option>
                        <option>Chile</option>
                        <option>Perú</option>
                        <option>Argentina</option>
                        <option>España</option>
                    </select>
                </div>

                <div class="form-group">
                    <input type="submit" class="btn btn-success" name = "btnEnviar" id="button" value="Enviar">
                </div>

            </form>
        </div>

    </body>

</html>
