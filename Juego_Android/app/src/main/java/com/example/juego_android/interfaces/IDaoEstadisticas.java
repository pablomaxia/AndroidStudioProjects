package com.example.juego_android.interfaces;

import com.example.juego_android.modelo.Estadisticas;

public interface IDaoEstadisticas {
    void guardarEstadisticas(Estadisticas estadisticas, String nombreColeccion, String nombreDocumento);
}
