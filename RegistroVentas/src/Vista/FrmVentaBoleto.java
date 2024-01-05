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
import Vista.Tabla.ModeloTablaPasajero;
import static java.awt.SystemColor.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modelo.Boleto;
import modelo.Pasajero;
import modelo.Venta;

/**
 *
 * @author Usuario
 */
public class FrmVentaBoleto extends javax.swing.JFrame {

    /**
     * Creates new form FrmVentaBoleto
     */
    public FrmVentaBoleto() throws EmptyException {
        initComponents();
        limpiar();
               
        }

    private final ModeloTablaPasajero mtp = new ModeloTablaPasajero();
    private final ModeloTablaBoleto mtb = new ModeloTablaBoleto();
    private final ControlBoleto controlBoleto = new ControlBoleto();
    private final ControlPasajero controlPasajero = new ControlPasajero();
    //private final VentaControl vc = new VentaControl();
    private ControlVenta controlVenta = new ControlVenta();

    public void cargarPasajeros() {
        mtp.setPasajeros(controlPasajero.getPasajeros());
        Tabla1.setModel(mtp);
        Tabla1.updateUI();
    }

    public void cargarBoletos() {

        mtb.setBoletos(controlBoleto.getBoletos());
        Tabla2.setModel(mtb);
        Tabla2.updateUI();
    }

    private void cargarVista() {
        int fila = Tabla1.getSelectedRow();
        int fila1 = Tabla2.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                controlPasajero.setPasajero(mtp.getPasajeros().getInfo(fila));
                txtDni.setText(controlPasajero.getPasajero().getDni());
                txtDni.setEnabled(false);
                txtApellido.setText(controlPasajero.getPasajero().getApellido());
                txtNombre.setText(controlPasajero.getPasajero().getNombre());
                txtTelefono.setText(controlPasajero.getPasajero().getTelefono());

                controlBoleto.setBoleto(mtb.getBoletos().getInfo(fila1));
                txtLugar_Origen.setText(controlBoleto.getBoleto().getLugar_Origen());
                txtLugar_Destino.setText(controlBoleto.getBoleto().getLugar_Destino());
                txtnum_boleto.setText(controlBoleto.getBoleto().getNum_Boleto().toString());
                txtcantidad.setText(controlBoleto.getBoleto().getCantidad().toString());
                txtNum_Asiento.setText(controlBoleto.getBoleto().getNumero_Asiento().toString());

                //cbxRol.setSelectedIndex(PersonaControl.getPersona().getId_rol() - 1);
            } catch (Exception ex) {
                Logger.getLogger(FrmVentaBoleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Boolean verificar() {
        return (!txtApellido.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty()
                && !txtDni.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtvalor.getText().trim().isEmpty()
                && !txtLugar_Origen.getText().trim().isEmpty()
                && !txtLugar_Destino.getText().trim().isEmpty()
                && !txtnum_boleto.getText().trim().isEmpty()
                && !txtcantidad.getText().trim().isEmpty());
    }

    private void guardar() throws EmptyException {
        if (verificar()) {

            try {
                if (validadorDeCedula(txtDni.getText())) {
                    controlPasajero.getPasajero().setApellido(txtApellido.getText());
                    controlPasajero.getPasajero().setDni(txtDni.getText());
                    controlPasajero.getPasajero().setTelefono(txtTelefono.getText());
                    controlPasajero.getPasajero().setNombre(txtNombre.getText());
                    
                    controlBoleto.getBoleto().setNum_Boleto(Integer.parseInt(txtnum_boleto.getText()));
                    controlBoleto.getBoleto().setValor(Double.valueOf(txtvalor.getText()));
                    controlBoleto.getBoleto().setLugar_Destino(txtLugar_Destino.getText());
                    controlBoleto.getBoleto().setLugar_Origen(txtLugar_Origen.getText());
                    controlBoleto.getBoleto().setNumero_Asiento(Integer.parseInt(txtNum_Asiento.getText()));
                    controlBoleto.getBoleto().setCantidad(Integer.parseInt(txtcantidad.getText()));
                    controlBoleto.getBoleto().setPasajero(txtNombre.getText());
                    if (controlPasajero.persist() && controlBoleto.persist()) {
                        controlVenta.getVenta().setTotal_Boleto(calcularTotal());
                        controlVenta.persist();
                        controlVenta.persist(controlVenta.getVenta());
                        JOptionPane.showMessageDialog(null, "Datos guardados");
                        limpiar();

                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Double calcularTotal() {
        Double total = 0.0;
        total = controlBoleto.getBoleto().getValor() * controlBoleto.getBoleto().getCantidad();
        return total;
    }

    private void limpiar() {
        txtApellido.setText("");
        txtDni.setText("");
        txtTelefono.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtnum_boleto.setText("");
        txtLugar_Destino.setText("");
        txtLugar_Origen.setText("");
        txtvalor.setText("");
        txtcantidad.setText("");
        txtNum_Asiento.setText("");
        cargarPasajeros();
        cargarBoletos();
        Tabla1.clearSelection();
        Tabla2.clearSelection();

    }

    private void modificar() throws EmptyException {
        int fila = Tabla1.getSelectedRow();
        int fila2 = Tabla2.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                controlPasajero.getPasajero().setApellido(txtApellido.getText());
                controlPasajero.getPasajero().setDni(txtDni.getText());
                controlPasajero.getPasajero().setNombre(txtNombre.getText());
                controlPasajero.getPasajero().setTelefono(txtTelefono.getText());

                controlBoleto.getBoleto().setNum_Boleto(Integer.parseInt(txtnum_boleto.getText()));
                controlBoleto.getBoleto().setValor(Double.valueOf(txtvalor.getText()));
                controlBoleto.getBoleto().setLugar_Destino(txtLugar_Destino.getText());
                controlBoleto.getBoleto().setLugar_Origen(txtLugar_Origen.getText());
                controlBoleto.getBoleto().setNumero_Asiento(Integer.parseInt(txtNum_Asiento.getText()));
                controlBoleto.getBoleto().setCantidad(Integer.parseInt(txtcantidad.getText()));
                controlBoleto.getBoleto().setPasajero(txtNombre.getText());

                if (controlPasajero.merge(controlPasajero.getPasajero(), fila)) {
                    JOptionPane.showMessageDialog(null, "Datos modificados");
                    cargarPasajeros();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar, hubo un error");
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmVentaBoleto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método Ordenar Pasajeros por QuickSort
    private void ordenar() {
        // Obtiene el criterio de ordenamiento seleccionado y el tipo de orden (ascendente/descendente)
        String criterio = cbxCriterio.getSelectedItem().toString();
        Integer tipo = 0; // Por defecto, tipo ascendente
        String tipoOrden = cbxOrden.getSelectedItem().toString();
        // Verifica el tipo de orden seleccionado
        if (tipoOrden.equals("Descendente")) {
            tipo = 1; // Cambia el tipo a descendente
        }
        try {
            // Llama al método de ordenamiento y actualiza la tabla de pasajeros con los datos ordenados
            mtp.setPasajeros(controlPasajero.ordenarQuicksort(controlPasajero.getPasajeros(), tipo, criterio));
            Tabla1.setModel(mtp);
            Tabla1.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método para ordenar por shell Sort
    private void ordenarBoleto() {
        // Obtiene el criterio de ordenamiento seleccionado y el tipo de orden (ascendente/descendente)
        String criterio = cbxCriterio2.getSelectedItem().toString();
        Integer tipo = cbxOrden2.getSelectedIndex();

        try {
            // Llama al método de ordenamiento y actualiza la tabla de boletos con los datos ordenados
            mtb.setBoletos(controlBoleto.shellSortOrden(controlBoleto.getBoletos(), tipo, criterio));
            Tabla2.setModel(mtb);
            Tabla2.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtLugar_Origen = new javax.swing.JTextField();
        txtLugar_Destino = new javax.swing.JTextField();
        txtnum_boleto = new javax.swing.JTextField();
        txtNum_Asiento = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        txtvalor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        btnVerVentasBoleto = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JToggleButton();
        cbxOrden = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTextoBuscar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnOrdenar2 = new javax.swing.JToggleButton();
        btnBuscar2 = new javax.swing.JButton();
        txtTextoBuscar2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbxOrden2 = new javax.swing.JComboBox<>();
        cbxCriterio2 = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("VENTA DE BOLETOS ");

        jLabel3.setText("Nombres:");

        jLabel4.setText("Apellidos:");

        jLabel5.setText("DNI:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Lugar Origen:");

        jLabel8.setText("Lugar Destino:");

        jLabel9.setText("Numero de Boleto");

        jLabel10.setText("Numero de Asiento:");

        jLabel11.setText("Cantidad:");

        jLabel12.setText("Valor del Boleto:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });

        txtLugar_Origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLugar_OrigenActionPerformed(evt);
            }
        });

        txtnum_boleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnum_boletoActionPerformed(evt);
            }
        });

        txtNum_Asiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum_AsientoActionPerformed(evt);
            }
        });

        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });

        txtvalor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtvalorActionPerformed(evt);
            }
        });

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(Tabla1);

        btnVerVentasBoleto.setText("Ver Total de Ventas de Boletos");
        btnVerVentasBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentasBoletoActionPerformed(evt);
            }
        });

        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(Tabla2);

        jLabel2.setText("Datos Pasajero");

        jLabel14.setText("Datos Boletos");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        cbxOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        cbxOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenActionPerformed(evt);
            }
        });

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "id" }));
        cbxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioActionPerformed(evt);
            }
        });

        jLabel13.setText("Orden:");

        jLabel15.setText("Criterio:");

        jLabel16.setText("Texto:");

        jToggleButton1.setText("Buscar");

        btnOrdenar2.setText("Ordenar");
        btnOrdenar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenar2ActionPerformed(evt);
            }
        });

        btnBuscar2.setText("Buscar");

        txtTextoBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTextoBuscar2ActionPerformed(evt);
            }
        });

        jLabel17.setText("Texto:");

        jLabel18.setText("Orden:");

        jLabel19.setText("Criterio:");

        cbxOrden2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));

        cbxCriterio2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "pasajero", "num_Boleto", "lugar_Origen", "lugar_Destino" }));
        cbxCriterio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterio2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(390, 390, 390)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel3)
                                .addGap(52, 52, 52)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel4)
                                .addGap(69, 69, 69)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel5)
                                .addGap(85, 85, 85)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel6)
                                .addGap(69, 69, 69)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30)
                                .addComponent(txtLugar_Origen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel8)
                                .addGap(39, 39, 39)
                                .addComponent(txtLugar_Destino, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel9)
                                .addGap(6, 6, 6)
                                .addComponent(txtnum_boleto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(jLabel10)
                                .addGap(10, 10, 10)
                                .addComponent(txtNum_Asiento, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(14, 14, 14)
                                                .addComponent(cbxOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(20, 20, 20)
                                                .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65)
                                                .addComponent(btnOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(100, 100, 100)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(25, 25, 25)
                                        .addComponent(txtTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel17)))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTextoBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxOrden2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxCriterio2, 0, 141, Short.MAX_VALUE))
                                        .addGap(89, 89, 89)
                                        .addComponent(btnOrdenar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel11)
                                        .addGap(59, 59, 59)
                                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(btnSeleccionar)
                                .addGap(30, 30, 30)
                                .addComponent(btnModificar)
                                .addGap(20, 20, 20)
                                .addComponent(btnVerVentasBoleto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(376, 376, 376)
                                .addComponent(btnGuardar)
                                .addGap(26, 26, 26)
                                .addComponent(btnCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLugar_Origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLugar_Destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnum_boleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum_Asiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11))
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(cbxOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19))
                    .addComponent(cbxOrden2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenar)
                    .addComponent(jLabel18)
                    .addComponent(cbxCriterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenar2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtTextoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1)
                    .addComponent(jLabel17)
                    .addComponent(txtTextoBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnModificar)
                    .addComponent(btnVerVentasBoleto)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtvalorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtvalorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtvalorActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtLugar_OrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLugar_OrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLugar_OrigenActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmVentaBoleto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNum_AsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum_AsientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum_AsientoActionPerformed

    private void txtnum_boletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnum_boletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnum_boletoActionPerformed

    private void btnVerVentasBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentasBoletoActionPerformed
        
        try {
            FrmListaVenta frmListaVenta = new FrmListaVenta(controlVenta);
            frmListaVenta.setVisible(true);
        } catch (EmptyException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar la lista de ventas", "Error", JOptionPane.ERROR_MESSAGE);
            // Muestra detalles del error en la consola
        }


    }//GEN-LAST:event_btnVerVentasBoletoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        cargarVista();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            // TODO add your handling code here:
            modificar();
        } catch (EmptyException ex) {
            Logger.getLogger(FrmVentaBoleto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbxOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        // TODO add your handling code here:
        ordenar();
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void cbxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterioActionPerformed

    private void txtTextoBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTextoBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTextoBuscar2ActionPerformed

    private void btnOrdenar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenar2ActionPerformed
        // TODO add your handling code here:
        ordenarBoleto();
    }//GEN-LAST:event_btnOrdenar2ActionPerformed

    private void cbxCriterio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterio2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterio2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVentaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentaBoleto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmVentaBoleto().setVisible(true);
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmVentaBoleto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla1;
    private javax.swing.JTable Tabla2;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JToggleButton btnOrdenar2;
    private javax.swing.JToggleButton btnSeleccionar;
    private javax.swing.JButton btnVerVentasBoleto;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxCriterio2;
    private javax.swing.JComboBox<String> cbxOrden;
    private javax.swing.JComboBox<String> cbxOrden2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtLugar_Destino;
    private javax.swing.JTextField txtLugar_Origen;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNum_Asiento;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTextoBuscar;
    private javax.swing.JTextField txtTextoBuscar2;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtnum_boleto;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables

}
