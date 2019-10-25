/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.reportes;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import mx.itson.instancias.InstanciaConexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dell
 */
public class GenerarReportes {
Connection cn = null;

    //Método de reporte para ventas.
    public void reporteVentas(String fecha) {
        try {
            JasperReport jrventa = (JasperReport) JRLoader.loadObject("C:\\Users\\Crusz\\Music\\Tortilleria\\Tortilleria\\src\\mx\\itson\\reportes\\reporteVentas.jasper");
           // JasperReport jrventa = JRLoader.loadObject("C:\\Users\\Crusz\\Downloads\\TORTILLINI\\Tortilleria\\src\\mx\\itson\\reportes\\reporteVentas.jasper");
            Map parametro = new HashMap();
            cn = InstanciaConexion.getInstanciaConexion().conexion();
            parametro.put("fecha", fecha);
            JasperPrint j = JasperFillManager.fillReport(jrventa, parametro,InstanciaConexion.getInstanciaConexion().conexion());
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte ventas");
            jv.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el reporte "+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Método de reporte para balance de ventas.
    public void reporteBalanceVentas(String fecha) {
        try {
            JasperReport jrventa = (JasperReport) JRLoader.loadObject("C:\\Users\\Crusz\\Music\\Tortilleria\\Tortilleria\\src\\mx\\itson\\reportes\\reporteBalanceVentas.jasper");
           // JasperReport jrventa = JRLoader.loadObject("C:\\Users\\Crusz\\Downloads\\TORTILLINI\\Tortilleria\\src\\mx\\itson\\reportes\\reporteVentas.jasper");
            Map parametro = new HashMap();
            cn = InstanciaConexion.getInstanciaConexion().conexion();
            parametro.put("fecha", fecha);
            JasperPrint j = JasperFillManager.fillReport(jrventa, parametro,InstanciaConexion.getInstanciaConexion().conexion());
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte balance de ventas");
            jv.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el reporte "+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Método de reporte para inventario.
    public void reporteInventario(String fecha) {
        try {
            JasperReport jrinventario = (JasperReport) JRLoader.loadObject("C:\\Users\\Crusz\\Music\\Tortilleria\\Tortilleria\\src\\mx\\itson\\reportes\\reporteInventario.jasper");
           // JasperReport jrventa = JRLoader.loadObject("C:\\Users\\Crusz\\Downloads\\TORTILLINI\\Tortilleria\\src\\mx\\itson\\reportes\\reporteVentas.jasper");
            Map parametro = new HashMap();
            cn = InstanciaConexion.getInstanciaConexion().conexion();
            parametro.put("fecha", fecha);
            JasperPrint j = JasperFillManager.fillReport(jrinventario, parametro,InstanciaConexion.getInstanciaConexion().conexion());
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte inventario");
            jv.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el reporte "+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Método de reporte para proveedores.
    public void reporteProveedores(String nombre) {
        try {
            JasperReport jrproveedores = (JasperReport) JRLoader.loadObject("C:\\Users\\Crusz\\Music\\Tortilleria\\Tortilleria\\src\\mx\\itson\\reportes\\reporteProveedor.jasper");
           // JasperReport jrventa = JRLoader.loadObject("C:\\Users\\Crusz\\Downloads\\TORTILLINI\\Tortilleria\\src\\mx\\itson\\reportes\\reporteVentas.jasper");
            Map parametro = new HashMap();
            cn = InstanciaConexion.getInstanciaConexion().conexion();
            parametro.put("nombre", nombre);
            JasperPrint j = JasperFillManager.fillReport(jrproveedores, parametro,InstanciaConexion.getInstanciaConexion().conexion());
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte proveedores");
            jv.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el reporte "+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
