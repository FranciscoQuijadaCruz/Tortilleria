/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.instancias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Conexion usando el patrón Singleton.
 * @author kevin
 */
public class InstanciaConexion {
    public Connection con = null;
    private static InstanciaConexion UnicaInstancia;
    private static String servidor = "jdbc:mysql://localhost/tortilleo";
    private static String user = "root";
    private static String pass = "fcoquijadacruz";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection conexion;
    
    private InstanciaConexion(){
        
    }
    
    public static synchronized InstanciaConexion getInstanciaConexion(){
        if(UnicaInstancia==null){
            UnicaInstancia = new InstanciaConexion();
            System.out.println("Instancia de conexion establecida");
        }else{
            System.out.println("Instancia de conexion previamente establecida, no se puede volver a instanciar");
        }
        return UnicaInstancia;
    }
    public Connection conexion(){
        
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost/tortilleria?user=root&password=fcoquijadacruz");
//      Class.forName(driver);
            con = DriverManager.getConnection(servidor, user, pass);

            if (con != null) {
                System.out.println("Conexion exitosa.");

            }  
        } catch (SQLException x){
            System.out.println("Error en la tabla de la Base de Datos: "+x.getMessage());
        }
        return con;
    }
    
    ResultSet rs = null;
    PreparedStatement ps = null;

    public ResultSet find(String s) {
        try {
            conexion = DriverManager.getConnection(servidor, user, pass);
//            conexion = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "");
            ps = conexion.prepareStatement("SELECT * FROM productos WHERE producto = ?");
            ps.setString(1, s);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }
    
    public void cerrar() throws SQLException{
        if(con != null){
            if(!con.isClosed()){
                con.close();
                UnicaInstancia = null;
            }
        }
    }
}
