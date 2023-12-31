/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.TDA.Listas;

/**
 *
 * @author Usuario
 */
public class Node <E> {
    //Declaracion de variables
    private E info;
    private Node <E> next;

    //constructor
    public Node(E info) {
        this.info=info;//asignar valor al nodo
        next= null; //inicializa con 'next' apuntando a null.                   
    }
    
    //constructor 
    public Node(E info, Node<E> next) {
        this.info= info;
        this.next = next;
    }
    
    public Node() {
        next = next;
        info= info;
    }
    
    //getters and setters
//obtener o acceder 
    public E getInfo() {
        return info;
    }
    //asignar
    public void setInfo(E info) {
        this.info = info;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    
}
