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
 * @author chela
 */

/* SEGURO TENFO QUE DECLARAR THIS.VISUALIZAR AQUI // VERIFICAR ESO */
public class VisualizarGrafoTransporte {
    public Graph graphStream;
    
    
    public VisualizarGrafoTransporte(GrafoTransporte grafoTransporte) {
        graphStream = new SingleGraph("Red de Transporte");
        
        graphStream.setAttribute("ui.stylesheet", "node { fill-color: grey; size: 25px; }");
        
        //Agrega vértices y aristas a GraphStream
        for (int i = 0; i < grafoTransporte.getContParadas(); i++) {
            String parada = grafoTransporte.getStop(i);
            System.out.println("adding node to graphstream " + parada);
            graphStream.addNode(parada); //Añade cada parada como un vértice
            for (int j = 0; j < grafoTransporte.getAdyList(i).size(); j++) {
                String vecino = grafoTransporte.getAdyList(i).get(j);
                if (graphStream.getEdge(parada + "-" + vecino) == null) {
                    graphStream.addEdge(parada + "-" + vecino, parada, vecino); //Añade aristas
                }
            }
        }
        //Enseñar el grafo visualmente
        graphStream.display(true);
    }
    
   
    
    /*
    public void highlightCobertura(MiLista paradasCubiertas) {
        System.out.println("Starting to highlight coverage");
        for (Node node : graphStream) {
            System.out.println("GraphStream node ID " + node.getId());
        }
        for (int i = 0; i < paradasCubiertas.size(); i++) {
            //String parada = paradasCubiertas.get(i);
            String parada = paradasCubiertas.get(i);
            System.out.println("Covered stop ID " + parada);
            Node node = graphStream.getNode(parada);
           // Node node = graphStream.getNode(paradasCubiertas.get(i));
            if (node != null) {
                System.out.println("Highlighting node " + parada);
                node.setAttribute("ui.style", "fill-color: red;");
               // node.setAttribute("ui.label", parada);
            } else {
                System.out.println("Node for stop " + parada + " not found in graph");
            }
        }
*
        graphStream.display();
      //  graphStream.nodes().forEach(n -> n.setAttribute("ui.style", n.getAttribute("ui.style")));
       // graphStream.display(false);
       // graphStream.display(true);
    }
    */
    /*public void highlightSucursalSugerida(String parada) {
        Node node = graphStream.getNode(parada);
        if (node != null) {
            node.setAttribute("ui.style", "fill-color: blue;");
        }
    } */
    
}
