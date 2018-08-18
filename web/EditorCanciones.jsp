<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="js/ActivarDesactivarBotones.js" type="text/javascript"></script>
        <script src="js/Aviso.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <title>EditorGeneros</title>



    </head>

    <body>

        <h1> Bienvenido al editor de generos </h1> 
        <h3 id="botones" hidden>${botones}</h3>
        <h3 id="aviso" hidden>${aviso}</h3>
        <h3>Lista de generos actuales</h3> 

        <div id="Error" class="col-md-11 alert alert-danger alert-dismissible" role="alert">
            En este momento no hay canciones registradas para este álbum
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>

        <div class="col-md-8">
            <table class="table table-hover ">


                <thead class="thead-dark">

                    <tr class="thead-dark success">
                        <th class="cabecera"> Código</th>
                        <th class="cabecera">Nombre</th>
                        <th class="cabecera">Duración</th>
                        <th class="cabecera">Acciones</th>

                    </tr>
                </thead>

                <c:forEach var="tempCanciones" items="${Canciones }">

                    <%-- Link actualizador para cada genero utilizando el campo clave --%>
                    <c:url var="linkCargar" value="LCanciones">
                        <c:param name="Accion" value="Cargar"></c:param>
                        <c:param name="idUsuario" value="${id}"></c:param>
                        <c:param name="idAlbum" value="${idA}"></c:param>
                        <c:param name="idCancion" value="${tempCanciones.getIdCancion()}"></c:param>

                    </c:url>

                    <%-- Link para eliminar cada genero utilizando el campo clave --%>

                    <c:url var="linkEliminar" value="LCanciones">
                        <c:param name="idUsuario" value="${id}"></c:param>
                        <c:param name="idAlbum" value="${idA}"></c:param>
                        <c:param name="Accion" value="Eliminar"></c:param>
                        <c:param name="idCancion" value="${tempCanciones.getIdCancion()}"></c:param>

                    </c:url>

                    <tr>
                        <td class="filas"> ${tempCanciones.getIdCancion() } </td>
                        <td class="filas"> ${tempCanciones.getNombre() } </td>
                        <td class="filas"> ${tempCanciones.getDuracion() } </td>
                        <td class="filas">  
                            <a href="${linkCargar}"><img src="Imagenes/icons8-actualizar-15.png" width="15" height="15" alt="icons8-actualizar-15"/></a>
                            &nbsp;
                            <a href="${linkEliminar}"><img src="Imagenes/icons8-basura-32.png" width="20" height="20" alt="icons8-basura-32"/></a>
                        </td>
                    </tr>

                </c:forEach>

                <%-- Promedio album --%>
                <div class="form-group">
                    <label for="promedio">Promedio del album</label>&nbsp;<label for="mensaje">${Promedio}</label>

                </div> 
            </table>
        </div>

        <div class="container" class ="col-md-3">
            <div id="Registrar" class ="col-md-3">

                <form action = "LCanciones" method="post" >
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="idAlbum" value="${idA}">
                    <input type="hidden" name="Accion" value="Insertar">


                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required="">
                    </div> 


                    <div class="form-group">

                        <div class="form-group col-xs-6">
                            <label for="duracionm">Duración</label>
                            <input type="text" maxlength="2" placeholder="Minutos" class="form-control" name="duracionm" required="">

                        </div>

                        <div class=" form-group col-xs-6">
                            <label for="duracions">:</label>
                            <input type="text" maxlength="2" placeholder="Segundos" class="form-control" name="duracions" required="">
                        </div>

                    </div> 

                    <div class="form-group">
                        <input type="submit" class=" btn btn-success" value="Insertar" >
                    </div> 


                </form>
            </div>
            <div id="Actualizar" class ="col-md-3">

                <form action = "LCanciones" method="post" >
                    <input type="hidden" name="idUsuario" value="${id}">
                    <input type="hidden" name="idAlbum" value="${idA}">
                    <input type="hidden" name="idCancion" value="${cancionActualizar.getIdCancion()}">
                    <input type="hidden" name="Accion" value="Actualizar">


                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" value="${cancionActualizar.getNombre()}" name="nombre" required="">
                    </div> 


                    <div class="form-group">
                        <label for="duracion">Duración</label>
                        <input type="text" class="form-control" value="${cancionActualizar.getDuracion()}" name="duracion" required="">
                    </div> 


                    <div class="form-group">
                        <input type="submit" class=" btn btn-success" value="Actualizar" >
                    </div> 

                </form>

            </div>
        </div>
        <div class="col-md-12">
            <c:url var="linkRegresar" value="LAlbum">
                <c:param name="idUsuario" value="${id}"></c:param>
                <c:param name="Accion" value="Mostrar"></c:param>
            </c:url>

            <a href="${linkRegresar}" class="btn btn-primary btn-block" ><img src="Imagenes/icons8-izquierda-2-30.png" width="20" height="20"/></a>
        </div>

    </body>
</html>
