<%-- 
    Document   : FormularioRegistro
    Created on : 30-jun-2018, 21:11:03
    Author     : Rodrigo Moreno S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <title>Formulario de registro</title>
    </head>
    <body>

        <h1>Formulario de registro</h1>

        <h2>Bienvenido a BestMusicFM</h2>

        <h3>Para ser parte de esta rítmica red social, primero inicia registrandote</h3>

        <form action = "LUsuarios" method="post">

            <div class="form-group col-md-5">
                <input type="hidden" name="Accion" value="Ingresar">
                <input type="hidden" name="idUsuario" value="0">

                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required="">
                </div>

                <div class="form-group">
                    <label for="apellido1">Primer apellido</label>
                    <input type="text" class="form-control" name="apellido1" id="nombre" required="">
                </div>

                <div class="form-group">
                    <label for="apellido2">Segundo apellido</label>
                    <input type="text" class="form-control" name="apellido2" required="">
                </div>

                <div class="form-group">
                    <label for="fechanaci">Fecha de nacimiento</label>
                    <input type="date" class="form-control" name="fechanaci" required="">
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" name="email" required="">
                </div>

                <div class="form-group">
                    <label for="email">Usuario</label>
                    <input type="text" class="form-control" name="usuario" required="">
                </div>

                <div class="form-group">
                    <label for="contrasena">Contraseña</label>
                    <input type="password" class="form-control" name="contrasena" required="">
                </div>

                <div class="form-group">
                    <label for="sexo">Género</label>
                    <br>
                    <input type="radio"  name="sexo" id="sexo_01" value="Femenino" required=""> Femenino
                    <input type="radio"  name="sexo" id="sexo_02" value="Masculino" required=""> Masculino
                </div>

                <div class="form-group">
                    <label for="pais">País de nacimiento</label>
                    <select name="pais" class="form-control" >
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
                    <label for="tipoUsuario">Tipo de usuario</label>
                    <br>
                    <input type="radio" name="tipoUsuario" value="Artista" required=""> Artista
                    <input type="radio" name="tipoUsuario" value="Experto" required=""> Experto
                    <input type="radio" name="tipoUsuario" value="Administrador" required=""> Administrador
                    <input type="radio" name="tipoUsuario" value="Fan" required=""> Fan

                </div>

                <input type="submit" class="btn btn-primary" name = "btnEnviar" id="button" value="Enviar">
            </div>
        </form>

    </body>
</html>
