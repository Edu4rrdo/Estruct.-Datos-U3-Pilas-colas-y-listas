package Ventana;

import ListasDinamicas.ListaDinamica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// Panel reutilizable: sirve para cualquiera de los 4 tipos de lista.
// Se construye por codigo (no tiene archivo .form).
public class PanelTipoLista extends JPanel {

    private final Ventana ventanaPadre;
    private final ListaDinamica lista;
    private final String flecha;   // separador para dibujar la lista, ej. " -> "
    private final String cierre;   // lo que va al final, ej. " -> null"

    private final DefaultListModel<String> modelo = new DefaultListModel<>();
    private final JList<String> jList = new JList<>(modelo);
    private final JTextField txtValor = new JTextField(10);
    private final JLabel lblDibujo = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel lblEstado = new JLabel(" ", SwingConstants.CENTER);

    public PanelTipoLista(Ventana ventanaPadre, String titulo, ListaDinamica lista,
            String flecha, String cierre) {
        this.ventanaPadre = ventanaPadre;
        this.lista = lista;
        this.flecha = flecha;
        this.cierre = cierre;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ---------- parte de arriba: titulo + descripcion + dibujo ----------
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblDesc = new JLabel(lista.descripcion(), SwingConstants.CENTER);
        lblDesc.setFont(new Font("SansSerif", Font.ITALIC, 14));
        lblDesc.setAlignmentX(CENTER_ALIGNMENT);

        lblDibujo.setFont(new Font("Monospaced", Font.BOLD, 18));
        lblDibujo.setAlignmentX(CENTER_ALIGNMENT);

        JPanel norte = new JPanel();
        norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
        norte.add(lblTitulo);
        norte.add(javax.swing.Box.createVerticalStrut(5));
        norte.add(lblDesc);
        norte.add(javax.swing.Box.createVerticalStrut(15));
        norte.add(lblDibujo);
        norte.add(javax.swing.Box.createVerticalStrut(10));

        // ---------- controles: caja de texto + botones ----------
        JPanel controles = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        txtValor.setFont(new Font("SansSerif", Font.PLAIN, 18));
        controles.add(new JLabel("Valor:"));
        controles.add(txtValor);

        JButton btnInsIni = new JButton("Insertar al inicio");
        JButton btnInsFin = new JButton("Insertar al final");
        JButton btnElimIni = new JButton("Eliminar inicio");
        JButton btnElimFin = new JButton("Eliminar final");
        JButton btnElimVal = new JButton("Eliminar valor");
        JButton btnBuscar = new JButton("Buscar");
        controles.add(btnInsIni);
        controles.add(btnInsFin);
        controles.add(btnElimIni);
        controles.add(btnElimFin);
        controles.add(btnElimVal);
        controles.add(btnBuscar);

        // ---------- centro: la lista ----------
        jList.setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(jList);

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.add(controles, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        // ---------- abajo: mensaje de estado + botones de regreso ----------
        lblEstado.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEstado.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnTipos = new JButton("Volver a tipos de lista");
        JButton btnMenu = new JButton("Volver al menu");
        JPanel botonesVolver = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botonesVolver.add(btnTipos);
        botonesVolver.add(btnMenu);

        JPanel sur = new JPanel();
        sur.setLayout(new BoxLayout(sur, BoxLayout.Y_AXIS));
        sur.add(lblEstado);
        sur.add(botonesVolver);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        // ---------- acciones ----------
        btnInsIni.addActionListener(e -> {
            Integer v = leerValor();
            if (v == null) return;
            lista.insertarInicio(v);
            actualizarVista();
            exito("Se inserto " + v + " al inicio.");
        });

        btnInsFin.addActionListener(e -> {
            Integer v = leerValor();
            if (v == null) return;
            lista.insertarFinal(v);
            actualizarVista();
            exito("Se inserto " + v + " al final.");
        });

        btnElimIni.addActionListener(e -> {
            Integer r = lista.eliminarInicio();
            actualizarVista();
            if (r == null) error("La lista esta vacia.");
            else exito("Se elimino el valor del inicio: " + r);
        });

        btnElimFin.addActionListener(e -> {
            Integer r = lista.eliminarFinal();
            actualizarVista();
            if (r == null) error("La lista esta vacia.");
            else exito("Se elimino el valor del final: " + r);
        });

        btnElimVal.addActionListener(e -> {
            Integer v = leerValor();
            if (v == null) return;
            if (lista.estaVacia()) {
                error("La lista esta vacia.");
            } else if (lista.eliminarValor(v)) {
                actualizarVista();
                exito("Se elimino el valor " + v + ".");
            } else {
                error("El valor " + v + " no esta en la lista.");
            }
        });

        btnBuscar.addActionListener(e -> {
            Integer v = leerValor();
            if (v == null) return;
            if (lista.buscar(v)) {
                int pos = lista.aLista().indexOf(v);
                jList.setSelectedIndex(pos);
                jList.ensureIndexIsVisible(pos);
                exito("El valor " + v + " SI esta en la lista (posicion " + (pos + 1) + ").");
            } else {
                jList.clearSelection();
                error("El valor " + v + " NO esta en la lista.");
            }
        });

        // Enter en la caja de texto = insertar al final
        txtValor.addActionListener(e -> btnInsFin.doClick());

        btnTipos.addActionListener(e -> ventanaPadre.mostrarPanel("Lista"));
        btnMenu.addActionListener(e -> ventanaPadre.mostrarPanel("Menu"));

        actualizarVista();
    }

    // Lee y valida el numero escrito. Regresa null si no es un entero valido.
    private Integer leerValor() {
        try {
            int v = Integer.parseInt(txtValor.getText().trim());
            txtValor.setText("");
            txtValor.requestFocusInWindow();
            return v;
        } catch (NumberFormatException ex) {
            error("Ingrese solo valores enteros.");
            return null;
        }
    }

    private void actualizarVista() {
        modelo.clear();
        List<Integer> datos = lista.aLista();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Integer d : datos) {
            modelo.addElement(i + ")  " + d);
            if (i > 1) sb.append(flecha);
            sb.append("[").append(d).append("]");
            i++;
        }
        if (datos.isEmpty()) {
            lblDibujo.setText("(lista vacia)");
        } else {
            lblDibujo.setText(sb + cierre);
        }
    }

    private void exito(String msg) {
        lblEstado.setForeground(new Color(0, 130, 0));
        lblEstado.setText(msg);
    }

    private void error(String msg) {
        lblEstado.setForeground(Color.RED);
        lblEstado.setText(msg);
    }
}