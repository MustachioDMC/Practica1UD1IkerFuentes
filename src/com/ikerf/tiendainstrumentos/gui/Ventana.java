package com.ikerf.tiendainstrumentos.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.ikerf.tiendainstrumentos.base.Instrumento;

import javax.swing.*;

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
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.pack ( );
        frame.setVisible (true);
        initComponents();
    }

    private void initComponents() {
        dlmIntrumento= new DefaultListModel<Instrumento> ();
        list1.setModel (dlmIntrumento);
    }
}
