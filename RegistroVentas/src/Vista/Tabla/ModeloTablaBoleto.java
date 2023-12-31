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
public class ModeloTablaBoleto extends AbstractTableModel{
    private ListaEnlazada <Boleto> boletos ;
  
    //get and set de la Lista del Objeto Boleto    
    public ListaEnlazada<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(ListaEnlazada<Boleto> boletos) {
        this.boletos = boletos;
    }
    
    //obtener cantidad de filas
    public int getRowCount() {
        return boletos.getLength();
    }
     
    //obtener el numero de columna
    public int getColumnCount() {
        return 8;
    }

    //Obtener el valor para una celda específica en la tabla
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            if (boletos == null || boletos.isEmpty()) {
                System.out.println("fallo");
                return null;
            }
            Boleto b = boletos.getInfo(rowIndex);

            return switch (columnIndex) {
                case 0 -> (b != null) ? b.getId(): "";
                case 1 -> (b != null) ? b.getNum_Boleto(): "";
                case 2 -> (b != null) ? b.getPasajero():"";
                case 3 -> (b != null) ? b.getNumero_Asiento(): "";
                case 4 -> (b != null) ? b.getLugar_Origen(): "";
                case 5 -> (b != null) ? b.getLugar_Destino(): "";
                case 6 -> (b != null) ? b.getCantidad(): "";
                case 7 -> (b != null) ? b.getValor(): "";
                
                 
                default -> null;
            };
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Obtener el nombre de la columna
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Numero Boleto";
            case 2:
                return "Usuario";
            case 3:
                return "Asiento";
            case 4:
                return "Lugar Origen";
            case 5:
                return "Lugar Destino";
            case 6:
                return "Cantidad";
            case 7:
                return "Valor";
            
            default:
                return null;
        }
    }

  
}

   
