/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DCanciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo Moreno S
 */
@WebServlet(name = "LCanciones", urlPatterns = {"/LCanciones"})
public class LCanciones extends HttpServlet {

//------------------------Conecciópn a la base de datos-------------------------
    private Conexion c = new Conexion();
    private Connection con = c.Conectar();
    private String consulta = "";

//--------------------------Ingresa un nuevo registro---------------------------
    public void IngresarNuevaCancion(DCanciones a) {

        consulta = "INSERT INTO `cancion`(`Nombre`,`duracion`, FK_album) VALUES (?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, a.getNombre());
            st.setString(2, a.getDuracion());
            st.setInt(3, a.getAlbum());
            st.executeUpdate();
        } catch (Exception e) {

        }

    }

//-------------------------Eliminar canción por el id---------------------------
    public void EliminarCancion(int id) {
        consulta = "DELETE FROM `cancion` WHERE `idCancion`= ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

//-------------------Elimina las todas canciones de un álbum--------------------
    public void EliminarCanciones(int obj) {

        consulta = "DELETE FROM `cancion` WHERE `FK_album`= ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, obj);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    //-------------------Elimina las todas canciones de un álbum--------------------
    public void ActualizarCancion(DCanciones obj) {

        consulta = "UPDATE `cancion` SET `Nombre`=?,`duracion`=? WHERE idCancion= ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getDuracion());
            st.setInt(3, obj.getIdCancion());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

//------------------------Muestra todas las canciones-------------------------
    public List<DCanciones> MostrarCanciones(int id) throws Exception {
        List<DCanciones> canciones = new ArrayList<>();

        consulta = "SELECT cancion.Nombre, album.Nombre as NombreAlbum, cancion.duracion, cancion.idCancion FROM cancion, album where album.idAlbum = cancion.FK_album and cancion.FK_album = ?";

        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idCancion");
            String nombre = rs.getString("Nombre");
            String duracion = rs.getString("duracion");
            String album = rs.getString("NombreAlbum");
            DCanciones cancionTemp = new DCanciones(codigo, nombre, duracion, album);
            canciones.add(cancionTemp);
        }

        return canciones;
    }

    //------------------------Promedio de las canciones-------------------------
    public double Promedio(int id) throws Exception {
        double avg = 0;

        consulta = "select AVG(Calificacion) as Promedio from reseña where fk_album = ?";

        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            double Promedio = rs.getDouble("Promedio");

            avg = Promedio;
        }

        return avg;
    }

//------------------------Muestra una canción por el id-------------------------
    public DCanciones ObtenerCancion(int idCancion) throws Exception {
        DCanciones obj = null;

        consulta = "SELECT * FROM cancion where idCancion=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setInt(1, idCancion);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("idCancion");
                String nombre = rs.getString("Nombre");
                String duracion = rs.getString("Duracion");
                // String album = rs.getString("Nopmbre")

                obj = new DCanciones(id, nombre, duracion);
            } else {
                throw new Exception("No hay datos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");

        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Cargar":
                CargarCancion(request, response);
                break;

            case "Eliminar":
                EliminarCancion(request, response);
                break;

            case "Mostrar":
                MostrarCanciones(request, response);
                break;
        }
    }

    private void CargarCancion(HttpServletRequest request, HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            int idA = Integer.parseInt(request.getParameter("idAlbum"));
            int idCancion = Integer.parseInt(request.getParameter("idCancion"));
            DCanciones cancion = ObtenerCancion(idCancion);

            request.setAttribute("cancionActualizar", cancion);
            request.setAttribute("id", id);
            request.setAttribute("idA", idA);
            request.setAttribute("botones", "Actualizar");

            MostrarCanciones(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void EliminarCancion(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idA = Integer.parseInt(request.getParameter("idAlbum"));
        int idCancion = Integer.parseInt(request.getParameter("idCancion"));
        EliminarCancion(idCancion);
        /* try (PrintWriter out = response.getWriter()) {
            out.println(id + " Eliminando  " + idA);
        } catch (Exception e) {
        }*/
        request.setAttribute("id", id);
        request.setAttribute("idA", idA);
        MostrarCanciones(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");

        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Insertar":
                InsertarCancion(request, response);
                break;

            case "Mostrar":
                MostrarCanciones(request, response);
                break;

            case "Actualizar":
                ActualizarCancion(request, response);
                break;
        }
    }

    private void InsertarCancion(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idA = Integer.parseInt(request.getParameter("idAlbum"));

        try {
            DCanciones g = new DCanciones();
            g.setNombre(request.getParameter("nombre"));
            g.setDuracion(request.getParameter("duracionm") + ":" + request.getParameter("duracions"));
            g.setAlbum(idA);

            IngresarNuevaCancion(g);
            MostrarCanciones(request, response);
            /* try (PrintWriter out = response.getWriter()) {
                out.println(request.getParameter("duracionm") + ":" + request.getParameter("duracions"));
            } catch (Exception e) {
            }*/

        } catch (Exception e) {

        }//Fin Catch

    }//Fin Insertar Generos

    private void MostrarCanciones(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            int idA = Integer.parseInt(request.getParameter("idAlbum"));

            List<DCanciones> TablaCanciones;

            TablaCanciones = MostrarCanciones(idA);

            double promedio = Promedio(idA);
            request.setAttribute("Promedio", promedio);

            request.setAttribute("Canciones", TablaCanciones);
            request.setAttribute("id", id);
            request.setAttribute("idA", idA);
            if (TablaCanciones.isEmpty()) {
                request.setAttribute("aviso", "error");
            }

            request.getRequestDispatcher("/EditorCanciones.jsp").forward(request, response);
        } catch (Exception ex) {

        }

    }

    private void ActualizarCancion(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idA = Integer.parseInt(request.getParameter("idAlbum"));
        try {
            int idCancion = Integer.parseInt(request.getParameter("idCancion"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("duracion");

            DCanciones cancionActualizar = new DCanciones(idCancion, nombre, descripcion);
            ActualizarCancion(cancionActualizar);
            request.setAttribute("id", id);
            request.setAttribute("id", idA);
            MostrarCanciones(request, response);

        } catch (Exception ex) {

        }
    }

}
