package org.example.domain;

public class CasaRural extends Alojamiento{
    private boolean hayPiscina;
    public CasaRural(String provincia, String nombre, int precioHabitacion, int valoracion, boolean hayPiscina) {
        super(provincia, nombre, precioHabitacion, valoracion);
        this.hayPiscina=hayPiscina;
    }

    @Override
    public String toStringFichero() {
        return super.toString()+";"+hayPiscina;
    }
}
