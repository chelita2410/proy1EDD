/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * Estructura pilas.
 */
public class Stack {
    private String[] elements;
    private int top;
    private int maxSize;
    
    /** Constructor que crea una nueva pila del tamaño espeficado.
     * @param maxSize es el n&uacute;mero m&aacute;ximo de elementos que la pila puede contener.
     */
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.elements = new String[maxSize];
        this.top = -1;
    }
    
    /**Agrega un nuevo elemento en la parte superior de la pila.
     * @param element el elemento de tipo String que se añadir&aacute;
     * @return <code> true </code> si el elemento se añadi&oacute; correctamente; 
     * <code> false </code> si ls pila est&aacute; llena
     */
    public boolean push (String element) {
       if (top < maxSize - 1) {
           elements[++top] = element;
           return true;
       }
       return false;
    }
    
    /** Elimina y devuelve el elemento en la parte superior de la pila.
     * @return el elemento de tipo String que fue eliminado de la parte superior de la pila,
     * devuelve <code> null </code> si est&aacute; vac&iacute;.
     */
    
    public String pop() {
        if (top >= 0) {
            return elements[top--];
        }
        return null;
    }
    
    /** Devuelve el elemento en la parte superior de la pila sin eliminarlo.
     * @return el elemento de tipo String en la parte superior de la pila,
     * devuelve <code> null </code> si est&aacute; vac&iacute;.
     */
    public String peek() {
        if (top >= 0) {
            return elements[top];
        }
        return null;
    }
    /** Verifica si la pila est&aacute; vac&iacute;a.
     * @return el elemento de tipo String en la parte superior de la pila,
     * devuelve <code> null </code> si est&aacute; vac&iacute;.
     */
    
    public boolean isEmpty() {
        return top == -1;
    }
}

    

