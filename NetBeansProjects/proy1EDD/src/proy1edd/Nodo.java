/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author chela
 */
public class Nodo {
    private String name;
    private boolean tieneSucursal;

    public Nodo(String name) {
        this.name = name;
        this.tieneSucursal = false;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean tieneSucursal() {
        return tieneSucursal;
    }
    
    public void ponerSucursal() {
        this.tieneSucursal = true;
    }
    
    public void eliminarSucursal() {
        this.tieneSucursal = false;
    }
    
    
    
}
