package com.example.juego_android.utilidades;

import com.example.juego_android.bd.FireBaseBD;
import com.example.juego_android.sprites.Sprite;

public class Utilidades {
    public static final int ALTO_PANTALLA = 2050;
    public static final int ANCHO_PANTALLA = 1000;
    public static final float GRAVEDAD = -9.8f;
    public static final int POSICION_X_INICIAL_NAVE = 500;
    public static final int POSICION_Y_INICIAL_NAVE = 2050;
    public static final int PUNTUACION_INICIAL = 0;
    public static final int TOTAL_VIDAS = 5;

    public static final String NOMBRE_COLECCION_ESTADISTICAS = "estadisticas";
    public static final String NOMBRE_DOCUMENTO_ESTADISTICAS = "estadisticas_juego_nave";

    public static float distancia(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static boolean colisionCirculos(float x1, float y1, float r1, float x2, float y2, float r2) {

        return (r1 + r2) > distancia(x1, y1, x2, y2);

    }

    public static void calculaReboteCirculos(Sprite b, Sprite s) {
    }
}
