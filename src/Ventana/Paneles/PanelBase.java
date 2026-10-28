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
 * ============================================================================
 * CLASE: PanelBase (Formulario visual base para Pilas y Colas)
 * ============================================================================
 * Esta clase unifica la interfaz grafica de usuario para Pila y Cola dinamica.
 *
 * NOTA DIDACTICA SOBRE APACHE NETBEANS:
 * ----------------------------------------------------------------------------
 * 1. ¿Por que algunas partes del codigo estan bloqueadas (fondo gris)?
 *    NetBeans protege el metodo initComponents() y la declaracion de variables
 *    mediante "Guarded Blocks" (// <editor-fold defaultstate="collapsed" desc="Generated Code">).
 *    Esto lo hace NetBeans automaticamente para evitar que se desincronice el codigo
 *    con el archivo visual PanelBase.form.
 * 2. ¿Como y donde se puede cambiar la forma en que se dibujan las Pilas y Colas?
 *    ¡Toda la representacion visual de los nodos NO esta bloqueada! Se encuentra
 *    en el metodo `actualizarVista()`. Puedes modificar libremente colores, fuentes,
 *    tamaños, bordes, flechas o la orientacion de los nodos en dicho metodo.
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
     * METODO DE DIBUJADO VISUAL: actualizarVista()
     * =========================================================================
     * Este metodo dibuja los nodos de la estructura lineal (Pila o Cola).
     *
     * ¿COMO CAMBIAR EL DIBUJO DE LAS PILAS O COLAS?
     * Aqui es donde puedes modificar la apariencia visual:
     * 1. Colores de fondo y borde: Ajusta las variables 'colorFondo', 'colorBorde'
     *    y 'colorTexto' para cambiar la paleta de colores.
     * 2. Formato de nodo: Modifica la variable 'etiquetaTextoNodo' para mostrar
     *    el nodo de forma diferente (ej. "[ Dato: %d | Liga ]").
     * 3. Flechas de conexion: Modifica 'crearFlechaLabel(" → ")' para usar
     *    otra flecha como " ➔ ", " ⇒ ", o cambiar su tamaño.
     * 4. Apuntadores externos: Modifica los textos '▼ TOPE', '▼ BASE', '▼ FRENTE', '▼ FINAL'.
     * =========================================================================
     */
    public void actualizarVista() {
        if (panelBloques == null) return;
        panelBloques.removeAll();

        // Si la estructura no contiene datos, mostrar mensaje descriptivo
        if (this.estructura == null || this.estructura.estaVacia()) {
            JLabel etiquetaMensajeVacio = new JLabel(
                "(La estructura esta vacia - Inserte elementos para visualizar los bloques)",
                SwingConstants.CENTER
            );
            etiquetaMensajeVacio.setFont(new Font("SansSerif", Font.ITALIC, 18));
            etiquetaMensajeVacio.setForeground(new Color(130, 130, 130));
            panelBloques.add(etiquetaMensajeVacio);
        } else {
            boolean esEstructuraCola = (this.estructura instanceof Cola);
            List<Integer> listaElementos = this.estructura.aLista();

            for (int indiceElemento = 0; indiceElemento < listaElementos.size(); indiceElemento++) {
                int valorDatoNodo = listaElementos.get(indiceElemento);
                boolean esPrimerElemento = (indiceElemento == 0);
                boolean esUltimoElemento = (indiceElemento == listaElementos.size() - 1);

                // --- 1. APUNTADORES EXTERNOS (UBICADOS AFUERA DE LOS RECUADROS) ---
                String textoIndicadorExterno = " ";
                Color colorIndicadorExterno = new Color(66, 66, 66);

                if (esEstructuraCola) {
                    // En Cola: Frente es el proximo elemento en salir, Final es el mas reciente
                    if (esPrimerElemento && esUltimoElemento) {
                        textoIndicadorExterno = "▼ FRENTE / FINAL";
                        colorIndicadorExterno = new Color(46, 125, 50); // Verde oscuro
                    } else if (esPrimerElemento) {
                        textoIndicadorExterno = "▼ FRENTE";
                        colorIndicadorExterno = new Color(46, 125, 50); // Verde oscuro
                    } else if (esUltimoElemento) {
                        textoIndicadorExterno = "▼ FINAL";
                        colorIndicadorExterno = new Color(230, 81, 0);  // Naranja
                    }
                } else {
                    // En Pila: Tope es la cima (LIFO), Base es el fondo
                    if (esPrimerElemento && esUltimoElemento) {
                        textoIndicadorExterno = "▼ TOPE / BASE";
                        colorIndicadorExterno = new Color(13, 71, 161); // Azul oscuro
                    } else if (esPrimerElemento) {
                        textoIndicadorExterno = "▼ TOPE";
                        colorIndicadorExterno = new Color(13, 71, 161); // Azul oscuro
                    } else if (esUltimoElemento) {
                        textoIndicadorExterno = "▼ BASE";
                        colorIndicadorExterno = new Color(97, 97, 97);   // Gris neutro
                    }
                }

                // Etiqueta para el texto del apuntador externo
                JLabel etiquetaIndicador = new JLabel(textoIndicadorExterno, SwingConstants.CENTER);
                etiquetaIndicador.setFont(new Font("SansSerif", Font.BOLD, 14));
                etiquetaIndicador.setForeground(colorIndicadorExterno);
                etiquetaIndicador.setAlignmentX(Component.CENTER_ALIGNMENT);

                // --- 2. RECUADRO DIDACTICO DEL NODO [ Dato | Liga ] ---
                String etiquetaTextoNodo;
                if (esUltimoElemento) {
                    etiquetaTextoNodo = "[ " + valorDatoNodo + " | null ]";
                } else {
                    etiquetaTextoNodo = "[ " + valorDatoNodo + " | • ]";
                }

                // Paleta de colores para destacar los nodos
                Color colorFondo;
                Color colorBorde;
                Color colorTexto;

                if (esEstructuraCola) {
                    colorFondo = esPrimerElemento ? new Color(200, 230, 201)
                            : (esUltimoElemento ? new Color(255, 236, 179) : new Color(232, 245, 233));
                    colorBorde = esPrimerElemento ? new Color(56, 142, 60)
                            : (esUltimoElemento ? new Color(245, 127, 23) : new Color(129, 199, 132));
                    colorTexto = esPrimerElemento ? new Color(27, 94, 32)
                            : (esUltimoElemento ? new Color(191, 54, 12) : new Color(33, 33, 33));
                } else {
                    colorFondo = esPrimerElemento ? new Color(187, 222, 251) : new Color(227, 242, 253);
                    colorBorde = esPrimerElemento ? new Color(25, 118, 210) : new Color(100, 181, 246);
                    colorTexto = new Color(13, 71, 161);
                }

                JLabel bloqueNodoVisual = crearBloqueLabel(etiquetaTextoNodo, colorFondo, colorBorde, colorTexto);
                Dimension dimensionPreferida = bloqueNodoVisual.getPreferredSize();
                bloqueNodoVisual.setPreferredSize(new Dimension(Math.max(140, dimensionPreferida.width + 16), 48));
                bloqueNodoVisual.setAlignmentX(Component.CENTER_ALIGNMENT);
                bloqueNodoVisual.setToolTipText("Nodo: Valor = " + valorDatoNodo + (esUltimoElemento ? ", Liga = null" : ", Liga = siguiente nodo"));

                // Contenedor vertical:
                // Arriba: Indicador exterior (Tope / Base / Frente / Final)
                // Abajo: Recuadro del nodo
                javax.swing.JPanel contenedorVerticalNodo = new javax.swing.JPanel();
                contenedorVerticalNodo.setLayout(new javax.swing.BoxLayout(contenedorVerticalNodo, javax.swing.BoxLayout.Y_AXIS));
                contenedorVerticalNodo.setOpaque(false);
                contenedorVerticalNodo.add(etiquetaIndicador);
                contenedorVerticalNodo.add(Box.createVerticalStrut(6));
                contenedorVerticalNodo.add(bloqueNodoVisual);

                panelBloques.add(contenedorVerticalNodo);

                // --- 3. FLECHA DE CONEXION ENTRE NODOS CONSECUTIVOS ---
                if (!esUltimoElemento) {
                    javax.swing.JPanel contenedorFlecha = new javax.swing.JPanel();
                    contenedorFlecha.setLayout(new javax.swing.BoxLayout(contenedorFlecha, javax.swing.BoxLayout.Y_AXIS));
                    contenedorFlecha.setOpaque(false);

                    // Espaciador superior alineado con la altura del indicador
                    JLabel espaciadorAlineacion = new JLabel(" ");
                    espaciadorAlineacion.setFont(new Font("SansSerif", Font.BOLD, 14));
                    espaciadorAlineacion.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contenedorFlecha.add(espaciadorAlineacion);
                    contenedorFlecha.add(Box.createVerticalStrut(6));

                    JLabel etiquetaFlecha = crearFlechaLabel(" → ");
                    etiquetaFlecha.setPreferredSize(new Dimension(40, 48));
                    etiquetaFlecha.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contenedorFlecha.add(etiquetaFlecha);

                    panelBloques.add(contenedorFlecha);
                }
            }
        }

        panelBloques.revalidate();
        panelBloques.repaint();
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
     * Crea un JLabel estilizado como recuadro de nodo con fondo y borde redondeado.
     *
     * @param textoContenido Texto a exhibir dentro del recuadro.
     * @param colorFondo Color de relleno del recuadro.
     * @param colorBorde Color de la linea del borde.
     * @param colorTexto Color de la tipografia.
     * @return Componente JLabel configurado.
     */
    protected JLabel crearBloqueLabel(String textoContenido, Color colorFondo, Color colorBorde, Color colorTexto) {
        JLabel etiquetaBloque = new JLabel(textoContenido, SwingConstants.CENTER);
        etiquetaBloque.setFont(new Font("Monospaced", Font.BOLD, 18));
        etiquetaBloque.setOpaque(true);
        etiquetaBloque.setBackground(colorFondo);
        etiquetaBloque.setForeground(colorTexto);
        etiquetaBloque.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorBorde, 3, true),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        etiquetaBloque.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaBloque;
    }

    /**
     * Sobrecarga para crear un bloque con colores predeterminados azules.
     *
     * @param textoContenido Texto a exhibir dentro del recuadro.
     * @return Componente JLabel con colores azules predeterminados.
     */
    protected JLabel crearBloqueLabel(String textoContenido) {
        return crearBloqueLabel(textoContenido, new Color(227, 242, 253), new Color(25, 118, 210), new Color(13, 71, 161));
    }

    /**
     * Crea un JLabel con flecha entre bloques.
     *
     * @param textoFlecha Simbolo de flecha (ej. " → ").
     * @return Componente JLabel con el simbolo centrado.
     */
    protected JLabel crearFlechaLabel(String textoFlecha) {
        JLabel etiquetaFlecha = new JLabel(textoFlecha, SwingConstants.CENTER);
        etiquetaFlecha.setFont(new Font("SansSerif", Font.BOLD, 20));
        etiquetaFlecha.setForeground(new Color(66, 66, 66));
        etiquetaFlecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaFlecha;
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
