package Ventana.Paneles.Dibujo;

import ListasDinamicas.ListaCircularDoblementeLigada;
import ListasDinamicas.ListaCircularSimple;
import ListasDinamicas.ListaDinamica;
import ListasDinamicas.ListaDoblementeLigada;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * ============================================================================
 * CLASE: PanelDibujoLista (Lienzo / Panel de Dibujo para Listas Dinamicas)
 * ============================================================================
 * Componente JPanel especializado exclusivamente en el dibujo y renderizado visual
 * de los nodos de cualquier tipo de Lista Dinamica:
 * - Lista Ligada Simple
 * - Lista Doblemente Ligada
 * - Lista Circular Simple
 * - Lista Circular Doblemente Ligada
 *
 * UBICACION:
 * Se encuentra en el paquete 'Ventana.Paneles.Dibujo' para mantener organizadas
 * las clases de dibujo separadas de los formularios principales.
 *
 * RESPONSABILIDAD:
 * Separa por completo la representacion visual de los nodos del formulario visual
 * (PanelTipoLista), haciendo que modificar fuentes, colores, etiquetas de nodo,
 * flechas o animaciones sea directo e independiente de la interfaz de botones.
 *
 * FUNDAMENTOS DIDACTICOS:
 * 1. Lista Simple: [ Dato | Liga al sucesor (• o null) ]
 * 2. Lista Doble:  [ Liga ant | Dato | Liga sig ]
 * 3. Listas Circulares: Todos los enlaces activos '•', cerrando el anillo.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class PanelDibujoLista extends JPanel {

    /**
     * Constructor del panel de dibujo de listas.
     * Configura el fondo y el administrador de diseño horizontal fluido.
     */
    public PanelDibujoLista() {
        this.setBackground(new Color(250, 250, 250));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 20));
    }

    /**
     * =========================================================================
     * METODO PRINCIPAL DE DIBUJADO: actualizarVista()
     * =========================================================================
     * Recibe la lista dinamica y la configuracion de visualizacion, limpia el
     * lienzo y genera los bloques y flechas que representan los nodos en memoria.
     *
     * @param lista Instancia polimorfica de ListaDinamica.
     * @param titulo Nombre del tipo de lista para distinguir variantes.
     * @param flecha Texto o simbolo de flecha de conexion (ej. " -> " o " <-> ").
     * @param cierre Texto explicativo que acompaña al final de la lista.
     */
    public void actualizarVista(ListaDinamica lista, String titulo, String flecha, String cierre) {
        // 1. Limpiar todos los componentes dibujados anteriormente
        this.removeAll();

        // 2. Si no hay lista o esta vacia, desplegar mensaje descriptivo
        if (lista == null) {
            this.revalidate();
            this.repaint();
            return;
        }

        List<Integer> datos = lista.aLista();

        if (datos.isEmpty()) {
            JLabel etiquetaVacia = new JLabel(
                "(La lista esta vacia - Inserte elementos para visualizar los bloques)",
                SwingConstants.CENTER
            );
            etiquetaVacia.setFont(new Font("SansSerif", Font.ITALIC, 18));
            etiquetaVacia.setForeground(new Color(130, 130, 130));
            this.add(etiquetaVacia);
        } else {
            // 3. Indicador de entrada a la lista
            JLabel etiquetaInicio = new JLabel("INICIO >> ", SwingConstants.CENTER);
            etiquetaInicio.setFont(new Font("SansSerif", Font.BOLD, 14));
            etiquetaInicio.setForeground(new Color(13, 71, 161));
            this.add(etiquetaInicio);

            // 4. Identificar caracteristicas de la lista para el formato del nodo
            boolean esCircular = (lista instanceof ListaCircularSimple)
                    || (lista instanceof ListaCircularDoblementeLigada)
                    || (titulo != null && titulo.toLowerCase().contains("circular"));

            boolean esDoble = (lista instanceof ListaDoblementeLigada)
                    || (lista instanceof ListaCircularDoblementeLigada)
                    || (titulo != null && titulo.toLowerCase().contains("doble"));

            int numeroElemento = 1;
            for (Integer elementoDato : datos) {
                boolean esPrimero = (numeroElemento == 1);
                boolean esUltimo = (numeroElemento == datos.size());

                Color colorFondo = esPrimero ? new Color(225, 245, 254)
                        : (esUltimo ? new Color(237, 231, 246) : new Color(245, 245, 245));
                Color colorBorde = esPrimero ? new Color(2, 136, 209)
                        : (esUltimo ? new Color(123, 31, 162) : new Color(158, 158, 158));
                Color colorTexto = new Color(33, 33, 33);

                String etiquetaNodo;
                String descripcionTooltip;

                if (esDoble) {
                    // LISTA DOBLEMENTE LIGADA [ izq | dato | der ]
                    if (esCircular) {
                        etiquetaNodo = "[ • | " + elementoDato + " | • ]";
                        descripcionTooltip = "Nodo: Dato = " + elementoDato + " (Ligas Izq y Der enlazadas)";
                    } else {
                        if (esPrimero && esUltimo) {
                            etiquetaNodo = "[ null | " + elementoDato + " | null ]";
                            descripcionTooltip = "Nodo unico: Dato = " + elementoDato + " (Liga Izq = null, Liga Der = null)";
                        } else if (esPrimero) {
                            etiquetaNodo = "[ null | " + elementoDato + " | • ]";
                            descripcionTooltip = "Primer nodo: Dato = " + elementoDato + " (Liga Izq = null, Liga Der = siguiente)";
                        } else if (esUltimo) {
                            etiquetaNodo = "[ • | " + elementoDato + " | null ]";
                            descripcionTooltip = "Ultimo nodo: Dato = " + elementoDato + " (Liga Izq = anterior, Liga Der = null)";
                        } else {
                            etiquetaNodo = "[ • | " + elementoDato + " | • ]";
                            descripcionTooltip = "Nodo intermedio: Dato = " + elementoDato + " (Liga Izq = anterior, Liga Der = siguiente)";
                        }
                    }
                } else {
                    // LISTA LIGADA SIMPLE [ dato | liga ]
                    if (esCircular) {
                        etiquetaNodo = "[ " + elementoDato + " | • ]";
                        descripcionTooltip = "Nodo: Dato = " + elementoDato + " (Liga al siguiente nodo)";
                    } else {
                        if (esUltimo) {
                            etiquetaNodo = "[ " + elementoDato + " | null ]";
                            descripcionTooltip = "Ultimo nodo: Dato = " + elementoDato + " (Liga = null, fin de la lista)";
                        } else {
                            etiquetaNodo = "[ " + elementoDato + " | • ]";
                            descripcionTooltip = "Nodo: Dato = " + elementoDato + " (Liga al siguiente nodo)";
                        }
                    }
                }

                JLabel bloqueVisual = crearBloqueLabel(etiquetaNodo, colorFondo, colorBorde, colorTexto);
                bloqueVisual.setToolTipText(descripcionTooltip);
                this.add(bloqueVisual);

                // Agregar flecha de conexion si no es el ultimo elemento
                if (!esUltimo) {
                    JLabel etiquetaFlecha = crearFlechaLabel(flecha != null ? flecha : " -> ");
                    this.add(etiquetaFlecha);
                }

                numeroElemento++;
            }

            // Para listas circulares o cierres especiales, mostrar etiqueta explicativa
            if (cierre != null && !cierre.isEmpty()) {
                if (esCircular || !cierre.contains("null")) {
                    JLabel etiquetaCierre = new JLabel(" " + cierre, SwingConstants.CENTER);
                    etiquetaCierre.setFont(new Font("SansSerif", Font.BOLD, 14));
                    etiquetaCierre.setForeground(new Color(66, 66, 66));
                    this.add(etiquetaCierre);
                }
            }
        }

        // 5. Forzar actualizacion grafica
        this.revalidate();
        this.repaint();
    }

    /**
     * Construye un JLabel formateado como bloque o nodo de lista.
     *
     * @param textoContenido Texto a exhibir dentro del bloque.
     * @param colorFondo Color de fondo del bloque.
     * @param colorBorde Color de la linea perimetral.
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
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        etiquetaBloque.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaBloque;
    }

    /**
     * Construye un JLabel que representa el puntero o flecha de enlace entre nodos.
     *
     * @param textoFlecha Simbolo de conexion (ej. " -> " o " <-> ").
     * @return Componente JLabel con la flecha formateada.
     */
    public JLabel crearFlechaLabel(String textoFlecha) {
        JLabel etiquetaFlecha = new JLabel(textoFlecha, SwingConstants.CENTER);
        etiquetaFlecha.setFont(new Font("SansSerif", Font.BOLD, 18));
        etiquetaFlecha.setForeground(new Color(66, 66, 66));
        etiquetaFlecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        return etiquetaFlecha;
    }
}
