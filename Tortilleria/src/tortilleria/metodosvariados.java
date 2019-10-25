/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortilleria;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mx.itson.entidades.Compras;
import mx.itson.implementacion.ComprasImp;
import mx.itson.interfaces.ComprasInt;

/**
 *
 * @author Dell
 */
public class metodosvariados {
//    String opcion = cbProducto.getSelectedItem().toString();
//
//        if (txtFormaPago.getText().isEmpty() || opcion.equals("Selecciona el producto")) {
//            JOptionPane.showMessageDialog(null, "Los siguientes campos no pueden estar vacíos: \nProducto\nForma de Pago", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
//        } else if (rbPeso.isSelected() == false && rbPago.isSelected() == false) {
//            JOptionPane.showMessageDialog(null, "No has seleccionado una forma de pago", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
//        } else if (rbPeso.isSelected() == true && rbPago.isSelected() == true) {
//            JOptionPane.showMessageDialog(null, "Selecciona unicamente una forma de pago", "Atención", JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                double resultado;
//                Compras compra = new Compras();
//                if (opcion.equals("Tortillas")) {
//                    if (rbPeso.isSelected() == true && rbPago.isSelected() == false) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPeso(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) * 19;
//                        compra.setPrecio(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    } else if (rbPeso.isSelected() == false && rbPago.isSelected() == true) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPrecio(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) / 19;
//                        compra.setPeso(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    }
//                } else if (opcion.equals("Masa")) {
//                    if (rbPeso.isSelected() == true && rbPago.isSelected() == false) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPeso(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) * 17;
//                        compra.setPrecio(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    } else if (rbPeso.isSelected() == false && rbPago.isSelected() == true) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPrecio(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) / 17;
//                        compra.setPeso(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    }
//                } else if (opcion.equals("Maíz")) {
//                    if (rbPeso.isSelected() == true && rbPago.isSelected() == false) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPeso(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) * 13;
//                        compra.setPrecio(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    } else if (rbPeso.isSelected() == false && rbPago.isSelected() == true) {
//                        compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                        compra.setPrecio(Double.parseDouble(txtFormaPago.getText()));
//                        resultado = Double.parseDouble(txtFormaPago.getText()) / 13;
//                        compra.setPeso(resultado);
//                        ComprasInt compImp = new ComprasImp();
//                    }
//                } else if (opcion.equals("Gorditas")) {
//                    compra.setProducto(new String(cbProducto.getSelectedItem().toString()));
//                    resultado = 20;
//                    compra.setPrecio(resultado);
//                    compra.setPeso(0);
//                    ComprasInt compImp = new ComprasImp();
//                }
//
//                if (compImp.Agregar(compra)) {
//                    JOptionPane.showMessageDialog(null, "Se registró la compra", "Compra registrada", JOptionPane.INFORMATION_MESSAGE);
//
//                }
//
//            } catch (Exception e) {
//            }
//        }
//        modelo = new DefaultTableModel();
//        tbCompras.setModel(modelo);
//        modelo.setColumnIdentifiers(titulos);
//        carritol();
//        sumita();
}
