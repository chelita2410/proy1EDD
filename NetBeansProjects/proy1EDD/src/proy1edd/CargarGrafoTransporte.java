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
import proy1edd.Nodo;
//Imports para leer el archivo JSON
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author chela
 */
public class CargarGrafoTransporte {
    public static void cargarDesdeJSON(GrafoTransporte grafo, String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        StringBuilder content = new StringBuilder();
        int c;
        
        while ((c = reader.read()) != -1) {
            content.append((char)c);
        }
        reader.close();
        String json = content.toString().trim();
        parseJSON(json, grafo);
        
        
    }
    
    private static void parseJSON(String json, GrafoTransporte grafo) {
        int i = 0;
        String nombreLinea = null;
        String nombreParada = null;
        boolean enLinea = false;
        boolean enParadas = false;
        String paradaAnterior = null;
        
        while (i < json.length()) {
            char ch = json.charAt(i);
            
            if (ch == '"' && !enLinea) {
                enLinea = true;
                nombreLinea = "";
                i++;
                while (json.charAt(i) != '"') {
                    nombreLinea += json.charAt(i);
                    i++;
                }
                i++;
            }
            if (ch == '[') {
                enParadas = true;
                nombreParada = "";
                i++;
            }
            while (enParadas && i < json.length()) {
                ch = json.charAt(i);
                
                if(ch == '"') {
                    i++;
                    nombreParada = "";
                    
                    while (json.charAt(i) != '"') {
                       nombreParada += json.charAt(i);
                        i++;
                    }
                    i++;
                    if (!grafo.contieneParada(nombreParada)) {
                        grafo.añadirParada(nombreParada);
                    }
                    if (paradaAnterior != null) {
                        grafo.añadirArista(paradaAnterior, nombreParada);
                    }
                    paradaAnterior = nombreParada;
                    //nombreParada = "";
                    i++;
                    
                        
                       /* grafo.añadirParada(nombreParada);
                    if(grafo.getContParadas() > 1) {
                        String nombreParadaPrevia = grafo.getStop(grafo.getContParadas() - 2);
                        grafo.añadirArista(nombreParadaPrevia, nombreParada);
                    }
                    nombreParada = "";
                    i++; **/
                } else if (ch == '{') {
                    i++;
                    String origen = "", destino = "";
                    while (json.charAt(i) != '"') {
                        origen += json.charAt(i);
                        i++;
                    }
                    //Revisar si si funciona y si no poner i += 3
                    i++;
                    while (json.charAt(i) != '"') {
                        destino += json.charAt(i);
                        i++;
                    }
                    i ++;
                    if (!grafo.contieneParada(origen)) {
                        grafo.añadirParada(origen);
                    }
                    if (!grafo.contieneParada(destino)) {
                        grafo.añadirParada(destino);
                        
                    }
                    grafo.añadirArista(origen, destino);
                    paradaAnterior = destino;
                }
                
                if (ch == ']') {
                    enParadas = false;
                    break;
                }       
            i++;
        }
            i++;
        
        }
    }
    
    
    
    
    
    
    
    
    
    
    
        /**JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        
        for (Object networkKey : jsonObject.keySet()) {
            Object lineasMetro = jsonObject.get(networkKey); //convierte todas las líneas en un String
            System.out.println("Loading lines for network " + networkKey);
            String lineasMetroStr = lineasMetro.toString();
            System.out.println("Raw metro line string: " + lineasMetroStr);
            
            String[] lineas = lineasMetroStr.split("\\}, \\{");
            
            for (String linea : lineas) {
                linea = linea.replace("[{", "").replace("}]", "");//.replace("{", "").replace("}", "").replace("[", "").replace("]", "");
                String[] paradas = linea.split(",");
                String paradaAnterior = null;
                
                for (String parada : paradas) {
                    parada = parada.trim();
                    
                    
                    if (parada.endsWith(":[")) {
                        System.out.println("Ignoring line label: " + parada);
                        continue;
                    }
                    
                    if(parada.isEmpty()) {
                        System.out.println("Skipping empty or malformed stop: " + parada);
                        continue;
                    }
                    System.out.println("Processing stop: " + parada);
                    
                    if (parada.startsWith("{") && parada.endsWith("}") && parada.contains(":")) {
                        parada = parada.substring(1, parada.length() -1);
                        String[] paradasConex = parada.split(":");
                        
                        if (paradasConex.length == 2) {
                        String conexComienza = paradasConex[0].replace("\"", "").trim();
                        String conexTermina = paradasConex[1].replace("\"", "").trim();  
                        System.out.println("Transfer stop detected " + conexComienza + " --> " + conexTermina);
                        grafo.añadirArista(conexComienza, conexTermina);
                        if (paradaAnterior != null) {
                            grafo.añadirArista(paradaAnterior, conexComienza);
                            }
                        paradaAnterior = conexTermina;
                        }
                    } else {
                        parada = parada.replace("\"", "");
                        System.out.println("Adding regular stop: " + parada);
                        if(paradaAnterior != null) {
                            grafo.añadirArista(paradaAnterior, parada);
                        }
                        paradaAnterior = parada;
                    }
                }
            }
        }
        reader.close();
    } */
}
