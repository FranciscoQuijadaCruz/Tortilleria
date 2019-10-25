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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import mx.itson.entidades.Insumo;
import mx.itson.instancias.InstanciaConexion;
import mx.itson.interfaces.InventarioInt;

/**
 *
 * @author Francisco
 */
public class InventarioImp implements InventarioInt {
    private static InstanciaConexion conexion = InstanciaConexion.getInstanciaConexion();

    @Override
    public boolean Agregar(Insumo pro) throws SQLException {
        boolean exito = false;
        try {
            String agregarSQL = "INSERT INTO inventario (producto, cantidad, fecha, precio) "
                    + "VALUES (?,?,?, ?)";
            PreparedStatement pst = conexion.conexion().prepareStatement(agregarSQL);
            pst.setString(1, pro.getProducto());
            pst.setString(2, pro.getCantidad());
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String fech = dia + "/" + (mes + 1) + "/" + año;
            pst.setString(3, fech);
            pst.setDouble(4, pro.getPrecio());
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
    public List<Insumo> lista_productos() throws SQLException {
        List<Insumo> lista = null;
        
        try{
            String listaSQL = "SELECT * FROM inventario";
            PreparedStatement pst = conexion.conexion().prepareStatement(listaSQL);
            lista = new ArrayList();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Insumo pro = new Insumo();
                pro.setId(rs.getInt("id"));
                pro.setProducto(rs.getString("producto"));
                pro.setCantidad(rs.getString("cantidad"));
                pro.setFecha(rs.getString("fecha"));
                pro.setPrecio(rs.getDouble("precio"));
                lista.add(pro);
            }
        }catch(SQLException e){
            System.out.println("Error al leer la tabla: "+e.getMessage());
        } finally{
            conexion.cerrar();
        }
        return lista;

    }

    @Override
    public boolean Eliminar(Insumo pro) throws SQLException {
        boolean exito = false;
        
        try {
            String eliminarSQL = "DELETE FROM inventario WHERE id= "+pro.getId();
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
    public boolean Editar(Insumo proAnterior, Insumo proNuevo) throws SQLException {
        boolean exito = false;
        
        try {
            String editarSQL = "UPDATE inventario SET producto= ?, cantidad= ?, fecha= ?, precio= ? "+ "WHERE id= "+proAnterior.getId();
            PreparedStatement pst = conexion.conexion().prepareStatement(editarSQL);
            pst.setString(1, proNuevo.getProducto());
            pst.setString(2, proNuevo.getCantidad());
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String fech = dia + "/" + (mes + 1) + "/" + año;
            pst.setString(3, fech);
            pst.setDouble(4, proNuevo.getPrecio());
            pst.executeUpdate();
            exito=true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar los datos: "+e.getMessage());
        }finally{
            conexion.cerrar();
        }
        return exito;
    }
    
}
