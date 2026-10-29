package com.ikerf.tiendainstrumentos.base;

import java.time.LocalDate;

public class Instrumento {
    private String nombre;
    private String marca;
    private String colorPrincipal;
    private int numStock;
    private double precio;
    private LocalDate fechaCreacion;

    public Instrumento(){

    }

    public Instrumento(String nombre, String marca, String colorPrincipal,int numStock, double precio, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.marca = marca;
        this.colorPrincipal=colorPrincipal;
        this.numStock=numStock;
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

    public int getNumStock() {
        return numStock;
    }

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }
}
