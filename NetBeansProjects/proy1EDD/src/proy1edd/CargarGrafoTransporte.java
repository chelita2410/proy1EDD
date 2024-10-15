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
//Imports para leer el archivo JSON
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author chela
 */
public class CargarGrafoTransporte {
    public static void cargarDesdeJSON(GrafoTransporte grafo, String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        
        for (Object lineName : jsonObject.keySet()) {
            String rawLineData = jsonObject.get(lineName).toString();
            rawLineData = rawLineData.replace("[", "").replace("]", "").replace("\"", "").replace("{", "").replace("}", ""); //Para eliminar los caracteres [] y "
            String[] paradas = rawLineData.split(","); //Para separar las paradas por comas
                    

            for (int i = 0; i < paradas.length - 1; i++) {
                String parada1 = paradas[i].trim();
                String parada2 = paradas[i + 1].trim();
                grafo.añadirArista(parada1, parada2); //Añade una arista al grafo
            }
        }
        reader.close();
    }
}
