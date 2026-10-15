package Ventana.Paneles;

import PilasYColas.Pila;
import Ventana.Ventana;

/**
 * Panel para la Pila Dinamica (LIFO).
 * Hereda de PanelBase, reutilizando toda la interfaz grafica y diseño.
 *
 * @author eduardo-b
 */
public class PanelPila extends PanelBase {

    public PanelPila() {
        super(null, new Pila());
    }

    public PanelPila(Ventana ventanaPadre) {
        super(ventanaPadre, new Pila());
    }
}
