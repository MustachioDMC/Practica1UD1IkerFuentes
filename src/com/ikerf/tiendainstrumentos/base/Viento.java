package com.ikerf.tiendainstrumentos.base;

import java.time.LocalDate;

public class Viento extends Instrumento {
    private String tipoBoquilla;

    public Viento(){
        super();
    }

    public Viento(String nombre, String marca,String colorPrincipal,int numStock, double precio, LocalDate fechaCreacion, String tipoBoquilla) {
        super (nombre, marca, colorPrincipal, numStock, precio, fechaCreacion);
        this.tipoBoquilla = tipoBoquilla;
    }

    public String getTipoBoquilla() {
        return tipoBoquilla;
    }

    public void setTipoBoquilla(String tipoBoquilla) {
        this.tipoBoquilla = tipoBoquilla;
    }

    @Override
    public String toString() {
        return "Viento{ " + getNombre() + " " + getMarca() + " " + getColorPrincipal () + " "
                + getNumStock () + " " + getPrecio() + " " + getFechaCreacion() + " " + getTipoBoquilla () + " }";
    }
}
