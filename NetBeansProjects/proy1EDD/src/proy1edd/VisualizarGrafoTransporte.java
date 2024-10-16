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
public class VisualizarGrafoTransporte {
    private Graph graphStream;
    
    public VisualizarGrafoTransporte(GrafoTransporte grafoTransporte) {
        graphStream = new SingleGraph("Red de Transporte");
        
        //Agrega vértices y aristas a GraphStream
        for (int i = 0; i < grafoTransporte.getContParadas(); i++) {
            String parada = grafoTransporte.getStop(i);
            graphStream.addNode(parada); //Añade cada parada como un vértice
            for (int j = 0; j < grafoTransporte.getAdyList(i).size(); j++) {
                String vecino = grafoTransporte.getAdyList(i).get(j);
                if (graphStream.getEdge(parada + "-" + vecino) == null) {
                    graphStream.addEdge(parada + "-" + vecino, parada, vecino); //Añade aristas
                }
            }
        }
        //Enseñar el grafo visualmente
        graphStream.display();
    }
    
    public void highlightCobertura(MiLista paradasCubiertas) {
        for (int i = 0; i < paradasCubiertas.size(); i++) {
            //String parada = paradasCubiertas.get(i);
            String parada = paradasCubiertas.get(i);
            Node node = graphStream.getNode(paradasCubiertas.get(i));
            if (node != null) {
                node.setAttribute("ui.style", "node { fill-color: red; }");
            }
        }
    }
    
    public void highlightSucursalSugerida(String parada) {
        Node node = graphStream.getNode(parada);
        if (node != null) {
            node.setAttribute("ui.style", "node { fill-color: blue; }");
        }
    }
    
}
