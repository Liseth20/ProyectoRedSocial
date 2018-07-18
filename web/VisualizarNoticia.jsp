
<%@page import="Logica.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="estilos.css"/>
        
        <title>Muro de publicaciones</title>
        
    </head>
    <body>

        <h1> Muro de publicaciones </h1> 

        <table border="0" width="600">

            <c:forEach var="tempNoticia" items="${Publicaciones}">

                <tr>
                    <td>${tempNoticia.getTitulo() }</td>
                </tr>

                <tr>
                    <td> ${tempNoticia.getContenido() } </td>
                </tr>

                <tr>
                    <td> <br> </td>

                </tr>

                <tr>
                    <td> <br> </td>

                </tr>


            </c:forEach>

        </table>

        <br> 

        <form action = "LNoticias" method="post">  

            <input type="hidden" name="idUsuario" value="${idUsuario}">

            <input type="hidden" name="Accion" value="Ir">

            <input type="submit" name = "btnPublicar" id="button" value="Escribir noticia"/>

        </form>



    </body>
</html>
