package org.example.dao;

import org.example.common.Comprobacion;
import org.example.common.EstrellasException;
import org.example.domain.Alojamiento;
import org.example.domain.Hotel;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaoAlojamientosImplementacion implements DaoAlojamientos {
    private Database database=null;
    public List<Alojamiento> getAlojamientos(){
        return this.database.getAlojamientos();
    }
    public DaoAlojamientosImplementacion() {
        this.database = DaoFicherosImplementacion.leerFicheroBinario();
    }

    public Database getDatabase() {
        return database;
    }

    @Override
    public boolean anyadirAlojamiento(Alojamiento alojamiento) {
        return database.getAlojamientos().add(alojamiento);
    }

    @Override
    public List<Alojamiento> listarPrecioProvincia(int minimo, int maximo, String provincia) {
        List<Alojamiento> lista;
        lista=database.getAlojamientos().stream().filter(alojamiento -> alojamiento.getPrecioHabitacion() >= minimo && alojamiento.getPrecioHabitacion() <= maximo && alojamiento.getProvincia().equals(provincia)).toList();
    return lista;
    }

    @Override
    public List<Alojamiento> listarProvinciaValor(String provincia) {
        Alojamiento alojamiento2;
        List<Alojamiento> alojamientos = database.getAlojamientos().stream().filter(alojamiento -> alojamiento.getProvincia().equals(provincia)).collect(Collectors.toList());
        alojamientos.sort(Comparator.comparing(Alojamiento::getValoracion));
        return alojamientos;
    }

    @Override
    public void cambiarEstrellas(String nombre, int estrellas) {
        database.getAlojamientos().stream().filter(alojamiento -> alojamiento.getNombre().equals(nombre) && alojamiento.getClass().isInstance(Hotel.class)).map(Hotel.class::cast).forEach(hotel -> {
            try {
                Comprobacion.estrellasOk(estrellas);
            } catch (EstrellasException e) {
                throw new RuntimeException(e);
            }
            hotel.setEstrellas(estrellas);
        });
    }

    @Override
    public boolean eliminarAlojamiento(Alojamiento alojamiento) {
        return database.getAlojamientos().remove(alojamiento);
    }

    @Override
    public List<Alojamiento> porProvincia(String provincia) {
        return database.getAlojamientos().stream().filter(alojamiento -> alojamiento.getProvincia().equals(provincia)).toList();
    }

    @Override
    public List<Hotel> porEstrellas(boolean ascendente) {
        List<Hotel> listaAl= (database.getAlojamientos().stream().filter(alojamiento -> alojamiento.getClass().isInstance(Hotel.class)).map(Hotel.class::cast).collect(Collectors.toList()));
        listaAl.sort(Comparator.comparing(Hotel::getEstrellas));
        if (!ascendente){
            Collections.reverse(listaAl);
        }
        return listaAl;
    }
}
