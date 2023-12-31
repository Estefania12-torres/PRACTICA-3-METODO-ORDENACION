/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Tabla;

import Controlador.TDA.Listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Boleto;
import modelo.Pasajero;


/**
 *
 * @author Estefania
 */
public class ModeloTablaPasajero extends AbstractTableModel{
    //Lista del Objeto Pasajeros
    private ListaEnlazada<Pasajero> pasajeros;
  
    //get and set
    public ListaEnlazada<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ListaEnlazada<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }
     //obtener filas de la tabla
    public int getRowCount() {
        return pasajeros.getLength();
    }
     
    //obtener la cantidad de columnas en la tabla
    public int getColumnCount() {
        return 4;
    }

    //Obtener el valor para una celda específica en la tabl
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            if (pasajeros == null || pasajeros.isEmpty()) {
                System.out.println("fallo");
                return null;
            }
            Pasajero p = pasajeros.getInfo(rowIndex);

            return switch (columnIndex) {
                case 0 -> (p != null) ? p.getId(): " ";
                case 1 -> (p != null) ? p.getDni() : " ";
                case 2 -> (p != null) ? p.getApellido() + " " + p.getNombre() : "";
                case 3 -> (p != null) ? p.getTelefono() : "";
                default -> null;
            };
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //obtener el nombre de la columna
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Dni";
            case 2:
                return "Usuario";
            case 3:
                return "Telefono";
            default:
                return null;
        }
    }


}
