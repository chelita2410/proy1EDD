/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 * Clase que implementa el algoritmo de b&uacute;queda en anchura (BFS)
 * para calcular las paradas cubiertas en el grafo de transporte.
 */
public class CalcularBFS {
   /**
    *Calcula las paradas alcanzables dentro de una distancia m&aacure;xima desde
    * una parada inicial en el grafo del transporte.
    * @param grafo el grafo que contiene las paradas y sus conexiones.
    * @param paradaSucursal el nombre de la parada inicial desde donde comienza el BFS.
    * @param t la distancia m&aacute;xima permitida para cubrir las paradas.
    * @return una lista de paradas cubiertas dentro de la distancia t desde la parada inicial.
    */
    public static MiLista calculadorBFS(GrafoTransporte grafo, String paradaSucursal, int t) {
        MiLista paradasCubiertas = new MiLista();
        boolean[] visitadas = new boolean[grafo.getContParadas()];
        int[] distancia = new int[grafo.getContParadas()];
        Cola cola = new Cola(grafo.getContParadas());
        
        int empezarIndice = grafo.encontrarIndiceParada(paradaSucursal);
        if (empezarIndice == -1) {
            return paradasCubiertas; 
        }
        //Inicializar BFS
        cola.añadirAlFinal(paradaSucursal);
        visitadas[empezarIndice] = true;
        distancia[empezarIndice] = 0;
        
        //Ejecutar el algoritmo BFS
        while (!cola.isEmpty()) {
            String paradaActual = cola.removerPrimero();
            int indiceActual = grafo.encontrarIndiceParada(paradaActual);
            
            //Añade la parada a la lista cubierta si está dentro de la distancia t
            if (distancia[indiceActual] <= t) {
                paradasCubiertas.add(paradaActual);
                System.out.println("adding covered stop to list " + paradaActual);
            }
            //Ver paradas adyacentes
            for (int i = 0; i < grafo.getAdyList(indiceActual).size(); i++) {
                String vecino = grafo.getAdyList(indiceActual).get(i);
                int indiceVecino = grafo.encontrarIndiceParada(vecino);
                
                if (!visitadas[indiceVecino] && distancia[indiceActual] + 1 <= t) {
                    visitadas[indiceVecino] = true;
                    distancia[indiceVecino] = distancia[indiceActual] + 1;
                    cola.añadirAlFinal(vecino);
                }
            }
        }
        return paradasCubiertas;
    }
    
    
}
