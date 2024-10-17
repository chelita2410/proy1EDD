/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author valer
 */
public class Stack {
    private String[] elements;
    private int top;
    private int maxSize;
    
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.elements = new String[maxSize];
        this.top = -1;
    }
    
    public boolean push (String element) {
       if (top < maxSize - 1) {
           elements[++top] = element;
           return true;
       }
       return false;
    }
    
    public String pop() {
        if (top >= 0) {
            return elements[top--];
        }
        return null;
    }
    
    public String peek() {
        if (top >= 0) {
            return elements[top];
        }
        return null;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
}

    

