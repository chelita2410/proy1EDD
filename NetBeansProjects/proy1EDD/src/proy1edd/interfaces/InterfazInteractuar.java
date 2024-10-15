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


/**
 *
 * @author chela
 */
public class InterfazInteractuar extends javax.swing.JFrame {
    private GrafoTransporte grafo; //Objeto grafo
    private JComboBox<String> seleccionarParada; //Enseña todas las paradas para seleccionar en cual se pone la sucursal
    
    

    /**
     * Creates new form InterfazInteractuar
     */
    
    public InterfazInteractuar() {
        setTitle("Cobertura sucursales por red de transporte");
        setSize(600, 400);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        
        //Crear menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo JSON");
        JMenuItem loadMenuItem= new JMenuItem("Carga el archivo con la red de transporte");
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        //Área para enseñar el grafo
        ensenarGrafo = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ensenarGrafo);
        add(scrollPane, BorderLayout.CENTER);
        
        //Acciones realizadas por el usuario
        JPanel controlPanel = new JPanel();
        seleccionarParada = new JComboBox<>();
        JButton nuevaSucursalButton = new JButton("Establecer Nueva Sucursal");
        JButton revisarCoberturaButton = new JButton("Revisar Cobertura");
        controlPanel.add(seleccionarParada);
        controlPanel.add(nuevaSucursalButton);
        controlPanel.add(revisarCoberturaButton);
        add(controlPanel, BorderLayout.SOUTH);
        
        //Acción para cargar la red de transporte
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                //PQ ME DA ERROR/
                cargarRedTransporte();
            }
        });
        
        nuevaSucursalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PQ ME DA ERROR/
                ponerSucursal();
            }
        });
        
        revisarCoberturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PQ ME DA ERROR
                revisarCobertura();
            }
        });
    }
    
    private void enseñarGrafoVisualmente() {
        if(grafo != null) {
            new VisualizarGrafoTransporte(grafo); //Enseña el grafo usando GraphStream
        }
    }
    
    //Método para cargar la Red de Transporte
    private void cargarRedTransporte() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                grafo = new GrafoTransporte(); //Inicializa el grafo
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                CargarGrafoTransporte.cargarDesdeJSON(grafo, filePath);
                actualizarSeleccionarParada();
                enseñarGrafo(); //Enseña el grafo cargado en el Text Area
                enseñarGrafoVisualmente();
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
    private void enseñarGrafo() {
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

    //Método para poner una sucursal
    private void ponerSucursal() {
        String paradaSeleccionada = (String) seleccionarParada.getSelectedItem();
        if(paradaSeleccionada != null) {
            grafo.ponerSucursal(paradaSeleccionada);
            JOptionPane.showMessageDialog(this, "Sucursal puesta en: " + paradaSeleccionada);
        }
    }
    
    //Método para revisar la covertura
    private void revisarCobertura() {
        JOptionPane.showMessageDialog(this, "Opción para revisar cobertura está bajo construcción");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        distanciaSucursales = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ensenarGrafo = new javax.swing.JTextArea();
        jFileChooser1 = new javax.swing.JFileChooser();
        nuevaSucursal = new javax.swing.JButton();
        revisarCobertura = new javax.swing.JButton();
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

        distanciaSucursales.setColumns(20);
        distanciaSucursales.setRows(5);
        jScrollPane2.setViewportView(distanciaSucursales);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 410, 30));

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
    private javax.swing.JTextArea distanciaSucursales;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nuevaSucursal;
    private javax.swing.JButton revisarCobertura;
    // End of variables declaration//GEN-END:variables
}
