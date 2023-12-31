/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.TDA.Listas.ListaEnlazada;
import modelo.Boleto;
import modelo.Venta;

/**
 *
 * @author Usuario
 */
public class VentaControl {
    //variables
    private Venta venta;
    private ListaEnlazada <Venta> ventas ;
    //constructor
    public VentaControl( ) {
        this.ventas = new ListaEnlazada<>();
        this.venta= new Venta();
    }
    //Get and set Lista Objeto Ventas
    public ListaEnlazada<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }
    //guardar   
    public Boolean guardar(){
        if(venta !=null){
            ventas.add(venta);
            return true;
        }
        return false;
    }
    
    //get and set Objeto Venta
    public Venta getVenta() {
         if (venta == null) {
            venta = new Venta();
        }
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
      
}
