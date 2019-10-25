/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.interfaces;

import java.sql.SQLException;
import java.util.List;
import mx.itson.entidades.Insumo;

/**
 *
 * @author Francisco
 */
public interface InventarioInt {
    public boolean Agregar(Insumo pro) throws SQLException;
    public boolean Eliminar(Insumo pro) throws SQLException;
    public boolean Editar(Insumo proAnterior, Insumo proNuevo) throws SQLException;
    public List <Insumo> lista_productos() throws SQLException;
    
}
