package com.example.examen_adat_pablo_maxia_2ev.modelo.interfaces;

import com.example.examen_adat_pablo_maxia_2ev.modelo.entidades.Ruta;

import java.util.List;

public interface IDaoRuta {
    void addRuta(Ruta ruta);
    List<Ruta> getRutas();
}
