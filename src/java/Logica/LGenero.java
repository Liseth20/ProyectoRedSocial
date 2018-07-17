package Logica;

import Datos.DGenero;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LGenero", urlPatterns = {"/LGenero"})
public class LGenero extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    //-----------------Mostrar datos desde la base de datos---------------------//
    public List<DGenero> MostrarDatos() throws Exception {
        List<DGenero> generos = new ArrayList<>();

        consulta = "SELECT * FROM GENERO_MUSICAL";

        PreparedStatement st = con.prepareStatement(consulta);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idGenero_musical");
            String nombre = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");

            DGenero generoTemporal = new DGenero(codigo, nombre, descripcion);
            generos.add(generoTemporal);
        }

        return generos;
    }

    //-----------------Eliminar datos IGNORAR---------------------//
    public void EliminarGenero(int id) throws Exception {

        consulta = "DELETE FROM GENERO_MUSICAL WHERE idGenero_musical=?";
        PreparedStatement pst = con.prepareStatement(consulta);
        try {

            pst.setInt(1, id);

            pst.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 

    }

    //-----------------Insertar genero---------------------//
    public String InsertarGenero(String nombre, String descripcion) {
        String result = "";

        //Se agregan los valores a la consulta
        consulta = "INSERT INTO genero_musical(Nombre,Descripcion)"
                + "VALUES('" + nombre + "','" + descripcion + "')";

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

    public DGenero ObtenerDatos(String idGenero) throws Exception {
        DGenero obj = null;
        int codigo = Integer.parseInt(idGenero);

        consulta = "SELECT * FROM GENERO_MUSICAL WHERE IDGENERO_MUSICAL=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setInt(1, codigo);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("idGenero_musical");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");

                obj = new DGenero(id, nombre, descripcion);

            } else {
                throw new Exception("No hay datos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }

    public void ActualizarGenero(DGenero GeneroActualizado) throws Exception {

        consulta = "UPDATE GENERO_MUSICAL SET NOMBRE =?, DESCRIPCION=?"
                + " WHERE IDGENERO_MUSICAL=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setString(1, GeneroActualizado.getNombre());
            st.setString(2, GeneroActualizado.getDescripcion());
            st.setInt(3, GeneroActualizado.getIdGenero_musical());

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");

        if (accion.equals("Mostrar")) {
            try {
                List<DGenero> TablaGeneros;

                TablaGeneros = MostrarDatos();

                request.setAttribute("Generos", TablaGeneros);

                request.getRequestDispatcher("/EditorGeneros.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        if (accion.equals("Cargar")) {
            try {

                String idGenero = request.getParameter("Codigo");

                DGenero CodigoGenero = ObtenerDatos(idGenero);

                request.setAttribute("GeneroActualizar", CodigoGenero);

                request.getRequestDispatcher("/ActualizarGeneros.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (accion.equals("Eliminar")) {

            int idGenero = Integer.parseInt(request.getParameter("Codigo"));

            try {
                EliminarGenero(idGenero);

                request.getRequestDispatcher("/PerfilAdmi.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LGenero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");

        //Obtener Datos
        if (accion.equals("Insertar")) {
            String encabezado = request.getParameter("nombre");
            String noticia = request.getParameter("descripcion");

            try {
                out.println(InsertarGenero(encabezado, noticia));
            } catch (Exception e) {

            }

            request.getRequestDispatcher("/PerfilAdmi.jsp").forward(request, response);

        }
        if (accion.equals("Actualizar")) {

            try {
                int id = Integer.parseInt(request.getParameter("CodigoGenero"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");

                DGenero GeneroActualizado = new DGenero(id, nombre, descripcion);

                ActualizarGenero(GeneroActualizado);

                request.getRequestDispatcher("/PerfilAdmi.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LGenero.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
