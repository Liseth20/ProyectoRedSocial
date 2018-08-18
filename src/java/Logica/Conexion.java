/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

/**
 *
 * @author Rodrigo Moreno S
 */
public class Conexion {

    public String db = "redsocialbd";
    public String url = "jdbc:mysql://localhost/" + db;
    public String user = "root";
    public String pass = "";

    public Conexion() {
    }

    public Connection Conectar() {
        Connection link = null;
        String result = "";
        
        try {
            //Se busca el driver
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            result = "Ha ocurrido un error:\n " + e.getMessage();
        }

        return link;
    }
}
