/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * Clase que representa una cola (FIFO) que almacena elementos de tipo {@code String}.
 */
public class Cola {
    private String[] data;
    private int front; //&iacute;ndice primer elemento
    private int rear; //&iacute;ndice &uacute;ltimo elemento
    private int capacity; //capacidad m&aacute;xima de la cola
    private int size; //n&uacute;mero de elementos actuales en la cola
    
    
    /**
     * Constructor que inicializa la cola.
     * @param capacity Capacidad m&aacute;xima de la cola.
     */
    public Cola(int capacity) {
        this.capacity = capacity;
        data = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * Añade un elemento al final de la cola.
     * @param elemento El elemento a añadir.
     */
    public void añadirAlFinal(String elemento) {
        if (size == capacity) {
            resize();
        }
        rear = (rear + 1) % capacity;
        data[rear] = elemento;
        size++;
    }
    
    /**
     * Remueve y retorna el primer elemento de la cola.
     * @return El primer elemento de la cola, o {@code null} si la cola est&aacute; vac&iacute;a.
     */
    public String removerPrimero() {
        if(isEmpty()) {
            return null;
        }
        String elemento = data[front];
        front = (front + 1) % capacity;
        size--;
        return elemento;
    }
    
    /** 
     * Redimensiona la cola para duplicar su capacidad.
     */
    public void resize() {
        int newCapacity = 2 * capacity;
        String[] newData = new String[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }
    
    /** Verifica si la cola est&aacute; vac&iacute;a.
     * @return {@code true} si la cola est&aacute; vac&iacute;a, {@code false} en 
     * caso contrario.
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
