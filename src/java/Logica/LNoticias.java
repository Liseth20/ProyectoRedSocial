package Logica;

import Datos.DUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LNoticias", urlPatterns = {"/LNoticias"})
public class LNoticias extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    //-----------------Insertar noticia---------------------//
    public String InsertarNoticia(String titulo, String contenido, String fecha, String usuario) {
        String result = "";

        //Se agregan los valores a la consulta, se ingresarán desde el jsp
        consulta = "INSERT INTO NOTICIAS(Titulo,Contenido,Fecha,fk_usuario)"
                + "VALUES('" + titulo + "','" + contenido + "', '" + fecha + "', '" + usuario + "')";

        try {
            Statement st = con.createStatement();
            int n = st.executeUpdate(consulta);

            if (n != 0) {
                result = "El registro se ha ingresado correctamente";
            }

        } catch (Exception e) {
            result = "Ha ocurrido un problema: \n " + e.getMessage();
        }

        return result;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");
        String id = request.getParameter("idUsuario");

        //----------------Ingresar una nueva noticia------------
        if (accion.equals("Escribir")) {

            Date now = new Date(System.currentTimeMillis());

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String encabezado = request.getParameter("titulo");
            String noticia = request.getParameter("contenido");
            String fecha_publicacion = date.format(now);

            //InsertarNoticia(encabezado, noticia, fecha_publicacion, id);

             try (PrintWriter out = response.getWriter()) {
                out.println("Noticia " + InsertarNoticia(encabezado, noticia, fecha_publicacion, id));
            } catch (Exception e) {

            }
            
            request.getRequestDispatcher("/VisualizarNoticia.jsp").forward(request, response);
            
           
        }

        //------------Dirigirse al formulario de escribir noticia--------------
        
        if (accion.equals("Iniciar")) {
           request.setAttribute("id", id);
            request.getRequestDispatcher("/Noticias.jsp").forward(request, response);
        }

    }

}
