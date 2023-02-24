package com.example.juego_android.utilidades;

import com.example.juego_android.sprites.Sprite;

public class UtilidadesSprites {
    public static final float GRAVEDAD = -9.8f;
    public static final int POSICION_X_INICIAL_NAVE = 500;
    public static final int POSICION_Y_INICIAL_NAVE = 2050;
    public static final int POSICION_Y_INICIAL_OBSTACULOS = 10;
    public static final int RADIO_OBSTACULOS = 20;
    public static final int VARIACION_X_OBSTACULOS = 45;
    public static final float INCREMENTO_RADIO = 2.5f;

    public static float distancia(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static boolean colisionCirculos(float x1, float y1, float r1, float x2, float y2, float r2) {

        return (r1 + r2) > distancia(x1, y1, x2, y2);

    }

    public static void calculaReboteCirculos(Sprite b, Sprite s) {
    }
}
