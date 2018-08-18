<%-- 
    Document   : PaginaPrincipalExperto
    Created on : 19-jul-2018, 11:54:43
    Author     : Rodrigo Moreno S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet"  href="css/Auxiliar.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <form action = "LExpertos" method="post">
                <input type="hidden" name="idUsuario" value="${id}">
                <input type="hidden" name="Accion" value="EliminarExperto">
                <input type="submit" class=" btn btn-primary" value="Eliminar cuenta">

            </form>
        </div>
    </div>

    <div class=" col-md-10">
        <div id="" class="col-md-11" > 
            <h1> Bienvenido ${nombre}</h1>
            <h3>¿Qué deseas hacer hoy?</h3>
        </div>

        <div id="aa" class="form-group col-md-2">
            <form action = "LResegnas" method="post">
                <input type="hidden" name="idUsuario" value="${id}">
                <input type="hidden" name="Accion" value="Mostrar">
                <input class="btn btn-primary" type="submit" value="Ver reseñas">
            </form>
        </div>
    </div>


</body>
</html>
