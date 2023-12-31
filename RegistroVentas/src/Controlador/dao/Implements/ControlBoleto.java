/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao.Implements;

import Controlador.*;
import Controlador.TDA.Listas.Exception.EmptyException;
import Controlador.TDA.Listas.ListaEnlazada;
import Controlador.Validacion.Utiles;
import Controlador.dao.DaoImplements;
import java.lang.reflect.Field;
import modelo.Boleto;
import modelo.Pasajero;

/**
 *
 * @author Estefania
 */
public class ControlBoleto extends DaoImplements<Boleto> {

    private ListaEnlazada<Boleto> boletos = new ListaEnlazada<>();
    private Boleto boleto = new Boleto();

    public ControlBoleto() {
        super(Boleto.class);
    }

    public ControlBoleto(Boleto boleto, Class<Boleto> clazz) {
        super(clazz);
        this.boleto = boleto;
    }
    public ListaEnlazada<Boleto> getBoletos() {
        if (boletos.isEmpty()) {
            boletos = this.all();
        }
        return boletos;
    }

    public void setBoletos(ListaEnlazada<Boleto> boletos) {
        this.boletos = boletos;
    }

    public Boleto getBoleto() {
        if (boleto == null) {
            boleto = new Boleto();
        }
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Boolean persist() {
        boleto.setId(all().getLength()+1);
        return persist(boleto);
    }

 
        public Double calcularTotal(Boleto boleto){
        Double total=0.0;
        total = boleto.getCantidad()* boleto.getValor();
        return total;
        
    } 

    // Metodo QuickSort
    public ListaEnlazada<Boleto> ordenarQuicksortB(ListaEnlazada<Boleto> lista, Integer tipo, String field) throws EmptyException, Exception {
        Boleto[] boletos = lista.toArray(); // Convierte la lista a un array de boletos

        Field attribute = Utiles.getField(Boleto.class, field); // Obtiene el campo de la clase Boleto
        if (attribute != null) { // Verifica si el campo existe
            quickSortB(boletos, 0, boletos.length - 1, tipo, field); // Llama al método para ordenar QuickSort
        } else {
            throw new Exception("El criterio de búsqueda no existe"); // Lanza una excepción si el campo no existe
        }

        return lista.toList(boletos); // Convierte el array ordenado de vuelta a ListaEnlazada y lo devuelve
    }

    // Implementación del algoritmo QuickSort para ordenar el array de boletos
    private void quickSortB(Boleto[] boletos, int izq, int der, Integer tipo, String field) {
        if (izq < der) { // Comprueba si el índice izquierdo es menor que el derecho
            int particionIndex = particion(boletos, izq, der, tipo, field); // Obtiene el índice de la partición
            quickSortB(boletos, izq, particionIndex - 1, tipo, field); // Ordena la mitad izquierda del array
            quickSortB(boletos, particionIndex + 1, der, tipo, field); // Ordena la mitad derecha del array
        }
    }

    // Método auxiliar para particionar el array durante el QuickSort
    private int particion(Boleto[] boletos, int izq, int der, Integer tipo, String field) {
        Boleto pivot = boletos[der]; // Selecciona el último elemento como pivote
        int i = izq - 1; // Inicializa el índice i

        for (int j = izq; j < der; j++) { // Recorre desde el índice izquierdo hasta el derecho - 1
            if (boletos[j].compare(pivot, field, tipo)) { // Compara usando el método compare
                i++; // Incrementa el índice i
                Boleto temp = boletos[i];
                boletos[i] = boletos[j]; // Intercambia boletos[i] con boletos[j]
                boletos[j] = temp;
            }
        }

        Boleto temp = boletos[i + 1];
        boletos[i + 1] = boletos[der]; // Intercambia boletos[i+1] con boletos[der]
        boletos[der] = temp;

        return i + 1; // Retorna el índice de la partición
    }
//metodo shell sort 
// Ordena la lista de boletos utilizando el algoritmo Shell Sort
public ListaEnlazada<Boleto> shellSortOrden(ListaEnlazada<Boleto> lista, Integer tipo, String field) throws EmptyException, Exception {
    Field attribute = Utiles.getField(Boleto.class, field); // Obtiene el campo de la clase Boleto
    Boleto[] boletos = lista.toArray(); // Convierte la lista a un array de boletos

    if (attribute == null) { // Verifica si el campo existe
        throw new Exception("El criterio de búsqueda no existe"); // Lanza una excepción si el campo no existe
    }

    int N = boletos.length; // Obtiene la longitud del array de boletos
    int salto = N / 2; // Define el tamaño inicial del salto 

    while (salto >= 1) { // Inicia el ciclo principal de Shell Sort
        for (int i = 0; i < salto; i++) { // Itera sobre cada sublista generada por el salto
            for (int j = salto + i; j < N; j += salto) { // iserta en cada sublista
                Boleto v = boletos[j]; // Guarda el valor en la posición j
                int n = j - salto; // Calcula el índice anterior dentro de la sublista

                // Realiza el intercambio entre elementos de la sublista si es necesario
                while (n >= 0 && boletos[n].compare(v, field, tipo)) {
                    boletos[n + salto] = boletos[n];
                    n -= salto;
                }

                boletos[n + salto] = v; // Inserta el valor en la posición adecuada
            }
        }
        salto /= 2; // Reduce el tamaño del salto a la mitad
    }

    lista.limpiar(); // Limpia la lista original
    for (Boleto boleto : boletos) {
        lista.add(boleto); //Añade los elementos ordenados a la lista original
    }
    return lista.toList(boletos); 
}


    public static void main(String[] args) {
        ControlBoleto controlBoleto = new ControlBoleto();

        try {
            ListaEnlazada<Boleto> listaBoletos = controlBoleto.getBoletos();

            // Ordenar la lista de pasajeros usando ShellSort
            ListaEnlazada<Boleto> listaOrdenada = controlBoleto.shellSortOrden(listaBoletos, 0, "id");

            // Mostrar la lista ordenada
            for (int i = 0; i < listaOrdenada.getLength(); i++) {
                Boleto boleto = listaOrdenada.getInfo(i);
                System.out.println("Nombre: " + boleto.getPasajero() + ", ID: " + boleto.getId());
            }
        } catch (EmptyException e) {
            e.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
        } catch (Exception ex) {
            ex.printStackTrace(); // Manejo de excepciones, reemplaza por la lógica adecuada
        }
    }
}
