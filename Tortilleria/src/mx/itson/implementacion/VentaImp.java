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
import mx.itson.entidades.Venta;
import mx.itson.instancias.InstanciaConexion;
import mx.itson.interfaces.VentaInt;


/**
 *
 * @author Francisco
 */
public class VentaImp implements VentaInt {

    private static InstanciaConexion conexion = InstanciaConexion.getInstanciaConexion();

    @Override
    public boolean Agregar(Venta ven) throws SQLException {
        boolean exito = false;
        try {
            String agregarSQL = "INSERT INTO venta (producto, peso (kg), precio, fecha) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = conexion.conexion().prepareStatement(agregarSQL);
            pst.setString(1, ven.getProducto());
            pst.setDouble(2, ven.getPeso());
            pst.setDouble(3, ven.getPrecio());
            pst.setString(4,  ven.getFecha());
            pst.executeUpdate();
            exito = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conexion.cerrar();
        }
        return exito;

    }

    @Override
    public List<Venta> lista_ventas() throws SQLException {
        List<Venta> lista = null;

        try {
            String listaSQL = "SELECT * FROM venta";
            PreparedStatement pst = conexion.conexion().prepareStatement(listaSQL);
            lista = new ArrayList();
            ResultSet rs = pst.executeQuery();
            

            while (rs.next()) {
                Venta ven = new Venta();
                ven.setId(rs.getInt("id"));
                ven.setProducto(rs.getString("producto"));
                ven.setPeso(rs.getDouble("peso"));
                ven.setPrecio(rs.getDouble("precio"));
                ven.setFecha(rs.getString("fecha"));
                lista.add(ven);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer la tabla: " + e.getMessage());
        } finally {
            conexion.cerrar();
        }
        return lista;

    }

    @Override
    public boolean Eliminar(Venta ven) throws SQLException {
        boolean exito = false;

        try {
            String eliminarSQL = "DELETE FROM venta WHERE id= " + ven.getId();
            PreparedStatement pst = conexion.conexion().prepareStatement(eliminarSQL);
            pst.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos: " + e.getMessage());
        } finally {
            conexion.cerrar();
        }
        return exito;
    }

    @Override
    public boolean EliminarTodo(Venta ven) throws SQLException {
        boolean exito = false;

        try {
            String eliminarSQL = "DELETE FROM carrito";
            PreparedStatement pst = conexion.conexion().prepareStatement(eliminarSQL);
            pst.executeUpdate();
            exito = true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos: " + e.getMessage());
        } finally {
            conexion.cerrar();
        }
        return exito;
    }
    




    
    

}
