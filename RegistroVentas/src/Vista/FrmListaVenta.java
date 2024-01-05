/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.BoletoControl;
import Controlador.PasajeroControl;
import Controlador.TDA.Listas.Exception.EmptyException;
import Controlador.TDA.Listas.ListaEnlazada;
import static Controlador.Validacion.Utiles.validadorDeCedula;
import Controlador.VentaControl;
import Controlador.dao.Implements.ControlBoleto;
import Controlador.dao.Implements.ControlPasajero;
import Controlador.dao.Implements.ControlVenta;
import Vista.Tabla.ModeloTablaBoleto;
import Vista.Tabla.ModeloTablaVentas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Boleto;
import modelo.Venta;

/**
 *
 * @author Usuario
 */
public class FrmListaVenta extends javax.swing.JFrame {

    //txtTotalBoleto.setText(Double.toString(controlVenta.getVenta().getTotal_Boleto()));
    //txtMonto_Total.setText(Double.toString(montoTotal()));
    
    public FrmListaVenta(ControlVenta controlVenta) throws EmptyException {
        initComponents();
        this.controlVenta = controlVenta;
        cargarVista();
        
        //txtTotalBoleto.setText(Double.toString(controlVenta.getVenta().getTotal_Boleto()));
        //txtMonto_Total.setText(Double.toString(montoTotal()));
        
        //txtTotalBoleto.setText(String.valueOf(controlVenta.getVenta().getTotal_Boleto()));
        //txtMonto_Total.setText(String.valueOf(montoTotal()));
        
        // Carga las ventas al inicializar la ventana
    }
    private ModeloTablaVentas mtv = new ModeloTablaVentas();
    private ControlBoleto controlBoleto = new ControlBoleto();
    private ControlPasajero controlPasajero = new ControlPasajero();
    private ControlVenta controlVenta = new ControlVenta();

    public void cargarVentas() {
        mtv.setVentas(controlVenta.getVentas());
        Tabla3.setModel(mtv);
        Tabla3.updateUI();
    }
         private void cargarVista(){
        int fila = Tabla3.getSelectedRow();
            try {
               
                //controlVenta.setVenta(mtv.getVentas().getInfo(fila));
                txtTotalBoleto.setText(String.valueOf(controlVenta.getVenta().getTotal_Boleto()));
                txtMonto_Total.setText(String.valueOf(montoTotal()));
            } catch (Exception ex) {
                Logger.getLogger(FrmListaVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    

    public Boolean verificar() {
        return (!txtnum_Venta.getText().trim().isEmpty()
                && !txtfecha_Compra.getText().trim().isEmpty());
    }

    private void guardar() throws EmptyException {
        if (verificar()) {

            controlVenta.getVenta().setNum_Venta(txtnum_Venta.getText());
            controlVenta.getVenta().setFecha(txtfecha_Compra.getText());
            controlVenta.getVenta().setTotal_Boleto(Double.valueOf(txtTotalBoleto.getText()));
            controlVenta.getVenta().setMonto_Total(Double.valueOf(txtMonto_Total.getText()));
            if (controlVenta.persist()) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
                controlVenta.getVenta().setMonto_Total(montoTotal());
                 controlVenta.persist();
                 controlVenta.persist(controlVenta.getVenta());
                //ControlVenta controlVenta = new ControlVenta();
                //controlVenta.persist(controlVenta.getVenta());
                cargarVentas();

                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        txtnum_Venta.setText("");
        txtfecha_Compra.setText("");

        cargarVentas();
    }

    private Double montoTotal() throws EmptyException {
        Double total = 0.0;

        for (int i = 0; i < controlVenta.getVentas().getLength(); i++) {
            total += controlVenta.getVentas().getInfo(i).getTotal_Boleto();
        }

        System.out.println(total);
        return total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarNuevaVenta = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        txtMonto_Total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnum_Venta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfecha_Compra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTotalBoleto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Tabla3);

        jLabel1.setText("Total Boletos Vendidos ");

        btnAgregarNuevaVenta.setText("Agregar Nueva Venta");
        btnAgregarNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevaVentaActionPerformed(evt);
            }
        });

        jLabel2.setText("Monto Total:");

        txtMonto_Total.setEnabled(false);
        txtMonto_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonto_TotalActionPerformed(evt);
            }
        });

        jLabel3.setText("Numero de Venta:");

        txtnum_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnum_VentaActionPerformed(evt);
            }
        });

        jLabel4.setText("fecha_Compra:");

        txtfecha_Compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfecha_CompraActionPerformed(evt);
            }
        });

        jLabel7.setText("Total Boleto:");

        txtTotalBoleto.setEnabled(false);
        txtTotalBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBoletoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnum_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfecha_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotalBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(btnGuardar)))
                                .addGap(46, 46, 46)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgregarNuevaVenta)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMonto_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtnum_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtfecha_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtTotalBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(btnGuardar)))))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMonto_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregarNuevaVenta)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfecha_CompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfecha_CompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfecha_CompraActionPerformed

    private void txtTotalBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBoletoActionPerformed
    
    }//GEN-LAST:event_txtTotalBoletoActionPerformed

    private void txtnum_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnum_VentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnum_VentaActionPerformed

    private void txtMonto_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonto_TotalActionPerformed
      

    }//GEN-LAST:event_txtMonto_TotalActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmListaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevaVentaActionPerformed
        FrmListaVenta frmListaVenta = null;

        FrmVentaBoleto frm = null;
        try {
            frm = new FrmVentaBoleto();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmListaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }

        frm.setVisible(true);

    }//GEN-LAST:event_btnAgregarNuevaVentaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla3;
    private javax.swing.JToggleButton btnAgregarNuevaVenta;
    private javax.swing.JToggleButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtMonto_Total;
    private javax.swing.JTextField txtTotalBoleto;
    private javax.swing.JTextField txtfecha_Compra;
    private javax.swing.JTextField txtnum_Venta;
    // End of variables declaration//GEN-END:variables
}
