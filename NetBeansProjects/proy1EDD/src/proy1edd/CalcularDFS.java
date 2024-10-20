/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 * Clase que implementa el algoritmo de b&uacute;squeda en profundidad (DFS)
 * para calcular las paradas visitadas en el grafo de transporte.
 * 
 */
public class CalcularDFS {
    /** Realiza una b&uacute;squeda en profundidad para encontrar las paradas alcanzables
     * desde una parada inicial en el grafo.
     * @param grafo el grafo de transporte que contiene las paradas y sus conexiones.
     * @param primeraParada el nombre de la parada inicial desde donde comienza el DFS.
     * @return una lista de paradas en el orden que fueron alcanzadas.
     */
    public static MiLista calculadorDFS(GrafoTransporte grafo, String primeraParada) {
        Stack stack = new Stack(grafo.getContParadas());
        MiLista paradasVisitadas = new MiLista();
        boolean[] visitadas = new boolean[grafo.getContParadas()];
        int empezarIndice = grafo.encontrarIndiceParada(primeraParada);
        if (empezarIndice == -1) {
            return paradasVisitadas;
        }
        //Inicializar DFS
        stack.push(primeraParada);
        visitadas[empezarIndice] = true;
        paradasVisitadas.add(primeraParada);
        
        //Ejecutar DFS
        while (!stack.isEmpty()) {
            String paradaActual = stack.peek();
            int indiceActual = grafo.encontrarIndiceParada(paradaActual);
            boolean tieneVecinoNoVisitado = false;
            MiLista adyList = grafo.getAdyList(indiceActual);
            
            
            //Verificar paradas no visitadas
            for (int i = 0; i < adyList.size(); i++) {
                String vecino = adyList.get(i);
                int indiceVecino = grafo.encontrarIndiceParada(vecino);
                if (vecino != null && !visitadas[indiceVecino]) {
                    stack.push(vecino);
                    visitadas[indiceVecino] = true;
                    paradasVisitadas.add(vecino);
                    tieneVecinoNoVisitado = true;
                    break;
                }
            }
            
            //Si no tiene vecinos no visitados, retroceder en la pila.
            if (!tieneVecinoNoVisitado) {
                stack.pop();
            }
        }
        return paradasVisitadas;
    }
}
