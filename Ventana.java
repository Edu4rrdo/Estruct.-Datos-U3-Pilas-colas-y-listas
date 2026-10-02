import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{
    
Ventana(){

    this.setTitle("Pilas, Colas y Listas Dinamicas");
    this.setSize(1920,1080);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setLocationRelativeTo(null);

//se crea un panel para los botones en forma vertical
    JPanel ventanaPrincipal = new JPanel();
    ventanaPrincipal.setLayout(new BoxLayout(ventanaPrincipal, BoxLayout.Y_AXIS));


    //creamos los botones
    JButton btnCola = new JButton("Cola Dinamica");
    JButton btnPila = new JButton("Pila Dinamica");
    JButton btnLista = new JButton("Lista Dinamica");

//centramos los botones en el panel
    btnCola.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnPila.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnLista.setAlignmentX(Component.CENTER_ALIGNMENT);

    //lo que van a hacer cuando se presionen los botones 
    btnCola.addActionListener(e -> System.out.println("Boton Cola Dinamica presionado"));
    btnPila.addActionListener(e -> System.out.println("Boton Pila Dinamica presionado"));
    btnLista.addActionListener(e -> System.out.println("Boton Lista Dinamica presionado"));

    //ponemos todos los botones de el mismo tamaño 
    Dimension tamanoBtn = new Dimension(800, 20000);
    btnCola.setMaximumSize(tamanoBtn);
    btnPila.setMaximumSize(tamanoBtn);
    btnLista.setMaximumSize(tamanoBtn);


    //añadimos el panel con el espaciado

    ventanaPrincipal.add(Box.createVerticalGlue()); //empuja del centro hacia arriba 
    ventanaPrincipal.add(btnCola);
    ventanaPrincipal.add(Box.createRigidArea(new Dimension(0,200))); //espaciado
    ventanaPrincipal.add(btnPila);
    ventanaPrincipal.add(Box.createRigidArea(new Dimension(0,200))); //espaciado
    ventanaPrincipal.add(btnLista);
    ventanaPrincipal.add(Box.createVerticalGlue()); //empuja del centro hacia abajo

    this.add(ventanaPrincipal);

   

    this.setVisible(true);


}
    
}