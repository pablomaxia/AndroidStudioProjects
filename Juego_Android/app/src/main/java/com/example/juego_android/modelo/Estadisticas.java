package com.example.juego_android.modelo;

import com.example.juego_android.utilidades.UtilidadesJuego;

public class Estadisticas {
    private int vidas;
    private int puntuacion;
    private float xNave;

    public Estadisticas() {
        this.vidas = UtilidadesJuego.TOTAL_VIDAS;
        this.puntuacion = UtilidadesJuego.PUNTUACION_INICIAL;
        this.xNave = 0;
    }

    public Estadisticas(int vidas, int puntuacion, float xNave) {
        this.vidas = vidas;
        this.puntuacion = puntuacion;
        this.xNave = xNave;
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

    @Override
    public String toString() {
        return "Estadisticas{" +
                "vidas=" + vidas +
                ", puntuacion=" + puntuacion +
                ", xNave=" + xNave +
                '}';
    }

}
