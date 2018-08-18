package Logica;

import Datos.DGenero;
import Datos.DGenerosFans;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LGenerosFans", urlPatterns = {"/LGenerosFans"})
public class LGenerosFans extends HttpServlet {

//-----------------------Declaración de variables---------------------------
    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

//-----------------------Insertar genero para un fan---------------------------    
    public void InsertarGeneroFan(DGenerosFans f) {
        consulta = "INSERT INTO `generoxusuario`(`Fk_idGenero`, `Fk_idUsuario`) VALUES (?,?)";
        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, f.getIdGenero());
            st.setInt(2, f.getIdFan());
            st.execute();
        } catch (Exception e) {
        }
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
            case "IngresarGeneroFan":
                IngresarGeneroFan(request, response);
                break;
            case "FinalizarFan":
                FinalizarFan(request, response);
                break;
        }
    }

    private void IngresarGeneroFan(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("idUsuario");
        try {
            DGenerosFans f = new DGenerosFans();
            f.setIdGenero(Integer.parseInt(request.getParameter("generoM")));
            f.setIdFan(Integer.parseInt(id));

            InsertarGeneroFan(f);
            try {
                LGenero d = new LGenero();
                List<DGenero> TablaGeneros;
                TablaGeneros = d.MostrarDatos();
                request.setAttribute("Generos", TablaGeneros);

            } catch (Exception ex) {
            }
            request.setAttribute("id", id);
            request.setAttribute("saluso", "RegistroCompletado");
            request.getRequestDispatcher("/RegistroFan.jsp").forward(request, response);
        } catch (Exception ex) {

        }
    }

    private void FinalizarFan(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("idUsuario");
            request.setAttribute("id", id);
            request.setAttribute("saluso", "RegistroCompletado");
            request.getRequestDispatcher("PaginaInicio.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

}
