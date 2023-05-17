package org.example.dao;

import org.example.domain.Alojamiento;
import org.example.domain.Hotel;

import java.util.List;

public interface DaoAlojamientos {

    boolean anyadirAlojamiento(Alojamiento alojamiento);
    List<Alojamiento> listarPrecioProvincia(int minimo, int maximo, String provincia);
    List<Alojamiento> listarProvinciaValor(String provincia);
    void cambiarEstrellas(String nombre, int estrellas);
    boolean eliminarAlojamiento(Alojamiento alojamiento);
    List<Alojamiento> porProvincia(String provincia);
    List<Hotel> porEstrellas(boolean ascendente);

    Database getDatabase();
    List<Alojamiento> getAlojamientos();
}
