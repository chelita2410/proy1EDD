/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;

/**
 *
 * @author valer
 */
public class CalcularDFS {
    public static MiLista calculadorDFS(GrafoTransporte grafo, String primeraParada) {
        Stack stack = new Stack(grafo.getContParadas());
        MiLista paradasVisitadas = new MiLista();
        boolean[] visitadas = new boolean[grafo.getContParadas()];
        int empezarIndice = grafo.encontrarIndiceParada(primeraParada);
        if (empezarIndice == -1) {
            return paradasVisitadas;
        }
        stack.push(primeraParada);
        visitadas[empezarIndice] = true;
        paradasVisitadas.add(primeraParada);
        
        while (!stack.isEmpty()) {
            String paradaActual = stack.peek();
            int indiceActual = grafo.encontrarIndiceParada(paradaActual);
            boolean tieneVecinoNoVisitado = false;
            MiLista adyList = grafo.getAdyList(indiceActual);
            
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
            if (!tieneVecinoNoVisitado) {
                stack.pop();
            }
        }
        return paradasVisitadas;
    }
}
