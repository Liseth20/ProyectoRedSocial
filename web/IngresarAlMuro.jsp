<%-- 
    Document   : IngresarAlMuro
    Created on : Jul 18, 2018, 12:00:10 PM
    Author     : darian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ingresar al muro de publicaciones</h1>
        
             <form action = "LNoticias" method="get">
            
            <input type="hidden" name="Accion" value="Visualizar">

            <input type="submit" name = "btnInsertar" id="button" value="Ingresar">


        </form>
        
    </body>
</html>
