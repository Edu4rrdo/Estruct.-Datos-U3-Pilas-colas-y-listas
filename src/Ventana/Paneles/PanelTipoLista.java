/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles;

import ListasDinamicas.ListaDinamica;
import Ventana.Ventana;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Panel para los tipos de listas dinamicas.
 * Hereda directamente de javax.swing.JPanel para coincidir con su formulario visual
 * de NetBeans (PanelTipoLista.form) y evitar conflictos de herencia en el diseñador grafico.
 *
 * @author eduardo-b
 */
public class PanelTipoLista extends javax.swing.JPanel {

    // Referencia a la ventana principal para navegacion entre pantallas
    private Ventana ventanaPadre;
    private ListaDinamica lista;
    private String flecha;
    private String cierre;
    private String titulo;

    private final DefaultListModel<String> modelo = new DefaultListModel<>();

    /**
     * Constructor por defecto para el diseñador de NetBeans GUI.
     */
    public PanelTipoLista() {
        this(null, "Lista Ligada Simple", new ListasDinamicas.ListaLigadaSimple(), " -> ", " -> null");
    }

    /**
     * Constructor utilizado por Ventana para cada tipo de lista.
     */
    public PanelTipoLista(Ventana ventanaPadre, String titulo, ListaDinamica lista,
            String flecha, String cierre) {
        this.ventanaPadre = ventanaPadre;
        this.titulo = titulo;
        this.lista = lista;
        this.flecha = flecha;
        this.cierre = cierre;

        initComponents();
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setSize(1920, 1080);
        jList1.setModel(modelo);

        if (titulo != null) {
            lblTitulo.setText(titulo);
        }
        if (lista != null) {
            lblDesc.setText(lista.descripcion());
        }
        configurarBotonCircular();
        actualizarVista();
    }

    /**
     * Refresca la vista tanto del JList como del panel de bloques visuales
     * representando los nodos y sus ligas de conexion.
     */
    public void actualizarVista() {
        modelo.clear();
        if (panelBloques != null) {
            panelBloques.removeAll();
        }

        if (lista == null)
            return;
        List<Integer> datos = lista.aLista();

        if (datos.isEmpty()) {
            if (panelBloques != null) {
                JLabel lblVacia = new JLabel("(La lista esta vacia - Inserte elementos para visualizar los bloques)",
                        SwingConstants.CENTER);
                lblVacia.setFont(new Font("SansSerif", Font.ITALIC, 18));
                lblVacia.setForeground(new Color(130, 130, 130));
                panelBloques.add(lblVacia);
            }
        } else {
            if (panelBloques != null) {
                JLabel lblInicio = new JLabel("INICIO >> ", SwingConstants.CENTER);
                lblInicio.setFont(new Font("SansSerif", Font.BOLD, 14));
                lblInicio.setForeground(new Color(13, 71, 161));
                panelBloques.add(lblInicio);
            }

            int i = 1;
            for (Integer d : datos) {
                modelo.addElement(i + ")  " + d);

                if (panelBloques != null) {
                    boolean esPrimero = (i == 1);
                    boolean esUltimo = (i == datos.size());

                    Color fondo = esPrimero ? new Color(225, 245, 254)
                            : (esUltimo ? new Color(237, 231, 246) : new Color(245, 245, 245));
                    Color borde = esPrimero ? new Color(2, 136, 209)
                            : (esUltimo ? new Color(123, 31, 162) : new Color(158, 158, 158));
                    Color texto = new Color(33, 33, 33);

                    // Didactico - Estructura de Datos (Representacion de Nodos en Memoria):
                    // 1. Identificar si la lista es circular o lineal (con extremos nulos).
                    boolean esCircular = (lista instanceof ListasDinamicas.ListaCircularSimple)
                            || (lista instanceof ListasDinamicas.ListaCircularDoblementeLigada)
                            || (titulo != null && titulo.toLowerCase().contains("circular"));

                    // 2. Identificar si la lista es doblemente ligada o ligada simple.
                    boolean esDoble = (lista instanceof ListasDinamicas.ListaDoblementeLigada)
                            || (lista instanceof ListasDinamicas.ListaCircularDoblementeLigada)
                            || (titulo != null && titulo.toLowerCase().contains("doble"));

                    String etiquetaNodo;
                    String tooltipNodo;

                    if (esDoble) {
                        // LISTA DOBLEMENTE LIGADA:
                        // Posee dos campos de liga: izquierda (anterior) y derecha (siguiente).
                        if (esCircular) {
                            // En la circular doble: todos los nodos estan enlazados con sus vecinos.
                            etiquetaNodo = "[ • | " + d + " | • ]";
                            tooltipNodo = "Nodo: Dato = " + d + " (Ligas Izq y Der enlazadas)";
                        } else {
                            // En la lista doblemente ligada simple (lineal):
                            // - El PRIMER nodo no tiene anterior: su liga izquierda es null.
                            // - El ULTIMO nodo no tiene siguiente: su liga derecha es null.
                            // - Si solo hay un nodo: ambas ligas son null.
                            if (esPrimero && esUltimo) {
                                etiquetaNodo = "[ null | " + d + " | null ]";
                                tooltipNodo = "Nodo unico: Dato = " + d + " (Liga Izq = null, Liga Der = null)";
                            } else if (esPrimero) {
                                etiquetaNodo = "[ null | " + d + " | • ]";
                                tooltipNodo = "Primer nodo: Dato = " + d + " (Liga Izq = null, Liga Der = siguiente)";
                            } else if (esUltimo) {
                                etiquetaNodo = "[ • | " + d + " | null ]";
                                tooltipNodo = "Ultimo nodo: Dato = " + d + " (Liga Izq = anterior, Liga Der = null)";
                            } else {
                                etiquetaNodo = "[ • | " + d + " | • ]";
                                tooltipNodo = "Nodo intermedio: Dato = " + d + " (Liga Izq = anterior, Liga Der = siguiente)";
                            }
                        }
                    } else {
                        // LISTA LIGADA SIMPLE:
                        // Posee un campo de informacion (dato) y un campo de liga al sucesor.
                        if (esCircular) {
                            // En la circular simple: todos los nodos tienen liga activa '•'.
                            etiquetaNodo = "[ " + d + " | • ]";
                            tooltipNodo = "Nodo: Dato = " + d + " (Liga al siguiente nodo)";
                        } else {
                            // En la lista ligada simple (lineal):
                            // El ULTIMO elemento no apunta a nada: su liga es null.
                            if (esUltimo) {
                                etiquetaNodo = "[ " + d + " | null ]";
                                tooltipNodo = "Ultimo nodo: Dato = " + d + " (Liga = null, fin de la lista)";
                            } else {
                                etiquetaNodo = "[ " + d + " | • ]";
                                tooltipNodo = "Nodo: Dato = " + d + " (Liga al siguiente nodo)";
                            }
                        }
                    }

                    JLabel bloque = crearBloqueLabel(etiquetaNodo, fondo, borde, texto);
                    bloque.setToolTipText(tooltipNodo);
                    panelBloques.add(bloque);

                    if (!esUltimo) {
                        JLabel flechaLbl = crearFlechaLabel(flecha != null ? flecha : " -> ");
                        panelBloques.add(flechaLbl);
                    }
                }
                i++;
            }

            // Para listas circulares se muestra el texto explicativo de retorno.
            // Para listas lineales simples, como el ultimo nodo ya contiene 'null' en su campo de liga,
            // no se agrega " -> null" redundante afuera del recuadro.
            if (panelBloques != null && cierre != null && !cierre.isEmpty()) {
                boolean esCircularLista = (lista instanceof ListasDinamicas.ListaCircularSimple)
                        || (lista instanceof ListasDinamicas.ListaCircularDoblementeLigada)
                        || (titulo != null && titulo.toLowerCase().contains("circular"));
                if (esCircularLista || !cierre.contains("null")) {
                    JLabel lblCierre = new JLabel(" " + cierre, SwingConstants.CENTER);
                    lblCierre.setFont(new Font("SansSerif", Font.BOLD, 14));
                    lblCierre.setForeground(new Color(66, 66, 66));
                    panelBloques.add(lblCierre);
                }
            }
        }

        if (panelBloques != null) {
            panelBloques.revalidate();
            panelBloques.repaint();
        }
    }

    private Integer leerValor() {
        return leerEntero(txtValor);
    }

    private void exito(String msg) {
        lblEstado.setForeground(new Color(0, 130, 0));
        lblEstado.setText(msg);
    }

    private void error(String msg) {
        lblEstado.setForeground(Color.RED);
        lblEstado.setText(msg);
    }

    /**
     * Valida y extrae un numero entero desde un campo de texto JTextField.
     * Si el campo esta vacio o no es un numero entero valido, muestra
     * un mensaje de error didactico en la etiqueta de estado y enfoca el campo.
     *
     * @param txt Campo de texto de entrada
     * @return El entero parseado o null si la entrada fue invalida
     */
    private Integer leerEntero(javax.swing.JTextField txt) {
        if (txt == null) return null;
        String texto = txt.getText().trim();
        if (texto.isEmpty()) {
            error("Por favor, ingrese un valor en el campo requerido.");
            txt.requestFocusInWindow();
            return null;
        }
        try {
            int valor = Integer.parseInt(texto);
            txt.setText("");
            txt.requestFocusInWindow();
            return valor;
        } catch (NumberFormatException e) {
            error("Valor invalido: debe ingresar un numero entero.");
            txt.requestFocusInWindow();
            return null;
        }
    }

    /**
     * Crea un JLabel personalizado con estilo de bloque o nodo de lista.
     *
     * @param texto Contenido visual del nodo (dato)
     * @param fondo Color de fondo del bloque
     * @param borde Color del borde redondeado
     * @param textoColor Color de la tipografia
     * @return JLabel configurado con padding y borde
     */
    private JLabel crearBloqueLabel(String texto, Color fondo, Color borde, Color textoColor) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 18));
        lbl.setOpaque(true);
        lbl.setBackground(fondo);
        lbl.setForeground(textoColor);
        lbl.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(borde, 3, true),
            javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    /**
     * Crea un JLabel para representar visualmente el puntero o flecha de enlace entre nodos.
     *
     * @param flecha Texto de la flecha (por ejemplo " -> " o " <-> ")
     * @return JLabel con la flecha formateada
     */
    private JLabel crearFlechaLabel(String flecha) {
        JLabel lbl = new JLabel(flecha, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    /**
     * Regresa a la pantalla del menu principal de la aplicacion.
     */
    private void volverAlMenu() {
        if (ventanaPadre != null) {
            ventanaPadre.mostrarPanel("Menu");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnInsIni = new javax.swing.JButton();
        btnInsFin = new javax.swing.JButton();
        btnElimIni = new javax.swing.JButton();
        btnElimFin = new javax.swing.JButton();
        btnElimVal = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPaneBloques = new javax.swing.JScrollPane();
        panelBloques = new javax.swing.JPanel();
        jScrollPaneLista = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        lblEstado = new javax.swing.JLabel();
        btnTipos = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        jButton1 = new BotonCircular("?");
        lblValor1 = new javax.swing.JLabel();
        txtValor1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Monospaced", 1, 28)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Lista Dinamica");

        lblDesc.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDesc.setText("Descripcion");

        lblValor.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblValor.setText("Valor:");

        txtValor.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtValor.addActionListener(this::txtValorActionPerformed);

        btnInsIni.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnInsIni.setText("Insertar al inicio");
        btnInsIni.addActionListener(this::btnInsIniActionPerformed);

        btnInsFin.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnInsFin.setText("Insertar al final");
        btnInsFin.addActionListener(this::btnInsFinActionPerformed);

        btnElimIni.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnElimIni.setText("Eliminar inicio");
        btnElimIni.addActionListener(this::btnElimIniActionPerformed);

        btnElimFin.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnElimFin.setText("Eliminar final");
        btnElimFin.addActionListener(this::btnElimFinActionPerformed);

        btnElimVal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnElimVal.setText("Eliminar valor");
        btnElimVal.addActionListener(this::btnElimValActionPerformed);

        btnBuscar.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        panelBloques.setBackground(new java.awt.Color(250, 250, 250));
        jScrollPaneBloques.setViewportView(panelBloques);

        jList1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jScrollPaneLista.setViewportView(jList1);

        lblEstado.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText(" ");

        btnTipos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnTipos.setText("Volver a tipos de lista");
        btnTipos.addActionListener(this::btnTiposActionPerformed);

        btnMenu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenu.setText("Volver al menu");
        btnMenu.addActionListener(this::btnMenuActionPerformed);

        jButton1.setText("?");
        jButton1.setToolTipText("Información y ayuda de la lista");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        lblValor1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblValor1.setText("Referencia");

        txtValor1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        txtValor1.addActionListener(this::txtValor1ActionPerformed);

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jButton2.setText("Insertar antes");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jButton3.setText("Insertar despues");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jButton4.setText("Eliminar ref");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jButton5.setText("Eliminar antes");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jButton6.setText("Eliminar despues");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(392, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneBloques, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblValor1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton5)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton6))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblValor)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnInsIni)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnInsFin)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnElimIni)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnElimFin)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnElimVal)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnBuscar)))
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(360, 360, 360)
                                .addComponent(btnTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(895, 895, 895)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(430, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(6, 6, 6)
                .addComponent(lblDesc)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValor)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsIni, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInsFin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnElimIni, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnElimFin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnElimVal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValor1)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneLista, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(jScrollPaneBloques))
                .addGap(18, 18, 18)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTipos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(333, 333, 333))
        );
    }// </editor-fold>//GEN-END:initComponents

    
   
    private void txtValor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValor1ActionPerformed

    }//GEN-LAST:event_txtValor1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (lista == null)
            return;
        Integer ref = leerEntero(txtValor1);       // campo "Referencia"
        if (ref == null)
            return;
        Integer v = leerValor();                    // campo "Valor"
        if (v == null) {
            txtValor1.setText(String.valueOf(ref)); // no perder la referencia escrita
            return;
        }
        if (lista.insertarAntes(ref, v)) {
            actualizarVista();
            exito("Se inserto " + v + " antes de " + ref + ".");
        } else {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + ref + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if (lista == null)
            return;
        Integer ref = leerEntero(txtValor1);
        if (ref == null)
            return;
        Integer v = leerValor();
        if (v == null) {
            txtValor1.setText(String.valueOf(ref));
            return;
        }
        if (lista.insertarDespues(ref, v)) {
            actualizarVista();
            exito("Se inserto " + v + " despues de " + ref + ".");
        } else {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + ref + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
                if (lista == null)
            return;
        Integer ref = leerEntero(txtValor1);
        if (ref == null)
            return;
        if (lista.estaVacia()) {
            error("La lista esta vacia.");
        } else if (lista.eliminarValor(ref)) {
            actualizarVista();
            exito("Se elimino la referencia " + ref + ".");
        } else {
            error("La referencia " + ref + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                if (lista == null)
            return;
        Integer ref = leerEntero(txtValor1);
        if (ref == null)
            return;
        Integer r = lista.eliminarAntes(ref);
        if (r != null) {
            actualizarVista();
            exito("Se elimino " + r + " (estaba antes de " + ref + ").");
        } else if (!lista.buscar(ref)) {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + ref + " no esta en la lista.");
        } else {
            error("No hay ningun nodo antes de " + ref + ".");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (lista == null)
            return;
        Integer ref = leerEntero(txtValor1);
        if (ref == null)
            return;
        Integer r = lista.eliminarDespues(ref);
        if (r != null) {
            actualizarVista();
            exito("Se elimino " + r + " (estaba despues de " + ref + ").");
        } else if (!lista.buscar(ref)) {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + ref + " no esta en la lista.");
        } else {
            error("No hay ningun nodo despues de " + ref + ".");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtValorActionPerformed
        btnInsFinActionPerformed(evt);
    }// GEN-LAST:event_txtValorActionPerformed

    private void btnInsIniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInsIniActionPerformed
        if (lista == null)
            return;
        Integer v = leerValor();
        if (v == null)
            return;
        lista.insertarInicio(v);
        actualizarVista();
        exito("Se inserto " + v + " al inicio.");
    }// GEN-LAST:event_btnInsIniActionPerformed

    private void btnInsFinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInsFinActionPerformed
        if (lista == null)
            return;
        Integer v = leerValor();
        if (v == null)
            return;
        lista.insertarFinal(v);
        actualizarVista();
        exito("Se inserto " + v + " al final.");
    }// GEN-LAST:event_btnInsFinActionPerformed

    private void btnElimIniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimIniActionPerformed
        if (lista == null)
            return;
        Integer r = lista.eliminarInicio();
        actualizarVista();
        if (r == null) {
            error("La lista esta vacia.");
        } else {
            exito("Se elimino el valor del inicio: " + r);
        }
    }// GEN-LAST:event_btnElimIniActionPerformed

    private void btnElimFinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimFinActionPerformed
        if (lista == null)
            return;
        Integer r = lista.eliminarFinal();
        actualizarVista();
        if (r == null) {
            error("La lista esta vacia.");
        } else {
            exito("Se elimino el valor del final: " + r);
        }
    }// GEN-LAST:event_btnElimFinActionPerformed

    private void btnElimValActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimValActionPerformed
        if (lista == null)
            return;
        Integer v = leerValor();
        if (v == null)
            return;
        if (lista.estaVacia()) {
            error("La lista esta vacia.");
        } else if (lista.eliminarValor(v)) {
            actualizarVista();
            exito("Se elimino el valor " + v + ".");
        } else {
            error("El valor " + v + " no esta en la lista.");
        }
    }// GEN-LAST:event_btnElimValActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarActionPerformed
        if (lista == null)
            return;
        Integer v = leerValor();
        if (v == null)
            return;
        if (lista.buscar(v)) {
            int pos = lista.aLista().indexOf(v);
            jList1.setSelectedIndex(pos);
            jList1.ensureIndexIsVisible(pos);
            exito("El valor " + v + " SI esta en la lista (posicion " + (pos + 1) + ").");
        } else {
            jList1.clearSelection();
            error("El valor " + v + " NO esta en la lista.");
        }
    }// GEN-LAST:event_btnBuscarActionPerformed

    private void btnTiposActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTiposActionPerformed
        if (ventanaPadre != null) {
            ventanaPadre.mostrarPanel("Lista");
        }
    }// GEN-LAST:event_btnTiposActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMenuActionPerformed
        volverAlMenu();
    }// GEN-LAST:event_btnMenuActionPerformed

    /**
     * =========================================================================
     * GUIA DIDACTICA: CONFIGURACION DEL BOTON CIRCULAR (jButton1)
     * =========================================================================
     * Este metodo demuestra como se configura un boton para que luzca y actue
     * de forma circular en Java Swing:
     * 
     * 1. FORMA Y TAMAÑO:
     *    Para que sea un circulo (y no un ovalo o elipse), su ancho y su alto
     *    deben ser EXACTAMENTE IGUALES (por ejemplo 35x35 pixeles).
     * 
     * 2. TEXTO O ICONO CORTO:
     *    En los botones circulares se suele colocar un solo caracter o simbolo
     *    (por ejemplo: "?", "i", "+", "✕", "★") o un icono grafico (ImageIcon)
     *    para que no se desborde del contorno del circulo.
     * 
     * 3. COLORES Y ESTILOS:
     *    Gracias a la clase BotonCircular, podemos definir facilmente:
     *    - Color normal
     *    - Color cuando el cursor pasa por encima (Hover)
     *    - Color cuando se presiona con el raton (Armed / Pressed)
     *    - Color del borde perimetral
     * =========================================================================
     */
    private void configurarBotonCircular() {
        if (jButton1 instanceof BotonCircular botonCirc) {
            // Personalizamos la paleta de colores para este panel:
            // Azul suave en reposo, azul mas intenso al pasar el mouse
            botonCirc.setColores(
                new Color(33, 150, 243),  // Normal: Azul Material
                new Color(30, 136, 229),  // Hover: Azul mas vivo
                new Color(21, 101, 192),  // Presionado: Azul profundo
                new Color(25, 118, 210)   // Borde circular
            );
        }
        
        // Asignamos el texto centrado del boton
        jButton1.setText("?");
        
        // Tooltip: mensaje de ayuda flotante que se muestra al poner el cursor encima
        jButton1.setToolTipText("Informacion y ayuda sobre este tipo de lista");
    }

    /**
     * Evento al hacer clic en jButton1 (Boton Circular).
     * Muestra una ventana emergente informativa con la explicacion teorica
     * del tipo de lista dinamica que el usuario esta visualizando.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        if (lista != null) {
            String mensaje = "TIPO DE LISTA:\n"
                    + (titulo != null ? titulo : "Lista Dinámica") + "\n\n"
                    + "DESCRIPCIÓN Y FUNCIONAMIENTO:\n"
                    + lista.descripcion();

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    mensaje,
                    "Ayuda / Información - " + (titulo != null ? titulo : "Lista"),
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnElimFin;
    private javax.swing.JButton btnElimIni;
    private javax.swing.JButton btnElimVal;
    private javax.swing.JButton btnInsFin;
    private javax.swing.JButton btnInsIni;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnTipos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPaneBloques;
    private javax.swing.JScrollPane jScrollPaneLista;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblValor1;
    private javax.swing.JPanel panelBloques;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtValor1;
    // End of variables declaration//GEN-END:variables
}
