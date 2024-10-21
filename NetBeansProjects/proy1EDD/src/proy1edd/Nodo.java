/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * Clase que representa un nodo en el grafo de la red de transporte.
 * Cada nodo tiene un nombre que indica si tiene o no una sucursal.
 */
public class Nodo {
    private String name;
    private boolean tieneSucursal;

    /**
     * Constructor que inicializa un nodo con un nombre especificado.
     * @param name El nombre del nodo.
     */
    public Nodo(String name) {
        this.name = name;
        this.tieneSucursal = false;
    }
    
    /**
     * Obtiene el nombre del nodo.
     * @return El nombre del nodo.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Verifica si el nodo tiene una sucursal.
     * @return {@code true} si el nodo tiene sucursal, {@code false} en caso contrario.
     */
    public boolean tieneSucursal() {
        return tieneSucursal;
    }
    
    /**
     * Marca el nodo como una sucursal.
     */
    public void ponerSucursal() {
        this.tieneSucursal = true;
    }
    
    /**
     * Elimina la marca de sucursal del nodo.
     */
    public void eliminarSucursal() {
        this.tieneSucursal = false; //Establece que el nodo no tiene sucursal.
    }
    
    
    
}
