package Ventana.Paneles;

import Ventana.Ventana;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Clase padre abstracta para los paneles de estructuras de datos (Pila, Cola, Listas).
 * Provee metodos comunes para lectura de datos, creacion de bloques visuales (Labels con recuadros)
 * y navegacion hacia la ventana principal.
 *
 * @author eduardo-b
 */
public abstract class PanelBase extends JPanel {

    protected Ventana ventanaPadre;

    public PanelBase() {
        this(null);
    }

    public PanelBase(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
    }

    public Ventana getVentanaPadre() {
        return ventanaPadre;
    }

    public void setVentanaPadre(Ventana ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
    }

    /**
     * Valida y lee un numero entero desde un campo de texto.
     * Si no es valido, muestra una alerta y retorna null.
     */
    protected Integer leerEntero(JTextField txt) {
        if (txt == null) return null;
        String texto = txt.getText().trim();
        if (texto.isEmpty()) {
            mostrarError("Por favor, ingrese un valor.");
            txt.requestFocusInWindow();
            return null;
        }
        try {
            int valor = Integer.parseInt(texto);
            txt.setText("");
            txt.requestFocusInWindow();
            return valor;
        } catch (NumberFormatException e) {
            mostrarError("Ingrese solo valores enteros validos.");
            txt.requestFocusInWindow();
            return null;
        }
    }

    /**
     * Crea un JLabel estilizado como bloque/recuadro rectangular con borde solido.
     */
    protected JLabel crearBloqueLabel(String texto, Color fondo, Color borde, Color textoColor) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 18));
        lbl.setOpaque(true);
        lbl.setBackground(fondo);
        lbl.setForeground(textoColor);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borde, 3, true),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    /**
     * Sobrecarga para crear bloque con colores predeterminados.
     */
    protected JLabel crearBloqueLabel(String texto) {
        return crearBloqueLabel(texto, new Color(227, 242, 253), new Color(25, 118, 210), new Color(13, 71, 161));
    }

    /**
     * Crea un JLabel con flecha o separador entre bloques.
     */
    protected JLabel crearFlechaLabel(String flecha) {
        JLabel lbl = new JLabel(flecha, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 20));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    protected void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void volverAlMenu() {
        if (ventanaPadre != null) {
            ventanaPadre.mostrarPanel("Menu");
        }
    }

    /**
     * Metodo abstracto que cada clase hija implementa para refrescar
     * la representacion visual de bloques y datos.
     */
    public abstract void actualizarVista();
}
