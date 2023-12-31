/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Controlador.*;
import Controlador.TDA.Listas.ListaEnlazada;
import modelo.Boleto;
import modelo.Pasajero;

/**
 *
 * @author Estefania Torres 
 */
public class PasajeroControl {
    //variables 
    private Pasajero pasajero;
    private ListaEnlazada <Pasajero> pasajeros ;
    
    //constructor 
    public PasajeroControl( ) {
        this.pasajeros = new ListaEnlazada<>();//inicializacion lista y objeto
        this.pasajero= new Pasajero();
    }
    //getters and setter de la lista 
    //obtener Lista del Objeto Pasajeros
    public ListaEnlazada<Pasajero> getPasajeros() {
        return pasajeros;
    }
    //asignar la lista Pasajeros
    public void setPasajeros(ListaEnlazada<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    } 
    //guardar
    public Boolean guardar(){
        if(pasajero !=null){
            pasajeros.add(pasajero);
            return true;
        }
        return false;
    }
    //obtener objeto pasajero
    public Pasajero getPasajero() {
        if (pasajero == null) {
            pasajero = new Pasajero();
        }
        return pasajero;
    }
    //asignar Objeto pasajero
    public void setPasajero(Pasajero pasajeros) {
        this.pasajero = pasajeros;
    }

   
    
    
}
