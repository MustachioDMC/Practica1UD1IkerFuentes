package com.ikerf.tiendainstrumentos.gui;

import com.ikerf.tiendainstrumentos.base.Cuerda;
import com.ikerf.tiendainstrumentos.base.Instrumento;
import com.ikerf.tiendainstrumentos.base.Percusion;
import com.ikerf.tiendainstrumentos.base.Viento;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
                    break;
                }
                try {
                    double precio = Double.parseDouble (ventana.precioTXT.getText ( ));

                    if (ventana.vientoRadioButton.isSelected ( )) {
                        modelo.altaViento (ventana.nombreTXT.getText ( ), ventana.marcaTXT.getText ( ), ventana.colorPrincipalTXT.getText ( ), (Integer) ventana.StockSpinner.getValue ( ),
                               precio, ventana.fechaCeacionDatePicker.getDate ( ), ventana.bdcTXT.getText ( ));
                    } else if (ventana.percusionRadioButton.isSelected ( )) {
                        modelo.altaPercusion (ventana.nombreTXT.getText ( ), ventana.marcaTXT.getText ( ), ventana.colorPrincipalTXT.getText ( ), (Integer) ventana.StockSpinner.getValue ( ),
                               precio, ventana.fechaCeacionDatePicker.getDate ( ), ventana.bdcTXT.getText ( ));
                    } else {
                        int bdc = Integer.parseInt (ventana.bdcTXT.getText ( ));
                        modelo.altaCuerda (ventana.nombreTXT.getText ( ), ventana.marcaTXT.getText ( ), ventana.colorPrincipalTXT.getText ( ), (Integer) ventana.StockSpinner.getValue ( ),
                                precio, ventana.fechaCeacionDatePicker.getDate ( ), bdc);
                    }
                }catch (NumberFormatException ex){
                    Util.mensajeError ("Tienes error en el precio o numero de cuerdas: Tienen que ser numeros");
                    break;
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero (ultimaRutaExportada, "Archivo XML", "xml");
                int opt= selectorFichero.showSaveDialog (null);
                if(opt==JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.importarXML ((selectorFichero.getSelectedFile ()));
                        actualizarDatosConfiguracion (selectorFichero.getSelectedFile ());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace ( );
                    } catch (IOException ex) {
                        ex.printStackTrace ( );
                    } catch (SAXException ex) {
                        ex.printStackTrace ( );
                    }
                    refrescar ();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero (ultimaRutaExportada, "Archivo XML", "xml");
                int opt2= selectorFichero2.showSaveDialog (null);
                if(opt2==JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.exportarXML((selectorFichero2.getSelectedFile ()));
                        actualizarDatosConfiguracion (selectorFichero2.getSelectedFile ());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace ( );
                    } catch (TransformerException ex) {
                        ex.printStackTrace ( );
                    }
                    refrescar ();
                }
                break;
            case "Borrar":
                int respuesta= Util.mensajeConfirmacion ("¿Quieres borrar los campos rellenados?(No la lista)", "Borrar campos");
                if (respuesta==JOptionPane.OK_OPTION){
                    int respuesta2= Util.mensajeConfirmacion ("¿Quieres borrar tambien la lista?", "Borrar lista");
                    if (respuesta2==JOptionPane.OK_OPTION){
                        limpiarCampos ();
                        modelo.borrarLista ();
                        refrescar ();
                    }else if(respuesta2==JOptionPane.NO_OPTION){
                        limpiarCampos ();
                    }
                }else if(respuesta==JOptionPane.NO_OPTION){
                    int respuesta2= Util.mensajeConfirmacion ("¿Y la lista la quieres borrar?", "Borrar lista");
                    if (respuesta2==JOptionPane.OK_OPTION){
                        modelo.borrarLista ();
                        refrescar ();
                    }
                }
                break;
            case "Viento":
                ventana.lblBDC.setText ("Boquilla");
                break;
            case "Percusion":
                ventana.lblBDC.setText ("Dificultad");
                break;
            case "Cuerda":
                ventana.lblBDC.setText ("Numero de cuerdas");
                break;


        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting ()){
            Instrumento instrumentoSelecionado = (Instrumento) ventana.list1.getSelectedValue ();
            ventana.nombreTXT.setText (instrumentoSelecionado.getNombre ());
            ventana.marcaTXT.setText (instrumentoSelecionado.getMarca ());
            ventana.colorPrincipalTXT.setText (instrumentoSelecionado.getColorPrincipal ());
            ventana.StockSpinner.setValue (instrumentoSelecionado.getNumStock ());
            ventana.precioTXT.setText (String.valueOf (instrumentoSelecionado.getPrecio ()));
            ventana.fechaCeacionDatePicker.setDate (instrumentoSelecionado.getFechaCreacion ());
            if (instrumentoSelecionado instanceof Viento){
                ventana.vientoRadioButton.doClick ();
                ventana.bdcTXT.setText (((Viento) instrumentoSelecionado).getTipoBoquilla ());
            }else if (instrumentoSelecionado instanceof Percusion){
                ventana.percusionRadioButton.doClick ();
                ventana.bdcTXT.setText (((Percusion) instrumentoSelecionado).getDificultad ());
            }else{
                ventana.cuerdaRadioButton.doClick ();
                ventana.bdcTXT.setText (String.valueOf (((Cuerda)instrumentoSelecionado).getNumeroCuerdas()));
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp= Util.mensajeConfirmacion ("Quieres cerrar la ventana", "Cerrar programa");
        if (resp== JOptionPane.OK_OPTION){
            try {
                guardarDatosConfiguracion ();
            } catch (IOException ex) {
                ex.printStackTrace ( );
            }
            System.exit (0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowOpened(WindowEvent e) {

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


}
