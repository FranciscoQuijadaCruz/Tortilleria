/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.instancias;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kevin
 */
public class InstanciaLogin {
    private static InstanciaLogin UnicaInstancia;
    
    private InstanciaLogin(){
        
    }
    
    public static synchronized InstanciaLogin getInstanciaLogin(){
        if(UnicaInstancia==null){
            UnicaInstancia = new InstanciaLogin();
            System.out.println("Instancia Login establecida");
        }else{
            System.out.println("Instancia Login previamente establecida, no se puede volver a generar");
        }
        return UnicaInstancia;
    }
    
    public boolean Login(String nombreUsuario, String contrasena, Connection con){
        boolean coincide = false;
        try {
            String sql = "SELECT * FROM usuario WHERE nombreUsuario= '"+nombreUsuario+"' AND contrasena= '"+contrasena+"'";
            Statement login = con.createStatement();
            ResultSet rs = login.executeQuery(sql);
            while(rs.next()){
                coincide = true;
            }if(!coincide){
                System.out.println("Datos de usuario incorrectos");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return coincide;
    }
}
