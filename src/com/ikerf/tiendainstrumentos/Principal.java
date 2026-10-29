package com.ikerf.tiendainstrumentos;

import com.ikerf.tiendainstrumentos.gui.InstrumentoControlador;
import com.ikerf.tiendainstrumentos.gui.InstrumentoModelo;
import com.ikerf.tiendainstrumentos.gui.Ventana;

public class Principal {
    public static void main(String[] args){
        Ventana vista =new Ventana ();
        InstrumentoModelo modelo= new InstrumentoModelo ();
        InstrumentoControlador controlador= new InstrumentoControlador (vista, modelo);
    }
}
