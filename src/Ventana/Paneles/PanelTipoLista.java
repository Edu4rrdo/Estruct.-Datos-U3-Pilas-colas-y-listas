/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles;

import ListasDinamicas.ListaDinamica;
import Ventana.Paneles.Dibujo.PanelDibujoLista;
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

    // Componente JPanel especializado exclusivamente en el dibujo de nodos de listas
    private PanelDibujoLista panelDibujoLista;

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

        // Inicializar el panel de dibujo dedicado y colocarlo en el viewport del JScrollPane
        this.panelDibujoLista = new PanelDibujoLista();
        jScrollPaneBloques.setViewportView(this.panelDibujoLista);

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
     * Refresca la lista textual (JList) y delega el dibujo grafico
     * de los bloques al componente PanelDibujoLista.
     */
    public void actualizarVista() {
        modelo.clear();

        if (lista != null) {
            List<Integer> datos = lista.aLista();
            int numeroElemento = 1;
            for (Integer elementoDato : datos) {
                modelo.addElement(numeroElemento + ")  " + elementoDato);
                numeroElemento++;
            }
        }

        // Delegar todo el dibujo grafico de los nodos a la clase especializada
        if (this.panelDibujoLista != null) {
            this.panelDibujoLista.actualizarVista(this.lista, this.titulo, this.flecha, this.cierre);
        }
    }

    private Integer leerValor() {
        return leerEntero(txtValor);
    }

    private void exito(String mensajeExito) {
        lblEstado.setForeground(new Color(0, 130, 0));
        lblEstado.setText(mensajeExito);
    }

    private void error(String mensajeError) {
        lblEstado.setForeground(Color.RED);
        lblEstado.setText(mensajeError);
    }

    /**
     * Valida y extrae un numero entero desde un campo de texto JTextField.
     * Si el campo esta vacio o no es un numero entero valido, muestra
     * un mensaje de error didactico en la etiqueta de estado y enfoca el campo.
     *
     * @param campoTextoEntrada Campo de texto de entrada
     * @return El entero parseado o null si la entrada fue invalida
     */
    private Integer leerEntero(javax.swing.JTextField campoTextoEntrada) {
        if (campoTextoEntrada == null) return null;
        String textoCapturado = campoTextoEntrada.getText().trim();
        if (textoCapturado.isEmpty()) {
            error("Por favor, ingrese un valor en el campo requerido.");
            campoTextoEntrada.requestFocusInWindow();
            return null;
        }
        try {
            int valorConvertido = Integer.parseInt(textoCapturado);
            campoTextoEntrada.setText("");
            campoTextoEntrada.requestFocusInWindow();
            return valorConvertido;
        } catch (NumberFormatException excepcionFormato) {
            error("Valor invalido: debe ingresar un numero entero.");
            campoTextoEntrada.requestFocusInWindow();
            return null;
        }
    }

    /**
     * Metodo delegado para construir un bloque label en el panel de dibujo.
     */
    public JLabel crearBloqueLabel(String textoContenido, Color colorFondo, Color colorBorde, Color colorTexto) {
        if (this.panelDibujoLista != null) {
            return this.panelDibujoLista.crearBloqueLabel(textoContenido, colorFondo, colorBorde, colorTexto);
        }
        return new JLabel(textoContenido);
    }

    /**
     * Metodo delegado para construir una flecha label en el panel de dibujo.
     */
    public JLabel crearFlechaLabel(String textoFlecha) {
        if (this.panelDibujoLista != null) {
            return this.panelDibujoLista.crearFlechaLabel(textoFlecha);
        }
        return new JLabel(textoFlecha);
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
        // Evento: Insertar antes de un valor de referencia
        if (lista == null)
            return;
        Integer valorReferencia = leerEntero(txtValor1);       // campo "Referencia"
        if (valorReferencia == null)
            return;
        Integer valorAInsertar = leerValor();                   // campo "Valor"
        if (valorAInsertar == null) {
            txtValor1.setText(String.valueOf(valorReferencia)); // no perder la referencia escrita
            return;
        }
        if (lista.insertarAntes(valorReferencia, valorAInsertar)) {
            actualizarVista();
            exito("Se inserto " + valorAInsertar + " antes de " + valorReferencia + ".");
        } else {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + valorReferencia + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Evento: Insertar despues de un valor de referencia
        if (lista == null)
            return;
        Integer valorReferencia = leerEntero(txtValor1);
        if (valorReferencia == null)
            return;
        Integer valorAInsertar = leerValor();
        if (valorAInsertar == null) {
            txtValor1.setText(String.valueOf(valorReferencia));
            return;
        }
        if (lista.insertarDespues(valorReferencia, valorAInsertar)) {
            actualizarVista();
            exito("Se inserto " + valorAInsertar + " despues de " + valorReferencia + ".");
        } else {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + valorReferencia + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Evento: Eliminar nodo con el valor de la referencia
        if (lista == null)
            return;
        Integer valorReferencia = leerEntero(txtValor1);
        if (valorReferencia == null)
            return;
        if (lista.estaVacia()) {
            error("La lista esta vacia.");
        } else if (lista.eliminarValor(valorReferencia)) {
            actualizarVista();
            exito("Se elimino la referencia " + valorReferencia + ".");
        } else {
            error("La referencia " + valorReferencia + " no esta en la lista.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Evento: Eliminar el nodo antes de la referencia
        if (lista == null)
            return;
        Integer valorReferencia = leerEntero(txtValor1);
        if (valorReferencia == null)
            return;
        Integer valorEliminado = lista.eliminarAntes(valorReferencia);
        if (valorEliminado != null) {
            actualizarVista();
            exito("Se elimino " + valorEliminado + " (estaba antes de " + valorReferencia + ").");
        } else if (!lista.buscar(valorReferencia)) {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + valorReferencia + " no esta en la lista.");
        } else {
            error("No hay ningun nodo antes de " + valorReferencia + ".");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Evento: Eliminar el nodo despues de la referencia
        if (lista == null)
            return;
        Integer valorReferencia = leerEntero(txtValor1);
        if (valorReferencia == null)
            return;
        Integer valorEliminado = lista.eliminarDespues(valorReferencia);
        if (valorEliminado != null) {
            actualizarVista();
            exito("Se elimino " + valorEliminado + " (estaba despues de " + valorReferencia + ").");
        } else if (!lista.buscar(valorReferencia)) {
            error(lista.estaVacia() ? "La lista esta vacia." : "La referencia " + valorReferencia + " no esta en la lista.");
        } else {
            error("No hay ningun nodo despues de " + valorReferencia + ".");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtValorActionPerformed
        btnInsFinActionPerformed(evt);
    }// GEN-LAST:event_txtValorActionPerformed

    private void btnInsIniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInsIniActionPerformed
        // Evento: Insertar al inicio de la lista
        if (lista == null)
            return;
        Integer valorAInsertar = leerValor();
        if (valorAInsertar == null)
            return;
        lista.insertarInicio(valorAInsertar);
        actualizarVista();
        exito("Se inserto " + valorAInsertar + " al inicio.");
    }// GEN-LAST:event_btnInsIniActionPerformed

    private void btnInsFinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInsFinActionPerformed
        // Evento: Insertar al final de la lista
        if (lista == null)
            return;
        Integer valorAInsertar = leerValor();
        if (valorAInsertar == null)
            return;
        lista.insertarFinal(valorAInsertar);
        actualizarVista();
        exito("Se inserto " + valorAInsertar + " al final.");
    }// GEN-LAST:event_btnInsFinActionPerformed

    private void btnElimIniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimIniActionPerformed
        // Evento: Eliminar el primer elemento de la lista
        if (lista == null)
            return;
        Integer valorEliminado = lista.eliminarInicio();
        actualizarVista();
        if (valorEliminado == null) {
            error("La lista esta vacia.");
        } else {
            exito("Se elimino el valor del inicio: " + valorEliminado);
        }
    }// GEN-LAST:event_btnElimIniActionPerformed

    private void btnElimFinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimFinActionPerformed
        // Evento: Eliminar el ultimo elemento de la lista
        if (lista == null)
            return;
        Integer valorEliminado = lista.eliminarFinal();
        actualizarVista();
        if (valorEliminado == null) {
            error("La lista esta vacia.");
        } else {
            exito("Se elimino el valor del final: " + valorEliminado);
        }
    }// GEN-LAST:event_btnElimFinActionPerformed

    private void btnElimValActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnElimValActionPerformed
        // Evento: Eliminar por valor
        if (lista == null)
            return;
        Integer valorAEliminar = leerValor();
        if (valorAEliminar == null)
            return;
        if (lista.estaVacia()) {
            error("La lista esta vacia.");
        } else if (lista.eliminarValor(valorAEliminar)) {
            actualizarVista();
            exito("Se elimino el valor " + valorAEliminar + ".");
        } else {
            error("El valor " + valorAEliminar + " no esta en la lista.");
        }
    }// GEN-LAST:event_btnElimValActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarActionPerformed
        // Evento: Buscar valor en la lista
        if (lista == null)
            return;
        Integer valorABuscar = leerValor();
        if (valorABuscar == null)
            return;
        if (lista.buscar(valorABuscar)) {
            int posicionElemento = lista.aLista().indexOf(valorABuscar);
            jList1.setSelectedIndex(posicionElemento);
            jList1.ensureIndexIsVisible(posicionElemento);
            exito("El valor " + valorABuscar + " SI esta en la lista (posicion " + (posicionElemento + 1) + ").");
        } else {
            jList1.clearSelection();
            error("El valor " + valorABuscar + " NO esta en la lista.");
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
