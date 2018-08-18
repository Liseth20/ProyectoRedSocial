<%-- 
    Document   : PaginaPrincipalAdministradores
    Created on : 18-jul-2018, 13:10:18
    Author     : Rodrigo Moreno S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Auxiliar.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>Pagina Administrador</title>
    </head>
    <body>


        <%-- ----------------------------------COLUMNA DERECHA---------------------------------- --%>

        <div class="col-md-2">

        </div>
        <div id="espacio" class="col-md-2" > 
            <br>
            <div class="form-group">
                <form action = "LUsuarios" method="post">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="CapturarDatos">

                    <input type="submit" class=" btn btn-primary" value="Actualizar perfil">

                </form>
            </div>

            <div class="form-group">
                <form action = "LAdministradores" method="post">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="Eliminar">
                    <input type="submit" class=" btn btn-primary" value="Eliminar cuenta">

                </form>
            </div>
        </div>

        <div  class="col-md-10" >  
            <div id="" class="col-md-11" > 
                <h1> Bienvenido ${nombre}</h1>
                <h3>¿Qué deseas hacer hoy?</h3>
            </div> 





            <form action = "LGenero" method="post">  
                <input type="hidden" name="Accion" value="Mostrar">
                <input type="hidden" name="idUsuario" value="${id}">



                <br> <br>
                <input type="submit" class=" btn btn-primary" name = "btnMantenimiento" value="Ver generos"/>

            </form>
        </div>          

    </body>
</html>
