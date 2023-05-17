package org.example.dao;

import org.example.domain.Alojamiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private List<Alojamiento> alojamientos;

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public Database() {
        this.alojamientos=new ArrayList<>();
    }
}
