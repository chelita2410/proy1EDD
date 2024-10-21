/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author chela
 */

/**{@code MiLista} es la clase creada para la lista de adyacencia
 * que se ocupar&aacute; de leer el grafo, las listas de adyacencia son m&aacute;s adecuadas
 * para trabajar con este tipo de datos.
 */
public class MiLista {
    private String[] data;
    private int size;
    private int capacity;
    
    /**
     * Constructor que inicializa la lista con una capacidad inicial.
     */
    public MiLista() {
        capacity = 10; //capacidad inicial de la lista
        data = new String[capacity];
        size = 0; //inicializa el tamaño a cero
    }
    
   /**
     * Añade un elemento a la lista.
     * @param value El valor a añadir.
     */
    public void add(String value) {
        if (size == capacity) {
            resize(); //redimensiona si es necesario.
        }
        data[size++] = value; //añade el valor y aumenta el tamaño.
    }
    
    /**
     * Obtiene el elemento en un &iacute;ndice espec&iacute;fico;.
     * @param index El &iacute;ndice del elemento a obtener.
     * @return El elemento en el &iacute;ndice especificado o {@code null} si este es
     * inv&aacute;lido.
     */
    public String get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }
    
    /**
     * Ajusta el tamaño de la lista cuando llega a su capacidad m&aacute;xima
     */
    private void resize() {
        capacity *= 2; //duplica la capacidad
        String[] newData = new String[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData; //actualiza la referencia del array.
    }
    
    /**
     * Obtiene el tamaño actual de la lista.
     * @return El n&uacute;mero de elementos en la lista.
     */
    public int size() {
        return size;
    }
    
   /**
    * Verifica si la lista contiene un elemento espec&iacute;fico.
    * @param value El valor a buscar en la lista.
    * @return {@code true} si el elemento se encuentra en la lista, {@code false}
    * en caso contrario.
    */
    public boolean contains(String value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }
}
