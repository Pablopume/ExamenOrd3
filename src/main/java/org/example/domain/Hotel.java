package org.example.domain;

import org.example.common.Comprobacion;
import org.example.common.EstrellasException;

public class Hotel extends Alojamiento{
  private int estrellas;

    public Hotel(String nombre, int valoracion, String provincia, int precioHabitacion,int estrellas) throws EstrellasException {
        super(provincia, nombre, precioHabitacion, valoracion);
        Comprobacion.estrellasOk(estrellas);
        this.estrellas = estrellas;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    @Override
    public String toString() {
        return super.toString()+";"+estrellas;
    }

    @Override
    public String toStringFichero() {
        return super.toString()+";"+estrellas;
    }
}
