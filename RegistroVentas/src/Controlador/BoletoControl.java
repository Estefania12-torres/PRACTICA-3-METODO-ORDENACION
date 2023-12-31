/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.TDA.Listas.ListaEnlazada;
import modelo.Boleto;
import modelo.Pasajero;
import modelo.Venta;

/**
 *
 * @author Usuario
 */
public class BoletoControl {
    //variables
    private Boleto boleto;//Objeto Boleto
    private Venta venta;
    private ListaEnlazada <Boleto> boletos ;//lista Objeto

    //constructor 
    public BoletoControl( ) {
        this.boletos = new ListaEnlazada<>();//inicializa
        this.boleto= new Boleto();
    }
    //obtener lista del Objeto Boleto
    public ListaEnlazada<Boleto> getBoletos() {
        return boletos;
    }
    //metodo asignar la lista Boleto
    public void setBoleto(ListaEnlazada<Boleto> boletos) {
        this.boletos = boletos;
    } 
    //guardar lista
    public Boolean guardar(){
        if(boleto !=null){
            boletos.add(boleto);
            return true;
        }
        return false;
    }
    //obtener objeto boleto
    public Boleto getBoleto() {
        if (boleto == null) {
            boleto = new Boleto();
        }
        return boleto;
    }
    //asignar Objeto Boleto
    public void setBoleto(Boleto boletos) {
        this.boleto = boletos;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
  


    
}
