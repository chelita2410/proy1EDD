package proy1edd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chela
 */
public class GrafoTransporte {
    private String[] paradas;
    private MiLista[] adyList; 
    private int contParadas;
    private int capacity;
    
    //Constructor
    public GrafoTransporte() {
        capacity = 100; //capacidad inicial de paradas
        paradas = new String[capacity];
        adyList = new MiLista[capacity];
        contParadas = 0;
    }
    
    //Añadir una parada al grafo
    public void añadirParada(String parada) {
        if (contParadas == capacity) {
            resizeGraph();
        }
        paradas[contParadas] = parada;
        adyList[contParadas] = new MiLista();
        contParadas++;
    }
    
    //Encontrar el índice de una parada específica
    public int encontrarIndiceParada(String parada) {
        for (int i = 0; i < contParadas; i++) {
            if (paradas[i].equals(parada)) {
                return i;
            }
        }
        return -1;
    }
    
    //Retorna la lista de adyacencias de una parada en un índice específico
    public MiLista getAdyList(int indice) {
        if (indice >= 0 && indice < contParadas) {
            return adyList[indice]; //Retorna la lista de adyacencias para la parada en un índice específico
        } else {
            return null;
        }
    }
    
    //Retorna el número actual de paradas en el grafo
    public int getContParadas() {
        return contParadas;
    }
    
    public String getStop(int indice) {
        if (indice >= 0 && indice < contParadas) {
            return paradas[indice]; 
        } else {
            return null;
        }
    }
    
    //Añade arista entre dos paradas
    public void añadirArista(String parada1, String parada2) {
        int indice1 = encontrarIndiceParada(parada1);
        int indice2 = encontrarIndiceParada(parada2);
        
        if (indice1 == -1) {
            añadirParada(parada1);
            indice1 = contParadas -1;
        }
        if (indice2 == -1) {
            añadirParada(parada2);
            indice2 = contParadas -1;
        }
        
        adyList[indice1].add(parada2);
        adyList[indice2].add(parada1);
    }
    
    
    //Cambia el tamaño del grafo cuando este llega a su capacidad máxima
    private void resizeGraph() {
        capacity *= 2;
        String[] nuevaParada = new String[capacity];
        MiLista[] nuevaAdyList = new MiLista[capacity];
        
        for (int i = 0; i < contParadas; i++) {
            nuevaParada[i] = paradas[i];
            nuevaAdyList[i] = adyList[i];
        }
        paradas = nuevaParada;
        adyList = nuevaAdyList;
    }
    
}
