/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.gui;

import com.placeholder.PlaceHolder;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mx.itson.entidades.Compras;
import mx.itson.entidades.Producto;
import mx.itson.entidades.Usuario;
import mx.itson.entidades.Venta;
import static mx.itson.gui.AdminUsuariosAdmi.usuario;
import mx.itson.implementacion.ComprasImp;
import mx.itson.implementacion.ProductoImp;
import mx.itson.implementacion.UsuarioImp;
import mx.itson.implementacion.VentaImp;
import mx.itson.instancias.InstanciaConexion;
import mx.itson.interfaces.ComprasInt;
import mx.itson.interfaces.ProductoInt;
import mx.itson.interfaces.UsuarioInt;
import mx.itson.interfaces.VentaInt;

/**
 *
 * @author Dell
 */
public class ComprasUsuario extends javax.swing.JFrame {
    private static InstanciaConexion conexion = InstanciaConexion.getInstanciaConexion();


    ComprasInt compImp = new ComprasImp();
    VentaInt venImp = new VentaImp();
    DefaultTableModel modelo;
    String[] titulos = {"Id", "Producto", "Peso", "Precio"};
    List<Compras> compras;
    List<Venta> ventas;
    public static Compras compra;

    ProductoInt proImp = new ProductoImp();
    DefaultComboBoxModel modelopro;
//    String[] titulos = {"Id", "Producto", "Precio"};
    List<Producto> productos;
    public static Producto pro;

    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    /**
     * Creates new form ComprasUsuario
     */
    public ComprasUsuario() throws IOException {
        initComponents();    
 Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mx/itson/items/iconotorti.png"));
        setIconImage(icon);
        this.setTitle("Caja Usuario");
        PlaceHolder holder = new PlaceHolder(txtFormaPago, "Elige forma de compra (Por kilo o por dinero)");
        PlaceHolder holder1 = new PlaceHolder(txtPago, "Pagó con $ ");
        setLocationRelativeTo(null);
        modelo = new DefaultTableModel();
        tbCompras.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        modelopro = new DefaultComboBoxModel();
        cbProducto.setModel(modelopro);
        carritol();
        sumita();
        cargarCB();
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fech="Fecha Actual: "
                           + dia + "/" + (mes+1) + "/" + año;
        lbfecha.setText("Fecha Actual: "
                           + dia + "/" + (mes+1) + "/" + año);
    }


    //Método para sacar el total de la compra.
    public void sumita() {
        double t = 0;
        double p = 0;
        if (tbCompras.getRowCount() >= 0) {
            for (int i = 0; i < tbCompras.getRowCount(); i++) {
                p = Double.parseDouble(tbCompras.getValueAt(i, 3).toString());
                t += p;
            }
            lbPrecio.setText(Double.toString(t));
        } else {
        }
    }

    //Método para realizar cobro.
    public void cambio() {
        double t = 0;
        double p = 0;
        if (tbCompras.getRowCount() > 0) {
            for (int i = 0; i < tbCompras.getRowCount(); i++) {
                p = Double.parseDouble(tbCompras.getValueAt(i, 3).toString());
                t += p;

            }
            double resu = Double.parseDouble(txtPago.getText().toString()) - t;
            lbCambio.setText(Double.toString(resu));
        } else {
        }
    }

    //Método para llenar tabla.
    public void carritol() {
        try {
            compras = compImp.lista_compras();
            for (Compras usr : compras) {
                Object[] o = new Object[4];
                o[0] = usr.getId();
                o[1] = usr.getProducto();
                o[2] = usr.getPeso();
                o[3] = usr.getPrecio();

                modelo.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos en la tabla");
        }
    }
    


    //Método para llenar datos en un combo box.
    public void cargarCB() {
        try {
            sql = "SELECT * FROM productos";
            cn = InstanciaConexion.getInstanciaConexion().conexion();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            System.out.println(sql);
            cbProducto.addItem("Selecciona el producto");
            while (rs.next()) {
                cbProducto.addItem(rs.getString("producto"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    double pago;
    
    
    
    public void limpiarCaja(){
           try {
                    if (compImp.EliminarTodo(compra)) {
                        JOptionPane.showMessageDialog(null, "Compra finalizada con éxito", "Compra finalizada", JOptionPane.INFORMATION_MESSAGE);
                        modelo = new DefaultTableModel();
                        tbCompras.setModel(modelo);
                        modelo.setColumnIdentifiers(titulos);
                        compras = compImp.lista_compras();
                        for (Compras comp : compras) {
                            Object[] o = new Object[4];
                            o[0] = comp.getId();
                            o[1] = comp.getProducto();
                            o[2] = comp.getPeso();
                            o[3] = comp.getPrecio();
                            modelo.addRow(o);
                            sumita();
                        }

                    }
                } catch (Exception e) {
                }
          
        modelo = new DefaultTableModel();
        tbCompras.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        carritol();
    
    }

    //Método de carga de datos mediante una selección de valor de un CB.
//    public void cargarOpcionCB() {
//        String opcion = cbProducto.getSelectedItem().toString();
//        try {
//            sql = "SELECT * FROM productos";
//            cn = InstanciaConexion.getInstanciaConexion().conexion();
//            stm = cn.createStatement();
//            rs = stm.executeQuery(sql);
//            System.out.println(sql);
//            if (cbProducto.getSelectedIndex() >= 1 && rbPeso.isSelected() == true && rbPago.isSelected() == false) {
//                pago = rs.getDouble("precio");
//            } else if (cbProducto.getSelectedIndex() >= 1 && rbPeso.isSelected() == false && rbPago.isSelected() == true) {
//                pago = rs.getDouble("precio");
//            }
//
//        } catch (Exception e) {
//        }
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVentas = new javax.swing.JButton();
        btnCompra = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lbUsuarioSesion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbPrecio = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        rbPago = new javax.swing.JRadioButton();
        rbPeso = new javax.swing.JRadioButton();
        txtFormaPago = new javax.swing.JTextField();
        cbProducto = new javax.swing.JComboBox<>();
        btnCobrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbCambio = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        btnFinalizar = new javax.swing.JButton();
        lbfecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCompras = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1322, 720));
        jPanel1.setLayout(null);

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/ventas.png"))); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel1.add(btnVentas);
        btnVentas.setBounds(300, 80, 230, 80);

        btnCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/compra.png"))); // NOI18N
        btnCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnCompra);
        btnCompra.setBounds(540, 80, 230, 80);

        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/productos.png"))); // NOI18N
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel1.add(btnProductos);
        btnProductos.setBounds(780, 80, 230, 80);

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrarSesion);
        btnCerrarSesion.setBounds(1090, 10, 200, 50);

        btnProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/proveedores.png"))); // NOI18N
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedor);
        btnProveedor.setBounds(1020, 110, 190, 50);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SESIÓN ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(870, 30, 100, 30);

        lbUsuarioSesion.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbUsuarioSesion.setForeground(new java.awt.Color(255, 255, 255));
        lbUsuarioSesion.setText("USUARIO");
        jPanel1.add(lbUsuarioSesion);
        lbUsuarioSesion.setBounds(970, 30, 340, 30);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Introduce los datos del producto");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Total");

        lbPrecio.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lbPrecio.setForeground(new java.awt.Color(0, 153, 153));
        lbPrecio.setText("-----");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/agregar.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/quitar.png"))); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        rbPago.setBackground(new java.awt.Color(255, 255, 255));
        rbPago.setText("Cantidad (Dinero)");

        rbPeso.setBackground(new java.awt.Color(255, 255, 255));
        rbPeso.setText("Peso (Kg)");

        cbProducto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona el producto", "Tortillas", "Masa", "Maíz", "Gorditas" }));
        cbProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProductoItemStateChanged(evt);
            }
        });

        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mx/itson/items/cobrar.png"))); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setText("Cambio");

        lbCambio.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lbCambio.setForeground(new java.awt.Color(0, 153, 153));
        lbCambio.setText("-----");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("Pago");

        btnFinalizar.setText("Finalizar Venta");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        lbfecha.setText("jLabel8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rbPeso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbPago))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(lbfecha))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPeso)
                    .addComponent(rbPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(lbfecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 240, 450, 440);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tbCompras.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbCompras.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tbCompras);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(510, 240, 790, 440);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CAJA");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 180, 1320, 50);

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

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        try {
            VentasUsuario fl = new VentasUsuario();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VentasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraActionPerformed
        try {
            ComprasUsuario fl = new ComprasUsuario();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ComprasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompraActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        try {
            ProductosUsuario fl = new ProductosUsuario();
            this.setVisible(false);
            fl.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ProductosUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnProductosActionPerformed


    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String opcion = cbProducto.getSelectedItem().toString();

        Producto prr = new Producto();
        if (txtFormaPago.getText().isEmpty() || cbProducto.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Datos incompletos, llene adecuadamente", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
        } else if (rbPeso.isSelected() == false && rbPago.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "No has seleccionado una forma de pago", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
        } else if (rbPeso.isSelected() == true && rbPago.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Selecciona unicamente una forma de pago", "Atención", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double resultado;
                Compras compra = new Compras();
                rs = InstanciaConexion.getInstanciaConexion().find(opcion);
                if (rs.next()) {
                    pago = rs.getDouble("precio");
                    String pago2 = String.valueOf(pago);
                    if (rbPeso.isSelected() == true && rbPago.isSelected() == false) {
                        
                        compra.setProducto(rs.getString("producto"));
                        compra.setPeso(Double.parseDouble(txtFormaPago.getText()));
                        resultado = Double.parseDouble(txtFormaPago.getText()) * pago;
                        compra.setPrecio(resultado);
                        ComprasInt compImp = new ComprasImp();
//                        if (compImp.Agregar(compra)) {
//                            JOptionPane.showMessageDialog(null, "Se registró la compra", "Compra registrada", JOptionPane.INFORMATION_MESSAGE);
//                        }
                    } else if (rbPeso.isSelected() == false && rbPago.isSelected() == true) {
                        compra.setProducto(rs.getString("producto"));
                        compra.setPrecio(Double.parseDouble(txtFormaPago.getText()));
                        resultado = Double.parseDouble(txtFormaPago.getText()) / rs.getDouble("precio");
                        compra.setPeso(resultado);
                        ComprasInt compImp = new ComprasImp();
//                        if (compImp.Agregar(compra)) {
//                            JOptionPane.showMessageDialog(null, "Se registró la compra", "Compra registrada", JOptionPane.INFORMATION_MESSAGE);
//                        }
                    }
                }
                if (compImp.Agregar(compra)) {
                            JOptionPane.showMessageDialog(null, "Se registró la compra", "Compra registrada", JOptionPane.INFORMATION_MESSAGE);
                        }
                else {
                    JOptionPane.showMessageDialog(null, "No existen los datos");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        modelo = new DefaultTableModel();
        tbCompras.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        carritol();
        sumita();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed

        if (!tbCompras.getSelectionModel().isSelectionEmpty()) {
            compra = compras.get(tbCompras.getSelectedRow());
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea quitar el producto " + compra.getProducto() + "?", "Quitar producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                try {
                    if (compImp.Eliminar(compra)) {
                        JOptionPane.showMessageDialog(null, "Producto quitado del carrito con éxito", "Producto quitado", JOptionPane.INFORMATION_MESSAGE);
                        modelo = new DefaultTableModel();
                        tbCompras.setModel(modelo);
                        modelo.setColumnIdentifiers(titulos);
                        compras = compImp.lista_compras();
                        for (Compras comp : compras) {
                            Object[] o = new Object[4];
                            o[0] = comp.getId();
                            o[1] = comp.getProducto();
                            o[2] = comp.getPeso();
                            o[3] = comp.getPrecio();
                            modelo.addRow(o);
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
        modelo = new DefaultTableModel();
        tbCompras.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        carritol();
        sumita();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (!tbCompras.getSelectionModel().isSelectionEmpty()) {
            compra = compras.get(tbCompras.getSelectedRow());
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la compra? ",
                    "Cancelar compra", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                try {
                    if (compImp.EliminarTodo(compra)) {
                        JOptionPane.showMessageDialog(null, "Compra cancelada con éxito", "Compra cancelada", JOptionPane.INFORMATION_MESSAGE);
                        modelo = new DefaultTableModel();
                        tbCompras.setModel(modelo);
                        modelo.setColumnIdentifiers(titulos);
                        compras = compImp.lista_compras();
                        for (Compras comp : compras) {
                            Object[] o = new Object[4];
                            o[0] = comp.getId();
                            o[1] = comp.getProducto();
                            o[2] = comp.getPeso();
                            o[3] = comp.getPrecio();
                            modelo.addRow(o);
                            sumita();
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
        modelo = new DefaultTableModel();
        tbCompras.setModel(modelo);
        modelo.setColumnIdentifiers(titulos);
        carritol();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        cambio();
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void cbProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProductoItemStateChanged

//        if (cbProducto.getSelectedIndex() >= 1) {
//            String opcion = cbProducto.getSelectedItem().toString();
//            try {
//                double resultado;
//                rs = InstanciaConexion.getInstanciaConexion().find(opcion);
//                if (rs.next()) {
//                    String prueba = rs.getString("producto");
//                    pago = rs.getDouble("precio");
//                    String pago2 = String.valueOf(pago);
//                    
//                    
//                    
//                    lbPrueba.setText(pago2);
//                }
//            } catch (Exception e) {
//            }
//        } else if (cbProducto.getSelectedIndex() == 0) {
//            lbPrueba.setText("no");
//        }
    }//GEN-LAST:event_cbProductoItemStateChanged

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
                
                Calendar fecha = new GregorianCalendar();
                int año = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                String fech = dia + "/" + (mes + 1) + "/" + año;
                String agregarSQL2 = "INSERT INTO total_compra (total, pago, cambio, fecha) "
                        + "VALUES (?,?,?,?)";
                
                PreparedStatement pst1 = conexion.conexion().prepareStatement(agregarSQL2);
                pst1.setDouble(1, Double.parseDouble(lbPrecio.getText().toString()));
                pst1.setDouble(2, Double.parseDouble(txtPago.getText().toString()));
                pst1.setDouble(3, Double.parseDouble(lbCambio.getText().toString()));
                pst1.setString(4, fech);
                
                pst1.executeUpdate();


            for (int i = 0; i < tbCompras.getRowCount(); i++) {

                String agregarSQL = "INSERT INTO venta (id, producto, peso, precio, fecha) "
                        + "VALUES (?,?,?,?,?)";
                PreparedStatement pst = conexion.conexion().prepareStatement(agregarSQL);
                
                pst.setString(1, tbCompras.getValueAt(i, 0).toString());
                pst.setString(2, tbCompras.getValueAt(i, 1).toString());
                pst.setString(3, tbCompras.getValueAt(i, 2).toString());
                pst.setDouble(4, Double.parseDouble(tbCompras.getValueAt(i, 3).toString()));
                pst.setString(5, fech);

                pst.executeUpdate();
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos en la tabla");
        }
        limpiarCaja();
        cargarCB();
        lbCambio.setText("-----");
        lbPrecio.setText("-----");
        txtFormaPago.setText("");
        txtPago.setText("");

        PlaceHolder holder = new PlaceHolder(txtFormaPago, "Introduce la forma de compra (Por kilo o por dinero)");
        PlaceHolder holder1 = new PlaceHolder(txtPago, "Pago con ");
        cargarCB();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login fl = new Login();
        this.setVisible(false);
        JOptionPane.showMessageDialog(rootPane, "Sesión cerrada. Vuelve pronto.");
        fl.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        ProveedorUsuario fl = new ProveedorUsuario();
        this.setVisible(false);
        fl.setVisible(true);
    }//GEN-LAST:event_btnProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(ComprasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComprasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComprasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComprasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ComprasUsuario().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ComprasUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCompra;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cbProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCambio;
    private javax.swing.JLabel lbPrecio;
    public javax.swing.JLabel lbUsuarioSesion;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JRadioButton rbPago;
    private javax.swing.JRadioButton rbPeso;
    private javax.swing.JTable tbCompras;
    private javax.swing.JTextField txtFormaPago;
    private javax.swing.JTextField txtPago;
    // End of variables declaration//GEN-END:variables
}
