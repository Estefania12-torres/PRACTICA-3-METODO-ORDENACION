/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.dao;

import Controlador.TDA.Listas.ListaEnlazada;


/**
 *
 * @author Usuario
 */
public interface daoInterface <T> {
    public Boolean persist(T data);
    public Boolean merge (T data, Integer index);
    public ListaEnlazada<T> all();
    public T get(Integer id);
    
}
