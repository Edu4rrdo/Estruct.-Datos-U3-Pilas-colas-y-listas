package Componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Componente personalizado de Swing que representa un botón circular.
 * Incluye soporte para efectos hover, presionado, borde personalizable
 * y detección de clics exacta en la zona circular.
 */
public class BotonCircular extends JButton {

    private Color colorFondo = new Color(52, 152, 219);
    private Color colorHover = new Color(41, 128, 185);
    private Color colorPresionado = new Color(31, 97, 141);
    private Color colorBorde = new Color(255, 255, 255, 180);
    private int grosorBorde = 2;
    private boolean dibujarBorde = true;

    // Figura para comprobación precisa del área de clic
    private Shape formaCircular;

    public BotonCircular() {
        this("");
    }

    public BotonCircular(String texto) {
        super(texto);
        inicializar();
    }

    public BotonCircular(Icon icono) {
        super(icono);
        inicializar();
    }

    public BotonCircular(String texto, Icon icono) {
        super(texto, icono);
        inicializar();
    }

    private void inicializar() {
        // Configuraciones base para permitir dibujo personalizado
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Tamaño sugerido por defecto (cuadrado)
        Dimension tamDefecto = new Dimension(60, 60);
        setPreferredSize(tamDefecto);
        setMinimumSize(tamDefecto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int diametro = Math.min(getWidth(), getHeight());
        int radio = diametro / 2;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        // Selección de color según el estado del botón
        if (getModel().isPressed()) {
            g2.setColor(colorPresionado);
        } else if (getModel().isRollover()) {
            g2.setColor(colorHover);
        } else {
            g2.setColor(colorFondo);
        }

        // Rellenar círculo
        g2.fillOval(x, y, diametro, diametro);

        g2.dispose();

        // Pinta el texto o icono centrado
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (!dibujarBorde || grosorBorde <= 0) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diametro = Math.min(getWidth(), getHeight()) - grosorBorde;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        g2.setStroke(new BasicStroke(grosorBorde));
        g2.setColor(colorBorde);
        g2.drawOval(x, y, diametro, diametro);

        g2.dispose();
    }

    /**
     * Limita el área activa de clic al círculo exacto,
     * evitando que se activen clics en las esquinas transparentes.
     */
    @Override
    public boolean contains(int x, int y) {
        int diametro = Math.min(getWidth(), getHeight());
        int posX = (getWidth() - diametro) / 2;
        int posY = (getHeight() - diametro) / 2;

        formaCircular = new Ellipse2D.Float(posX, posY, diametro, diametro);
        return formaCircular.contains(x, y);
    }

    // Getters y Setters para personalización
    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
        repaint();
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
        repaint();
    }

    public Color getColorPresionado() {
        return colorPresionado;
    }

    public void setColorPresionado(Color colorPresionado) {
        this.colorPresionado = colorPresionado;
        repaint();
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        repaint();
    }

    public int getGrosorBorde() {
        return grosorBorde;
    }

    public void setGrosorBorde(int grosorBorde) {
        this.grosorBorde = grosorBorde;
        repaint();
    }

    public boolean isDibujarBorde() {
        return dibujarBorde;
    }

    public void setDibujarBorde(boolean dibujarBorde) {
        this.dibujarBorde = dibujarBorde;
        repaint();
    }
}
