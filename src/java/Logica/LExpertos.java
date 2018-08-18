/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.DExpertos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@WebServlet(name = "LExpertos", urlPatterns = {"/LExpertos"})
public class LExpertos extends HttpServlet {

    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    //----------------Ingresar un nuevo experto------------------------
    public void IngresarNuevoExperto(DExpertos ar) {

        consulta = "INSERT INTO `expertos`(`fk_usuario`, `Fecha_inicio`, `fk_genero`, `Descripcion`) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, ar.getFk_usuario());
            st.setString(2, ar.getFechaInicio());
            st.setInt(3, ar.getFk_genero());
            st.setString(4, ar.getDescripcion());
            st.executeUpdate();
        } catch (Exception e) {

        }
    }//Fin ingresar

    //---------------------Actualizar experto-------------------------------
    public void ActualizarExperto(DExpertos ar) {
        consulta = "UPDATE `expertos` SET `Fecha_inicio`= ?,`fk_genero`= ?,"
                + "`Descripcion`= ? WHERE = ?";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(4, ar.getFk_usuario());
            st.setString(1, ar.getFechaInicio());
            st.setInt(2, ar.getFk_genero());
            st.setString(3, ar.getDescripcion());

            st.executeUpdate();
        } catch (Exception e) {

        }
    }

//--------------------------Eliminar experto------------------------------------
    public void EliminarExperto(int ar) {
        consulta = "DELETE FROM `expertos` WHERE `fk_usuario`= ?;DELETE FROM `usuario` WHERE `idUsuario`= ?;";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, ar);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

//--------------------------doGet - doPost------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");

        switch (accion) {

            case "IngresarExperto":
                IngresarExperto(request, response);
                break;

            case "ActualizarExperto":
                ActualizarExperto(request, response);
                break;

            case "EliminarExperto":
                EliminarExperto(request, response);
                break;

        }
    }//Fon doPost

    protected void IngresarExperto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DExpertos d = new DExpertos();
            d.setFk_usuario(id);
            d.setDescripcion(request.getParameter("descripcion"));
            d.setFechaInicio(request.getParameter("fecha"));
            d.setFk_genero(Integer.parseInt(request.getParameter("generoM")));

            IngresarNuevoExperto(d);
            request.setAttribute("saludo", "RegistroCompletado");
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);

        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void ActualizarExperto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DExpertos d = new DExpertos();
            d.setFk_usuario(id);
            d.setDescripcion(request.getParameter("biografia"));
            d.setFechaInicio(request.getParameter("fecha"));
            d.setFk_genero(Integer.parseInt(request.getParameter("generoM")));

            ActualizarExperto(d);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }

    }

    protected void EliminarExperto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            EliminarExperto(id);
            LUsuarios u = new LUsuarios();
            request.setAttribute("idUsuario", id);
            u.Eliminar(request, response);

            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        }
    }

}
