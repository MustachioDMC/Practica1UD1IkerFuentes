package com.ikerf.tiendainstrumentos.base;

import java.time.LocalDate;

public class Percusion extends Instrumento {
    private String dificultad;

    public Percusion(){
        super();
    }

    public Percusion(String dificultad) {
        this.dificultad = dificultad;
    }

    public Percusion(String nombre, String marca, String colorPrincipal,int numStock, double precio, LocalDate fechaCreacion, String dificultad) {
        super (nombre, marca, colorPrincipal, numStock, precio, fechaCreacion);
        this.dificultad = dificultad;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    public String toString() {
        return "Percusion{ " + getNombre() + " " + getMarca() + " " + getColorPrincipal () + " "
                + getNumStock () + " " + getPrecio() + " " + getFechaCreacion() + " " + getDificultad () + " }";
    }
}
