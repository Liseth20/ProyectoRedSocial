package Logica;

import Datos.DGenero;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<DNoticias> MostrarDatos(String id) throws Exception {

        int idUsuario = Integer.parseInt(id);

        List<DNoticias> noticias = new ArrayList<>();

        consulta = "SELECT * FROM NOTICIAS WHERE FK_USUARIO=?";

        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, idUsuario);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idNoticias");
            String nombre = rs.getString("Titulo");
            String descripcion = rs.getString("Contenido");
            String fecha = rs.getString("Fecha");

            DNoticias generoTemporal = new DNoticias(codigo, nombre, descripcion, fecha);
            noticias.add(generoTemporal);
        }

        return noticias;
    }

    //-----------------Cargar noticia---------------------//
    public DNoticias ObtenerDatos(String idGenero) throws Exception {
        DNoticias obj = null;
        int codigo = Integer.parseInt(idGenero);

        consulta = "SELECT * FROM NOTICIAS WHERE IDNOTICIAS=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setInt(1, codigo);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("idNoticias");
                String titulo = rs.getString("Titulo");
                String contenido = rs.getString("Contenido");

                obj = new DNoticias(id, titulo, contenido);

            } else {
                throw new Exception("No hay datos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }

    //-----------------Actualizar noticia---------------------//
    public void ActualizarNoticia(DNoticias noticiaActualizada) throws Exception {

        consulta = "UPDATE NOTICIAS SET TITULO =?, CONTENIDO=?"
                + " WHERE IDNOTICIAS=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setString(1, noticiaActualizada.getTitulo());
            st.setString(2, noticiaActualizada.getContenido());
            st.setInt(3, noticiaActualizada.getIdNoticias());

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //-----------------Eliminar noticia---------------------//
    public void EliminarPublicacion(int id) throws Exception {

        consulta = "DELETE FROM NOTICIAS WHERE IDNOTICIAS=?";
        PreparedStatement pst = con.prepareStatement(consulta);
        try {

            pst.setInt(1, id);

            pst.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String accion = request.getParameter("Accion");

        if (accion.equals("Visualizar")) {
            try {

                List<DNoticias> TablaGeneros;

                TablaGeneros = MostrarDatos(id);

                request.setAttribute("Publicaciones", TablaGeneros);

                request.setAttribute("id", id);

                request.getRequestDispatcher("/VisualizarNoticia.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        if (accion.equals("Cargar")) {
            try {

                String idNoticia = request.getParameter("Codigo");

                DNoticias CodigoNoticia = ObtenerDatos(idNoticia);

                request.setAttribute("PublicacionActualizar", CodigoNoticia);
                request.setAttribute("id", id);

                request.getRequestDispatcher("/ActualizarPublicacion.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (accion.equals("Eliminar")) {

            int idNoticia = Integer.parseInt(request.getParameter("Codigo"));

            try {
                EliminarPublicacion(idNoticia);
                request.setAttribute("id", id);
                request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LGenero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String accion = request.getParameter("Accion");

        //----------------Ingresar una nueva noticia------------
        if (accion.equals("Escribir")) {

            try {
                Date now = new Date(System.currentTimeMillis());

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String encabezado = request.getParameter("titulo");
                String noticia = request.getParameter("contenido");
                String fecha_publicacion = date.format(now);

                InsertarNoticia(encabezado, noticia, fecha_publicacion, id);

                request.setAttribute("id", id);

                request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);
            } catch (Exception e) {

            }

        }

        if (accion.equals("Ir")) {
            try {

                request.setAttribute("id", id);

                request.getRequestDispatcher("/Noticias.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        if (accion.equals("Actualizar")) {

            try {

                int idPublicacion = Integer.parseInt(request.getParameter("CodigoNoticia"));
                String titulo = request.getParameter("titulo");
                String contenido = request.getParameter("contenido");

                DNoticias NoticiaActualizada = new DNoticias(idPublicacion, titulo, contenido);

                ActualizarNoticia(NoticiaActualizada);

                request.setAttribute("id", id);
                request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LGenero.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
