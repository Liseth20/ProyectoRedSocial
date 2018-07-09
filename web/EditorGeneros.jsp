
<%@page import="Datos.DGenero"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Logica.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EditorGeneros</title>
    </head>
    <%
        //Generos del controlador
        List<DGenero> generos = (List<DGenero>) request.getAttribute("Generos");


    %>
    <body>

        <h1> Bienvenido administrador al editor de generos </h1> 

        <h3>Lista de generos actuales</h3> 

        <table border="2" width="600">

            <tr bgcolor="ed5151">
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
            </tr>
            <% for (DGenero tempGeneros : generos) {%>
            <tr>
                <td> <%=tempGeneros.getIdGenero_musical()%> </td>
                <td> <%=tempGeneros.getNombre()%> </td>
                <td> <%=tempGeneros.getDescripcion()%> </td>
            </tr>

            <% }%>  

            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>

        </table>

        <br> <br>

        <%-- Formulario insertar --%>
        <form action = "InsertarGeneros.jsp" method="post">  
            <input type="submit" name = "btnInsertar" id="button" value="Insertar"/>
        </form>

        <%-- Formulario Editar --%>
        <form action = "LGenero" method="post">  
            <input type="hidden" name="Accion" value="editar">
            <input type="submit" name = "btnEditar" id="button" value="Editar"/>
        </form>

        <%-- Formulario Eliminar --%>
        <form action = "LGenero" method="post">  
            <input type="hidden" name="Accion" value="eliminar">
            <input type="submit" name = "btnEliminar" id="button" value="Eliminar"/>
        </form>

        <%-- Formulario Actualizar --%>
        <form action = "LGenero" method="post">  
            <input type="hidden" name="Accion" value="actualizar">
            <input type="submit" name = "btnActualizar" id="button" value="Actualizar"/>
        </form>


        <%--
               <table border="2" width="600">

            <tr bgcolor="ed5151">
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
            </tr>
            
           
            <%
                Conexion mysql = new Conexion();
                Connection con = mysql.Conectar();
                String consulta;
                consulta = "SELECT * FROM `genero_musical`";

                PreparedStatement st = con.prepareStatement(consulta);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
            %>

            <tr>
                <th><%=rs.getString(1)%></th>
                <th><%=rs.getString(2)%></th>
                <th><%=rs.getString(3)%></th>
            </tr>

            <%
                }

                st.close();
                rs.close();
                con.close();

            %>




        </table>


        <form action = "LGenero" method="post">  




        </form>
        --%>
    </body>
</html>
