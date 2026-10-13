/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventana.Paneles;

import ColasDinamicas.ListaCola;
import ColasDinamicas.Nodo;
import Ventana.Ventana;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Panel para la Cola Dinamica (FIFO).
 * Clase hija de PanelBase con dibujo de recuadros en bloque enlazados verticalmente.
 *
 * @author eduardo-b
 */
public class PanelCola extends PanelBase {

    private final ListaCola cola = new ListaCola();

    /**
     * Constructor por defecto para el diseñador de NetBeans GUI.
     */
    public PanelCola() {
        this(null);
    }

    /**
     * Constructor con referencia a la ventana principal.
     */
    public PanelCola(Ventana ventanaPadre) {
        super(ventanaPadre);
        initComponents();
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setSize(1920, 1080);
        actualizarVista();
    }

    @Override
    public void actualizarVista() {
        if (panelBloques == null) return;
        panelBloques.removeAll();

        if (cola.P == null) {
            panelBloques.add(Box.createVerticalStrut(50));
            JLabel lblVacia = new JLabel("(La cola esta vacia - Inserte elementos para visualizar los bloques)", SwingConstants.CENTER);
            lblVacia.setFont(new Font("SansSerif", Font.ITALIC, 18));
            lblVacia.setForeground(new Color(130, 130, 130));
            lblVacia.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelBloques.add(lblVacia);
        } else {
            panelBloques.add(Box.createVerticalStrut(15));
            JLabel lblFrenteIndicador = new JLabel("▼ FRENTE (Proximo a salir - Desencolar) ▼", SwingConstants.CENTER);
            lblFrenteIndicador.setFont(new Font("SansSerif", Font.BOLD, 15));
            lblFrenteIndicador.setForeground(new Color(46, 125, 50));
            lblFrenteIndicador.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelBloques.add(lblFrenteIndicador);
            panelBloques.add(Box.createVerticalStrut(10));

            // Recolectar datos de la cola
            List<Integer> listaNodos = new ArrayList<>();
            Nodo temp = cola.P;
            while (temp != null) {
                listaNodos.add(temp.info);
                temp = temp.liga;
            }
            // Invertimos para que el Frente (sale primero) se ubique arriba y el Final abajo
            Collections.reverse(listaNodos);

            for (int i = 0; i < listaNodos.size(); i++) {
                int valor = listaNodos.get(i);
                boolean esFrente = (i == 0);
                boolean esFinal = (i == listaNodos.size() - 1);

                if (i > 0) {
                    panelBloques.add(Box.createVerticalStrut(6));
                    JLabel flecha = crearFlechaLabel("↓");
                    flecha.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panelBloques.add(flecha);
                    panelBloques.add(Box.createVerticalStrut(6));
                }

                Color fondo = esFrente ? new Color(200, 230, 201) : (esFinal ? new Color(255, 236, 179) : new Color(232, 245, 233));
                Color borde = esFrente ? new Color(56, 142, 60) : (esFinal ? new Color(245, 127, 23) : new Color(129, 199, 132));
                Color texto = esFrente ? new Color(27, 94, 32) : (esFinal ? new Color(191, 54, 12) : new Color(33, 33, 33));

                String etiqueta;
                if (esFrente && esFinal) {
                    etiqueta = "[ FRENTE / FINAL: " + valor + " | liga: null ]";
                } else if (esFrente) {
                    etiqueta = "[ FRENTE: " + valor + " | liga: ↓ ]";
                } else if (esFinal) {
                    etiqueta = "[ FINAL: " + valor + " | liga: null ]";
                } else {
                    etiqueta = "[ DATO: " + valor + " | liga: ↓ ]";
                }

                JLabel bloque = crearBloqueLabel(etiqueta, fondo, borde, texto);
                bloque.setPreferredSize(new Dimension(360, 48));
                bloque.setMaximumSize(new Dimension(360, 48));
                panelBloques.add(bloque);
            }

            panelBloques.add(Box.createVerticalStrut(12));
            JLabel lblFinalIndicador = new JLabel("▲ FINAL (Ultimo en entrar - Encolado) ▲", SwingConstants.CENTER);
            lblFinalIndicador.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblFinalIndicador.setForeground(new Color(230, 81, 0));
            lblFinalIndicador.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelBloques.add(lblFinalIndicador);
            panelBloques.add(Box.createVerticalStrut(20));
        }

        panelBloques.revalidate();
        panelBloques.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelBloques = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cola Dinamica (FIFO)");

        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Insertar (Enqueue)");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setText("Eliminar (Dequeue)");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        panelBloques.setBackground(new java.awt.Color(250, 250, 250));
        panelBloques.setLayout(new javax.swing.BoxLayout(panelBloques, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelBloques);

        jButton3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton3.setText("Volver al Menu");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(560, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(368, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Integer valor = leerEntero(jTextField1);
        if (valor == null) return;
        cola.insertarInicio(valor);
        actualizarVista();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Integer eliminado = cola.quitarFinal();
        if (eliminado != null) {
            mostrarExito("Se elimino el elemento del frente de la cola: " + eliminado);
            actualizarVista();
        } else {
            mostrarError("La cola esta vacia, no hay elementos para eliminar.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        volverAlMenu();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelBloques;
    // End of variables declaration//GEN-END:variables
}
