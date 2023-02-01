package com.example.videojuego;

import com.example.videojuego.sprites.Sprite;

public class Utilidades {

    public static float distancia(float x1, float y1, float x2, float y2) {

        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static boolean colisionCirculos(float x1, float y1, float r1, float x2, float y2, float r2) {

        return !((r1 + r2) <= distancia(x1, y1, x2, y2));

    }

    public static void calculaReboteCirculos(Sprite b, Sprite s) {


    }


}

