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


/**
 *
 * @author chela
 */
public class InterfazInteractuar extends javax.swing.JFrame {
    private VisualizarGrafoTransporte visualizar;
    private GrafoTransporte grafo; //Objeto grafo
    private JComboBox<String> seleccionarParada; //Enseña todas las paradas para seleccionar en cual se pone la sucursal
    
/**NO ME INICIALIZA EL GRAFO Y POR LO TANTO ME DA ERROR AL TRATAR 
 DE VER LA COBERTURA Y/O DE SUGERIR NUEVAS SUCURSALES.
 BUSCAR SOLUCION PARA QUE ME INICIALICE EL GRAFO
 
 FALTAN LAS PILAS Y LA BUSQUEDA POR DFA
 
 TODAVIA ME DA ERROR EN EL PRIMER NODO AL CARGAR EL JSON AUNQUE ESTE APARENTEMENTE
 NO AFECTA EN NADA*/
    

    /**
     * Creates new form InterfazInteractuar
     */
    
    public InterfazInteractuar() {
        setTitle("Cobertura sucursales por red de transporte");
        setSize(800, 600);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        grafo = new GrafoTransporte();
 

        
        //Crear menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo JSON");
        JMenuItem loadMenuItem= new JMenuItem("Carga el archivo con la red de transporte");
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        //Área para enseñar el grafo
        //visualizar = new VisualizarGrafoTransporte(grafo);
        ensenarGrafo = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ensenarGrafo);
        add(scrollPane, BorderLayout.CENTER);
        
        //Acciones realizadas por el usuario
       
        JPanel controlPanel = new JPanel();
        seleccionarParada = new JComboBox<>();
        tValueField = new JTextField(3);
        JButton nuevaSucursalButton = new JButton("Establecer Nueva Sucursal");
        JButton revisarCoberturaButton = new JButton("Revisar Cobertura");
        JButton sugerirSucursalButton = new JButton("Sugerir Nueva Sucursal");
        JButton buscarDFSButton = new JButton ("Busqueda por DFS");
        JTextField anadeLineaField = new JTextField(5);
        JButton anadeLineaButton = new JButton("Agregar Linea");
        //JButton verificarCoberturaButton = new JButton("Verificar cobertura completa");
       // controlPanel.add(verificarCoberturaButton);
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
        
        /**verificarCoberturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean estaCubiertoCompletamente = verificarCoberturaCompleta();
                if (estaCubiertoCompletamente) {
                    JOptionPane.showMessageDialog(null, "Todas las paradas estan cubiertas");
                } else {
                    JOptionPane.showMessageDialog(null, "No todas las paradas estan cubiertas");
                }
            }
        });
        */
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
    
   /* private void enseñarGrafoVisualmente() {
        if(grafo != null) {
            new VisualizarGrafoTransporte(grafo); //Enseña el grafo usando GraphStream
        }
    } **/
    
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
        JOptionPane.showMessageDialog(this, "Linea agregada: " + lineaParadas);
    }
    
    private boolean verificarCoberturaCompleta() {
        if (grafo.getContParadas() == 0) {
            return false;
        }
        MiLista paradasVisitadas = CalcularDFS.calculadorDFS(grafo, grafo.getStop(0));
        return paradasVisitadas.size() == grafo.getContParadas();
    }
    //Método para cargar la Red de Transporte
    private void cargarRedTransporte() {
        //grafo = new GrafoTransporte();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                //grafo = new GrafoTransporte(); //Inicializa el grafo
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                CargarGrafoTransporte.cargarDesdeJSON(grafo, filePath);
                actualizarSeleccionarParada();
                if (grafo != null) {
                    visualizar = new VisualizarGrafoTransporte(grafo);
                }
                //visualizar = new VisualizarGrafoTransporte(grafo);
                ensenarGrafo.setText("Red de Transporte Cargada\n");
                
                this.revalidate();
                this.repaint();
                //enseñarGrafo();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error cargando archivo: " + e.getMessage());
            }
        }
    }
    
    //Actualiza el seleccionador de paradas con las paradas del grafo
    private void actualizarSeleccionarParada() {
        seleccionarParada.removeAllItems();
        for (int i = 0; i < grafo.getContParadas(); i++) {
            seleccionarParada.addItem(grafo.getStop(i));
        }
    }
    
    //Método para enseñar el grafo en el Text Area
   /* private void enseñarGrafo() {
        if (grafo != null) {
            ensenarGrafo.setText(""); //Limpia el texto previo
            for (int i = 0; i < grafo.getContParadas(); i++) {
                ensenarGrafo.append(grafo.getStop(i) + ": ");
                for (int j = 0; j < grafo.getAdyList(i).size(); j++) {
                    ensenarGrafo.append(grafo.getAdyList(i).get(j) + " ");
                }
                ensenarGrafo.append("\n");
            }
        }
    }
*/
    //Método para poner una sucursal
    private void ponerSucursal() {
        String paradaSeleccionada = (String) seleccionarParada.getSelectedItem();
        if(paradaSeleccionada != null) {
            grafo.ponerSucursal(paradaSeleccionada);
            JOptionPane.showMessageDialog(this, "Sucursal puesta en: " + paradaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una parada para poner una sucursal");
        }
    }
    
    //Método para revisar la covertura buscando por BFS
    private void revisarCobertura() {
        String paradaSeleccionada = (String) seleccionarParada.getSelectedItem();
        if (paradaSeleccionada != null) {
            try {
                int t = Integer.parseInt(tValueField.getText());
                MiLista paradasCubiertas = CalcularBFS.calculadorBFS(grafo, paradaSeleccionada, t);
                
                if(visualizar != null) {
                    enseñarCobertura(paradasCubiertas);
                    visualizar.highlightCobertura(paradasCubiertas);
                } else {
                    JOptionPane.showMessageDialog(this, "La visualización del grafo no está iniciada");
                }
                //MiLista paradasNoCubiertas = grafo.getParadasNoCubiertas(paradasCubiertas);
                //enseñarCobertura(paradasCubiertas);
                //VisualizarGrafoTransporte visualizar = new VisualizarGrafoTransporte(grafo);
               // visualizar.highlightCobertura(paradasCubiertas);   
                
                //if (paradasNoCubiertas.size() > 0) {
                 //   JOptionPane.showMessageDialog(this, "Todavía hay paradas que no están cubiertas");
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para t");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una parada"); 
        }
    }
     
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
    
    private void enseñarCobertura(MiLista paradasCubiertas) {
        ensenarGrafo.append("\nParadas cubiertas de la sucursal:\n");
        for (int i = 0; i < paradasCubiertas.size(); i++) {
            ensenarGrafo.append(paradasCubiertas.get(i) + "\n");
        }
    }
    
    //por bfs
    private void sugerirNuevaSucursal() {
        try {
            int t = Integer.parseInt(tValueField.getText());
            MiLista paradasCubiertas = CalcularBFS.calculadorBFS(grafo, (String) seleccionarParada.getSelectedItem(), t);
            MiLista paradasNoCubiertas = grafo.getParadasNoCubiertas(paradasCubiertas);
           if (visualizar != null) {
            if (paradasNoCubiertas.size() > 0) {
                String paradaSugerida = grafo.sugerirNuevaSucursal(paradasNoCubiertas, t);
                visualizar.highlightSucursalSugerida(paradaSugerida);
                JOptionPane.showMessageDialog(this, "Localización sugerida para la nueva sucursal: " + paradaSugerida);
            } else {
                JOptionPane.showMessageDialog(this, "Todas las paradas ya están cubiertas");
            }
           } else {
               JOptionPane.showMessageDialog(this, "La visualización del grafo no está iniciada");
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
