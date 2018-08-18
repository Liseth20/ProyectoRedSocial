/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DAdministradores;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BestMusicFM Inc
 */
@WebServlet(name = "LAdministradores", urlPatterns = {"/LAdministradores"})
public class LAdministradores extends HttpServlet {

    Conexion myslq = new Conexion();
    String consulta;
    Connection con = myslq.Conectar();

///-----------------Ingresar admin-----------------------listo-----------
    public String IngresarNuevoAdmin(DAdministradores ad) {
        String result = "";
        consulta = "insert into Administradores (fk_usuario, fecha_contratacion) values(?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, ad.getPkIdUsuario());
            st.setString(2, ad.getFechaContatracion());
            int num = st.executeUpdate();
            if (num != 0) {
                result = "Registro ingresado correctamente";
            }
        } catch (Exception e) {
            result = "Hemos encontrado un error:\n" + e.getMessage();
        }

        return result;
    }

    ///-----------------Eliminar-----------------------listo-----------
    public String EliminarAdmin(int ad) {
        String result = "";

        consulta = "Delete from administradores where fk_usuario=?";

        try {
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(1, ad);
            int n = pst.executeUpdate();

            if (n != 0) {
                result = "El registro se ha eliminado correctamente";
            }

        } catch (SQLException ex) {
            result = "He ocurrido un error\n: " + ex.getMessage();
        }
        return result;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");
        switch (accion) {
            case "Ingresar Admin":
                IngresarAdmin(request, response);
                break;
            case "Eliminar":
                Eliminar(request, response);
                break;
        }

    }

    private void IngresarAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DAdministradores ad = new DAdministradores();

            String fecha = request.getParameter("fecha");

            ad.setFechaContatracion(fecha);
            ad.setPkIdUsuario(id);

            IngresarNuevoAdmin(ad);
            request.setAttribute("saludo", "RegistroCompletado");
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LAdministradores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LAdministradores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            EliminarAdmin(id);
            LUsuarios u = new LUsuarios();
            request.setAttribute("idUsuario", id);
            
            u.Eliminar(request, response);

            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        }
    }

}
