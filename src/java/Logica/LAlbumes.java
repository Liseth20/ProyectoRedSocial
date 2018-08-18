package Logica;

import Datos.DAlbumes;
import Datos.DGenero;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "LAlbumes", urlPatterns = {"/LAlbum"})
public class LAlbumes extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    //----------------Mostrar Albumes------listo------
    public List<DAlbumes> MostrarDatos(int id) throws Exception {

        List<DAlbumes> albumes = new ArrayList<>();

        consulta = "SELECT album.idAlbum,Album.Nombre, genero_musical.Nombre as NombreGenero, album.Descripcion, album.fecha_lanzamiento, artista.Nombre_BandaArtistico, album.SelloDiscografico FROM album, artista, genero_musical where album.fk_artista = artista.fk_usuario and genero_musical.idGenero_musical=album.Fk_genero and album.fk_artista =?";

        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idAlbum");
            String nombre = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");
            String fecha = rs.getString("fecha_lanzamiento");
            String artista = rs.getString("Nombre_BandaArtistico");
            String sello = rs.getString("SelloDiscografico");
            String genero = rs.getString("NombreGenero");

            DAlbumes generoTemporal = new DAlbumes(codigo, nombre, descripcion, fecha, artista, sello, genero);
            albumes.add(generoTemporal);
        }

        return albumes;
    }

    //----------------EliminarAlbum------listo------
    public int EliminarAlbum(int id) throws Exception {
        int result = 0;
        consulta = "DELETE FROM `album` WHERE `idAlbum`= ?";
        PreparedStatement pst = con.prepareStatement(consulta);
        try {

            pst.setInt(1, id);

            result = pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void IngresarNuevoAlbum(DAlbumes a) {

        consulta = "INSERT INTO `album`(`Nombre`, `Descripcion`, `fk_artista`, `fecha_lanzamiento`, `SelloDiscografico`, `Fk_genero`) VALUES  (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, a.getNombre());
            st.setString(2, a.getDescripcion());
            st.setInt(3, a.getFk_artista());
            st.setString(4, a.getFechaLancimiento());
            st.setString(5, a.getSello());
            st.setInt(6, a.getIdGenero());
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    //-----------------Mostrar album---------------------//
    public DAlbumes ObtenerAlbum(int idAlbum) throws Exception {
        DAlbumes obj = null;

        consulta = "SELECT * FROM album WHERE idAlbum=?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setInt(1, idAlbum);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                int idAbum = rs.getInt("idAlbum");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                String fecha = rs.getString("fecha_lanzamiento");
                String sello = rs.getString("SelloDiscografico");

                obj = new DAlbumes(idAbum, nombre, descripcion, fecha, sello);

            } else {
                throw new Exception("No hay datos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }

    //-----------------Actualizar album---------------------//
    public void ActualizarAlbum(DAlbumes AlbumActualizado) throws Exception {

        consulta = "UPDATE `album` SET `Nombre`=?,`Descripcion`=?,"
                + "`fecha_lanzamiento`=?, `Fk_genero`=?, SelloDiscografico = ? WHERE idAlbum = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setString(1, AlbumActualizado.getNombre());
            st.setString(2, AlbumActualizado.getDescripcion());
            st.setString(3, AlbumActualizado.getFechaLancimiento());
            st.setInt(4, AlbumActualizado.getIdGenero());
            st.setString(5, AlbumActualizado.getSello());
            st.setInt(6, AlbumActualizado.getIdAlbum());

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*try (PrintWriter out = response.getWriter()) {

            out.println(accion);
        } catch (Exception e) {
            out.println(e.getMessage());
        }*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");

        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Cargar":
                CargarAlbum(request, response);
                break;

            case "Eliminar":
                EliminarAlbum(request, response);
                break;

            case "Mostrar":
                MostrarAlbumes(request, response);
                break;

            case "VerCanciones":
                VerCanciones(request, response);
                break;

            case "irArtista":
                Regresar(request, response);
                break;
        }

    }

    private void Regresar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipalArtista.jsp").forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void EliminarAlbum(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idAlbum = Integer.parseInt(request.getParameter("Codigo"));

        try {
            EliminarAlbum(idAlbum);
            request.setAttribute("id", id);
            MostrarAlbumes(request, response);
        } catch (Exception ex) {

        }
    }//Fin Eliminar album

    private void CargarAlbum(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            int idAlbum = Integer.parseInt(request.getParameter("Codigo"));
            DAlbumes Album = ObtenerAlbum(idAlbum);

            request.setAttribute("AlbumActualizar", Album);
            request.setAttribute("id", id);
            request.setAttribute("botones", "Actualizar");

            MostrarAlbumes(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//Fin Cargar Género

    private void VerCanciones(HttpServletRequest request, HttpServletResponse response) {
        int idAlbum = Integer.parseInt(request.getParameter("Codigo"));
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {
            request.setAttribute("idA", idAlbum);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/EditarCanciones.jsp").forward(request, response);
        } catch (Exception e) {
        }

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
                IngresarAlbum(request, response);
                break;

            case "Mostrar":
                MostrarAlbumes(request, response);
                break;

            case "Actualizar":
                ActualizarAlbum(request, response);
                break;

        }

    }
//---------------------------------------listo-------------

    private void IngresarAlbum(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {
            DAlbumes a = new DAlbumes();
            a.setFk_artista(id);
            a.setDescripcion(request.getParameter("desc"));
            a.setFechaLancimiento(request.getParameter("fecha"));
            a.setNombre(request.getParameter("nombre"));
            a.setSello(request.getParameter("sello"));
            a.setIdGenero(Integer.parseInt(request.getParameter("genero")));

            /*try (PrintWriter out = response.getWriter()) {
                out.println("value  ");
            } catch (Exception e) {
            }*/
            IngresarNuevoAlbum(a);
            request.setAttribute("id", id);
            MostrarAlbumes(request, response);
        } catch (Exception e) {

        }//Fin Catch

    }//Fin Insertar Generos

//---------------------------------------listo-------------
    private void MostrarAlbumes(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {

            LGenero g = new LGenero();
            List<DGenero> TablaGeneros;
            TablaGeneros = g.MostrarDatos();
            request.setAttribute("Generos", TablaGeneros);

            List<DAlbumes> TablaAlbumes;
            TablaAlbumes = MostrarDatos(id);
            request.setAttribute("Album", TablaAlbumes);

            if (TablaAlbumes.isEmpty()) {
                request.setAttribute("aviso", "error");
            }
            request.setAttribute("id", id);

            request.getRequestDispatcher("/EditarAlbumes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void ActualizarAlbum(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {
            int idA = Integer.parseInt(request.getParameter("CodigoAlbum"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            String sello = request.getParameter("sello");
            int idgenero = Integer.parseInt(request.getParameter("genero"));

            DAlbumes AlbumActualizado = new DAlbumes(idA, nombre, descripcion, fecha, sello, idgenero);

            ActualizarAlbum(AlbumActualizado);
            request.setAttribute("id", id);
            MostrarAlbumes(request, response);

        } catch (Exception ex) {

        }
    }
    /*if (accion.equals ( 
        "Regresar")) {
            request.setAttribute("id", id);
        request.getRequestDispatcher("/PaginaPrincipalArtista.jsp").forward(request, response);
    }*/

}
