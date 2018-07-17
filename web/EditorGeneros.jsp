
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
        <title>EditorGeneros</title>

        <style type="text/css">
            .cabecera{
                font-weight: bold;
                background-color: #8258FA;
            }  
            .filas{
                text-align:center;
                background-color: #CECEF6;
            }  

            table{
                float:left;    
            }

            #contenedorBoton{
                margin-left: 650px;

            }
        </style>

    </head>

    <body>
        <h1> Bienvenido al editor de generos </h1> 

        <h3>Lista de generos actuales</h3> 

        <table border="2" width="600">

            <tr >
                <th class="cabecera"> Código</th>
                <th class="cabecera">Nombre</th>
                <th class="cabecera">Descripción</th>
                <th class="cabecera">Acciones</th>

            </tr>

            <c:forEach var="tempGeneros" items="${Generos }">

                <%-- Link actualizador para cada genero utilizando el campo clave --%>
                <c:url var="linkCargar" value="LGenero">

                    <c:param name="Accion" value="Cargar"></c:param>

                    <c:param name="Codigo" value="${tempGeneros.getIdGenero_musical()}"></c:param>

                </c:url>

                <%-- Link para eliminar cada genero utilizando el campo clave --%>

                <c:url var="linkEliminar" value="LGenero">

                    <c:param name="Accion" value="Eliminar"></c:param>

                    <c:param name="Codigo" value="${tempGeneros.getIdGenero_musical()}"></c:param>

                </c:url>

                <tr>
                    <td class="filas"> ${tempGeneros.getIdGenero_musical() } </td>
                    <td class="filas"> ${tempGeneros.getNombre() } </td>
                    <td class="filas"> ${tempGeneros.getDescripcion() } </td>
                    <td class="filas">  
                        <a href="${linkCargar}"><img src="icons8-actualizar-15.png" width="15" height="15" alt="icons8-actualizar-15"/></a>
                        &nbsp;
                        <a href="${linkEliminar}"><img src="icons8-basura-32.png" width="20" height="20" alt="icons8-basura-32"/></a>
                    </td>
                </tr>

            </c:forEach>

        </table>

        <%-- Contenedor --%>

        <div id ="contenedorBoton">

            <%-- Insertar --%>
            <input type="button" name = "btnInsertar" value="Insertar" 
                   onclick="window.location.href = 'InsertarGeneros.jsp'"/>
            <br><br>          

        </div>

    </body>
</html>
