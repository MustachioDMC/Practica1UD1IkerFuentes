package com.ikerf.tiendainstrumentos.gui;

import com.ikerf.tiendainstrumentos.base.Instrumento;
import util.Util;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class InstrumentoControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana ventana;
    private InstrumentoModelo modelo;
    File ultimaRutaExportada;

    public InstrumentoControlador(Ventana ventana, InstrumentoModelo modelo) {
        this.ventana = ventana;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        addActionListener(this);
        addListSelection(this);
        addWindowListener(this);
    }

    private boolean hayCamposVacios(){
        if(ventana.nombreTXT.getText ().isEmpty () || ventana.marcaTXT.getText ().isEmpty() || ventana.colorPrincipalTXT.getText ().isEmpty() ||
                ventana.StockSpinner.getValue ().equals (0) ||  ventana.marcaTXT.getText ().isEmpty() || ventana.precioTXT.getText ().isEmpty() ||
                ventana.fechaCeacionDatePicker.getText ().isEmpty() ||  ventana.bdcTXT.getText ().isEmpty()){
                return true;
        }
        return false;
    }

    private void limpiarCampos() {
        ventana.nombreTXT.setText (null);
        ventana.marcaTXT.setText (null);
        ventana.colorPrincipalTXT.setText (null);
        ventana.StockSpinner.setValue(0);
        ventana.precioTXT.setText (null);
        ventana.fechaCeacionDatePicker.setText (null);
        ventana.bdcTXT.setText (null);
        ventana.nombreTXT.requestFocus ();
    }

    public void refrescar(){
        ventana.dlmIntrumento.clear ();
        for(Instrumento unInstrumento:modelo.obtenerInstrumento ()){
            ventana.dlmIntrumento.addElement (unInstrumento);
        }
    }

    private void addActionListener(ActionListener listener){
        ventana.nuevoButton.addActionListener (listener);
        ventana.exportarButton.addActionListener (listener);
        ventana.importarButton.addActionListener (listener);
        ventana.borrarButton.addActionListener (listener);
        ventana.vientoRadioButton.addActionListener (listener);
        ventana.percusionRadioButton.addActionListener (listener);
        ventana.cuerdaRadioButton.addActionListener (listener);
    }

    private void addListSelection(ListSelectionListener listener){ ventana.list1.addListSelectionListener (listener);}
    private void addWindowListener(WindowListener listener){ ventana.frame.addWindowListener (listener);}

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion=new Properties ();
        configuracion.load (new FileReader ("instrumentos.conf"));
        ultimaRutaExportada= new File (configuracion.getProperty ("ultimaRutaExportada"));
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter ("vehiculos.conf"),"Datos configuracion instrumentos");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand ();

        switch (actionCommand){
            case "Nuevo":
                if(hayCamposVacios ()) {
                    Util.mensajeError ("Tienen que estar todos los campos rellenados");
                    break;
                }
                if (!modelo.hayStock ((Integer) ventana.StockSpinner.getValue ())) {
                    Util.mensajeError ("Tiene que haber stock si no pa que lo pones");
                }
                if (ventana.vientoRadioButton.isSelected ()){
                    modelo.altaViento (ventana.nombreTXT.getText (),ventana.marcaTXT.getText (),ventana.colorPrincipalTXT.getText (), Integer.parseInt ((String) ventana.StockSpinner.getValue ()),
                             Double.parseDouble ( ventana.precioTXT.getText ()), ventana.fechaCeacionDatePicker.getDate (),ventana.bdcTXT.getText ());
                }else if (ventana.percusionRadioButton.isSelected ()){
                    modelo.altaPercusion (ventana.nombreTXT.getText (),ventana.marcaTXT.getText (),ventana.colorPrincipalTXT.getText (), Integer.parseInt ((String) ventana.StockSpinner.getValue ()),
                            Double.parseDouble ( ventana.precioTXT.getText ()), ventana.fechaCeacionDatePicker.getDate (),ventana.bdcTXT.getText ());
                }else{
                    try {
                        int bdc = Integer.parseInt (ventana.bdcTXT.getText ( ));
                        modelo.altaCuerda (ventana.nombreTXT.getText ( ), ventana.marcaTXT.getText ( ), ventana.colorPrincipalTXT.getText ( ), Integer.parseInt ((String) ventana.StockSpinner.getValue ( )),
                                Double.parseDouble (ventana.precioTXT.getText ( )), ventana.fechaCeacionDatePicker.getDate ( ),bdc );
                    }catch (NumberFormatException ex){
                        Util.mensajeError ("No puede contener texto. Solo numeros");
                    }
                    }
                break;
            case "Importar":
                break;
            case "Exportar":
                break;
            case "Borrar":
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
