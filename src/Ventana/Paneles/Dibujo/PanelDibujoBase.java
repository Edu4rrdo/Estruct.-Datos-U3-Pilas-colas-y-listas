/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles.Dibujo;

import PilasYColas.Cola;
import PilasYColas.EstructuraLineal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * ============================================================================
 * CLASE: PanelDibujoBase (JPanel Form para Pilas y Colas en Apache NetBeans)
 * ============================================================================
 * Formulario visual (JPanel Form) especializado exclusivamente en el dibujo y
 * renderizado visual de los nodos de Pilas y Colas dinamicas.
 *
 * SOPORTE PARA APACHE NETBEANS:
 * Cuenta con su archivo visual 'PanelDibujoBase.form' asociado, permitiendo que
 * en NetBeans aparezca la pestaña "Diseño" (Design) para modificar visualmente
 * sus propiedades (color de fondo, espaciado de layout, etc.).
 *
 * @author eduardo-b
 * ============================================================================
 */
public class PanelDibujoBase extends javax.swing.JPanel {

    /**
     * Constructor del formulario visual de dibujo para Pilas y Colas.
     */
    public PanelDibujoBase() {
        initComponents();
    }

    /**
     * =========================================================================
     * METODO PRINCIPAL DE DIBUJADO: actualizarVista()
     * =========================================================================
     * Recibe la estructura lineal (Pila o Cola), limpia los dibujos anteriores
     * y genera dinamicamente los bloques graficos que representan los nodos en memoria.
     *
     * @param estructura Instancia de Pila o Cola a dibujar en pantalla.
     */
    public void actualizarVista(EstructuraLineal estructura) {
        // 1. Limpiar todos los componentes visuales dibujados anteriormente
        this.removeAll();

        // 2. Si no hay estructura o se encuentra vacia, mostrar mensaje informativo
        if (estructura == null || estructura.estaVacia()) {
            JLabel etiquetaMensajeVacio = new JLabel(
                "(La estructura esta vacia - Inserte elementos para visualizar los bloques)",
                SwingConstants.CENTER
            );
            etiquetaMensajeVacio.setFont(new Font("SansSerif", Font.ITALIC, 18));
            etiquetaMensajeVacio.setForeground(new Color(130, 130, 130));
            this.add(etiquetaMensajeVacio);
        } else {
            // 3. Determinar el tipo de estructura para adaptar los indicadores
            boolean esEstructuraCola = (estructura instanceof Cola);
            List<Integer> listaElementos = estructura.aLista();

            for (int indiceElemento = 0; indiceElemento < listaElementos.size(); indiceElemento++) {
                int valorDatoNodo = listaElementos.get(indiceElemento);
                boolean esPrimerElemento = (indiceElemento == 0);
                boolean esUltimoElemento = (indiceElemento == listaElementos.size() - 1);

                // --- A. APUNTADORES EXTERNOS (SE DIBUJAN ARRIBA DEL NODO) ---
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

                JLabel etiquetaIndicador = new JLabel(textoIndicadorExterno, SwingConstants.CENTER);
                etiquetaIndicador.setFont(new Font("SansSerif", Font.BOLD, 14));
                etiquetaIndicador.setForeground(colorIndicadorExterno);
                etiquetaIndicador.setAlignmentX(Component.CENTER_ALIGNMENT);

                // --- B. RECUADRO DEL NODO [ Dato | Liga ] ---
                String etiquetaTextoNodo;
                if (esUltimoElemento) {
                    etiquetaTextoNodo = "[ " + valorDatoNodo + " | null ]";
                } else {
                    etiquetaTextoNodo = "[ " + valorDatoNodo + " | • ]";
                }

                // Paleta de colores para los recuadros
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
                JPanel contenedorVerticalNodo = new JPanel();
                contenedorVerticalNodo.setLayout(new BoxLayout(contenedorVerticalNodo, BoxLayout.Y_AXIS));
                contenedorVerticalNodo.setOpaque(false);
                contenedorVerticalNodo.add(etiquetaIndicador);
                contenedorVerticalNodo.add(Box.createVerticalStrut(6));
                contenedorVerticalNodo.add(bloqueNodoVisual);

                this.add(contenedorVerticalNodo);

                // --- C. FLECHA DE CONEXION ENTRE NODOS CONSECUTIVOS ---
                if (!esUltimoElemento) {
                    JPanel contenedorFlecha = new JPanel();
                    contenedorFlecha.setLayout(new BoxLayout(contenedorFlecha, BoxLayout.Y_AXIS));
                    contenedorFlecha.setOpaque(false);

                    // Espaciador para nivelar la flecha con el recuadro del nodo
                    JLabel espaciadorAlineacion = new JLabel(" ");
                    espaciadorAlineacion.setFont(new Font("SansSerif", Font.BOLD, 14));
                    espaciadorAlineacion.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contenedorFlecha.add(espaciadorAlineacion);
                    contenedorFlecha.add(Box.createVerticalStrut(6));

                    JLabel etiquetaFlecha = crearFlechaLabel(" → ");
                    etiquetaFlecha.setPreferredSize(new Dimension(40, 48));
                    etiquetaFlecha.setAlignmentX(Component.CENTER_ALIGNMENT);
                    contenedorFlecha.add(etiquetaFlecha);

                    this.add(contenedorFlecha);
                }
            }
        }

        // 4. Forzar al layout a recalcular dimensiones y redibujar
        this.revalidate();
        this.repaint();
    }

    /**
     * Construye un JLabel estilizado con forma de bloque rectangular y bordes suaves.
     *
     * @param textoContenido Texto a exhibir dentro del nodo.
     * @param colorFondo Color de relleno del bloque.
     * @param colorBorde Color de la linea del borde.
     * @param colorTexto Color de la tipografia.
     * @return Componente JLabel configurado.
     */
    public JLabel crearBloqueLabel(String textoContenido, Color colorFondo, Color colorBorde, Color colorTexto) {
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
     * Construye un JLabel que representa el puntero o flecha entre nodos.
     *
     * @param textoFlecha Simbolo de conexion (ej. " → ").
     * @return Componente JLabel con la flecha formateada.
     */
    public JLabel crearFlechaLabel(String textoFlecha) {
        JLabel etiquetaFlecha = new JLabel(textoFlecha, SwingConstants.CENTER);
        etiquetaFlecha.setFont(new Font("SansSerif", Font.BOLD, 20));
        etiquetaFlecha.setForeground(new Color(66, 66, 66));
        etiquetaFlecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaFlecha;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(250, 250, 250));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 20));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
