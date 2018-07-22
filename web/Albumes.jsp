<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Logica.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Albumes</title>
    </head>
    <body>

        <h1> Bienvenido al editor de albumes </h1> 

        <h3>Lista de albumes </h3> 

        <table border="2" width="600">

            <tr >
                <th >Código</th>
                <th >Nombre</th>
                <th >Descripción</th>
                <th >Acciones</th>
            </tr>

            <c:forEach var="tempAlbum" items="${Album}">

                <%-- Link actualizador para cada genero utilizando el campo clave --%>
                <c:url var="linkCargar" value="LAlbum">

                    <c:param name="id" value="${id}"></c:param>

                    <c:param name="Accion" value="Cargar"></c:param>

                    <c:param name="Codigo" value="${tempAlbum.getIdAlbum()}"></c:param>

                </c:url>

                <%-- Link para eliminar cada genero utilizando el campo clave --%>

                <c:url var="linkEliminar" value="LAlbum">

                    <c:param name="id" value="${id}"></c:param>

                    <c:param name="Accion" value="Eliminar"></c:param>

                    <c:param name="Codigo" value="${tempAlbum.getIdAlbum()}"></c:param>

                </c:url>

                <tr>
                    <td> ${tempAlbum.getIdAlbum() } </td>
                    <td> ${tempAlbum.getNombre() } </td>
                    <td> ${tempAlbum.getDescripcion() } </td>
                    <td>  
                        <a href="${linkCargar}"><img src="icons8-actualizar-15.png" width="15" height="15" alt="icons8-actualizar-15"/></a>
                        &nbsp;
                        <a href="${linkEliminar}"><img src="icons8-basura-32.png" width="20" height="20" alt="icons8-basura-32"/></a>
                    </td>
                </tr>

            </c:forEach>

        </table>

        <%-- Contenedor --%>

        <div id ="contenedorBoton">
            <input type="hidden" name="id" value="${id}">
            <%-- Insertar --%>

            <input type="button" name = "btnInsertar" value="Insertar" 
                   onclick="window.location.href = 'InsertarAlbum.jsp'"/>

            <br><br>          

        </div>


    </body>
</html>
