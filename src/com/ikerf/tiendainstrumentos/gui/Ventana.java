package com.ikerf.tiendainstrumentos.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.ikerf.tiendainstrumentos.base.Instrumento;

import javax.swing.*;
import java.awt.*;

public class Ventana {
    public JPanel panel1;
    public JRadioButton vientoRadioButton;
    public JRadioButton percusionRadioButton;
    public JRadioButton cuerdaRadioButton;
    public JLabel lblBDC;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public JTextField nombreTXT;
    public JTextField marcaTXT;
    public JTextField colorPrincipalTXT;
    public JSpinner StockSpinner;
    public JTextField precioTXT;
    public JTextField bdcTXT;
    public DatePicker fechaCeacionDatePicker;
    public JButton borrarButton;

    JFrame frame;
    public DefaultListModel<Instrumento> dlmIntrumento;

    public Ventana() {
        frame = new JFrame ("Ventana");
        frame.setContentPane (panel1);
        frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack ( );
        frame.setVisible (true);
        initComponents ( );
        cambiarFuente(panel1, 30);
    }
    private void cambiarFuente(Component componente, int tamano) {
        componente.setFont(
                componente.getFont().deriveFont((float) tamano)
        );

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                cambiarFuente(hijo, tamano);
            }
        }
    }

    private void initComponents() {
        dlmIntrumento = new DefaultListModel<Instrumento> ( );
        list1.setModel (dlmIntrumento);
        nombreTXT.setPreferredSize(new java.awt.Dimension(250, 35));
        marcaTXT.setPreferredSize(new java.awt.Dimension(250, 35));
        colorPrincipalTXT.setPreferredSize(new java.awt.Dimension(250, 35));
        precioTXT.setPreferredSize(new java.awt.Dimension(250, 35));
        bdcTXT.setPreferredSize(new java.awt.Dimension(250, 35));
        StockSpinner.setPreferredSize(new java.awt.Dimension(100, 35));
        list1.setFont(list1.getFont().deriveFont(18f));
    }
}
