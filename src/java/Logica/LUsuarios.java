
package Logica;

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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public String EliminarUsuario(DUsuario user) {
        String result = "";

        consulta = "Delete from usuario where idUsuario=?";

        try {
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(1, user.getIdUsuario());
            int n = pst.executeUpdate();

            if (n != 0) {
                result = "El registro se ha eliminado correctamente";
            }

        } catch (SQLException ex) {
            result = "He ocurrido un error\n: " + ex.getMessage();
        }
        return result;
    }

    
    
    
    ///-----------------Actualizar usuario-------------------------listo---------
    public String ActualizarUsuario(DUsuario user) {
        String result = "";

        consulta = "UPDATE `usuario` SET `Nombre`= ?,`Ape1`= ?,`Ape2`= ?,"
                + "`Fecha_naci`= ?,`Nombre_usuario`= ?,`Email`= ?,`Contrasena`= ?,"
                + "`Sexo`= ?,`PaisOrigen`= ?,`TipoUsuario`= ? "
                + "WHERE  idUsuario = ?";

        try {
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setInt(11, user.getIdUsuario());
            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApe1());
            pst.setString(3, user.getApe2());
            pst.setString(4, user.getFechaNacimiento());
            pst.setString(5, user.getUser());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getPass());
            pst.setString(8, user.getGenero());
            pst.setString(9, user.getPaisOrigen());
            pst.setString(10, user.getTipoUsuario());

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
    public int ExisteUsuario(DUsuario user) {
        int result = 0;
        consulta = "SELECT * FROM `usuario` where Nombre_usuario = ?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, user.getUser());
            ResultSet rs = st.executeQuery();

            
            if (rs.next()) {                
                result = rs.getInt("idUsuario") ;
            }
            
            

        } catch (Exception e) {
            result =0;
        }

        return result;
    }
    
    
    
    ///-----------------Si el nombre de usuario ya existe----------------------------------
    public int ExisteUsuarioYContra(DUsuario user) {
        int result = 0;
        consulta = "SELECT * FROM `usuario` where Nombre_usuario = ? and Contrasena =?";

        try {
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, user.getUser());
            st.setString(2, user.getPass());
            ResultSet rs = st.executeQuery();

            
            while (rs.next()) {                
                result = rs.getInt("idUsuario") ;
            }
            
            

        } catch (Exception e) {
            result =0;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("Accion");
        int id = Integer.parseInt(request.getParameter("idUsuario"));

        //----------------Ingresar un nuevo registro------------listo-------------------
        if (accion.equals("Ingresar")) {

            Date now = new Date(System.currentTimeMillis());

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String nombre = request.getParameter("nombre");
            String ape1 = request.getParameter("apellido1");
            String ape2 = request.getParameter("apellido2");
            String fechanaci = request.getParameter("fechanaci");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");
            String genero = request.getParameter("sexo");
            String pais = request.getParameter("pais");
            String tipo = request.getParameter("tipoUsuario");
            String fecha_creacion = date.format(now);

            int num = Integer.parseInt(InsertarUsuario(nombre, ape1, ape2, fechanaci, usuario, email, contrasena, fecha_creacion,
                    genero, pais, tipo));
            request.setAttribute("id", num);
            
            
            request.getRequestDispatcher("/TipoUsuario.jsp").forward(request, response);

            /*try (PrintWriter out = response.getWriter()) {
                out.println("Estoy Ingresando " + num);
            } catch (Exception e) {

            }*/
        }

        //----------------Actulizar un registro-------------------------------
        if (accion.equals("Actualizar")) {

            DUsuario user = new DUsuario();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String nombre = request.getParameter("nombre");
            String ape1 = request.getParameter("apellido1");
            String ape2 = request.getParameter("apellido2");
            String fechanaci = request.getParameter("fechanaci");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");
            String genero = request.getParameter("sexo");
            String pais = request.getParameter("pais");
            String tipo = request.getParameter("tipoUsuario");
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
            user.setTipoUsuario(usuario);

            ActualizarUsuario(user);

            /*try (PrintWriter out = response.getWriter()) {
                out.println( "Estoy actualizando");
            } catch (Exception e) {

            }*/
        }

        //-------------------Mostrar los datos de un usuario----------------------------
        if (accion.equals("CapturarDatos")) {

            DUsuario user = new DUsuario();
            
            user.setIdUsuario(id);
            
            String datos[] = CapturarDatos(user);
            
            request.setAttribute("id", datos[0]);
            request.setAttribute("Nombre", datos[1]);
            request.setAttribute("Ape1", datos[2]);
            request.setAttribute("Ape2", datos[3]);
            request.setAttribute("Fecha_naci", datos[4]);
            request.setAttribute("Nombre_usuario", datos[5]);
            request.setAttribute("Email", datos[6]);
            request.setAttribute("Contrasena", datos[7]);
            request.setAttribute("Fecha_creacion", datos[8]);
            request.setAttribute("Sexo", datos[9]);
            request.setAttribute("PaisOrigen", datos[10]);
            request.setAttribute("TipoUsuario", datos[11]);
            
            /*try (PrintWriter out = response.getWriter()) {
                out.println("Estoy capturando los datos ");
                out.println("Datos capturados " + dato );
            } catch (Exception e) {
                out.println(e.getMessage());
            }*/
            request.getRequestDispatcher("/ActualizarUsuario.jsp").forward(request, response);
        }
        
        
        
        if (accion.equals("Eliminar")) {
            
            
        DUsuario user = new DUsuario();
       
        user.setIdUsuario(id);
            EliminarUsuario(user);
        request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
        /* try (PrintWriter out = response.getWriter()) {
                out.println("Id: " + id);
                out.println("Datos eliminandos" );
            } catch (Exception e) {
                out.println(e.getMessage());
            }*/
        }
        
        
        if (accion.equals("Verificar")) {
            
           String us= request.getParameter("txtUsuario");
           String pass= request.getParameter("txtPass");
        DUsuario user = new DUsuario();
         
        user.setUser(us);
        user.setPass(pass);
        
        int ids = ExisteUsuarioYContra(user);
        
        request.setAttribute("Er", "Usuario y/o contraseña incorrecta");
        request.setAttribute("id", ids);
            if (ids!=0) {
                
                request.getRequestDispatcher("/PaginaPrincipal.jsp").forward(request, response);
                
            }else{
                
                request.getRequestDispatcher("/PaginaInicio.jsp").forward(request, response);
                
            }
        
        
         /*try (PrintWriter out = response.getWriter()) {
                
                out.println("verificado "+id +" "+ us +" "+ pass);
            } catch (Exception e) {
                out.println(e.getMessage());
            }*/
        }
    }
    

}
