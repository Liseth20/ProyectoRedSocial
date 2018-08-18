/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Logica.LGenero;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author Rodrigo Moreno S
 */
@WebServlet(name = "LUsuarios", urlPatterns = {"/LUsuarios"})
public class LUsuarios extends HttpServlet {

    //-----------------------Declaración de variables---------------------------
    private Conexion mysql = new Conexion();
    private Connection con = mysql.Conectar();
    private String consulta = "";

    ///-----------------Insertar---------------------listo-------------
    public String InsertarUsuario(String nombre, String ape1, String ape2, String fechanaci, String usuario,
            String email, String contrasena, String fecha_creacion, String genero, String pais, String tipo) {
        String result = "";

        //Se agregan los valores a la consulta, se ingresarán en desde jsp
        consulta = "INSERT INTO USUARIO(Nombre,Ape1,Ape2,Fecha_naci,Nombre_usuario,Email,Contrasena,Fecha_creacion,Sexo,PaisOrigen,TipoUsuario)"
                + "VALUES('" + nombre + "','" + ape1 + "', '" + ape2 + "', '" + fechanaci + "', '" + usuario + "', '"
                + email + "', '" + contrasena + "', '" + fecha_creacion + "', '" + genero + "', '" + pais + "', '"
                + tipo + "')";

        try {
            Statement st = con.createStatement();
            int n = st.executeUpdate(consulta);

            if (n != 0) {
                result = idUltimoRegistrado();
            }

        } catch (Exception e) {
            result = "He ocurrido un problema: \n " + e.getMessage();
        }

        //------------------------------------
        return result;
    }

    ///-----------------Eliminar-----------------------listo-----------
    public void EliminarUsuario(int user) {
        consulta = "Delete from usuario where idUsuario=?";
        try {
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setInt(1, user);
            pst.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    ///-----------------Actualizar usuario-------------------------listo---------
    public String ActualizarUsuario(DUsuario user) {
        String result = "";

        consulta = "UPDATE `usuario` SET `Nombre`= ?,`Ape1`= ?,`Ape2`= ?,"
                + "`Fecha_naci`= ?,`Nombre_usuario`= ?,`Email`= ?,`Contrasena`= ?,"
                + "`Sexo`= ?,`PaisOrigen`= ?"
                + "WHERE  idUsuario = ?";

        try {
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(10, user.getIdUsuario());
            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApe1());
            pst.setString(3, user.getApe2());
            pst.setString(4, user.getFechaNacimiento());
            pst.setString(5, user.getUser());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getPass());
            pst.setString(8, user.getGenero());
            pst.setString(9, user.getPaisOrigen());

            int n = pst.executeUpdate();

            if (n != 0) {
                result = "El registro se ha actualizado correctamente";
            }

        } catch (SQLException ex) {
            result = "He ocurrido un error\n: " + ex.getMessage();
        }
        return result;
    }

    ///-----------------Id del último registrado-------------------listo---------------
    public String idUltimoRegistrado() {
        String result = "";
        consulta = "SELECT * FROM `usuario`";

        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {

                result = rs.getString("idUsuario");
            }

        } catch (Exception e) {
            result = "He ocurrido un problema: \n " + e.getMessage();
        }

        return result;
    }

    ///-----------------Si el nombre de usuario ya existe----------------------------------
    public String ExisteUsuario(DUsuario user) {
        String result = "";
        consulta = "SELECT idUsuario FROM `usuario` where Nombre_usuario = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, user.getUser());
            ResultSet r = st.executeQuery();

            if (r.next()) {
                result = "" + r.getInt("idUsuario");
            } else {
                result = "" + 0;
            }

        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

//-----------------Si el usuario y contraseña son correctos------------listo----------------------
    public int ExisteUsuarioYContra(DUsuario user) {
        int result = 0;
        consulta = "SELECT * FROM `usuario` where Nombre_usuario = ? and Contrasena =?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, user.getUser());
            st.setString(2, user.getPass());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getInt("idUsuario");
            }

        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    ///-----------------Saber tipo de usuario------------listo----------------------
    public String TipoUser(DUsuario us) {
        String result = "";
        consulta = "SELECT * FROM `usuario` where idUsuario = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, us.getIdUsuario());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getString("TipoUsuario");
            }

        } catch (Exception e) {
            result = "Error:\n " + e.getMessage();
        }

        return result;
    }

    ///-----------------Saber tipo de usuario------------listo----------------------
    public String SaberNombre(int us) {
        String result = "";
        consulta = "SELECT * FROM `usuario` where idUsuario = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, us);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getString("Nombre_usuario");
            }

        } catch (Exception e) {
            result = "Error:\n " + e.getMessage();
        }

        return result;
    }

    ///-----------------Captura los datos del usuario----------------listo------------------
    public String[] CapturarDatos(DUsuario user) {
        String datos[] = new String[12];
        String result = "";
        consulta = "SELECT * FROM `usuario` where idUsuario= ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, user.getIdUsuario());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                datos[0] = rs.getString("idUsuario");
                datos[1] = rs.getString("Nombre");
                datos[2] = rs.getString("Ape1");
                datos[3] = rs.getString("Ape2");
                datos[4] = rs.getString("Fecha_naci");
                datos[5] = rs.getString("Nombre_usuario");
                datos[6] = rs.getString("Email");
                datos[7] = rs.getString("Contrasena");
                datos[8] = rs.getString("Fecha_creacion");
                datos[9] = rs.getString("Sexo");
                datos[10] = rs.getString("PaisOrigen");
                datos[11] = rs.getString("TipoUsuario");
            }

        } catch (Exception e) {
            datos = null;
        }
        return datos;
    }

//-----------------Encriptar contraseña-----------------------------------------
    public String Encriptar(String s) {
        String result = "";
        char encrip[] = s.toCharArray();

        for (int i = 0; i < encrip.length; i++) {
            encrip[i] = (char) (encrip[i] + (char) 5);
            result += encrip[i];
        }
        return result;
    }

    //-----------------Desencriptar contraseña----------------------------------
    public String Desencriptar(String s) {
        String result = "";
        char encrip[] = s.toCharArray();

        for (int i = 0; i < encrip.length; i++) {
            encrip[i] = (char) (encrip[i] - (char) 5);
            result += encrip[i];
        }
        return result;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accion = request.getParameter("Accion");
            switch (accion) {

                case "Actualizar":
                    ActualizarUsuario(request, response);
                    break;

                case "Eliminar":
                    Eliminar(request, response);
                    break;

                case "CapturarDatos":
                    CargarDatos(request, response);
                    break;

                case "Verificar":
                    Verificar(request, response);
                    break;
                case "SaberNombreArtistas":
                    SaberNombreArtistas(request, response);
                    break;
                case "SaberNombreA":
                    SaberNombreA(request, response);
                    break;

            }
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("Accion");

        switch (accion) {

            case "Ingresar":
                IngresarUsuario(request, response);
                break;

            case "Actualizar":
                ActualizarUsuario(request, response);
                break;

            case "Eliminar":
                Eliminar(request, response);
                break;

            case "CapturarDatos":
                CargarDatos(request, response);
                break;

            case "Verificar":
                Verificar(request, response);
                break;

            case "SaberNombreArtistas":
                SaberNombreArtistas(request, response);
                break;

            case "SaberNombreE":
                SaberNombreE(request, response);
                break;
        }

        /*if (accion.equals("Verificar")) {

            String us = request.getParameter("txtUsuario");
            String pass = Encriptar(request.getParameter("txtPass"));
            DUsuario user = new DUsuario();

            user.setUser(us);
            user.setPass(pass);

            int ids = ExisteUsuarioYContra(user);

            request.setAttribute("id", ids);

            String tipo = null;

            DUsuario u = new DUsuario();
            u.setIdUsuario(ids);
            tipo = TipoUser(u);
            if (ids != 0) {

                request.getRequestDispatcher("/PaginaPrincipal" + tipo + ".jsp").forward(request, response);
                
            } else {
                request.setAttribute("saludo", "login");
                request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);

            }

            /*try (PrintWriter out = response.getWriter()) {
                
                out.println("verificado "+id +" "+ us +" "+ pass);
            } catch (Exception e) {
                out.println(e.getMessage());
            }*/
        //  }//Fin verificar
    }

    private void IngresarUsuario(HttpServletRequest request, HttpServletResponse response) {
        String usuario = request.getParameter("usuario");
        DUsuario user = new DUsuario();
        user.setUser(usuario);

        if (Integer.parseInt(ExisteUsuario(user)) == 0) {

            try {
                Date now = new Date(System.currentTimeMillis());

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String nombre = request.getParameter("nombre");
                String ape1 = request.getParameter("apellido1");
                String ape2 = request.getParameter("apellido2");
                String fechanaci = request.getParameter("fechanaci");
                String email = request.getParameter("email");

                String contrasena = Encriptar(request.getParameter("contrasena"));
                String genero = request.getParameter("sexo");
                String pais = request.getParameter("pais");
                String tipo = request.getParameter("tipoUsuario");
                String fecha_creacion = date.format(now);

                int num = Integer.parseInt(InsertarUsuario(nombre, ape1, ape2, fechanaci, usuario, email, contrasena, fecha_creacion,
                        genero, pais, tipo));
                request.setAttribute("id", num);

                if (tipo.equals("Artista") || tipo.equals("Experto") || tipo.equals("Fan")) {
                    try {
                        LGenero d = new LGenero();
                        List<DGenero> TablaGeneros;
                        TablaGeneros = d.MostrarDatos();
                        request.setAttribute("Generos", TablaGeneros);

                    } catch (Exception ex) {
                    }
                }
                request.getRequestDispatcher("/Registro" + tipo + ".jsp").forward(request, response);
            } catch (ServletException ex) {

            } catch (IOException ex) {

            }

        } else {
            try {
                String e = "El nombre de usuario ya existe";
                request.setAttribute("error", e);
                request.getRequestDispatcher("/FormularioRegistro.jsp").forward(request, response);
            } catch (ServletException ex) {

            } catch (IOException ex) {

            }
        }
        /*try (PrintWriter out = response.getWriter()) {
                    out.println("");
                } catch (Exception e) {
                }*/

    }

    private void ActualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));

            DUsuario user = new DUsuario();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String nombre = request.getParameter("nombre");
            String ape1 = request.getParameter("apellido1");
            String ape2 = request.getParameter("apellido2");
            String fechanaci = request.getParameter("fechanaci");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String contrasena = Encriptar(request.getParameter("contrasena"));
            String genero = request.getParameter("sexo");
            String pais = request.getParameter("pais");
            //String tipo = request.getParameter("tipoUsuario");
            //int id = Integer.parseInt(request.getParameter("id"));

            user.setIdUsuario(id);
            user.setNombre(nombre);
            user.setApe1(ape1);
            user.setApe2(ape2);
            user.setFechaNacimiento(fechanaci);
            user.setEmail(email);
            user.setUser(usuario);
            user.setPass(contrasena);
            user.setGenero(genero);
            user.setPaisOrigen(pais);
            //user.setTipoUsuario(tipo);
            String tipo = null;

            DUsuario u = new DUsuario();
            u.setIdUsuario(id);
            tipo = TipoUser(u);
            ActualizarUsuario(user);

            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipal" + tipo + ".jsp").forward(request, response);
            /*try (PrintWriter out = response.getWriter()) {
            out.println( "Estoy actualizando");
            } catch (Exception e) {

            }*/
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    public void Eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            EliminarUsuario(id);
            request.setAttribute("saludo", "Eliminado");
            request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void CargarDatos(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            DUsuario user = new DUsuario();

            user.setIdUsuario(id);

            String datos[] = CapturarDatos(user);

            request.setAttribute("datos", datos);
            request.setAttribute("id", datos[0]);
            request.setAttribute("Nombre", datos[1]);
            request.setAttribute("Ape1", datos[2]);
            request.setAttribute("Ape2", datos[3]);
            request.setAttribute("Fecha_naci", datos[4]);
            request.setAttribute("Nombre_usuario", datos[5]);
            request.setAttribute("Email", datos[6]);
            request.setAttribute("Contrasena", Desencriptar(datos[7]));
            request.setAttribute("Fecha_creacion", datos[8]);
            request.setAttribute("Sexo", datos[9]);
            request.setAttribute("PaisOrigen", datos[10]);
            request.setAttribute("TipoUsuario", datos[11]);

            request.getRequestDispatcher("/ActualizarUsuario.jsp").forward(request, response);
            /*try (PrintWriter out = response.getWriter()) {
           
            } catch (Exception e) {
            out.println(e.getMessage()); 
            }*/
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void Verificar(HttpServletRequest request, HttpServletResponse response) {

        String us = request.getParameter("txtUsuario");
        String pass = Encriptar(request.getParameter("txtPass"));
        DUsuario user = new DUsuario();

        user.setUser(us);
        user.setPass(pass);

        int ids = ExisteUsuarioYContra(user);
        request.setAttribute("nombre", us);
        request.setAttribute("id", ids);

        String tipo = null;

        DUsuario u = new DUsuario();
        u.setIdUsuario(ids);
        tipo = TipoUser(u);
        if (ids != 0) {

            try {
                request.setAttribute("nombre", us);

                request.getRequestDispatcher("/PaginaPrincipal" + tipo + ".jsp").forward(request, response);
                //} else {
                // request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);
                //}
            } catch (ServletException ex) {

            } catch (IOException ex) {

            }
        } else {
            request.setAttribute("saludo", "login");
            try {
                request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(LUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*try (PrintWriter out = response.getWriter()) {
            
            out.println("verificado "+id +" "+ us +" "+ pass);
            } catch (Exception e) {
            out.println(e.getMessage());
            }*/
    }

    private void SaberNombreArtistas(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));

            request.setAttribute("nombre", SaberNombre(id));
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipalArtista.jsp").forward(request, response);

        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void SaberNombreE(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            request.setAttribute("nombre", SaberNombre(id));
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipalExperto.jsp").forward(request, response);

        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

    private void SaberNombreA(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("idUsuario"));
            request.setAttribute("nombre", SaberNombre(id));
            request.setAttribute("id", id);
            request.getRequestDispatcher("/PaginaPrincipalAdministrador.jsp").forward(request, response);

        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
    }

}
