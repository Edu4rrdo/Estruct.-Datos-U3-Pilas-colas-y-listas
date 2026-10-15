package Ventana;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Pantalla donde se elige el tipo de lista. Se construye por codigo.
public class PanelLista extends JPanel {

    private final Ventana ventanaPadre;

    public PanelLista(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Elige un tipo de lista:");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSimple = crearBoton("Lista Ligada Simple", "ListaSimple");
        JButton btnDoble = crearBoton("Lista Doblemente Ligada", "ListaDoble");
        JButton btnCircSimple = crearBoton("Lista Circular Simple", "ListaCircularSimple");
        JButton btnCircDoble = crearBoton("Lista Circular Doblemente Ligada", "ListaCircularDoble");
        JButton btnMenu = crearBoton("Volver al menu", "Menu");

        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(btnSimple);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(btnDoble);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(btnCircSimple);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(btnCircDoble);
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(btnMenu);
        add(Box.createVerticalGlue());
    }

    private JButton crearBoton(String texto, String destino) {
        JButton b = new JButton(texto);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setPreferredSize(new Dimension(400, 70));
        b.setMaximumSize(new Dimension(400, 70));
        b.setFont(new Font("SansSerif", Font.PLAIN, 18));
        b.addActionListener(e -> ventanaPadre.mostrarPanel(destino));
        return b;
    }
}