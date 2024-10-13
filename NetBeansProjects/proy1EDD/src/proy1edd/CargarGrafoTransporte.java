/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/** Imports </code>org.json.simple</code> cargados desde una librería descargada
 * independientemente 
 */
package proy1edd;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author chela
 */
public class CargarGrafoTransporte {
    public static void cargarDesdeJSON(GrafoTransporte grafo, String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        
        for (Object lineName : jsonObject.keySet()) {
            org.json.simple.JSONArray jsonParadas = (org.json.simple.JSONArray) jsonObject.get(lineName);
            MiLista listaParadas = new MiLista();
            for (Object parada : jsonParadas) {
                listaParadas.add((String) parada); //Añade cada parada a </code> MiLista </code>
            }
            for (int i = 0; i < listaParadas.size() - 1; i++) {
                String parada1 = listaParadas.get(i);
                String parada2 = listaParadas.get(i + 1);
                grafo.añadirArista(parada1, parada2); //Añade aristas entre paradas consecutivas
            }
        }
        reader.close();
    }
}
