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
    public String EliminarGenero(DGenero user) throws Exception {
       
        String result ="";
        consulta = "DELETE * FROM GENERO_MUSICAL WHERE idGenero_musical= ?";

       try {
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(1, user.getIdGenero_musical());

            int n = pst.executeUpdate();

            if (n != 0) {
                result = "El registro se ha eliminado correctamente";
            }

        } catch (SQLException ex) {
            result = "He ocurrido un error al eliminar el genero\n: " + ex.getMessage();
        }
        return result;
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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
         List<DGenero> TablaGeneros;

            TablaGeneros = MostrarDatos();

            //Agregar al request
            request.setAttribute("Generos", TablaGeneros);

            //Enviar al JSP
            request.getRequestDispatcher("/EditorGeneros.jsp").forward(request, response);

           
              } catch (Exception e) {
            e.printStackTrace();

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
        
      

    }

}
