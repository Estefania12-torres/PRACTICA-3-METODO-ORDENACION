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
public class Boleto {
    //atributos 
    private Integer id;
    private Double valor;
    private Integer numero_Asiento;
    private Date fecha_Compra;
    private Date fecha_Salida;
    private String lugar_Origen;
    private String lugar_Destino;
    private Integer num_boleto;
    private Boolean estado;
    private Integer num_Bus;
    private Integer cantidad;
    private Integer id_pasajero;
    private String pasajero;
    
    // getters y setters
    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

   
    
    public Integer getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(Integer id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getNumero_Asiento() {
        return numero_Asiento;
    }

    public void setNumero_Asiento(Integer numero_Asiento) {
        this.numero_Asiento = numero_Asiento;
    }

    public Date getFecha_Compra() {
        return fecha_Compra;
    }

    public void setFecha_Compra(Date fecha_Compra) {
        this.fecha_Compra = fecha_Compra;
    }

    public Date getFecha_Salida() {
        return fecha_Salida;
    }

    public void setFecha_Salida(Date fecha_Salida) {
        this.fecha_Salida = fecha_Salida;
    }

    public String getLugar_Origen() {
        return lugar_Origen;
    }

    public void setLugar_Origen(String lugar_Origen) {
        this.lugar_Origen = lugar_Origen;
    }

    public String getLugar_Destino() {
        return lugar_Destino;
    }

    public void setLugar_Destino(String lugar_Destino) {
        this.lugar_Destino = lugar_Destino;
    }

    public Integer getNum_Boleto() {
        return num_boleto;
    }

    public void setNum_Boleto(Integer num_boleto) {
        this.num_boleto = num_boleto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getNum_boleto() {
        return num_boleto;
    }

    public void setNum_boleto(Integer num_boleto) {
        this.num_boleto = num_boleto;
    }

    public Integer getNum_Bus() {
        return num_Bus;
    }

    public void setNum_Bus(Integer num_Bus) {
        this.num_Bus = num_Bus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //metodo para comparar los boletos ,el orden ascendente y descendente
    public Boolean compare(Boleto otroBoleto, String field, Integer tipo) {
    switch (field.toLowerCase()) {
        case "pasajero":
            if (tipo == 0) {  // Retorna verdadero si este pasajero es menor (ascendente) o mayor (descendente);
                return this.getPasajero().toLowerCase().compareTo(otroBoleto.getPasajero().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getPasajero().toLowerCase().compareTo(otroBoleto.getPasajero().toLowerCase()) > 0;
            }
            break;
        case "num_boleto":
            if (tipo == 0) {
                return this.getNum_Boleto().intValue() < otroBoleto.getNum_Boleto().intValue();
            } else if (tipo == 1) {
                return this.getNum_Boleto().intValue() > otroBoleto.getNum_Boleto().intValue();
            }
        case "id":
            if (tipo == 0) {
                return this.getId().intValue() < otroBoleto.getId().intValue();
            } else if (tipo == 1) {
                return this.getId().intValue() > otroBoleto.getId().intValue();
            }
        case "lugar_origen":
            if (tipo == 0) {
                return this.getLugar_Origen().toLowerCase().compareTo(otroBoleto.getLugar_Origen().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getLugar_Origen().toLowerCase().compareTo(otroBoleto.getLugar_Origen().toLowerCase()) > 0;
            }
        case "lugar_destino":
            if (tipo == 0) {
                return this.getLugar_Destino().toLowerCase().compareTo(otroBoleto.getLugar_Destino().toLowerCase()) < 0;
            } else if (tipo == 1) {
                return this.getLugar_Destino().toLowerCase().compareTo(otroBoleto.getLugar_Destino().toLowerCase()) > 0;
            }
            
            break;
        default:
            break;
    }
    return false; // Valor predeterminado si la comparación no se realiza correctamente
}

    public String toString() {
        return "id: " + id +"pasajero: " + pasajero + ", num_Boleto: " + num_boleto ;
    }

 
    
}
