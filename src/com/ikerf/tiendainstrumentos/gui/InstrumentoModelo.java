package com.ikerf.tiendainstrumentos.gui;

import com.ikerf.tiendainstrumentos.base.Cuerda;
import com.ikerf.tiendainstrumentos.base.Instrumento;
import com.ikerf.tiendainstrumentos.base.Percusion;
import com.ikerf.tiendainstrumentos.base.Viento;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class InstrumentoModelo {
    private ArrayList<Instrumento> listaInstrumentos;

    public InstrumentoModelo() { listaInstrumentos = new ArrayList<> (); }

    public ArrayList<Instrumento> obtenerInstrumento(){ return listaInstrumentos;}

    public void altaViento (String nombre, String marca, String colorPrincipal, int stock, double precio, LocalDate fechaCreacion, String boquilla){
        Viento unViento= new Viento (nombre,marca,colorPrincipal,stock,precio,fechaCreacion,boquilla);
        listaInstrumentos.add (unViento);
    }
    public void altaPercusion (String nombre, String marca, String colorPrincipal, int stock, double precio, LocalDate fechaCreacion, String dificultad){
        Percusion unPercusion= new Percusion (nombre,marca,colorPrincipal,stock,precio,fechaCreacion,dificultad);
        listaInstrumentos.add (unPercusion);
    }
    public void altaCuerda (String nombre, String marca, String colorPrincipal, int stock, double precio, LocalDate fechaCreacion, int numCuerdas){
        Cuerda unCuerda= new Cuerda (nombre,marca,colorPrincipal,stock,precio,fechaCreacion,numCuerdas);
        listaInstrumentos.add (unCuerda);
    }
    public boolean hayStock(int numStock){
        for(Instrumento unInstrumento: listaInstrumentos){
            if(unInstrumento.getNumStock ()==0){
                return false;
            }
        }
        return true;
    }
    public void exportarXML (File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance ();
        DocumentBuilder builder= factory.newDocumentBuilder ();
        DOMImplementation dom= builder.getDOMImplementation ();
        Document documento= dom.createDocument ( null, "xml", null);

        Element raiz=documento.createElement ("instrumentos");
        documento.getDocumentElement ().appendChild (raiz);

        Element nodoInstrumento=null;
        Element nodoDatos=null;
        Text texto= null;

        for(Instrumento unInstrumento: listaInstrumentos){
            if(unInstrumento instanceof Viento){
                nodoInstrumento=documento.createElement ("Viento");
            }else if(unInstrumento instanceof Percusion){
                nodoInstrumento=documento.createElement ("Percusion");
            }else{
                nodoInstrumento=documento.createElement ("Cuerda");
            }
            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("nombre");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (unInstrumento.getNombre ());
            nodoDatos.appendChild (texto);

            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("marca");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (unInstrumento.getMarca ());
            nodoDatos.appendChild (texto);

            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("color-principal");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (unInstrumento.getColorPrincipal ());
            nodoDatos.appendChild (texto);

            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("stock");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (String.valueOf (unInstrumento.getNumStock ()));
            nodoDatos.appendChild (texto);

            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("precio");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (unInstrumento.getNombre ());
            nodoDatos.appendChild (texto);

            raiz.appendChild (nodoInstrumento);
            nodoDatos=documento.createElement ("fecha-creacion");
            nodoInstrumento.appendChild (nodoDatos);
            texto=documento.createTextNode (unInstrumento.getFechaCreacion ().toString ());
            nodoDatos.appendChild (texto);

            if(unInstrumento instanceof Viento){
                raiz.appendChild (nodoInstrumento);
                nodoDatos=documento.createElement ("boquilla");
                nodoInstrumento.appendChild (nodoDatos);
                texto=documento.createTextNode (((Viento) unInstrumento).getTipoBoquilla ());
                nodoDatos.appendChild (texto);
            } else if(unInstrumento instanceof Percusion){
                raiz.appendChild (nodoInstrumento);
                nodoDatos=documento.createElement ("dificultad");
                nodoInstrumento.appendChild (nodoDatos);
                texto=documento.createTextNode (((Percusion) unInstrumento).getDificultad ());
                nodoDatos.appendChild (texto);
            } else{
                raiz.appendChild (nodoInstrumento);
                nodoDatos=documento.createElement ("numero-cuerdas");
                nodoInstrumento.appendChild (nodoDatos);
                texto=documento.createTextNode (String.valueOf (((Cuerda)unInstrumento).getNumeroCuerdas ()));
                nodoDatos.appendChild (texto);
            }
        }

        Source source = new DOMSource (documento);
        Result resultado = new StreamResult (fichero);
        Transformer transformer= TransformerFactory.newInstance ().newTransformer ();
        transformer.transform (source,resultado);
    }


}
