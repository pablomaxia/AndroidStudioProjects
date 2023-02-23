package com.example.juego_android.modelo;

import com.example.juego_android.utilidades.UtilidadesJuego;

public class Estadisticas {
    private int vidas;
    private int puntuacion;
    private float xNave;
    private int puntuacionMaxima;

    public Estadisticas() {
        this.vidas = UtilidadesJuego.TOTAL_VIDAS;
        this.puntuacion = UtilidadesJuego.PUNTUACION_INICIAL;
        this.xNave = 0;
        this.puntuacionMaxima = UtilidadesJuego.PUNTUACION_INICIAL;
    }

    public Estadisticas(int vidas, int puntuacion, float xNave) {
        this.vidas = vidas;
        this.puntuacion = puntuacion;
        this.xNave = xNave;
    }

    public Estadisticas(int vidas, int puntuacion, float xNave, int puntuacionMaxima) {
        this.vidas = vidas;
        this.puntuacion = puntuacion;
        this.xNave = xNave;
        this.puntuacionMaxima = puntuacionMaxima;
    }

    public void reducirVidas() {
        this.vidas--;
    }

    /* GET Y SET */
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public float getxNave() {
        return xNave;
    }

    public void setxNave(float xNave) {
        this.xNave = xNave;
    }

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = puntuacionMaxima;
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
                "vidas=" + vidas +
                ", puntuacion=" + puntuacion +
                ", xNave=" + xNave +
                '}';
    }

}
