package Ventana;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import PilasDinamicas.ListaPila;
import PilasDinamicas.Nodo;

public class panelPila extends JPanel {

    private ListaPila pila;
    private JPanel panelVistaPila;

    public panelPila(Ventana ventanaPadre) {
        pila = new ListaPila();

        // Configuracion de este panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lblPila = new JLabel("Interfaz para crear Pilas");
        lblPila.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Controles para interactuar con la pila
        JPanel panelControles = new JPanel();
        JTextField txtValor = new JTextField(10);
        JButton btnPush = new JButton("Insertar (Push)");
        JButton btnPop = new JButton("Eliminar (Pop)");

        panelControles.add(new JLabel("Valor: "));
        panelControles.add(txtValor);
        panelControles.add(btnPush);
        panelControles.add(btnPop);
        panelControles.setMaximumSize(new Dimension(800, 50));

        // Area visual para mostrar la pila
        panelVistaPila = new JPanel();
        panelVistaPila.setLayout(new BoxLayout(panelVistaPila, BoxLayout.Y_AXIS));
        panelVistaPila.setBackground(java.awt.Color.WHITE);

        JScrollPane scrollPila = new JScrollPane(panelVistaPila);
        scrollPila.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        scrollPila.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton btnVolverPila = new JButton("Volver al Menu");
        btnVolverPila.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Logica de los botones
        btnVolverPila.addActionListener(e -> ventanaPadre.mostrarPanel("Menu"));

        btnPush.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(txtValor.getText());
                pila.insertarInicio(valor);
                txtValor.setText("");
                actualizarVistaPila();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un numero entero valido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPop.addActionListener(e -> {
            Integer valor = pila.eliminarInicio();
            if (valor == null) {
                JOptionPane.showMessageDialog(this, "La pila esta vacia.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Se elimino el valor: " + valor, "Pop",
                        JOptionPane.INFORMATION_MESSAGE);
                actualizarVistaPila();
            }
        });

        // Agregando elementos al panel
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(lblPila);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(panelControles);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(scrollPila);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(btnVolverPila);
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        actualizarVistaPila();
    }

    private void actualizarVistaPila() {
        panelVistaPila.removeAll();

        Nodo temp = pila.P;
        if (temp == null) {
            JLabel lblVacia = new JLabel("(Pila vacía)");
            lblVacia.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 20)));
            panelVistaPila.add(lblVacia);
        } else {
            JLabel lblTope = new JLabel("--- TOPE DE LA PILA ---");
            lblTope.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));
            panelVistaPila.add(lblTope);
            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));

            while (temp != null) {
                JLabel lblCajon = new JLabel(String.valueOf(temp.info), javax.swing.SwingConstants.CENTER);
                lblCajon.setOpaque(true);
                lblCajon.setBackground(new java.awt.Color(173, 216, 230)); // Azul claro
                lblCajon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
                lblCajon.setPreferredSize(new Dimension(400, 40));
                lblCajon.setMaximumSize(new Dimension(800, 40));
                lblCajon.setMinimumSize(new Dimension(200, 40));
                lblCajon.setAlignmentX(Component.CENTER_ALIGNMENT);

                panelVistaPila.add(lblCajon);
                temp = temp.liga;
            }

            panelVistaPila.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel lblFondo = new JLabel("--- BASE DE LA PILA ---");
            lblFondo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelVistaPila.add(lblFondo);
        }

        panelVistaPila.revalidate();
        panelVistaPila.repaint();
    }
}
