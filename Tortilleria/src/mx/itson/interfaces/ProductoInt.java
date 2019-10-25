/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.itson.entidades.Producto;

/**
 *
 * @author dell
 */
public interface ProductoInt {
    public boolean Agregar(Producto pro) throws SQLException;
    public boolean Eliminar(Producto pro) throws SQLException;
    public boolean Editar(Producto proAnterior, Producto proNuevo) throws SQLException;
    public List <Producto> lista_productos() throws SQLException;
}
