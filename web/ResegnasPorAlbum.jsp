<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="js/Resegnas.js" type="text/javascript"></script>
        <script src="js/Aviso.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <title>Reseñas</title>
    </head>
    <body>


        <%-- -----------------------------------CUERPO------------------------------------- --%>

        <h3>Reseñas</h3> 
        <h3 id="botones" hidden>${botones}</h3>
        <h3 id="aviso" hidden>${aviso}</h3>

        <div id="Error" class="col-md-11 alert alert-danger alert-dismissible" role="alert">
            No existen albumes para este artista
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>

        </div>

        <%-- -----------------------------------TABLA------------------------------------------------------------------- --%>
        <div class="col-md-8">
            <table class="table table-hover ">

                <thead class="thead-dark">

                    <tr class="thead-dark success">
                        <th class="cabecera">Artista</th>
                        <th class="cabecera">Album</th>
                        <th class="cabecera">Descripción</th>
                        <th class="cabecera">Calificación</th>
                        <th class="cabecera">Fecha</th>  
                        
                    </tr>
                </thead>

                <c:forEach var="tempResegnas" items="${Resegna}">

                    <tr>
                        <td class="filas"> ${tempResegnas.getNombreArtista() } </td>
                        <td class="filas"> ${tempResegnas.getNombreAlbum()} </td>
                        <td class="filas"> ${tempResegnas.getDescripcion() } </td>
                        <td class="filas"> ${tempResegnas.getCalificacion() } </td>
                        <td class="filas"> ${tempResegnas.getFecha() } </td>
                     
                    </tr>
                </c:forEach>
                    
                      <%-- Promedio album --%>
                <div class="form-group">
                    <label for="promedio">Promedio del album</label>&nbsp;<label for="mensaje">${Promedio}</label>

                </div> 
            </table>
            
            
        </div>
    </body>
</html>
