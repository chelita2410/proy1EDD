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
import java.io.FileReader;
import java.io.IOException;
/**
 *
 *Carga un grafo de transporte desde un archivo JSON espec&iacute;fico.
 * @param grafo El objeto {@link GrafoTransporte} donde se cargar&aacute;n los datos.
 * @param filePath La ruta del archivo JSON a leer.
 * @throws IOException Si ocurre un error al leer el archivo.
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
    
    /**
     * Analiza una cadena JSON y actualiza el grafo de transporte con la informaci&oacute;n
     * recorri&eacute;ndolo y tomando en cuenta la manera en la que est&aacute; escrito.
     * @param json La cadena JSON que contiene la informaci&oacute;n del grafo.
     * @param grafo El objeto {@link GrafoTransporte} que se actualizar&aacute;.
     */
    private static void parseJSON(String json, GrafoTransporte grafo) {
        int i = 0;
        String nombreParada = null;
        String paradaAnterior = null;
        while (i < json.length()) {
            char ch = json.charAt(i);
            if (ch == '"' && nombreParada == null) {
                i++;
                String nombreLinea = "";
                while (json.charAt(i) != '"') {
                    nombreLinea += json.charAt(i);
                    i++;
                }
                i++;
                continue;
            }
            if (ch == '[') {
                i++;
                while (i < json.length() && json.charAt(i) != ']') {
                    ch = json.charAt(i);
                    
                    if (ch == '"') {
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
                    } else if (ch == '{') {
                        i++;
                        String origen = "", destino = "";
                        
                        while (json.charAt(i) != '"') {
                            origen += json.charAt(i);
                            i++;
                        }
                        i++;
                        
                        while (json.charAt(i) != '"') {
                            destino += json.charAt(i);
                            i++;
                        }
                        i++;
                        
                        if(!grafo.contieneParada(origen)) {
                            grafo.añadirParada(origen);
                        }
                        if (!grafo.contieneParada(destino)) {
                            grafo.añadirParada(destino);
                        }
                        grafo.añadirArista(origen, destino);
                        paradaAnterior = destino;
                    }
                    i++;
                    
                }
                i++;
                paradaAnterior = null;
            }
            i++;
        }
    }
}
   
        
        
 