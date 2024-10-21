/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proy1edd.interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proy1edd.CargarGrafoTransporte;
import proy1edd.GrafoTransporte;
import proy1edd.VisualizarGrafoTransporte;
import proy1edd.MiLista;
import proy1edd.CalcularBFS;
import proy1edd.CalcularDFS;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import java.awt.event.*;




/**
 *
 * Interfaz gr&acute;fica para interactuar con el grafo de la red de transporte.
 * Permite cargar la red, agregar l&iacute;neas, establecer sucursales y revisar coberturas.
 */
public class InterfazInteractuar extends javax.swing.JFrame {
    private JTextArea coverageTextArea; //&Aacute;rea de texto para mostrar la cobertura.
    private VisualizarGrafoTransporte visualizar; //Objeto para visualizar el grafo.
    private GrafoTransporte grafo; //Objeto grafo
    private JComboBox<String> seleccionarParada; //Enseña todas las paradas para seleccionar en cual se pone la sucursal
    private JPanel graphPanel; //Panel donde se mostrar&aacute; el grafo.
    

    /**
     * Constructor que inicializa la interfaz
     */
    
    public InterfazInteractuar() {
        setTitle("Cobertura sucursales por red de transporte");
       
        this.setUndecorated(false);
        this.setAlwaysOnTop(true);
        this.setResizable(true);
        this.setVisible(true);
        Toolkit a = Toolkit.getDefaultToolkit();
        int xSize = (int) a.getScreenSize().getWidth();
        int ySize = (int) a.getScreenSize().getHeight();
        this.setSize(xSize, ySize);
                
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        grafo = new GrafoTransporte();
 

        
        //Crear men&uacute;
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo JSON");
        JMenuItem loadMenuItem= new JMenuItem("Carga el archivo con la red de transporte");
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        //Configuraci&oacute;n para el &aacute;rea de texto.
        coverageTextArea = new JTextArea(10,30);
        coverageTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(coverageTextArea);
        add(scrollPane, BorderLayout.EAST);
        
        //Panel para el grafo
        graphPanel = new JPanel(new BorderLayout());
        add(graphPanel, BorderLayout.CENTER);
        
       
        JPanel controlPanel = new JPanel();
        seleccionarParada = new JComboBox<>();
        tValueField = new JTextField(3);
        JButton nuevaSucursalButton = new JButton("Establecer Nueva Sucursal");
        JButton revisarCoberturaButton = new JButton("Revisar Cobertura");
        JButton sugerirSucursalButton = new JButton("Sugerir Nueva Sucursal");
        JButton buscarDFSButton = new JButton ("Busqueda por DFS");
        JTextField anadeLineaField = new JTextField(5);
        JButton anadeLineaButton = new JButton("Agregar Linea");
        
        controlPanel.add(new JLabel("Agregar Linea:"));
        controlPanel.add(anadeLineaField);
        controlPanel.add(anadeLineaButton);
        controlPanel.add(new JLabel("Seleccionar Parada:"));
        controlPanel.add(seleccionarParada);
        controlPanel.add(new JLabel("t:"));
        controlPanel.add(tValueField);
        controlPanel.add(nuevaSucursalButton);
        controlPanel.add(revisarCoberturaButton);
        controlPanel.add(sugerirSucursalButton);
        controlPanel.add(buscarDFSButton);
        add(controlPanel, BorderLayout.SOUTH);
        
        
        anadeLineaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                agregarLinea(anadeLineaField.getText());
            }
        });
        //Acción para cargar la red de transporte
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                cargarRedTransporte();
            }
        });
        
        nuevaSucursalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ponerSucursal();
            }
        });
        
        revisarCoberturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revisarCobertura();
            }
        });
        
        sugerirSucursalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    sugerirNuevaSucursal();
            }        
        });
        
        buscarDFSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDFS();
            }
        });
    }
    
   
    /**
     * Agrega una l&iacute;nea al grafo a partir de paradas.
     * @param lineaParadas String con las paradas separadas por comas.
     */
    private void agregarLinea(String lineaParadas) {
        if (lineaParadas == null || lineaParadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa una secuencia de paradas");
            return;
        }
        String[] paradas = lineaParadas.split(",");
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = paradas[i].trim();
            
        }
        for (int i = 0; i < paradas.length; i++) {
            if (!grafo.existeParada(paradas[i])) {
                grafo.añadirParada(paradas[i]);
            }
            if (i > 0) {
                grafo.añadirArista(paradas[i - 1], paradas[i]);
            }
        }
        actualizarSeleccionarParada();
        enseñarGrafo();
      
        JOptionPane.showMessageDialog(this, "Linea agregada: " + lineaParadas);
    }
    
    /**
     * Verifica si todas las paradas en el grafo est&aacute;n cubiertas.
     * @return {@code true} si la cobertura es completa, {@code false} en caso contrario.
     */
    private boolean verificarCoberturaCompleta() {
        if (grafo.getContParadas() == 0) {
            return false;
        }
        MiLista paradasVisitadas = CalcularDFS.calculadorDFS(grafo, grafo.getStop(0));
        return paradasVisitadas.size() == grafo.getContParadas();
    }
    
    /**
     * Carga la red de transporte desde un archivo JSON.
     */
    private void cargarRedTransporte() {
        grafo = new GrafoTransporte();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                grafo = new GrafoTransporte(); //Inicializa el grafo
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                CargarGrafoTransporte.cargarDesdeJSON(grafo, filePath);
                actualizarSeleccionarParada();
                
                enseñarGrafo();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error cargando archivo: " + e.getMessage());
            }
        }
    }
    
    /**
     * Actualiza el combo box de selecci&oacute;n de paradas con las paradas del grafo.
     */
    private void actualizarSeleccionarParada() {
        seleccionarParada.removeAllItems();
        for (int i = 0; i < grafo.getContParadas(); i++) {
            seleccionarParada.addItem(grafo.getStop(i));
        }
    }
    
   /**
    * Muestra el grafo en al panel de visualizaci&oacute;n.
    */
    private void enseñarGrafo() {
        graphPanel.removeAll();
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Red de trasporte");
        graph.setAttribute("ui.stylesheet", "graph { fill-color: #EEE; }");
        for (int i = 0; i < grafo.getContParadas(); i++) {
            String parada = grafo.getStop(i);
            graph.addNode(parada);
            graph.getNode(parada).setAttribute("ui.label", parada);
            
            }
        for (int i  = 0; i < grafo.getContParadas(); i++) {
                String sourceStop = grafo.getStop(i);
                MiLista adyList = grafo.getAdyList(i);
                
                for (int j = 0; j < adyList.size(); j++) {
                    String targetStop = adyList.get(j);
                    String edgeId = sourceStop + targetStop;
                    
                    if (graph.getNode(sourceStop) != null && graph.getNode(targetStop) != null) {
                        if (graph.getEdge(edgeId) == null & graph.getEdge(targetStop + sourceStop) == null) {
                            graph.addEdge(edgeId, sourceStop, targetStop);
                        }
                    }
                }
        }
       
        Viewer viewer = graph.display();
        ViewPanel viewPanel = (ViewPanel) viewer.getDefaultView();
       
        viewer.enableAutoLayout();
      
       
      
        graphPanel.add(viewPanel, BorderLayout.CENTER);
        graphPanel.revalidate();
        graphPanel.repaint();
        
    }
        
   
    /**
     * Establece una sucursal en la parada seleccionada.
     */
    private void ponerSucursal() {
        String paradaSeleccionada = (String) seleccionarParada.getSelectedItem();
        if(paradaSeleccionada != null) {
            grafo.ponerSucursal(paradaSeleccionada);
            JOptionPane.showMessageDialog(this, "Sucursal puesta en: " + paradaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una parada para poner una sucursal");
        }
    }
    
    /**
     * revisa la cobertura de la parada seleccionada.
     */
    private void revisarCobertura() {
        String paradaSucursal = (String) seleccionarParada.getSelectedItem();
        if (paradaSucursal == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una parada");
            return;
        }
        String tValueText = tValueField.getText().trim();
        int t;
        try {
            t = Integer.parseInt(tValueText);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un valor válido para t");
            return;
        }
        MiLista paradasCubiertas = CalcularBFS.calculadorBFS(grafo, paradaSucursal, t);
        coverageTextArea.setText("");
        coverageTextArea.append("Cobertura para la parada: " + paradaSucursal + "\n");
        coverageTextArea.append("Distancia t = " + t + "\n");
        coverageTextArea.append("Paradas cubiertas: \n");
        for (int i = 0; i < paradasCubiertas.size(); i++) {
            String parada = paradasCubiertas.get(i);
            coverageTextArea.append("- " + parada + "\n");
        }
  
       
    }
    
    /**
     * Realiza una b&uacute;squeda DFS desde la parada seleccionada.
     */
    private void buscarDFS() {
        String paradaSeleccionada = (String) seleccionarParada.getSelectedItem();
        if (paradaSeleccionada != null) {
            MiLista resultadoDFS = CalcularDFS.calculadorDFS(grafo, paradaSeleccionada);
            JOptionPane.showMessageDialog(this, "Resultado de DFS desde " + paradaSeleccionada + ":");
            for (int i = 0; i < resultadoDFS.size(); i++) {
                JOptionPane.showMessageDialog(this, resultadoDFS.get(i));
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una parada para buscar");
        }
    }
    
    
    /**
     * Sugiere una nueva sucursal utilizando cobertura BFS.
     */
    private void sugerirNuevaSucursal() {
        try {
            int t = Integer.parseInt(tValueField.getText());
            String paradaSugerida = grafo.sugerirNuevaSucursal(t);
            
           
            coverageTextArea.setText("");
          
            if (paradaSugerida != null) {
                coverageTextArea.append("Parada sugerida para la nueva sucursal: " + paradaSugerida + "\n");
                MiLista paradasNoCubiertas = grafo.getParadasNoCubiertas();
                coverageTextArea.append("Paradas no cubiertas restantes:\n");
                for (int i = 0; i < paradasNoCubiertas.size(); i++) {
                    String parada = paradasNoCubiertas.get(i);
                    coverageTextArea.append("- " + parada + "\n");
                }
            } else {
                coverageTextArea.append("No hay más paradas disponibles para nuevas sucursales");
            }
           
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido para t");
       }
        
       
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ensenarGrafo = new javax.swing.JTextArea();
        jFileChooser1 = new javax.swing.JFileChooser();
        nuevaSucursal = new javax.swing.JButton();
        revisarCobertura = new javax.swing.JButton();
        sugerirSucursal = new javax.swing.JButton();
        tValueField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jLabel1.setFont(new java.awt.Font("Savoye LET", 0, 24)); // NOI18N
        jLabel1.setText("Ubicación de Sucursales");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, 40));

        jLabel2.setText("Ingrese el archivo JSON con la información de transporte de su ciudad:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel3.setText("Ingrese la distancia \"t\" que debe haber entre sucursales:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 440, -1));

        jLabel5.setText("t =");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jLabel4.setText("Enseñar grafo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        ensenarGrafo.setColumns(20);
        ensenarGrafo.setRows(5);
        jScrollPane1.setViewportView(ensenarGrafo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 650, 110));
        getContentPane().add(jFileChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 660, -1));

        nuevaSucursal.setText("Establecer Nueva Sucursal");
        getContentPane().add(nuevaSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 670, -1, -1));

        revisarCobertura.setText("Revisar Cobertura");
        getContentPane().add(revisarCobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 670, -1, -1));

        sugerirSucursal.setText("Sugerir Nueva Sucursal");
        getContentPane().add(sugerirSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 670, -1, -1));
        getContentPane().add(tValueField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazInteractuar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazInteractuar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazInteractuar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazInteractuar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazInteractuar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ensenarGrafo;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevaSucursal;
    private javax.swing.JButton revisarCobertura;
    private javax.swing.JButton sugerirSucursal;
    private javax.swing.JTextField tValueField;
    // End of variables declaration//GEN-END:variables
}
