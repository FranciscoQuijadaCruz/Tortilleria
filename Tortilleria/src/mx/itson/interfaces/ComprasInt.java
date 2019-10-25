/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.itson.entidades.Compras;

/**
 *
 * @author dell
 */
public interface ComprasInt {
    public boolean Agregar(Compras comp) throws SQLException;
    public boolean Eliminar(Compras comp) throws SQLException;
    public boolean EliminarTodo(Compras comp) throws SQLException;
    public List <Compras> lista_compras() throws SQLException;
}
