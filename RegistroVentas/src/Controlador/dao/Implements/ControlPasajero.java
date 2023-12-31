/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao.Implements;

import Controlador.TDA.Listas.Exception.EmptyException;
import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.Validacion.Utiles;
import Controlador.dao.DaoImplements;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import modelo.Pasajero;

/**
 *
 * @author Estefania
 *
 */
public class ControlPasajero extends DaoImplements<Pasajero> {

    private Pasajero pasajero = new Pasajero();
    private ListaEnlazada<Pasajero> pasajeros = new ListaEnlazada<>();

    public ControlPasajero() {
        super(Pasajero.class);
    }

    public ControlPasajero(Pasajero pasajero, Class<Pasajero> clazz) {
        super(clazz);
        this.pasajero = pasajero;
    }

    public ListaEnlazada<Pasajero> getPasajeros() {
        if (pasajeros.isEmpty()) {
            pasajeros = this.all();
        }
        return pasajeros;

    }

    public void setPasajeros(ListaEnlazada<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Boolean guardar() {
        if (pasajero != null) {
            pasajeros.add(pasajero);
            pasajero = null;
            return true;
        }
        return false;
    }

    public Pasajero getPasajero() {
        if (pasajero == null) {
            pasajero = new Pasajero();
        }
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Boolean persist() {
        pasajero.setId(all().getLength()+1);
        return persist(pasajero);
    }


    public ListaEnlazada<Pasajero> ordenarQuicksort(ListaEnlazada<Pasajero> lista, Integer tipo, String field) throws EmptyException, Exception {
        // Obtener la lista de pasajeros como un array
        Pasajero[] pasajeros = lista.toArray();

        // Verificar el campo de atributo
        Field attribute = Utiles.getField(Pasajero.class, field);

        if (attribute != null) {//Comprueba si se encontró el campo
            // Llamar a la función quickSort
            quickSort(pasajeros, 0, pasajeros.length - 1, tipo, field);
        } else {
            throw new Exception("El criterio de búsqueda no existe");//Lanza una excepción si no se encontró el campo
        }
        // Devolver una nueva lista enlazada a partir del arreglo ordenado
        return lista.toList(pasajeros);
    }

private void quickSort(Pasajero[] pasajeros, int izq, int der, Integer tipo, String field) {
    if (izq < der) { // Verifica si el índice izquierdo es menor que el derecho 
        int particionIndex = particion(pasajeros, izq, der, tipo, field); // Obtiene el índice de la partición
        // Llama recursivamente a quickSort para las submatrices izquierda y derecha de la partición
        quickSort(pasajeros, izq, particionIndex - 1, tipo, field);
        quickSort(pasajeros, particionIndex + 1, der, tipo, field);
    }
}

private int particion(Pasajero[] pasajeros, int izq, int der, Integer tipo, String field) {
    Pasajero pivot = pasajeros[der]; // Elige el último elemento del array como pivote
    int i = izq - 1; // Inicializa el índice de la partición (i) una posición antes del primer elemento

    // Recorre el array desde el índice izquierdo hasta el penúltimo elemento
    for (int j = izq; j < der; j++) {
        if (pasajeros[j].compare(pivot, field, tipo)) { // Compara el elemento actual con el pivote usando el método 'compare'
            i++; // Incrementa el índice de la partición
            // Intercambia los elementos en las posiciones 'i' y 'j' del array
            Pasajero temp = pasajeros[i];
            pasajeros[i] = pasajeros[j];
            pasajeros[j] = temp;
        }
    }
    // Intercambia el pivote con el elemento en la posición siguiente al índice de la partición
    Pasajero temp = pasajeros[i + 1];
    pasajeros[i + 1] = pasajeros[der];
    pasajeros[der] = temp;

    return i + 1; // Retorna el índice del pivote después de la partición
}


public static void main(String[] args) {
    ControlPasajero controlPasajero = new ControlPasajero();

    try {
        ListaEnlazada<Pasajero> listaPasajeros = controlPasajero.getPasajeros();

        // Ordenar la lista de pasajeros usando QuickSort
        ListaEnlazada<Pasajero> listaOrdenada = controlPasajero.ordenarQuicksort(listaPasajeros, 0, "nombre");

        // Mostrar la lista ordenada
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            Pasajero pasajero = listaOrdenada.getInfo(i);
            System.out.println("Nombre: " + pasajero.getNombre() + ", ID: " + pasajero.getId());
        }
    } catch (EmptyException e) {
        e.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
    } catch (Exception ex) {
        ex.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
    }
    
}

}
