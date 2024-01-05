/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Venta {
    private Integer id;
    private String fecha;
    private Double total_Boleto;
    private String num_Venta;
    private Integer id_pasajero;
    private Pasajero pasajero;
    private Boleto boleto;
    private Double Monto_Total;
    

    public Double getMonto_Total() {
        return Monto_Total;
    }

    public void setMonto_Total(Double Monto_Total) {
        this.Monto_Total = Monto_Total;
    }
   
    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Double getTotal_Boleto() {
        return total_Boleto;
    }

    public void setTotal_Boleto(Double total_Boleto) {
        this.total_Boleto = total_Boleto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getNum_Venta() {
        return num_Venta;
    }

    public void setNum_Venta(String num_Venta) {
        this.num_Venta = num_Venta;
    }

    public Integer getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(Integer id_pasajero) {
        this.id_pasajero = id_pasajero;
    }
    
    
        
}
