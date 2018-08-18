
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
                        <th class="cabecera">Acciones</th>

                    </tr>
                </thead>

                <c:forEach var="tempResegnas" items="${Resegna}">

                    <%-- Link actualizador para cada dato utilizando el campo clave --%>
                    <c:url var="linkCargar" value="LResegnas">
                        <c:param name="Accion" value="Cargar"></c:param>
                        <c:param name="idUsuario" value="${id}"></c:param>
                        <c:param name="idResegna" value="${tempResegnas.getIdResegna() }"></c:param>

                    </c:url>

                    <%-- Link para eliminar cada dato utilizando el campo clave --%>

                    <c:url var="linkEliminar" value="LResegnas">
                        <c:param name="idUsuario" value="${id}"></c:param>
                        <c:param name="Accion" value="Eliminar"></c:param>
                        <c:param name="Codigo" value="${tempResegnas.getIdResegna() }"></c:param>

                    </c:url>

                    <tr>
                        <td class="filas"> ${tempResegnas.getNombreArtista() } </td>
                        <td class="filas"> ${tempResegnas.getNombreAlbum()} </td>
                        <td class="filas"> ${tempResegnas.getDescripcion() } </td>
                        <td class="filas"> ${tempResegnas.getCalificacion() } </td>
                        <td class="filas"> ${tempResegnas.getFecha() } </td>
                        <td class="filas">  
                            <a href="${linkCargar}"><img src="Imagenes/icons8-actualizar-15.png" width="15" height="15" alt="icons8-actualizar-15"/></a>
                            &nbsp;
                            <a href="${linkEliminar}"><img src="Imagenes/icons8-basura-32.png" width="20" height="20" alt="icons8-basura-32"/></a>
                        </td>
                    </tr>

                </c:forEach>

            </table>
        </div>


        <div class="container" class ="col-md-3">


            <div id="Elegir" class ="form-group  col-md-3">

                <form action="LResegnas" method="POST">
                    <input type="hidden" name="idUsuario" value="${id}" >
                    <input type="hidden" name="Accion" value="ArtistaElegido" >
                    <div class="form-group">
                        <label for="nombre">Artista/Banda</label>

                        <select name="idArtista" class="form-control" >
                            <c:forEach var="tempArtista" items="${Artitas}">
                                <option value="${tempArtista.getFkUsuario()}">
                                    ${tempArtista.getNombreBanda()}
                                </option>  
                            </c:forEach>
                        </select>   

                    </div> 

                    <input type="submit" class="btn btn-primary" value="Elegir Artista" >
                </form>

            </div> 

            <%-- -----------------------------------REGISTRAR---------------------------------- --%>
            <div id="Registrar" class ="col-md-3">

                <form action = "LResegnas" method="post" >
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="idArtista" value="${idA}">
                    <input type="hidden" name="Accion" value="Insertar">

                    <div class="form-group">
                        <label for="Albumes">Albumes</label>
                        <select name="Albumes" class="form-control">
                            <c:forEach var="tempAlbum" items="${Album}">
                                <option value="${tempAlbum.getIdAlbum()}">
                                    ${tempAlbum.getNombre()}
                                </option>       
                            </c:forEach>
                        </select>                     
                    </div> 

                    <div class="form-group">
                        <label for="desc">Descripción</label>
                        <input type="text" class="form-control" name="desc" required="">
                    </div> 

                    <div class="form-group">
                        <label for="calificacion">Calificación</label>
                        <input type="number" step="0.1" class="form-control" name="calificacion" required="">
                    </div> 


                    <input type="submit" class=" btn btn-success" value="Insertar" >


                </form>
                <br>

                <%-- -----------------------------------CANCELAR------------------------------------- --%>
                <form action="LResegnas" method="POST" class="form-group">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="Cancelar">
                    <input type="submit" class=" btn btn-success " value="Cancelar" >
                </form>
            </div>

            <%-- -----------------------------------ACTUALIZAR---------------------------------------- --%>            
            <div id="Actualizar" class ="col-md-3">

                <form action = "LResegnas" method="post" >
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="CodigoGenero" value="${ResegnasA.getIdResegna()}">
                    <input type="hidden" name="Accion" value="Actualizar">

                    <div class="form-group">
                        <label for="nombre">Descripción</label>
                        <input type="text" class="form-control" value="${ResegnasA.getDescripcion()}" name="desc" required="">
                    </div> 

                    <div class="form-group">
                        <label for="nombre">Calificación</label>
                        <input type="number" step="0.1" class="form-control" value="${ResegnasA.getCalificacion()}" name="calificacion" required="">
                    </div> 

                    <input type="submit" class=" btn btn-success" value="Actualizar" >

                </form>
                <br>
                <%-- -----------------------------------CANCELAR------------------------------------- --%>
                <form action="LResegnas" method="POST" class="form-group">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="Cancelar">
                    <input type="submit" class=" btn btn-success " value="Cancelar" >
                </form>

            </div>
            <div>         
                <form action="LUsuarios" method="POST" class="form-group">
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="Accion" value="SaberNombreE">
                    <input type="submit" class=" btn btn-success " value="Ir al inicio" >
                </form>
            </div>
        </div>

    </body>
</html>
