/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author chela
 */

/**</code> MiLista </code> es la clase creada para la lista de adyacencia
 * que se ocupará de leer el grafo, las listas de adyacencia son más adecuadas
 * para trabajar con este tipo de datos
 */
public class MiLista {
    private String[] data;
    private int size;
    private int capacity;
    
    public MiLista() {
        capacity = 10;
        data = new String[capacity];
        size = 0;
    }
    
    //Añade elementos a la lista
    public void add(String value) {
        if (size == capacity) {
            resize();
        }
        data[size++] = value;
    }
    
    //Obtiene elemento en un índice específico
    public String get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }
    
    //Ajusta el tamaño de la lista cuando llega a su capacidad máxima
    private void resize() {
        capacity *= 2;
        String[] newData = new String[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    
    //Obtiene el tamaño actual de la lista
    public int size() {
        return size;
    }
}
