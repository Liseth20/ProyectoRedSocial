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

        <!--<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1 ">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="css/PaginaInicio.css">
       
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    </head>
    <body class="bodys">


        <h1 class="h1s">Login</h1>

        <h3 id="registroCompleto" hidden>${saludo}</h3>
       
        <div class="container">
            <div id="Registro" class="alert alert-success alert-dismissible" role="alert">
                Registro Completo!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="Error" class="alert alert-danger alert-dismissible" role="alert">
                Usuario y/o contraseña incorrecta. Intentelo nuevamente!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="Eliminado" class="alert alert-success alert-dismissible" role="alert">
                Usuario eliminado conrrectamente!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div> 
        </div>

        <div id="formulario">
            <form action="LUsuarios" method="post">

                <input type="hidden" name="Accion" value="Verificar">
                <input type="hidden" name="idUsuario" value="0">

                <label id="label">Usuario:  </label><br>
                <input type="text" class="campo" name="txtUsuario" placeholder="Ingrese su usuario" required>
                <br>
                <br>
                <label id="label">Contraseña: </label><br>
                <input type="password" class="campo" name="txtPass" placeholder="Ingrese su contraseña" required>
                <br>
                <div id="sub">

                    <input type="submit" class="boton" name="btnTipo" value="Ingresar">  
                </div>
                <br>
                <label id="n">No posees una cuenta?</label> <a id="a" href= "FormularioRegistro.jsp" onclick="">Registrate aquí</a>

            </form>
        </div>





    </body>
</html>
