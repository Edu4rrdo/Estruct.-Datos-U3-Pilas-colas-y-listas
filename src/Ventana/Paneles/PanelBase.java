/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles;

import PilasYColas.EstructuraLineal;
import PilasYColas.Pila;
import Ventana.Paneles.Dibujo.PanelDibujoBase;
import Ventana.Ventana;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ============================================================================
 * CLASE: PanelBase (Formulario visual base para Pilas y Colas)
 * ============================================================================
 * Esta clase unifica la interfaz grafica de usuario para Pila y Cola dinamica.
 * Contiene los controles de entrada (botones de insertar, eliminar, caja de texto).
 *
 * ARQUITECTURA MODULAR DE DIBUJO:
 * ----------------------------------------------------------------------------
 * Todo el dibujo y renderizado de los nodos ya NO se realiza dentro de esta clase,
 * sino en la clase especializada:
 * {@link PanelDibujoBase}
 *
 * De este modo, si deseas cambiar la apariencia visual de los nodos (colores,
 * orientacion, bordes, flechas o indicadores), solo debes modificar la clase
 * PanelDibujoBase sin alterar este formulario de NetBeans.
 * ============================================================================
 *
 * @author eduardo-b
 */
public class PanelBase extends javax.swing.JPanel {

    /**
     * Referencia a la ventana JFrame principal para permitir la navegacion al menu.
     */
    protected Ventana ventanaPadre;

    /**
     * Referencia polimorfica a la estructura de datos activa (Pila o Cola).
     */
    protected EstructuraLineal estructura;

    /**
     * Componente JPanel dedicado exclusivamente al dibujo grafico de los nodos.
     */
    protected PanelDibujoBase panelDibujoBase;

    /**
     * Constructor por defecto para que Apache NetBeans pueda cargar
     * el diseñador grafico visual (Diseño / Design).
     */
    public PanelBase() {
        this(null, new Pila());
    }

    /**
     * Constructor con referencia a la ventana principal.
     *
     * @param ventanaPadre Ventana contenedora principal.
     */
    public PanelBase(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        this.estructura = null;
    }

    /**
     * Constructor principal utilizado para inicializar el panel con una Pila o Cola.
     *
     * @param ventanaPadre Ventana contenedora principal.
     * @param estructura Instancia de Pila o Cola (subclases de EstructuraLineal).
     */
    public PanelBase(Ventana ventanaPadre, EstructuraLineal estructura) {
        this.ventanaPadre = ventanaPadre;
        this.estructura = estructura;
        initComponents();

        // Inicializar el panel de dibujo especializado y asignarlo al JScrollPane
        this.panelDibujoBase = new PanelDibujoBase();
        jScrollPane1.setViewportView(this.panelDibujoBase);

        this.setPreferredSize(new Dimension(1920, 1080));
        this.setSize(1920, 1080);
        configurarSegunEstructura();
        actualizarVista();
    }

    /**
     * Asigna una nueva estructura lineal y refresca la interfaz visual.
     *
     * @param estructura Instancia de Pila o Cola.
     */
    public void setEstructura(EstructuraLineal estructura) {
        this.estructura = estructura;
        configurarSegunEstructura();
        actualizarVista();
    }

    public EstructuraLineal getEstructura() {
        return this.estructura;
    }

    public Ventana getVentanaPadre() {
        return this.ventanaPadre;
    }

    public void setVentanaPadre(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
    }

    /**
     * Configura los textos del titulo y los botones segun la estructura recibida.
     */
    private void configurarSegunEstructura() {
        if (this.estructura != null) {
            jLabel1.setText(this.estructura.getTitulo());
            jButton1.setText(this.estructura.getTextoInsertar());
            jButton2.setText(this.estructura.getTextoEliminar());
        }
    }

    /**
     * =========================================================================
     * METODO: actualizarVista()
     * =========================================================================
     * Delega la responsabilidad de dibujo al panel especializado PanelDibujoBase.
     * =========================================================================
     */
    public void actualizarVista() {
        if (this.panelDibujoBase != null) {
            this.panelDibujoBase.actualizarVista(this.estructura);
        }
    }

    /**
     * Valida y lee un numero entero desde el campo de texto.
     *
     * @param campoTextoEntrada Campo JTextField desde el cual se extraera el texto.
     * @return El numero entero parseado, o null si la entrada es invalida.
     */
    protected Integer leerEntero(JTextField campoTextoEntrada) {
        if (campoTextoEntrada == null) return null;
        String textoCapturado = campoTextoEntrada.getText().trim();
        if (textoCapturado.isEmpty()) {
            mostrarError("Por favor, ingrese un valor.");
            campoTextoEntrada.requestFocusInWindow();
            return null;
        }
        try {
            int valorConvertido = Integer.parseInt(textoCapturado);
            campoTextoEntrada.setText("");
            campoTextoEntrada.requestFocusInWindow();
            return valorConvertido;
        } catch (NumberFormatException excepcionFormato) {
            mostrarError("Ingrese solo valores enteros validos.");
            campoTextoEntrada.requestFocusInWindow();
            return null;
        }
    }

    /**
     * Metodo delegado para construir un bloque label en el panel de dibujo.
     */
    public JLabel crearBloqueLabel(String textoContenido, Color colorFondo, Color colorBorde, Color colorTexto) {
        if (this.panelDibujoBase != null) {
            return this.panelDibujoBase.crearBloqueLabel(textoContenido, colorFondo, colorBorde, colorTexto);
        }
        return new JLabel(textoContenido);
    }

    /**
     * Metodo delegado para construir una flecha label en el panel de dibujo.
     */
    public JLabel crearFlechaLabel(String textoFlecha) {
        if (this.panelDibujoBase != null) {
            return this.panelDibujoBase.crearFlechaLabel(textoFlecha);
        }
        return new JLabel(textoFlecha);
    }

    /**
     * Despliega una ventana emergente de error.
     *
     * @param mensajeError Texto explicativo del error.
     */
    protected void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Despliega una ventana emergente de confirmacion de exito.
     *
     * @param mensajeExito Texto explicativo del resultado favorable.
     */
    protected void mostrarExito(String mensajeExito) {
        JOptionPane.showMessageDialog(this, mensajeExito, "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Notifica a la ventana principal para volver a mostrar el menu inicial.
     */
    protected void volverAlMenu() {
        if (this.ventanaPadre != null) {
            this.ventanaPadre.mostrarPanel("Menu");
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelBloques = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estructura Dinamica");

        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Insertar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        panelBloques.setBackground(new java.awt.Color(250, 250, 250));
        jScrollPane1.setViewportView(panelBloques);

        jButton3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton3.setText("Volver al Menu");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(560, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(368, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Integer valorAInsertar = leerEntero(jTextField1);
        if (valorAInsertar == null) return;
        if (this.estructura != null) {
            this.estructura.insertar(valorAInsertar);
            actualizarVista();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.estructura == null || this.estructura.estaVacia()) {
            mostrarError("La estructura esta vacia, no hay elementos para eliminar.");
            return;
        }
        Integer valorEliminado = this.estructura.eliminar();
        if (valorEliminado != null) {
            mostrarExito("Elemento eliminado: " + valorEliminado);
            actualizarVista();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        volverAlMenu();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Metodo main opcional para ejecutar y probar directamente este panel
     * como si fuera una ventana (JFrame) independiente con Shift+F6 en NetBeans.
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame framePrueba = new JFrame("Prueba de PanelBase (Pila y Cola)");
            framePrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framePrueba.setSize(1200, 800);
            framePrueba.setLocationRelativeTo(null);
            framePrueba.add(new PanelBase(null, new Pila()));
            framePrueba.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelBloques;
    // End of variables declaration//GEN-END:variables
}
