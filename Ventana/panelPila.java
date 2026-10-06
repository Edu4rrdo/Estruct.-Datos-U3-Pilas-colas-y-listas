package Ventana;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import PilasDinamicas.ListaPila;
import PilasDinamicas.Nodo;

/**
 * Panel que representa la interfaz gráfica interactiva para gestionar una Pila
 * Dinámica.
 * Permite realizar operaciones Push (Insertar) y Pop (Eliminar), mostrando
 * visualmente
 * los elementos apilados como "cajones".
 */
public class panelPila extends JPanel {

    // Estructura de datos dinámica (Pila)
    private ListaPila pila;

    // Panel interno donde se dibujarán los "cajones" de la pila
    private JPanel panelVistaPila;

    /**
     * Constructor del panel de la pila.
     * 
     * @param ventanaPadre Referencia a la ventana principal para permitir la
     *                     navegación al menú.
     */
    public panelPila(Ventana ventanaPadre) {
        // Inicializamos la pila vacía
        pila = new ListaPila();

        // Configuración del layout principal del panel (organización vertical)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Título de la interfaz
        JLabel lblPila = new JLabel("Interfaz para crear Pilas");
        lblPila.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- CONTROLES (Caja de texto y Botones) ---
        JPanel panelControles = new JPanel();
        JTextField txtValor = new JTextField(10);
        JButton btnPush = new JButton("Insertar (Push)");
        JButton btnPop = new JButton("Eliminar (Pop)");

        // Agregamos los componentes de control al sub-panel de controles
        panelControles.add(new JLabel("Valor: "));
        panelControles.add(txtValor);
        panelControles.add(btnPush);
        panelControles.add(btnPop);
        panelControles.setMaximumSize(new Dimension(800, 50));

        // --- ÁREA VISUAL (Contenedor de los cajones) ---
        panelVistaPila = new JPanel();
        panelVistaPila.setLayout(new BoxLayout(panelVistaPila, BoxLayout.Y_AXIS));
        panelVistaPila.setBackground(java.awt.Color.WHITE);

        // Agregamos un scroll por si la pila sobrepasa el tamaño visible de la ventana
        JScrollPane scrollPila = new JScrollPane(panelVistaPila);
        scrollPila.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        scrollPila.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Botón para regresar al menú principal
        JButton btnVolverPila = new JButton("Volver al Menu");
        btnVolverPila.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- LÓGICA DE EVENTOS (LISTENERS) ---

        // Evento: Regresar al menú principal
        btnVolverPila.addActionListener(e -> ventanaPadre.mostrarPanel("Menu"));

        // Evento: Operación Push (Insertar elemento en el tope)
        btnPush.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(txtValor.getText());
                pila.insertarInicio(valor); // Inserta el valor en la pila
                txtValor.setText(""); // Limpia la caja de texto
                actualizarVistaPila(); // Redibuja la interfaz visual de la pila
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número entero válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento: Operación Pop (Eliminar elemento del tope)
        btnPop.addActionListener(e -> {
            Integer valor = pila.eliminarInicio(); // Elimina el elemento del tope
            if (valor == null) {
                JOptionPane.showMessageDialog(this, "La pila está vacía.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Se eliminó el valor: " + valor, "Pop",
                        JOptionPane.INFORMATION_MESSAGE);
                actualizarVistaPila(); // Redibuja la interfaz tras eliminar
            }
        });

        // --- ENSAMBLADO DE COMPONENTES EN EL PANEL PRINCIPAL ---
        this.add(Box.createRigidArea(new Dimension(0, 20))); // Margen superior
        this.add(lblPila);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(panelControles);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(scrollPila); // Vista visual que se expande
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnVolverPila);
        this.add(Box.createRigidArea(new Dimension(0, 20))); // Margen inferior

        // Dibujar el estado inicial de la pila
        actualizarVistaPila();
    }

    /**
     * Método encargado de reconstruir gráficamente el contenido de la pila.
     * Recorre los nodos de la estructura de datos y dibuja un "cajón" (JLabel
     * estilizado) por cada nodo.
     */
    private void actualizarVistaPila() {
        // Limpiamos la vista anterior para redibujar
        panelVistaPila.removeAll();

        Nodo temp = pila.P; // Referencia al tope de la pila (Primer nodo)

        if (temp == null) {
            // Estado cuando no hay elementos en la pila
            JLabel lblVacia = new JLabel("(Pila vacía)");
            lblVacia.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 20)));
            panelVistaPila.add(lblVacia);
        } else {
            // Indicador del tope
            JLabel lblTope = new JLabel("--- TOPE DE LA PILA ---");
            lblTope.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));
            panelVistaPila.add(lblTope);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));

            // Recorremos la lista enlazada nodo por nodo (desde el tope hasta el fondo)
            while (temp != null) {
                // Creamos una etiqueta por cada nodo para simular un "cajón"
                JLabel lblCajon = new JLabel(String.valueOf(temp.info), javax.swing.SwingConstants.CENTER);
                lblCajon.setOpaque(true);
                lblCajon.setBackground(new java.awt.Color(173, 216, 230)); // Fondo Azul claro
                lblCajon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2)); // Borde negro
                                                                                                         // grueso
                lblCajon.setPreferredSize(new Dimension(400, 40));
                lblCajon.setMaximumSize(new Dimension(800, 40));
                lblCajon.setMinimumSize(new Dimension(200, 40));
                lblCajon.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Añadimos el cajón al panel de vista
                panelVistaPila.add(lblCajon);

                // Avanzamos al siguiente nodo en la pila
                temp = temp.liga;
            }

            // Indicador de la base de la pila
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel lblFondo = new JLabel("--- BASE DE LA PILA ---");
            lblFondo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(lblFondo);
        }

        // Forzamos al contenedor Swing a refrescar y redibujar sus componentes
        panelVistaPila.revalidate();
        panelVistaPila.repaint();
    }
}
