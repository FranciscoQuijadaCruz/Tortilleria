/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.entidades.Proveedor;
import mx.itson.instancias.InstanciaConexion;
import mx.itson.interfaces.ProveedorInt;

/**
 *
 * @author Francisco
 */
public class ProveedorImp implements ProveedorInt{
    private static InstanciaConexion conexion = InstanciaConexion.getInstanciaConexion();

    @Override
    public boolean Agregar(Proveedor usr) throws SQLException {
        boolean exito = false;
        try {
            String agregarSQL = "INSERT INTO proveedor (nombre, producto, telefono, direccion) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = conexion.conexion().prepareStatement(agregarSQL);
            pst.setString(1, usr.getNombre());
            pst.setString(2, usr.getProducto());
            pst.setString(3, usr.getTelefono());
            pst.setString(4, usr.getDireccion());
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
    public List<Proveedor> lista_usuarios() throws SQLException {
        List<Proveedor> lista = null;
        
        try{
            String listaSQL = "SELECT * FROM proveedor";
            PreparedStatement pst = conexion.conexion().prepareStatement(listaSQL);
            lista = new ArrayList();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Proveedor usr = new Proveedor();
                usr.setId(rs.getInt("id"));
                usr.setNombre(rs.getString("nombre"));
                usr.setProducto(rs.getString("producto"));
                usr.setTelefono(rs.getString("telefono"));
                usr.setDireccion(rs.getString("direccion"));
                lista.add(usr);
            }
        }catch(SQLException e){
            System.out.println("Error al leer la tabla: "+e.getMessage());
        } finally{
            conexion.cerrar();
        }
        return lista;

    }

     
    @Override
    public boolean Editar(Proveedor usrAnterior, Proveedor usrNuevo) throws SQLException {
        boolean exito = false;
        
        try {
            String editarSQL = "UPDATE proveedor SET nombre= ?, producto= ?, telefono= ?,"
                    + "direccion= ? "
                    + "WHERE id= "+usrAnterior.getId();
            PreparedStatement pst = conexion.conexion().prepareStatement(editarSQL);
            pst.setString(1, usrNuevo.getNombre());
            pst.setString(2, usrNuevo.getProducto());
            pst.setString(3, usrNuevo.getTelefono());
            pst.setString(4, usrNuevo.getDireccion());
            pst.executeUpdate();
            exito=true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar los datos: "+e.getMessage());
        }finally{
            conexion.cerrar();
        }
        return exito;
    }

    @Override
    public boolean Eliminar(Proveedor usr) throws SQLException {
        boolean exito = false;
        
        try {
            String eliminarSQL = "DELETE FROM proveedor WHERE id= "+usr.getId();
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
