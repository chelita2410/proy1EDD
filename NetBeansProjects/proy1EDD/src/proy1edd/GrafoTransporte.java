package proy1edd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * CLase que representa el grafo de la red de transporte, donde las paradas son  
 * nodos y las aristas representan las conexiones entre ellas.
 * 
 */
public class GrafoTransporte {
    private String[] paradas; //Array que contiene las paradas
    private MiLista[] adyList; //Lista de adyacencias
    private int contParadas; //Contador de paradas
    private int capacity; //Capacidad m&aacute;xima del grafo
    private boolean[] sucursales; //Array para marcar qu&eacute; paradas tienen sucursales
    private MiLista paradasCubiertas; //Lista de paradas cubiertas por sucursales
    
    /**
     * Constructor que inicializa el grafo con una capacidad inicial.
     */
    public GrafoTransporte() {
        capacity = 100; //capacidad inicial de paradas
        paradas = new String[capacity];
        adyList = new MiLista[capacity];
        sucursales = new boolean[capacity]; // inicializa el array de sucursales
        contParadas = 0;
        paradasCubiertas = new MiLista();
    }
    
    /**
     * Añade una nueva parada al grafo.
     * @param parada El nombre de la parada a añadir.
     */
    public void añadirParada(String parada) {
        if (contieneParada(parada)) {
            return; //No añade si ya existe.
        }
        if (contParadas == capacity) {
            resizeGraph(); //Redimensiona si es necesario.
        }
        paradas[contParadas] = parada;
        adyList[contParadas] = new MiLista();
        sucursales[contParadas] = false; //Porque todav&iacute;a no se ha puesto ninguna sucursal
        contParadas++;
        
    }
    
    /**
     * Encuentra el &iacute;ndice de una parada espec&iacute;fica.
     * @param parada El nombre de la parada a buscar.
     * @return El &iacute;ndice de la parada o -1 si no se encuentra.
     */
    public int encontrarIndiceParada(String parada) {
        for (int i = 0; i < contParadas; i++) {
            if (paradas[i].equals(parada)) {
                return i;
            }
        }
        return -1; //parada no encontrada
    }
    
    /**
     * Retorna la lista de adyacencias de una parada en un &iacute;ndice espec&iacute;fico.
     * @param indice El &iacute;ndice de la parada.
     * @return La lista de adyacencias de la parada o {@code null} si el &iacute;ndice es inv&aacute;lido.
     */
    public MiLista getAdyList(int indice) {
        if (indice >= 0 && indice < contParadas) {
            return adyList[indice]; //Retorna la lista de adyacencias para la parada.
        } else {
            return null; //&iacute;ndice inv&aacute;lido.
        }
    }
    
    /**
     * Retorna el n&uacute;mero actual de paradas en el grafo.
     * @return El contador de paradas.
     */
    public int getContParadas() {
        return contParadas;
    }
    
    
    /**
     * Retorna el nombre de la parada en un &iacute;ndice espec&iacute;fico.
     * @param indice El &iacute;ndice de la parada.
     * @return El nombre de la parada o {@code null} si el &iacute;ndice es inv&aacute;lido.
     */
    public String getStop(int indice) {
        if (indice >= 0 && indice < contParadas) {
            return paradas[indice]; 
        } else {
            return null;
        }
    }
    
    /**
     * Añade una arista entre dos paradas.
     * @param parada1 El nombre de la primera parada.
     * @param parada2 EL nombre de la segunda parada.
     */
    public void añadirArista(String parada1, String parada2) {
        int indice1 = encontrarIndiceParada(parada1);
        int indice2 = encontrarIndiceParada(parada2);
        
        if (indice1 == -1) {
            
            añadirParada(parada1);
            indice1 = contParadas -1; //Actualiza el &iacute;ndice despu&eacute;s de añadir
        }
        if (indice2 == -1) {
            
            añadirParada(parada2);
            indice2 = contParadas -1; //Actualiza el &iacute;ndice despu&eacute;s de añadir
        }
        
        adyList[indice1].add(parada2); //Añade la arista en ambas direcciones.
        adyList[indice2].add(parada1);
        
    }
    
    /**
     * Verifica si existe una parada en el grafo.
     * @param parada El nombre de la parada a verificar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    public boolean existeParada(String parada) {
        return encontrarIndiceParada(parada) != -1;
    }
    
    /**
     * Verifica si el grafo contiene una parada espec&iacute;fica.
     * @param parada El nombre de la parada a verificar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    public boolean contieneParada(String parada) {
        return encontrarIndiceParada(parada) != -1;
    }
    

    
    /**
     * Marca una parada como una sucursal.
     * @param parada El nombre de la parada a marcar.
     */
    public void ponerSucursal(String parada) {
        int indice = encontrarIndiceParada(parada);
        if (indice != -1) {
            sucursales[indice] = true;
        }
    }
    
    /**
     * Verifica si una parada es una sucursal.
     * @param parada El nombre de la parada a verificar.
     * @return {@code true} si es una sucursal, {@code false} en caso contrario.
     */
    public boolean esSucursal(String parada) {
        int indice = encontrarIndiceParada(parada);
        return indice != -1 && sucursales[indice];
    }

    
    /**
     * Cambia el tamaño del grafo cuando alcanza su capacidad m&aacute;xima.
     */
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
        paradas = nuevaParada; //Actualiza las referencias.
        adyList = nuevaAdyList;
        sucursales = nuevaSucursal;
        
    }

    /**
     * Sugiere una nueva sucursal basada en la cobertura de paradas.
     * @param t El tiempo para calcular la cobertura.
     * @return El nombre de la mejor parada candidata o {@code null} si no hay ninguna.
     */
    public String sugerirNuevaSucursal(int t) {
        MiLista paradasNoCubiertas = getParadasNoCubiertas();
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
        if (mejorParada != null) {
            MiLista coberturaNuevaSucursal = CalcularBFS.calculadorBFS(this, mejorParada, t);
            for (int i = 0; i < coberturaNuevaSucursal.size(); i++) {
                String parada = coberturaNuevaSucursal.get(i);
                if (!paradasCubiertas.contains(parada)) {
                    paradasCubiertas.add(parada); //Añade la parada a las cubiertas.
                }
            }
            ponerSucursal(mejorParada); //Marca como sucursal
        }
        return mejorParada; //Retorna la mejor parada encontrada.
    }

    /**
     * Retorna una lista de paradas que no est&aacute;n cubiertas por sucursales.
     * @return Una lista de paradas no cubiertas.
     */
    public MiLista getParadasNoCubiertas() {
       MiLista paradasNoCubiertas = new MiLista();
        for (int i = 0; i < contParadas; i++) {
            String parada = paradas[i];
        
            if (!paradasCubiertas.contains(parada)) {
                paradasNoCubiertas.add(parada);
            }
    }
        return paradasNoCubiertas; 
    }
    
    
}
