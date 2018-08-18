package Logica;

import Datos.DGenero;
import Datos.DUsuario;
import Logica.Conexion;
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
    public int EliminarGenero(int id) throws Exception {
        int result = 0;
        consulta = "DELETE FROM GENERO_MUSICAL WHERE idGenero_musical=?";
        PreparedStatement pst = con.prepareStatement(consulta);
        try {

            pst.setInt(1, id);

            result = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //-----------------Insertar genero---------------------//
    public String InsertarGenero(DGenero g) {
        String result = "";

        //Se agregan los valores a la consulta
        consulta = "INSERT INTO genero_musical(Nombre,Descripcion)"
                + "VALUES(?,?)";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, g.getNombre());
            st.setString(2, g.getDescripcion());
            int n = st.executeUpdate();

            if (n != 0) {
                result = "El registro se ha ingresado correctamente";
            }

        } catch (Exception e) {
            result = "Ha ocurrido un problema: \n " + e.getMessage();
        }

        return result;
    }
    //-----------------Optener géneros generos---------------------//

    public DGenero ObtenerDatos(int idGenero) throws Exception {
        DGenero obj = null;

        consulta = "SELECT * FROM GENERO_MUSICAL WHERE IDGENERO_MUSICAL=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setInt(1, idGenero);

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
    //-----------------Actualizar genero---------------------//

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

//--------------------------Método doGet-------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");

        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Cargar":
                CargarGenero(request, response);
                break;

            case "Eliminar":
                EliminarGenero(request, response);
                break;

            case "Mostrar":
                MostrarGeneros(request, response);
                break;
            case "irPrincipal":
                Regresar(request, response);
                break;
        }

    }

    private void CargarGenero(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            int idGenero = Integer.parseInt(request.getParameter("Codigo"));

            DGenero CodigoGenero = ObtenerDatos(idGenero);

            request.setAttribute("GeneroActualizar", CodigoGenero);
            request.setAttribute("id", id);
            request.setAttribute("botones", "Actualizar");
            MostrarGeneros(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//Fin Cargar Género

    private void EliminarGenero(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idGenero = Integer.parseInt(request.getParameter("Codigo"));

        try {
            
            if (EliminarGenero(idGenero) == 0) {

                request.setAttribute("aviso", "error");
                MostrarGeneros(request, response);

            } else {

                MostrarGeneros(request, response);
            }
        } catch (Exception ex) {

        }
    }//Fin Eliminar Género

    //--------------------------Método doPost-------------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");

        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Insertar":
                InsertarGenero(request, response);
                break;

            case "Mostrar":
                MostrarGeneros(request, response);
                break;

            case "Actualizar":
                ActualizarGenero(request, response);
                break;

        }

    }

    private void InsertarGenero(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));

        try {
            DGenero g = new DGenero();
            g.setNombre(request.getParameter("nombre"));
            g.setDescripcion(request.getParameter("descripcion"));
            InsertarGenero(g);
            request.setAttribute("id", id);

            MostrarGeneros(request, response);
        } catch (Exception e) {

        }//Fin Catch

    }//Fin Insertar Generos

    private void MostrarGeneros(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {

            List<DGenero> TablaGeneros;

            TablaGeneros = MostrarDatos();

            request.setAttribute("Generos", TablaGeneros);
            request.setAttribute("id", id);

            request.getRequestDispatcher("/EditorGeneros.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void ActualizarGenero(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {
            int idG = Integer.parseInt(request.getParameter("CodigoGenero"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            DGenero GeneroActualizado = new DGenero(idG, nombre, descripcion);

            ActualizarGenero(GeneroActualizado);
            request.setAttribute("id", id);
            MostrarGeneros(request, response);

        } catch (Exception ex) {

        }

    }

    private void Regresar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("idUsuario");
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipalAdministrador.jsp").forward(request, response);
        } catch (Exception ex) {

        }
    }

}
