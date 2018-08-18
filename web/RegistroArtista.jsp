<%-- 
    Document   : RegistroArtista
    Created on : 26-jun-2018, 0:39:20
    Author     : BestMusicFM Inc
--%>

<%@page import="java.util.List"%>
<%@page import="Datos.DUsuario"%>
<%@page import="Logica.LUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de artistas</title>
    </head>
    <body>
        <h1> Usted a escogido tipo de usuario artista </h1>

        <h2>Ya casi termina con el registro, solo llene unos cuantos datos más</h2>

        <form action = "LArtistas" method="post">  
            <input type="hidden" name="Accion" value="IngresarArtista">
            <input type="hidden" name="idUsuario" value="${id}">
            
            <div class="form-group col-md-5">

                <div class="form-group">
                    <label for="TipoArtista">Tipo de artista</label>
                    <select name="TipoArtista" id="tipoArtista" class="form-control" required >
                        <option value="Solista">Solista</option>
                        <option value="Banda">Integrante de una Banda</option>
                    </select>
                </div>


                <div class="form-group">
                    <label for="fecha">Fecha de inicio</label>
                    <input type="date" class="form-control" name="fecha" required>
                </div>


                <div class="form-group">
                    <label for="generoM">Género musical</label>
                    <select name="generoM" id="generoM" class="form-control" required>
                        <c:forEach var="tempGeneros" items="${Generos }">
                            <option value="${tempGeneros.getIdGenero_musical()}">
                                ${tempGeneros.getNombre()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="biografia">Biografía</label>
                    <textarea name="biografia" class="form-control" rows="4" cols="20" required></textarea>
                </div>

                <div class="form-group">
                    <label for="nombre">Nombre de la banda o nombre artístico</label>
                    <input type="text" name="nombre" id="nombre" class="form-control" required>
                </div>
                
                <input type="submit" name="button" id="button" value="Enviar" class="btn btn-primary" required>
                
            </div>



        </form>


    </body>


</html>
