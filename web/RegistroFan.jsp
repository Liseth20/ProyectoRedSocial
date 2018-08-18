<%@page import="java.util.List"%>
<%@page import="Datos.DUsuario"%>
<%@page import="Logica.LUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="js/main.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de artistas</title>
    </head>
    <body>
        <h1>Registro del fan #1!</h1>

        <h3 id="registroCompleto" hidden>${saludo}</h3>
        <div class="container">
            <div id="Registro" class="alert alert-success alert-dismissible" role="alert">
                Género ingresado satisfactoriamente!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>

        <div class="form-group col-md-5">
            <form action = "LGenerosFans" method="post">  
                <input type="hidden" name="Accion" value="IngresarGeneroFan">
                <input type="hidden" name="idUsuario" value="${id}">


                <div class="form-group">
                    <label for="generoM">Selecione sus géneros musicales favoritos</label>
                    <select name="generoM" id="generoM" class="form-control" required>
                        <c:forEach var="tempGeneros" items="${Generos }">
                            <option value="${tempGeneros.getIdGenero_musical()}">
                                ${tempGeneros.getNombre()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <input type="submit" name="button" id="button" value="Agregar género favorito" class="btn btn-primary" required>

            </form>
            <br>
            <form action = "LArtistas" method="post">  
                <input type="hidden" name="Accion" value="FinalizarRegistroFan">
                <input type="hidden" name="idUsuario" value="${id}">

                <input type="submit" name="button" id="button" value="Finalizar registro" class="btn btn-primary" required>
            </form>
        </div>




    </body>


</html>
