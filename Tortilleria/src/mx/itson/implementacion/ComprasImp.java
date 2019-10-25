/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.implementacion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.entidades.Compras;
import mx.itson.instancias.InstanciaConexion;
import mx.itson.interfaces.ComprasInt;

/**
 *
 * @author Francisco
 */
public class ComprasImp implements ComprasInt{
    private static InstanciaConexion conexion = InstanciaConexion.getInstanciaConexion();

    @Override
    public boolean Agregar(Compras comp) throws SQLException {
        boolean exito = false;
        try {
            String agregarSQL = "INSERT INTO carrito (producto, peso, precio) "
                    + "VALUES (?,?,?)";
            PreparedStatement pst = conexion.conexion().prepareStatement(agregarSQL);
            pst.setString(1, comp.getProducto());
            pst.setDouble(2, comp.getPeso());
            pst.setDouble(3, comp.getPrecio());
            pst.executeUpdate();
            exito = true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            conexion.cerrar();
        }
        return exito;
        
    }

    @Override
    public List<Compras> lista_compras() throws SQLException {
        List<Compras> lista = null;
        
        try{
            String listaSQL = "SELECT * FROM carrito";
            PreparedStatement pst = conexion.conexion().prepareStatement(listaSQL);
            lista = new ArrayList();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Compras comp = new Compras();
                comp.setId(rs.getInt("id"));
                comp.setProducto(rs.getString("producto"));
                comp.setPeso(rs.getDouble("peso"));
                comp.setPrecio(rs.getDouble("precio"));
                lista.add(comp);
            }
        }catch(SQLException e){
            System.out.println("Error al leer la tabla: "+e.getMessage());
        } finally{
            conexion.cerrar();
        }
        return lista;

    }

    @Override
    public boolean Eliminar(Compras comp) throws SQLException {
        boolean exito = false;
        
        try {
            String eliminarSQL = "DELETE FROM carrito WHERE id= "+comp.getId();
            PreparedStatement pst = conexion.conexion().prepareStatement(eliminarSQL);
            pst.executeUpdate();
            exito=true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos: "+e.getMessage());
        }finally{
            conexion.cerrar();
        }
        return exito;
    }

    @Override
    public boolean EliminarTodo(Compras comp) throws SQLException {
        boolean exito = false;
        
        try {
            String eliminarSQL = "DELETE FROM carrito";
            PreparedStatement pst = conexion.conexion().prepareStatement(eliminarSQL);
            pst.executeUpdate();
            exito=true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos: "+e.getMessage());
        }finally{
            conexion.cerrar();
        }
        return exito;
    }
        
    
}
