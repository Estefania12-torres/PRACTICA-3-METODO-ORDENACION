/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao.Implements;

import Controlador.*;
import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.dao.DaoImplements;

import modelo.Venta;

/**
 *
 * @author Usuario
 */
public class ControlVenta extends DaoImplements<Venta>{
    private ListaEnlazada<Venta> ventas=new ListaEnlazada<>();
    private Venta venta=new Venta();
    
     public ControlVenta() {
        super(  Venta.class);
    }
    public ControlVenta(Class<Venta> clazz) {
        super(clazz);
        this.venta = venta;

    }

    public ListaEnlazada<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }

    public Venta getVenta() {
        if (venta == null)
            venta = new Venta();
        
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
       public Boolean persist(){
        venta.setId(all().getLength()+1);
        return persist(venta);
    }
       
    
       
}
       
    
    
    
    

