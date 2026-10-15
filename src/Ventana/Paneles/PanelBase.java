/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles;

import PilasYColas.Cola;
import PilasYColas.EstructuraLineal;
import PilasYColas.Pila;
import Ventana.Ventana;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Formulario visual base para Pilas y Colas en Apache NetBeans.
 *
 * Esta clase unifica la interfaz grafica de usuario para Pila y Cola.
 * Se puede editar visualmente desde la pestaña "Diseño" (Design) de NetBeans.
 * Cualquier cambio en colores, botones o tamaños aqui afectara automaticamente
 * tanto a las Pilas como a las Colas.
 *
 * @author eduardo-b
 */
public class PanelBase extends javax.swing.JPanel {

    // Referencia a la ventana principal para navegacion
    protected Ventana ventanaPadre;

    // Referencia a la estructura lineal (Pila o Cola) segun polimorfismo
    protected EstructuraLineal estructura;

    /**
     * Constructor por defecto para que Apache NetBeans pueda cargar
     * el diseñador grafico visual (Diseño / Design).
     */
    public PanelBase() {
        this(null, new Pila());
    }

    public PanelBase(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        this.estructura = null;
    }

    /**
     * Constructor principal utilizado para inicializar con una Pila o Cola.
     *
     * @param ventanaPadre Ventana principal contenedora
     * @param estructura Instancia de Pila o Cola (hijas de EstructuraLineal)
     */
    public PanelBase(Ventana ventanaPadre, EstructuraLineal estructura) {
        this.ventanaPadre = ventanaPadre;
        this.estructura = estructura;
        initComponents();
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setSize(1920, 1080);
        configurarSegunEstructura();
        actualizarVista();
    }

    /**
     * Asigna una nueva estructura lineal y refresca la pantalla.
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
        if (estructura != null) {
            jLabel1.setText(estructura.getTitulo());
            jButton1.setText(estructura.getTextoInsertar());
            jButton2.setText(estructura.getTextoEliminar());
        }
    }

    /**
     * Dibuja los nodos de la estructura lineal (Pila o Cola) de manera horizontal.
     *
     * Fundamentos Didacticos de Estructuras de Datos:
     * 1. RECUADROS HORIZONTALES:
     *    Los nodos se alinean de izquierda a derecha simulando el encadenamiento
     *    lineal en memoria con punteros directos.
     * 2. APUNTADORES EXTERNOS AFUERA DE LOS RECUADROS:
     *    Variables de referencia externa como FRENTE y FINAL (en Colas) o TOPE y BASE
     *    (en Pilas) no residen dentro del nodo, sino que apuntan a el desde el exterior.
     *    Por ello, se posicionan arriba de los recuadros con indicadores '▼'.
     * 3. CAMPO DE LIGA SIN LA PALABRA 'LIGA':
     *    Dentro del recuadro se muestra unicamente el dato almacenado y el valor
     *    del enlace: '•' (referencia activa hacia el siguiente nodo) o 'null'
     *    (cuando no apunta a ningun otro nodo, indicando el fin de la estructura).
     */
    public void actualizarVista() {
        if (panelBloques == null) return;
        panelBloques.removeAll();

        if (estructura == null || estructura.estaVacia()) {
            JLabel lblVacia = new JLabel("(La estructura esta vacia - Inserte elementos para visualizar los bloques)", SwingConstants.CENTER);
            lblVacia.setFont(new Font("SansSerif", Font.ITALIC, 18));
            lblVacia.setForeground(new Color(130, 130, 130));
            panelBloques.add(lblVacia);
        } else {
            boolean esCola = (estructura instanceof Cola);
            List<Integer> elementos = estructura.aLista();

            for (int i = 0; i < elementos.size(); i++) {
                int valor = elementos.get(i);
                boolean esPrimero = (i == 0);
                boolean esUltimo = (i == elementos.size() - 1);

                // --- 1. APUNTADORES EXTERNOS (FUERA DE LOS RECUADROS) ---
                String textoIndicador = " ";
                Color colorIndicador = new Color(66, 66, 66);

                if (esCola) {
                    // En Cola: Frente es el proximo en salir, Final es el mas reciente
                    if (esPrimero && esUltimo) {
                        textoIndicador = "▼ FRENTE / FINAL";
                        colorIndicador = new Color(46, 125, 50); // Verde oscuro
                    } else if (esPrimero) {
                        textoIndicador = "▼ FRENTE";
                        colorIndicador = new Color(46, 125, 50); // Verde oscuro
                    } else if (esUltimo) {
                        textoIndicador = "▼ FINAL";
                        colorIndicador = new Color(230, 81, 0);  // Naranja
                    }
                } else {
                    // En Pila: Tope es la cima (LIFO), Base es el fondo
                    if (esPrimero && esUltimo) {
                        textoIndicador = "▼ TOPE / BASE";
                        colorIndicador = new Color(13, 71, 161); // Azul oscuro
                    } else if (esPrimero) {
                        textoIndicador = "▼ TOPE";
                        colorIndicador = new Color(13, 71, 161); // Azul oscuro
                    } else if (esUltimo) {
                        textoIndicador = "▼ BASE";
                        colorIndicador = new Color(97, 97, 97);   // Gris neutro
                    }
                }

                // Etiqueta del apuntador externo (situada fuera del recuadro)
                JLabel lblIndicador = new JLabel(textoIndicador, SwingConstants.CENTER);
                lblIndicador.setFont(new Font("SansSerif", Font.BOLD, 14));
                lblIndicador.setForeground(colorIndicador);
                lblIndicador.setAlignmentX(Component.CENTER_ALIGNMENT);

                // --- 2. RECUADRO DEL NODO (SIN LA PALABRA 'LIGA', SOLO LA LIGA) ---
                // Nodo con dos campos didacticos: [ Dato | Liga ]
                // Si es el ultimo nodo, su liga es 'null'; de lo contrario, es el apuntador '•'
                String etiqueta;
                if (esUltimo) {
                    etiqueta = "[ " + valor + " | null ]";
                } else {
                    etiqueta = "[ " + valor + " | • ]";
                }

                // Paleta de colores didactica para diferenciar nodos destacados
                Color fondo, borde, texto;
                if (esCola) {
                    fondo = esPrimero ? new Color(200, 230, 201) : (esUltimo ? new Color(255, 236, 179) : new Color(232, 245, 233));
                    borde = esPrimero ? new Color(56, 142, 60) : (esUltimo ? new Color(245, 127, 23) : new Color(129, 199, 132));
                    texto = esPrimero ? new Color(27, 94, 32) : (esUltimo ? new Color(191, 54, 12) : new Color(33, 33, 33));
                } else {
                    fondo = esPrimero ? new Color(187, 222, 251) : new Color(227, 242, 253);
                    borde = esPrimero ? new Color(25, 118, 210) : new Color(100, 181, 246);
                    texto = new Color(13, 71, 161);
                }

                JLabel bloque = crearBloqueLabel(etiqueta, fondo, borde, texto);
                Dimension pref = bloque.getPreferredSize();
                bloque.setPreferredSize(new Dimension(Math.max(140, pref.width + 16), 48));
                bloque.setAlignmentX(Component.CENTER_ALIGNMENT);
                bloque.setToolTipText("Nodo: Valor = " + valor + (esUltimo ? ", Liga = null" : ", Liga = siguiente nodo"));

                // Contenedor vertical para cada elemento:
                // Arriba: Apuntador externo (Frente / Final / Tope / Base)
                // Abajo: Recuadro del nodo
                javax.swing.JPanel nodoPanel = new javax.swing.JPanel();
                nodoPanel.setLayout(new javax.swing.BoxLayout(nodoPanel, javax.swing.BoxLayout.Y_AXIS));
                nodoPanel.setOpaque(false);
                nodoPanel.add(lblIndicador);
                nodoPanel.add(Box.createVerticalStrut(6));
                nodoPanel.add(bloque);

                // Agregar el nodo al panel de visualizacion horizontal
                panelBloques.add(nodoPanel);

                // --- 3. FLECHA DE ENLACE HORIZONTAL ENTRE RECUEADROS ---
                if (!esUltimo) {
                    javax.swing.JPanel flechaPanel = new javax.swing.JPanel();
                    flechaPanel.setLayout(new javax.swing.BoxLayout(flechaPanel, javax.swing.BoxLayout.Y_AXIS));
                    flechaPanel.setOpaque(false);

                    // Espaciador superior para que la flecha quede nivelada exactamente con el recuadro
                    JLabel spacer = new JLabel(" ");
                    spacer.setFont(new Font("SansSerif", Font.BOLD, 14));
                    spacer.setAlignmentX(Component.CENTER_ALIGNMENT);
                    flechaPanel.add(spacer);
                    flechaPanel.add(Box.createVerticalStrut(6));

                    JLabel flecha = crearFlechaLabel(" → ");
                    flecha.setPreferredSize(new Dimension(40, 48));
                    flecha.setAlignmentX(Component.CENTER_ALIGNMENT);
                    flechaPanel.add(flecha);

                    panelBloques.add(flechaPanel);
                }
            }
        }

        panelBloques.revalidate();
        panelBloques.repaint();
    }

    /**
     * Valida y lee un numero entero desde el campo de texto.
     */
    protected Integer leerEntero(JTextField txt) {
        if (txt == null) return null;
        String texto = txt.getText().trim();
        if (texto.isEmpty()) {
            mostrarError("Por favor, ingrese un valor.");
            txt.requestFocusInWindow();
            return null;
        }
        try {
            int valor = Integer.parseInt(texto);
            txt.setText("");
            txt.requestFocusInWindow();
            return valor;
        } catch (NumberFormatException e) {
            mostrarError("Ingrese solo valores enteros validos.");
            txt.requestFocusInWindow();
            return null;
        }
    }

    /**
     * Crea un JLabel estilizado como bloque rectangular con borde solido.
     */
    protected JLabel crearBloqueLabel(String texto, Color fondo, Color borde, Color textoColor) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 18));
        lbl.setOpaque(true);
        lbl.setBackground(fondo);
        lbl.setForeground(textoColor);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borde, 3, true),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    /**
     * Sobrecarga para crear bloque con colores predeterminados.
     */
    protected JLabel crearBloqueLabel(String texto) {
        return crearBloqueLabel(texto, new Color(227, 242, 253), new Color(25, 118, 210), new Color(13, 71, 161));
    }

    /**
     * Crea un JLabel con flecha entre bloques.
     */
    protected JLabel crearFlechaLabel(String flecha) {
        JLabel lbl = new JLabel(flecha, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 20));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    protected void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void volverAlMenu() {
        if (ventanaPadre != null) {
            ventanaPadre.mostrarPanel("Menu");
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
        panelBloques.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));
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
        Integer valor = leerEntero(jTextField1);
        if (valor == null) return;
        if (estructura != null) {
            estructura.insertar(valor);
            actualizarVista();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (estructura == null || estructura.estaVacia()) {
            mostrarError("La estructura esta vacia, no hay elementos para eliminar.");
            return;
        }
        Integer eliminado = estructura.eliminar();
        if (eliminado != null) {
            mostrarExito("Elemento eliminado: " + eliminado);
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
            JFrame frame = new JFrame("Prueba de PanelBase (Pila y Cola)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.add(new PanelBase(null, new Pila()));
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jButton1;
    protected javax.swing.JButton jButton2;
    protected javax.swing.JButton jButton3;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextField jTextField1;
    protected javax.swing.JPanel panelBloques;
    // End of variables declaration//GEN-END:variables
}
