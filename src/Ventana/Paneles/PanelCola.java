package Ventana.Paneles;

import PilasYColas.Cola;
import Ventana.Ventana;

/**
 * Panel para la Cola Dinamica (FIFO).
 * Hereda de PanelBase, reutilizando toda la interfaz grafica y diseño.
 *
 * @author eduardo-b
 */
public class PanelCola extends PanelBase {

    public PanelCola() {
        super(null, new Cola());
    }

    public PanelCola(Ventana ventanaPadre) {
        super(ventanaPadre, new Cola());
    }
}
