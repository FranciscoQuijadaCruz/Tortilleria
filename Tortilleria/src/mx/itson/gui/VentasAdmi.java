/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.gui;

import com.placeholder.PlaceHolder;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mx.itson.entidades.Compras;
import mx.itson.entidades.Usuario;
import mx.itson.entidades.Venta;
import mx.itson.implementacion.UsuarioImp;
import mx.itson.implementacion.VentaImp;
import mx.itson.interfaces.UsuarioInt;
import mx.itson.interfaces.VentaInt;
import mx.itson.reportes.GenerarReportes;

/**
 *
 * @author Dell
 */
public class VentasAdmi extends javax.swing.JFrame {

    VentaInt ventaImp = new VentaImp();
    DefaultTableModel modelo;

    String[] titulos = {"Id", "Producto", "Peso (kg) o Paquete", "Precio", "Fecha"};
    List<Compras> compras;
    List<Venta> ventas;
//        public static Compras compra;

    /**
     * Creates new form ComprasUsuario
     */
    public VentasAdmi() throws IOException {
        initComponents();
        PlaceHolder holder1 = new PlaceHolder(txtFecha, "Formato: 1/1/2000 ");
        setLocationRelativeTo(null);
        modelo = new DefaultTableModel();
        tbVentas.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mx/itson/items/iconotorti.png"));
        setIconImage(icon);
        this.setTitle("Ventas admin.");
        ventasl();
    }

    public void ventasl() {
        try {
            ventas = ventaImp.lista_ventas();
            for (Venta usr : ventas) {
                Object[] o = new Object[5];
                o[0] = usr.getId();
                o[1] = usr.getProducto();
                o[2] = usr.getPeso();
                o[3] = usr.getPrecio();
                o[4] = usr.getFecha();

                modelo.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos en la tabla");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbUsuarioSesion = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAdmiUsuarios = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnCompra = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentas = new javax.swing.JTable();
        btnGenerar = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnBalance = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1322, 720));
        jPanel1.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SESIÓN ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(870, 30, 100, 30);

        lbUsuarioSesion.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbUsuarioSesion.setForeground(new java.awt.Color(255, 255, 255));
        lbUsuarioSesion.setText("ADMINISTRADOR");
        jPanel1.add(lbUsuarioSesion);
        lbUsuarioSesion.setBounds(970, 30, 340, 30);

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrarSesion);
        btnCerrarSesion.setBounds(1090, 180, 200, 50);

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/inventario.png"))); // NOI18N
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnInventario);
        btnInventario.setBounds(1060, 80, 230, 80);

        btnAdmiUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/admin-usuario.png"))); // NOI18N
        btnAdmiUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmiUsuariosActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdmiUsuarios);
        btnAdmiUsuarios.setBounds(740, 80, 310, 80);

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/ventas.png"))); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVentas);
        btnVentas.setBounds(20, 80, 230, 80);

        btnCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/compra.png"))); // NOI18N
        btnCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompra);
        btnCompra.setBounds(260, 80, 230, 80);

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/productos.png"))); // NOI18N
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnProductos);
        btnProductos.setBounds(500, 80, 230, 80);

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/proveedores.png"))); // NOI18N
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedor);
        btnProveedor.setBounds(20, 180, 200, 50);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tbVentas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbVentas.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tbVentas);

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/generarreportetotal.png"))); // NOI18N
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GENERAR REPORTES");

        btnBalance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/generarreportebalance.png"))); // NOI18N
        btnBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(btnBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(300, 240, 790, 430);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VENTAS");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(580, 180, 240, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/fondo-tortilleria.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1330, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        try {
            InventarioAdmi fl = new InventarioAdmi();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(InventarioAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnAdmiUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmiUsuariosActionPerformed
        try {
            AdminUsuariosAdmi fl = new AdminUsuariosAdmi();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(AdminUsuariosAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAdmiUsuariosActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        try {
            VentasAdmi fl = new VentasAdmi();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VentasAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraActionPerformed
        try {
            ComprasAdmi fl = new ComprasAdmi();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ComprasAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompraActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        try {
            ProductosAdmi fl = new ProductosAdmi();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ProductosAdmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login fl = new Login();
        this.setVisible(false);
        JOptionPane.showMessageDialog(rootPane, "Sesión cerrada. Vuelve pronto.");
        fl.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
//        ElegirFecha fl = new ElegirFecha();
//        this.setVisible(false);
//        fl.setVisible(true);
        GenerarReportes gr = new GenerarReportes();
        gr.reporteVentas(txtFecha.getText().toString());
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        ProveedorAdmin fl = new ProveedorAdmin();
        this.setVisible(false);
        fl.setVisible(true);
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanceActionPerformed
        // TODO add your handling code here:
        GenerarReportes gr = new GenerarReportes();
        gr.reporteBalanceVentas(txtFecha.getText().toString());
    }//GEN-LAST:event_btnBalanceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentasAdmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasAdmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasAdmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasAdmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VentasAdmi().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VentasAdmi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmiUsuarios;
    private javax.swing.JButton btnBalance;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCompra;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbUsuarioSesion;
    private javax.swing.JTable tbVentas;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
