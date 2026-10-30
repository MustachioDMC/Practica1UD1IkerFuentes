package com.ikerf.tiendainstrumentos.base;

import java.time.LocalDate;

public class Cuerda extends Instrumento {
    private int numeroCuerdas;

    public Cuerda(int numeroCuerdas) {
        this.numeroCuerdas = numeroCuerdas;
    }

    public Cuerda(String nombre, String marca, String colorPrincipal,int numStock, double precio, LocalDate fechaCreacion, int numeroCuerdas) {
        super (nombre, marca, colorPrincipal, numStock, precio, fechaCreacion);
        this.numeroCuerdas = numeroCuerdas;
    }

    public int getNumeroCuerdas() {
        return numeroCuerdas;
    }

    public void setNumeroCuerdas(int numeroCuerdas) {
        this.numeroCuerdas = numeroCuerdas;
    }
    public String toString() {
        return "Cuerda{ " + getNombre() + " " + getMarca() + " " + getColorPrincipal () + " "
                + getNumStock () + " " + getPrecio() + " " + getFechaCreacion() + " " + getNumeroCuerdas () + " }";
    }
}
