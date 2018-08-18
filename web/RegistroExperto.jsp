<%-- 
    Document   : RegistroExperto
    Created on : 30-jun-2018, 13:23:13
    Author     : Rodrigo Moreno S
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Experto</title>
    </head>
    <body>
        <h1> Usted a escogido tipo de usuario experto </h1>

        <h2>Ya casi termina con el registro, solo llene unos cuantos datos más</h2>

        <form action = "LExpertos" method="post">  
            <input type="hidden" name="Accion" value="IngresarExperto">
            <input type="hidden" name="idUsuario" value="${id}">

            <div class="form-group col-md-5">

                <div class="form-group">
                    <label for="fecha">Fecha de inicio</label>
                    <input type="date" class="form-control" name="fecha" required="">
                </div>

                <div class="form-group">
                    <label for="generoM">Género musical</label>
                    <select name="generoM" id="generoM" class="form-control" required>
                        <%int a = 0;%>
                        <c:forEach var="tempGeneros" items="${Generos }">
                            <option value="${tempGeneros.getIdGenero_musical()}">
                                ${tempGeneros.getNombre()}
                            </option>
                        </c:forEach>
                    </select>

                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <textarea name="descripcion" class="form-control" rows="4" cols="20" required>
                    </textarea>
                </div>

                <input type="submit" name ="button" id="button" class="btn btn-primary" value="Enviar" required>
            </div>

        </form>


    </body>

</html>
