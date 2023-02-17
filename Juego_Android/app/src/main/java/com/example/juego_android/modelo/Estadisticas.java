package com.example.juego_android.modelo;

public class Estadisticas {
    private int vidas;
    private int puntuacion;

    public Estadisticas(int vidas, int puntuacion) {
        this.vidas = vidas;
        this.puntuacion = puntuacion;
    }

    public void reducirVidas(){
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
}
