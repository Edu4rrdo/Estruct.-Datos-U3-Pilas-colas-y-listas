package Ventana;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panelCola extends JPanel {

    public panelCola(Ventana ventanaPadre) {
        // Configuracion de este panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lblCola = new JLabel("Interfaz para crear Colas");
        lblCola.setAlignmentX(Component.TOP_ALIGNMENT);

        JButton btnVolverCola = new JButton("Volver al Menu");
        btnVolverCola.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        
        // Llamamos al metodo de la ventana padre
        btnVolverCola.addActionListener(e -> ventanaPadre.mostrarPanel("Menu"));

        // Agregando elementos
        this.add(Box.createVerticalGlue());
        this.add(lblCola);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(btnVolverCola);
        this.add(Box.createVerticalGlue());
    }

}