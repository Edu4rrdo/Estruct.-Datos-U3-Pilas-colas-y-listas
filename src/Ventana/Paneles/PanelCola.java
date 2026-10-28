package Ventana.Paneles;

import PilasYColas.Cola;
import Ventana.Ventana;

/**
 * ============================================================================
 * CLASE: PanelCola
 * ============================================================================
 * Panel visual especifico para interactuar con la Cola Dinamica (FIFO).
 *
 * Hereda de PanelBase, reutilizando toda la interfaz grafica visual
 * (botones, cajas de texto y el area de dibujo horizontal de nodos).
 * Simplemente le suministra a la superclase una instancia nueva de Cola.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class PanelCola extends PanelBase {

    /**
     * Constructor por defecto requerido por Apache NetBeans.
     */
    public PanelCola() {
        super(null, new Cola());
    }

    /**
     * Constructor utilizado por la ventana principal de la aplicacion.
     *
     * @param ventanaPadre Referencia a la ventana JFrame principal para permitir navegacion.
     */
    public PanelCola(Ventana ventanaPadre) {
        super(ventanaPadre, new Cola());
    }
}
