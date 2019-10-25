/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.itson.entidades.Venta;

/**
 *
 * @author Francisco
 */
public interface VentaInt {
    public boolean Agregar(Venta ven) throws SQLException;
    public boolean Eliminar(Venta ven) throws SQLException;
    public boolean EliminarTodo(Venta ven) throws SQLException;
    public List <Venta> lista_ventas() throws SQLException;
    
    
}
