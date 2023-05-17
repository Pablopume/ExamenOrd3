package org.example.domain;

import java.util.Comparator;

public class Compare implements Comparator<Alojamiento> {
    @Override
    public int compare(Alojamiento o1, Alojamiento o2) {
        return o1.getValoracion()- o2.getValoracion();
    }
}
