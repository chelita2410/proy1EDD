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
    private MiLista[] adyList; //Lista de adyacencias
    private int contParadas;
    private int capacity;
    private boolean[] sucursales; //Array para marcar qué paradas tienen sucursales
    
    //Constructor
    public GrafoTransporte() {
        capacity = 100; //capacidad inicial de paradas
        paradas = new String[capacity];
        adyList = new MiLista[capacity];
        sucursales = new boolean[capacity]; // inicializa el array de sucursales
        contParadas = 0;
    }
    
    //Añadir una parada al grafo
    public void añadirParada(String parada) {
        if (contieneParada(parada)) {
            return;
        }
        if (contParadas == capacity) {
            resizeGraph();
        }
        paradas[contParadas] = parada;
        adyList[contParadas] = new MiLista();
        sucursales[contParadas] = false; //Porque todavía no se ha puesto ninguna sucursal
        contParadas++;
        System.out.println("Parada añadida: " + parada + " (Total paradas: " + contParadas + ")");
    }
    
    //Encontrar el índice de una parada específica
    public int encontrarIndiceParada(String parada) {
        for (int i = 0; i < contParadas; i++) {
            if (paradas[i].equals(parada)) {
                return i;
            }
        }
        return -1; //parada no encontrada
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
            System.out.println("Stop " + parada1 + "not found, adding it.");
            añadirParada(parada1);
            indice1 = contParadas -1;
        }
        if (indice2 == -1) {
            System.out.println("Stop " + parada2 + "not found, adding it");
            añadirParada(parada2);
            indice2 = contParadas -1;
        }
        
        adyList[indice1].add(parada2);
        adyList[indice2].add(parada1);
        System.out.println("Arista añadida exitosamente entre: " + parada1 + " y " + parada2);
    }
    
    public boolean existeParada(String parada) {
        return encontrarIndiceParada(parada) != -1;
    }
    
    public boolean contieneParada(String parada) {
        return encontrarIndiceParada(parada) != -1;
    }
    

    
    //Marcar una parada como una sucursal
    public void ponerSucursal(String parada) {
        int indice = encontrarIndiceParada(parada);
        if (indice != -1) {
            sucursales[indice] = true;
        }
    }
    
    //Revisar si una parada es una sucursal
    public boolean esSucursal(String parada) {
        int indice = encontrarIndiceParada(parada);
        return indice != -1 && sucursales[indice];
    }

    
    //Cambia el tamaño del grafo cuando este llega a su capacidad máxima
    private void resizeGraph() {
        capacity *= 2;
        String[] nuevaParada = new String[capacity];
        MiLista[] nuevaAdyList = new MiLista[capacity];
        boolean[] nuevaSucursal = new boolean[capacity];
        
        for (int i = 0; i < contParadas; i++) {
            nuevaParada[i] = paradas[i];
            nuevaAdyList[i] = adyList[i];
            nuevaSucursal[i] = sucursales[i];
        }
        paradas = nuevaParada;
        adyList = nuevaAdyList;
        sucursales = nuevaSucursal;
        System.out.println("Grafo resized a nueva capacidad: " + capacity);
    }
    
    public MiLista getParadasNoCubiertas(MiLista paradasCubiertas) {
        MiLista paradasNoCubiertas = new MiLista();
        for (int i = 0; i < contParadas; i++) {
            String parada = paradas[i];
            boolean esCubierto = false;
            for (int j = 0; j < paradasCubiertas.size(); j++) {
                if(paradasCubiertas.get(j).equals(parada)) {
                    esCubierto = true;
                    break;
                }
            }
        if (!esCubierto) {
            paradasNoCubiertas.add(parada);
            }    
        
        }
        return paradasNoCubiertas;
    }
    
    public String sugerirNuevaSucursal(MiLista paradasNoCubiertas, int t) {
        String mejorParada = null;
        int mayorCobertura = 0;
        for (int i = 0; i < paradasNoCubiertas.size(); i++) {
            String paradaCandidato = paradasNoCubiertas.get(i);
            MiLista coberturaCandidato = CalcularBFS.calculadorBFS(this, paradaCandidato, t);
            if (coberturaCandidato.size() > mayorCobertura) {
                mayorCobertura = coberturaCandidato.size();
                mejorParada = paradaCandidato;
            }
        }
        return mejorParada;
    }
    
}
