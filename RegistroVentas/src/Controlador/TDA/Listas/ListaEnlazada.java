/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Listas;

import Controlador.TDA.Listas.Exception.EmptyException;
import modelo.Pasajero;

/**
 *
 * @author Estefania
 */
public class ListaEnlazada<E> {

    //declaracion de variable
    private Node<E> header;//cabecera
    private Node<E> last;//ultimo
    private Integer length;//longitud 

    //constructor 
    public ListaEnlazada() {
        header = null;//inicializa como valor nulo
        last = null;
        length = 0;//vacio
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    //verificar si la lista esta vacia
    public Boolean isEmpty() {
        return (header == null || length.intValue() == 0);
    }

    //añadir un nodo al inicio
    public void addHeader(E info) {
        Node<E> help;//aux
        //si esta vacia,se crea el nodo y se establece en cabecera y en el nodo ultimo
        if (isEmpty()) {
            help = new Node<>(info);
            header = help;
            last = help;//
            length++;//Incrementa longitud
        } else {//si no esta vacia,crea nodo info y lo enlaza con el nodo actual 
            Node<E> headHelp = header;
            help = new Node<>(info, headHelp);
            header = help;
            length++;
        }
    }

    //metodo para anadir el nodo final 
    public void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            addHeader(info);//se agrega como encabezado si esta vacio
        } else {
            help = new Node<>(info, null);//crea nuevo 
            last.setNext(help);//se establece en el siguiente del ultimo nodo como nuevo
            last = help;//actualiza
            length++;//incrementa
        }
    }

    public void add(E info) {
        addLast(info);//añade un elemento

    }

    public E getInfo(Integer index) throws EmptyException {
        return getNode(index).getInfo();
    }

    private Node<E> getNode(Integer index) throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException("Error. Lista vacia");
        } else if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Error. Fuera de rango");
        } else if (index == 0) {
            return header;
        } else if (index == (length - 1)) {
            return last;
        } else {
            Node<E> search = header;
            Integer cont = 0;
            while (cont < index) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    /*public E merge(E data, Integer index) throws EmptyException, IndexOutOfBoundsException {
        Node<E> search = getNode(index);
        E help = search.getInfo();
        search.setInfo(data);
        return help;
    }*/
    public void merge(E data, Integer index) throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException("Error, la lista esta vacia");
        } else if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Error, esta fuera del limite de la lista");
        } else if (index == 0) {
            header.setInfo(data);
        } else if (index == (length - 1)) {
            last.setInfo(data);
        } else {
            Node<E> search = header;
            Integer cont = 0;
            while (cont < index) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(data);
        }
    }

    public E extractFirst() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException("Lista vacia");
        } else {
            E element = header.getInfo();
            Node<E> help = header.getNext();
            header = null;
            header = help;
            if (length == 1) {
                last = null;
            }
            length--;
            return element;
        }
    }

    public E extractLast() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException("Lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> help = getNode(length - 2);
            if (help == null) {
                last = null;
                if (length == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = help;
                last.setNext(null);
            }
            length--;
            return element;
        }
    }

    /**
     * Extrae y devuelve el elemento en la posición especificada en la lista
     * enlazada.
     *
     * @param index El índice del elemento a extraer.
     * @return El elemento extraído de la lista.
     * @throws EmptyException Si la lista está vacía.
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango.
     */
    public E extract(Integer index) throws EmptyException, IndexOutOfBoundsException {
        if (isEmpty()) { // Verifica si la lista está vacía
            throw new EmptyException("Error. Lista vacia");
        } else if (index.intValue() < 0 || index.intValue() >= length) { // Verifica si el índice está dentro del rango válido
            throw new IndexOutOfBoundsException("Error. Fuera de rango");
        } else if (index == 0) { // Si el índice es el primero, llama a extractFirst()
            return extractFirst();
        } else if (index == (length - 1)) { // Si el índice es el último, llama a extractLast()
            return extractLast();
        } else {
            // Obtiene los nodos y el elemento en la posición index
            Node<E> node_preview = getNode(index - 1);
            Node<E> node_actually = getNode(index);
            E info = node_actually.getInfo();
            Node<E> help_next = node_actually.getNext();
            node_actually = null; // Elimina la referencia al nodo
            node_preview.setNext(help_next); // Actualiza los enlaces
            length--; // Reduce la longitud de la lista
            return info; // Retorna el elemento extraído
        }
    }

    /**
     * Convierte la lista enlazada en array.
     * @return Un array con los elementos de la lista enlazada.
     */
    public E[] toArray() {
        Class clazz = null;
        E[] matriz = null;
        if (length > 0) {
            clazz = header.getInfo().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, length);
            Node<E> aux = header;
            for (int i = 0; i < length; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matriz;
    }

    /**
     * Convierte un array en una lista enlazada.
     *
     * @param m El array a convertir en una lista enlazada.
     * @return La lista enlazada generada a partir del array.
     */
    public ListaEnlazada<E> toList(E[] m) {
        reset(); // Reinicia la lista para agregar nuevos elementos
        for (int i = 0; i < m.length; i++) {
            this.add(m[i]); // Agrega cada elemento del array a la lista
        }
        return this; // Retorna la lista enlazada resultante
    }

    /**
     * Reinicia la lista enlazada
     */
    public void reset() {
        header = null;
        last = null; 
        length = 0;
    }

    /**
     * Devuelve una representación en formato de cadena de la lista enlazada.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista Data\n");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append("\n"); // Agrega cada elemento de la lista al StringBuilder
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage()); // En caso de error, agrega el mensaje de excepción al StringBuilder
        }
        return sb.toString(); // Retorna la representación en cadena de la lista
    }

    /**
     * Elimina todos los elementos de la lista enlazada.
     */
    public void limpiar() {
        header = null;
        last = null; 
        length = 0; 
    }

}
