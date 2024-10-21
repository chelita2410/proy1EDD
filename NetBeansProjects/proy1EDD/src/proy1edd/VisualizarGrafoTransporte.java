 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proy1edd;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * Clase responsable de visualizar el grafo de la red de transporte utilizando GraphStream.
 * Se toma un objeto de tipo {@code GrafoTransporte} y se convierte en una representaci&oacute;n
 * visual.
 */


public class VisualizarGrafoTransporte {
    public Graph graphStream; //Objeto GraphStream que representa el grafo visualmente.
    
    
    /**
     * Constructor que inicializa una visualizaci&oacute;n del grafo de la red de transporte.
     * @param grafoTransporte El objeto {@code GrafoTransporte} que se desea visualizar.
     */
    public VisualizarGrafoTransporte(GrafoTransporte grafoTransporte) {
        graphStream = new SingleGraph("Red de Transporte"); //Crea un nuevo grafo.
        
        //Establece el estilo de los nodos en la visualizaci&oacute;n.
        graphStream.setAttribute("ui.stylesheet", "node { fill-color: grey; size: 25px; }");
        
        //Agrega v&eacute;rtices y aristas a GraphStream
        for (int i = 0; i < grafoTransporte.getContParadas(); i++) {
            String parada = grafoTransporte.getStop(i);
            System.out.println("adding node to graphstream " + parada);
            graphStream.addNode(parada); //Añade cada parada como un v&eacute;rtice
            for (int j = 0; j < grafoTransporte.getAdyList(i).size(); j++) {
                String vecino = grafoTransporte.getAdyList(i).get(j);
                if (graphStream.getEdge(parada + "-" + vecino) == null) {
                    graphStream.addEdge(parada + "-" + vecino, parada, vecino); //Añade aristas
                }
            }
        }
        //Muestra el grafo visualmente
        graphStream.display(true);
    }
    
   
  
}
