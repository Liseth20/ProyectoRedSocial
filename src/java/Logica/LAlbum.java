package Logica;

import Datos.DAlbum;
import Datos.DGenero;
import Datos.DNoticias;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LAlbum", urlPatterns = {"/LAlbum"})
public class LAlbum extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    //-----------------Mostrar album---------------------//
    public List<DAlbum> MostrarDatos() throws Exception {

        List<DAlbum> album = new ArrayList<>();
        
        //Consulta para que muestre del album el codigo, nombre y descripcion y del artista la banda o nombre artistico
        
        /*consulta = "SELECT alb.idAlbum, alb.Nombre, alb.Descripcion, art.Banda_o_nombre_artistico\n"
                + "FROM album alb inner join artista art \n"
                + "on alb.fk_artista = art.fk_usuario";*/
        
        consulta = "SELECT * FROM ALBUM";

        PreparedStatement st = con.prepareStatement(consulta);
//        st.setInt(1, idArtista);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            int codigo = rs.getInt("idAlbum");
            String nombre = rs.getString("Nombre");
            String descripcion = rs.getString("Descripcion");
           
            DAlbum AlbumTemporal = new DAlbum(codigo, nombre, descripcion);
            album.add(AlbumTemporal);
        }

        return album;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String accion = request.getParameter("Accion");

        if (accion.equals("Ver")) {
            try {

                List<DAlbum> TablaAlbumes;

                TablaAlbumes = MostrarDatos();

                request.setAttribute("Album", TablaAlbumes);

                request.setAttribute("id", id);

                request.getRequestDispatcher("/Albumes.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
