package Ventana.Paneles;

import PilasYColas.Pila;
import Ventana.Ventana;

/**
 * ============================================================================
 * CLASE: PanelPila
 * ============================================================================
 * Panel visual especifico para interactuar con la Pila Dinamica (LIFO).
 *
 * Hereda de PanelBase, reutilizando toda la interfaz grafica visual
 * (botones, cajas de texto y el area de dibujo horizontal de nodos).
 * Simplemente le suministra a la superclase una instancia nueva de Pila.
 *
 * @author eduardo-b
 * ============================================================================
 */
public class PanelPila extends PanelBase {

    /**
     * Constructor por defecto requerido por Apache NetBeans.
     */
    public PanelPila() {
        super(null, new Pila());
    }

    /**
     * Constructor utilizado por la ventana principal de la aplicacion.
     *
     * @param ventanaPadre Referencia a la ventana JFrame principal para permitir navegacion.
     */
    public PanelPila(Ventana ventanaPadre) {
        super(ventanaPadre, new Pila());
    }
}
