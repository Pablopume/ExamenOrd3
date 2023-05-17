package org.example.domain;

import java.io.Serializable;

public abstract class Alojamiento implements Serializable {
    private String provincia;
    private String nombre;
    private int precioHabitacion;
    private int valoracion;

    public Alojamiento(String provincia, String nombre, int precioHabitacion, int valoracion) {
        this.provincia = provincia;
        this.nombre = nombre;
        this.precioHabitacion = precioHabitacion;
        this.valoracion = valoracion;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecioHabitacion() {
        return precioHabitacion;
    }

    public int getValoracion() {
        return valoracion;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+";"+nombre+";"+valoracion+";"+provincia+";"+precioHabitacion;
    }

public abstract String toStringFichero();
}
