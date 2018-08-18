package Logica;

import Datos.*;
import Logica.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LResegnas", urlPatterns = {"/LResegnas"})
public class LResegnas extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

//-------------------Mostrar datos desde la base de datos---------------------//
    public List<DResegnas> ObtenerResegnas(int id) throws Exception {
        List<DResegnas> resegnas = new ArrayList<>();

        consulta = "select res.idReseña, art.Nombre_BandaArtistico, alb.Nombre, u.Nombre as NombreExperto ,res.Descripcion, res.Calificacion, res.Fecha FROM reseña res, artista art, album alb, expertos ex, usuario u where res.fk_artista = art.fk_usuario and res.fk_album = alb.idAlbum and u.idUsuario = ex.fk_usuario and res.Fk_IdExperto =?";

//        
        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idReseña");
            String artista = rs.getString("Nombre_BandaArtistico");
            String album = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");
            double calificacion = rs.getDouble("Calificacion");
            String fecha = rs.getString("Fecha");
            String nombreExperto = rs.getString("NombreExperto");

            DResegnas reseñaTemporal = new DResegnas(codigo, artista, album, descripcion, calificacion, fecha, nombreExperto);
            resegnas.add(reseñaTemporal);
        }

        return resegnas;
    }
       public List<DResegnas> ObtenerResegnasPorAlbum(int id) throws Exception {
        List<DResegnas> resegnas = new ArrayList<>();

        consulta = "select res.idReseña, art.Nombre_BandaArtistico, alb.Nombre, u.Nombre as NombreExperto ,res.Descripcion, res.Calificacion, res.Fecha "
                + "FROM reseña res, artista art, album alb, expertos ex, usuario u where res.fk_artista = art.fk_usuario and res.fk_album = alb.idAlbum and u.idUsuario = ex.fk_usuario and res.fk_album =?";

//        
        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idReseña");
            String artista = rs.getString("Nombre_BandaArtistico");
            String album = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");
            double calificacion = rs.getDouble("Calificacion");
            String fecha = rs.getString("Fecha");
            String nombreExperto = rs.getString("NombreExperto");

            DResegnas reseñaTemporal = new DResegnas(codigo, artista, album, descripcion, calificacion, fecha, nombreExperto);
            resegnas.add(reseñaTemporal);
        }

        return resegnas;
    }
       
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

//-------------------------------Insertar reseña--------------------------------
    private void InsertarResegna(DResegnas r) {

        //Se agregan los valores a la consulta
        consulta = "INSERT INTO `reseña`(`fk_artista`, `fk_album`, `Fk_IdExperto`, `Descripcion`, `Calificacion`, `Fecha`) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, r.getFk_artista());
            st.setInt(2, r.getFk_album());
            st.setInt(3, r.getExperto());
            st.setString(4, r.getDescripcion());
            st.setDouble(5, r.getCalificacion());
            st.setString(6, r.getFecha());
            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//-------------------------------Capturar artistas--------------------------------
    public List<DArtista> CargarComboArtista() throws Exception {
        List<DArtista> artistas = new ArrayList<>();

        consulta = "SELECT fk_usuario, Nombre_BandaArtistico FROM artista ";

        PreparedStatement st = con.prepareStatement(consulta);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            int codigo = rs.getInt("fk_usuario");
            String artista = rs.getString("Nombre_BandaArtistico");

            DArtista resegnaTemporal = new DArtista(codigo, artista);
            artistas.add(resegnaTemporal);
        }

        return artistas;
    }

//-------------------------------Capturar albumes--------------------------------
    public List<DAlbumes> CargarComboAlbumes(int id) throws Exception {
        List<DAlbumes> album = new ArrayList<>();

        consulta = "SELECT * from album WHERE fk_artista =?";

        PreparedStatement st = con.prepareStatement(consulta);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            int codigo = rs.getInt("idAlbum");
            String nombreAlbum = rs.getString("Nombre");

            DAlbumes albumTemporal = new DAlbumes(codigo, nombreAlbum);
            album.add(albumTemporal);
        }

        return album;
    }

//-----------------Eliminar reseña ---------------------//
    public int EliminarResegnas(int id) throws Exception {
        int result = 0;
        consulta = "DELETE FROM RESEÑA WHERE idReseña=?";
        PreparedStatement pst = con.prepareStatement(consulta);
        try {
            pst.setInt(1, id);
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //-----------------Actualizar reseña---------------------//
    public void ActualizarResegna(DResegnas a) throws Exception {

        consulta = "UPDATE `reseña` SET `Descripcion`=?,`Calificacion`=? WHERE idReseña = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setString(1, a.getDescripcion());
            st.setDouble(2, a.getCalificacion());
            st.setDouble(3, a.getIdResegna());

            st.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //-----------------Mostrar reseña---------------------//
    public DResegnas ObtenerResegna(int r) throws Exception {
        DResegnas obj = null;
        consulta = "SELECT * FROM reseña WHERE fk_album ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, r);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idReseña");
                double calificacion = rs.getDouble("Calificacion");
                String descripcion = rs.getString("Descripcion");
                obj = new DResegnas(id, descripcion, calificacion);
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
        /*try (PrintWriter out = response.getWriter()) {

            out.println("verificado " + accion);
        } catch (Exception e) {
            out.println(e.getMessage());
        }*/
        if (accion.equals(null)) {
            accion = "Mostrar";
        }
        switch (accion) {

            case "Mostrar":
                MostrarResegnas(request, response);
                break;

            case "Eliminar":
                EliminarResegna(request, response);
                break;

            case "Cargar":
                CargarResegna(request, response);
                break;

            case "CargarIdAlbum":
                MostrarResegnaPorAlbum(request, response);
                break;

        }
    }

    private void CargarResegna(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int idResegna = Integer.parseInt(request.getParameter("idResegna"));
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            int idAlbum = Integer.parseInt(request.getParameter("Codigo"));

            DResegnas resegnas = ObtenerResegna(idResegna);

            request.setAttribute("ResegnasA", resegnas);
            request.setAttribute("id", id);
            request.setAttribute("botones", "Actualizar");
            MostrarResegnas(request, response);
        } catch (Exception ex) {
        }
    }



    private void EliminarResegna(HttpServletRequest request, HttpServletResponse response) {
        int idResegna = Integer.parseInt(request.getParameter("Codigo"));

        try {
            EliminarResegnas(idResegna);
            MostrarResegnas(request, response);
        } catch (Exception ex) {
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

            case "Mostrar":
                MostrarResegnas(request, response);
                break;

            case "Insertar":
                InsertarResegna(request, response);
                break;

            case "ArtistaElegido":
                ArtistaElegido(request, response);
                break;

            case "Actualizar":
                ActualizarResegna(request, response);
                break;

            case "Cancelar":
                Cancelar(request, response);
                break;

        }

    }

    private void MostrarResegnas(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        
        try {

            List<DArtista> ComboArtistas;
            ComboArtistas = CargarComboArtista();
            request.setAttribute("Artitas", ComboArtistas);

            List<DResegnas> TablaResegnas;
            TablaResegnas = ObtenerResegnas(id);
            request.setAttribute("Resegna", TablaResegnas);
            if (TablaResegnas.isEmpty()) {
                request.setAttribute("aviso", "error");
            }

            request.setAttribute("id", id);

            request.getRequestDispatcher("/Resegnas.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
        private void MostrarResegnaPorAlbum(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idAlbum = Integer.parseInt(request.getParameter("Codigo"));
        try {

       
            List<DResegnas> TablaResegnas;
            TablaResegnas = ObtenerResegnasPorAlbum(idAlbum);
           
            double promedio = Promedio(idAlbum);
            request.setAttribute("Promedio", promedio);
            
            request.setAttribute("Resegna", TablaResegnas);
            
            if (TablaResegnas.isEmpty()) {
                request.setAttribute("aviso", "error");
            }

            request.setAttribute("id", id);

            request.getRequestDispatcher("/ResegnasPorAlbum.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void InsertarResegna(HttpServletRequest request, HttpServletResponse response) {
        int idArtista = Integer.parseInt(request.getParameter("idArtista"));
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        int idAlbum = Integer.parseInt(request.getParameter("Albumes"));

        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_publicacion = date.format(now);

        DResegnas r = new DResegnas();
        r.setFk_artista(idArtista);
        r.setFk_album(idAlbum);
        r.setDescripcion(request.getParameter("desc"));
        r.setCalificacion(Double.parseDouble(request.getParameter("calificacion")));
        r.setExperto(id);
        r.setFecha(fecha_publicacion);
        InsertarResegna(r);

        MostrarResegnas(request, response);
    }

    private void ArtistaElegido(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idArtista = Integer.parseInt(request.getParameter("idArtista"));
            int id = Integer.parseInt(request.getParameter("idUsuario"));

            List<DAlbumes> TablaAlbumes;
            TablaAlbumes = CargarComboAlbumes(idArtista);
            request.setAttribute("Album", TablaAlbumes);
            if (TablaAlbumes.isEmpty()) {
                request.setAttribute("id", id);
                request.setAttribute("aviso", "error");
                MostrarResegnas(request, response);

            } else {
                request.setAttribute("id", id);
                request.setAttribute("idA", idArtista);
                request.setAttribute("botones", "Registrar");
                MostrarResegnas(request, response);
            }
        } catch (Exception ex) {

        }
    }//Fin Artista Elegido

    private void Cancelar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        request.setAttribute("id", id);
        MostrarResegnas(request, response);

    }

    private void ActualizarResegna(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        try {
            DResegnas a = new DResegnas();
            a.setDescripcion(request.getParameter("desc"));
            a.setCalificacion(Double.parseDouble(request.getParameter("calificacion")));
            a.setIdResegna(Integer.parseInt(request.getParameter("CodigoGenero")));
            ActualizarResegna(a);
            request.setAttribute("id", id);
            MostrarResegnas(request, response);
        } catch (Exception ex) {

        }
    }

}
