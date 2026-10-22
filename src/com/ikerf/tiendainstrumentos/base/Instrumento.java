package com.ikerf.tiendainstrumentos.base;

import java.time.LocalDate;

public class Instrumento {
    private String nombre;
    private String marca;
    private double precio;
    private LocalDate fechaCreacion;

    public Instrumento(){

    }

    public Instrumento(String nombre, String marca, double precio, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
