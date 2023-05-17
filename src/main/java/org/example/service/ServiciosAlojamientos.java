package org.example.service;

import org.example.dao.DaoAlojamientos;
import org.example.dao.DaoAlojamientosImplementacion;
import org.example.dao.DaoFicherosImplementacion;
import org.example.dao.Database;
import org.example.domain.Alojamiento;
import org.example.domain.Hotel;


import java.util.List;

public class ServiciosAlojamientos implements IServicioAlojamientos{
private DaoAlojamientos dao;

    public ServiciosAlojamientos() {
        this.dao = new DaoAlojamientosImplementacion();
    }

    @Override
    public boolean anyadirAlojamiento(Alojamiento alojamiento) {
        return dao.anyadirAlojamiento(alojamiento);
    }

    @Override
    public List<Alojamiento> listarPrecioProvincia(int minimo, int maximo, String provincia) {
        return dao.listarProvinciaValor(provincia);
    }

    @Override
    public List<Alojamiento> listarProvinciaValor(String provincia) {
        return dao.listarProvinciaValor(provincia);
    }

    @Override
    public void cambiarEstrellas(String nombre, int estrellas) {
    dao.cambiarEstrellas(nombre, estrellas);
    }

    @Override
    public boolean eliminarAlojamiento(Alojamiento alojamiento) {
        return dao.eliminarAlojamiento(alojamiento);
    }

    @Override
    public List<Alojamiento> porProvincia(String provincia) {
        return dao.porProvincia(provincia);
    }

    @Override
    public List<Hotel> porEstrellas(boolean ascendente) {
        return dao.porEstrellas(ascendente);
    }

    @Override
    public Database getDatabase() {
        return dao.getDatabase();
    }

    @Override
    public Database leerFicheroBinario() {
        return DaoFicherosImplementacion.leerFicheroBinario();
    }

    @Override
    public void escribirFicherioBinario(Database database) {
         DaoFicherosImplementacion.escribirFicheroBinario(database);
    }

    @Override
    public void escribirFichero(List<Alojamiento> casas) {
        DaoFicherosImplementacion.escribirFichero(casas);
    }

    @Override
    public List<Alojamiento> leerFichero() {
        return DaoFicherosImplementacion.leerFichero();
    }

    @Override
    public List<Alojamiento> getAlojamientos() {
        return dao.getAlojamientos();
    }
}
