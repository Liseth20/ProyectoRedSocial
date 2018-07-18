package Logica;

import Datos.DNoticias;
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
import java.util.ArrayList;
import java.util.List;
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

    //-----------------Mostrar noticia---------------------//
    public List<DNoticias> MostrarDatos() throws Exception {

        List<DNoticias> noticias = new ArrayList<>();

        consulta = "SELECT * FROM NOTICIAS";

        PreparedStatement st = con.prepareStatement(consulta);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idNoticias");
            String nombre = rs.getString("Titulo");
            String descripcion = rs.getString("Contenido");

            DNoticias generoTemporal = new DNoticias(codigo, nombre, descripcion);
            noticias.add(generoTemporal);
        }

        return noticias;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String id = request.getParameter("idUsuario");
        String accion = request.getParameter("Accion");
        if (accion.equals("Visualizar")) {
            try {

                List<DNoticias> TablaGeneros;

                TablaGeneros = MostrarDatos();

                request.setAttribute("Publicaciones", TablaGeneros);

                request.setAttribute("idUsuario", id);

                request.getRequestDispatcher("/VisualizarNoticia.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("idUsuario");
        String accion = request.getParameter("Accion");

        //----------------Ingresar una nueva noticia------------
        if (accion.equals("Escribir")) {

            Date now = new Date(System.currentTimeMillis());

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String encabezado = request.getParameter("titulo");
            String noticia = request.getParameter("contenido");
            String fecha_publicacion = date.format(now);

            //InsertarNoticia(encabezado, noticia, fecha_publicacion, id);
            try {
                InsertarNoticia(encabezado, noticia, fecha_publicacion, id);
            } catch (Exception e) {

            }

            request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);

        }

        if (accion.equals("Ir")) {
            try {

                request.setAttribute("idUsuario", id);

                request.getRequestDispatcher("/Noticias.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

}
