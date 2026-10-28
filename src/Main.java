import Ventana.Ventana;

/**
 * ============================================================================
 * CLASE PRINCIPAL: Main
 * ============================================================================
 * Punto de entrada principal para iniciar la ejecucion del programa.
 *
 * Se encarga de instanciar la ventana grafica inicial (JFrame) que contiene
 * el menu principal y los paneles para interactuar con:
 * - Pilas Dinamicas (LIFO)
 * - Colas Dinamicas (FIFO)
 * - Listas Dinamicas (Ligada Simple, Doblemente Ligada, Circulares)
 *
 * @author eduardo-b
 * ============================================================================
 */
public class Main {

    /**
     * Metodo principal (entry point) de la aplicacion en Java.
     *
     * @param argumentosLineaComandos Argumentos pasados por consola (no requeridos).
     */
    public static void main(String[] argumentosLineaComandos) {
        // Ejecucion en el hilo de despacho de eventos de Swing (Event Dispatch Thread)
        // para garantizar la seguridad y fluidez de la interfaz grafica
        javax.swing.SwingUtilities.invokeLater(() -> {
            Ventana ventanaPrincipal = new Ventana();
            ventanaPrincipal.setVisible(true);
        });
    }
}
