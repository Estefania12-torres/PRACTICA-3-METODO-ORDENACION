/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Tabla;

import Controlador.TDA.Listas.Exception.EmptyException;
import Controlador.TDA.Listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Boleto;

import modelo.Venta;

/**
 *
 * @author Estefania
 */
public class ModeloTablaVentas extends AbstractTableModel {
    
    private ListaEnlazada <Venta> ventas ;

    public ListaEnlazada<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }
    
    @Override
    public int getRowCount() {
        return ventas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            if (ventas == null || ventas.isEmpty()) {
                System.out.println("fallo");
                return null;
            }
           Venta v = ventas.getInfo(rowIndex);

            return switch (columnIndex) {
                case 0 -> (v != null) ? v.getId(): "";
                case 1 ->(v != null) ? v.getNum_Venta(): "";
                case 2 -> (v != null) ? v.getId_pasajero(): "";
                case 3 -> (v != null) ? v.getFecha(): "";
                case 4 -> (v != null) ? v.getTotal_Boleto(): "";
                default -> null;
            };
        }
        catch (EmptyException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "id";
            case 1 -> "Numero Venta";
            case 2 -> "Usuario";
            case 3 -> "Fecha";
            case 4 -> "Total Boleto";
            default -> null;
        };
    }
    
    }
    
    
    
    
    
