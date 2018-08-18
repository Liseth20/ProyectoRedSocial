/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DArtista;
import Datos.DUsuario;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.nio.file.*;
import java.nio.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo Moreno S
 */
@WebServlet(name = "LArtistas", urlPatterns = {"/LArtistas"})
public class LArtistas extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

//--------------------------Ingresar nuevo Artista------------------------------------
    public void IngresarNuevoArtista(DArtista ar) {
        consulta = "INSERT INTO `artista`(`fk_usuario`, `Tipo_artista`, `Fecha_Inicio`, `fk_generMusical`, `Fotografia`, `Biografia`, `Nombre_BandaArtistico`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, ar.getFkUsuario());
            st.setString(2, ar.getTipoArtista());
            st.setString(3, ar.getFechaIncio());
            st.setInt(4, ar.getGenero());
            st.setInt(5, ar.getImagen());
            st.setString(6, ar.getBiografia());
            st.setString(7, ar.getNombreBanda());

            st.executeUpdate();
        } catch (Exception e) {
        }

    }

//--------------------------Eliminar Artista------------------------------------
    public void ActualizarArtista(DArtista ar) {
        consulta = "UPDATE `artista` SET `Tipo_artista`= ?,`Fecha_Inicio`= ?,`fk_generMusical`= ?,"
                + "`Fotografia`= ?,`Biografia`= ?,`Nombre_BandaArtistico`= ? WHERE `fk_usuario`= ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);

            st.setString(1, ar.getTipoArtista());
            st.setString(2, ar.getFechaIncio());
            st.setInt(3, ar.getGenero());
            st.setInt(4, ar.getImagen());
            st.setString(5, ar.getBiografia());
            st.setString(6, ar.getNombreBanda());
            st.setInt(7, ar.getFkUsuario());

            st.executeUpdate();
        } catch (Exception e) {
        }
    }

//--------------------------Eliminar Artista------------------------------------
    public String EliminarArtista(int ar) {
        String result = null;
        consulta = "DELETE FROM `artista` WHERE `fk_usuario`= ? ";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, ar);
            st.executeUpdate();

        } catch (Exception e) {
        }
        return result;
    }
//--------------------------doPost-doGet------------------------------------

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");

        switch (accion) {

            case "IngresarArtista":
                IngresarArtista(request, response);
                break;

            case "ActualizarArtista":
                ActualizarArtista(request, response);
                break;

            case "EliminarArtista":
                EliminarArtista(request, response);
                break;

        }
    }//Fin doPost

    protected void IngresarArtista(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DArtista d = new DArtista();
            d.setFkUsuario(id);
            d.setBiografia(request.getParameter("biografia"));
            d.setFechaIncio(request.getParameter("fecha"));
            d.setImagen(1);
            d.setGenero(Integer.parseInt(request.getParameter("generoM")));
            d.setTipoArtista(request.getParameter("TipoArtista"));
            d.setBiografia(request.getParameter("biografia"));

            IngresarNuevoArtista(d);
            request.setAttribute("saludo", "RegistroCompletado");
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }//Fin Ingresar Artista

    protected void ActualizarArtista(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DArtista d = new DArtista();
            d.setFkUsuario(id);
            d.setBiografia(request.getParameter("biografia"));
            d.setFechaIncio(request.getParameter("fecha"));
            d.setImagen(1);
            d.setGenero(Integer.parseInt(request.getParameter("generoM")));
            d.setTipoArtista(request.getParameter("TipoArtista"));
            d.setNombreBanda(request.getParameter("nombre"));

            ActualizarArtista(d);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }//Fin Actualizar Artista

    protected void EliminarArtista(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            EliminarArtista(id);
            LUsuarios u = new LUsuarios();
            request.setAttribute("idUsuario", id);
            u.Eliminar(request, response);

            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        }
    }//Fin Eliminar
}//Fin LArtistas
