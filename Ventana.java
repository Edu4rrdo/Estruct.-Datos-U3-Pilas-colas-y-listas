import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ============================================================================
 * CLASE: Ventana (Versión Standalone / Prototipo)
 * ============================================================================
 * Ventana gráfica principal que se adapta automáticamente a cualquier monitor
 * y resolución de pantalla (1366x768, 1080p, 2K, 4K, etc.).
 *
 * @author eduardo-b
 * ============================================================================
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la ventana.
     * Configura el título, maximizado automático y centrado en pantalla.
     */
    public Ventana() {
        this.setTitle("Pilas, Colas y Listas Dinámicas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajustar automáticamente las dimensiones de la ventana a la pantalla del usuario
        try {
            // Obtener límites utilizables de la pantalla (descontando barra de tareas/docks)
            Rectangle limites = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            this.setBounds(limites);
            this.setPreferredSize(new Dimension(limites.width, limites.height));
            this.setMinimumSize(new Dimension(1024, 600));
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (Exception e) {
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(pantalla);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        this.setLocationRelativeTo(null);

        // Panel vertical para centrar el menú de botones
        JPanel ventanaPrincipal = new JPanel();
        ventanaPrincipal.setLayout(new BoxLayout(ventanaPrincipal, BoxLayout.Y_AXIS));

        // Creación de botones de navegación
        JButton btnCola = new JButton("Cola Dinámica");
        JButton btnPila = new JButton("Pila Dinámica");
        JButton btnLista = new JButton("Lista Dinámica");

        // Centrado horizontal de los botones en el panel
        btnCola.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPila.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLista.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acciones al presionar cada botón
        btnCola.addActionListener(e -> System.out.println("Botón Cola Dinámica presionado"));
        btnPila.addActionListener(e -> System.out.println("Botón Pila Dinámica presionado"));
        btnLista.addActionListener(e -> System.out.println("Botón Lista Dinámica presionado"));

        // Tamaño proporcional para los botones del menú
        Dimension tamanoBtn = new Dimension(650, 75);
        btnCola.setMaximumSize(tamanoBtn);
        btnCola.setPreferredSize(tamanoBtn);
        btnPila.setMaximumSize(tamanoBtn);
        btnPila.setPreferredSize(tamanoBtn);
        btnLista.setMaximumSize(tamanoBtn);
        btnLista.setPreferredSize(tamanoBtn);

        // Distribución con espaciado elástico para centrado vertical perfecto
        ventanaPrincipal.add(Box.createVerticalGlue()); // Empuja hacia el centro desde arriba
        ventanaPrincipal.add(btnCola);
        ventanaPrincipal.add(Box.createRigidArea(new Dimension(0, 25))); // Espaciado controlado
        ventanaPrincipal.add(btnPila);
        ventanaPrincipal.add(Box.createRigidArea(new Dimension(0, 25))); // Espaciado controlado
        ventanaPrincipal.add(btnLista);
        ventanaPrincipal.add(Box.createVerticalGlue()); // Empuja hacia el centro desde abajo

        this.add(ventanaPrincipal);
        this.setVisible(true);
    }
}