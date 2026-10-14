package Ventana.Paneles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * ============================================================================
 * CLASE: BotonCircular
 * ============================================================================
 * Esta clase hereda de JButton (Swing) y personaliza su dibujo para que
 * tenga una forma CIRCULAR en lugar del rectangulo por defecto.
 * 
 * CONCEPTOS CLAVE DE SWING PARA APRENDER:
 * ----------------------------------------------------------------------------
 * 1. setContentAreaFilled(false): Le dice a Swing que NO pinte el rectangulo
 *    de fondo nativo del sistema operativo.
 * 2. setFocusPainted(false): Desactiva el recuadro punteado de enfoque.
 * 3. setBorderPainted(false): Desactiva el borde rectangular nativo.
 * 4. setOpaque(false): Hace que las esquinas del componente sean transparentes.
 * 5. paintComponent(Graphics g): Sobrescribimos este metodo para dibujar con
 *    Graphics2D el circulo relleno y el texto centrado.
 * 6. contains(int x, int y): Sobrescribimos la deteccion del clic del mouse
 *    usando el teorema de Pitagoras (distancia al centro <= radio), para que
 *    hacer clic en las esquinas transparentes NO dispare el boton.
 * ============================================================================
 * 
 * @author eduardo-b
 */
public class BotonCircular extends JButton {

    // Colores para los diferentes estados del boton
    private Color colorNormal = new Color(33, 150, 243);       // Azul principal
    private Color colorHover = new Color(30, 136, 229);        // Azul un poco mas oscuro al pasar el raton
    private Color colorPresionado = new Color(21, 101, 192);   // Azul oscuro al presionar
    private Color colorBorde = new Color(25, 118, 210);        // Borde sutil del circulo

    /**
     * Constructor que recibe el texto o simbolo a mostrar en el boton (ej. "?", "i", "+").
     * Nota: En botones circulares se recomienda usar textos cortos de 1 a 3 caracteres o simbolos.
     * 
     * @param texto Texto a mostrar en el centro del circulo.
     */
    public BotonCircular(String texto) {
        super(texto);
        inicializarBoton();
    }

    /**
     * Constructor por defecto sin texto.
     */
    public BotonCircular() {
        this("");
    }

    /**
     * Configura las propiedades iniciales de Swing para habilitar la forma circular.
     */
    private void inicializarBoton() {
        // PASO 1: Desactivar los estilos rectangulares por defecto de Swing
        setContentAreaFilled(false); // No pintar el fondo rectangular
        setFocusPainted(false);       // No pintar el marco de foco
        setBorderPainted(false);     // No pintar el borde rectangular
        setOpaque(false);            // Las 4 esquinas deben ser transparentes

        // PASO 2: Estilos visuales del texto
        setForeground(Color.WHITE);                     // Texto en color blanco
        setFont(new Font("SansSerif", Font.BOLD, 15));   // Fuente clara y en negrita
        setCursor(new Cursor(Cursor.HAND_CURSOR));       // Cambiar cursor a mano interactiva

        // Tamaño sugerido por defecto (cuadrado para garantizar que sea un circulo perfecto)
        setPreferredSize(new Dimension(36, 36));
    }

    /**
     * Sobrescribimos el metodo de dibujado del componente.
     * Aqui es donde dibujamos el circulo usando Graphics2D.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Convertimos el objeto Graphics a Graphics2D para usar caracteristicas avanzadas
        Graphics2D g2 = (Graphics2D) g.create();

        // ACTIVAR ANTIALIASING:
        // Esto es muy importante en figuras curvas (circulos) para que los
        // bordes se vean suaves y no con "dientes de sierra" (pixelados).
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // DETERMINAR EL COLOR SEGUN EL ESTADO:
        // getModel().isArmed(): Retorna true cuando el usuario hace clic sostenido.
        // getModel().isRollover(): Retorna true cuando el cursor del mouse esta encima del boton.
        if (getModel().isArmed()) {
            g2.setColor(colorPresionado);
        } else if (getModel().isRollover()) {
            g2.setColor(colorHover);
        } else {
            g2.setColor(colorNormal);
        }

        // CALCULAR EL DIAMETRO Y CENTRADO:
        // Tomamos el menor lado (ancho o alto) para que siempre sea un circulo simetrico
        int diametro = Math.min(getWidth(), getHeight()) - 2;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        // DIBUJAR EL CIRCULO RELLENO
        g2.fillOval(x, y, diametro, diametro);

        // DIBUJAR EL BORDE DEL CIRCULO
        g2.setColor(colorBorde);
        g2.drawOval(x, y, diametro, diametro);

        // Liberar recursos de la copia de Graphics
        g2.dispose();

        // Llamamos al paintComponent original para que Swing pinte el texto centrado
        super.paintComponent(g);
    }

    /**
     * Sobrescribimos contains(x, y) para redefinir la ZONA DE CONTACTO / CLIC.
     * 
     * Por defecto, un componente Swing detecta clics en cualquier parte de su rectangulo.
     * Al ser un boton circular, queremos que SOLO detecte el clic si este cae
     * dentro del circulo y NO en las esquinas transparentes.
     * 
     * Formula matematica (Distancia Euclidiana / Pitagoras):
     * (x - centroX)^2 + (y - centroY)^2 <= radio^2
     */
    @Override
    public boolean contains(int x, int y) {
        int radio = Math.min(getWidth(), getHeight()) / 2;
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        int dx = x - centroX;
        int dy = y - centroY;

        // Si la distancia al centro es menor o igual al radio, el clic es valido
        return (dx * dx + dy * dy) <= (radio * radio);
    }

    // ========================================================================
    // GETTERS Y SETTERS PARA PERSONALIZAR COLORES SI SE DESEA
    // ========================================================================

    public void setColores(Color normal, Color hover, Color presionado, Color borde) {
        this.colorNormal = normal;
        this.colorHover = hover;
        this.colorPresionado = presionado;
        this.colorBorde = borde;
        repaint(); // Vuelve a dibujar el boton con los nuevos colores
    }
}
