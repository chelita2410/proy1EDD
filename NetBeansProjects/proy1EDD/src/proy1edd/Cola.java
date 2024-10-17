/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author chela
 */
public class Cola {
    private String[] data;
    private int front; //índice primer elemento
    private int rear; //índice último elemento
    private int capacity; //capacidad máxima de la cola
    private int size; //número de elementos actuales en la cola
    
    public Cola(int capacity) {
        this.capacity = capacity;
        data = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    public void añadirAlFinal(String elemento) {
        if (size == capacity) {
            resize();
        }
        rear = (rear + 1) % capacity;
        data[rear] = elemento;
        size++;
    }
    
    
    public String removerPrimero() {
        if(isEmpty()) {
            return null;
        }
        String elemento = data[front];
        front = (front + 1) % capacity;
        size--;
        return elemento;
    }
    
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
    
    public boolean isEmpty() {
        return size == 0;
    }
}
