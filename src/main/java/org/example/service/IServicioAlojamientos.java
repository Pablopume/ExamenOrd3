package org.example.service;

import org.example.dao.Database;
import org.example.domain.Alojamiento;
import org.example.domain.Hotel;

import java.util.List;

public interface IServicioAlojamientos {
    boolean anyadirAlojamiento(Alojamiento alojamiento);
    List<Alojamiento> listarPrecioProvincia(int minimo, int maximo, String provincia);
    List<Alojamiento> listarProvinciaValor(String provincia);
    void cambiarEstrellas(String nombre, int estrellas);
    boolean eliminarAlojamiento(Alojamiento alojamiento);
    List<Alojamiento> porProvincia(String provincia);
    List<Hotel> porEstrellas(boolean ascendente);
    Database getDatabase();
    Database leerFicheroBinario();
    void escribirFicherioBinario(Database database);
    void escribirFichero(List<Alojamiento> casas);
    List<Alojamiento> leerFichero();
    List<Alojamiento> getAlojamientos();
}
