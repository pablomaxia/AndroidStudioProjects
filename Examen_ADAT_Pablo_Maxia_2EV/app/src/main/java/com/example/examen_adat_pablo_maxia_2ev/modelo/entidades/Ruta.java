package com.example.examen_adat_pablo_maxia_2ev.modelo.entidades;

import com.example.examen_adat_pablo_maxia_2ev.modelo.interfaces.Observer;

import java.util.ArrayList;

public class Ruta {
    private double longInicial;
    private double latInicial;
    private double rumbo;
    private double distancia;
    private ArrayList<Observer> observers;

    public Ruta(){
        observers = new ArrayList<>();
    }

    public Ruta(double longInicial, double latInicial, double rumbo, double distancia) {
        this.longInicial = longInicial;
        this.latInicial = latInicial;
        this.rumbo = rumbo;
        this.distancia = distancia;
        observers = new ArrayList<>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(longInicial, latInicial, rumbo, distancia);
        }
    }

    public double getLongInicial() {
        return longInicial;
    }

    public void setLongInicial(double longInicial) {
        this.longInicial = longInicial;
    }

    public double getLatInicial() {
        return latInicial;
    }

    public void setLatInicial(double latInicial) {
        this.latInicial = latInicial;
    }

    public double getRumbo() {
        return rumbo;
    }

    public void setRumbo(double rumbo) {
        this.rumbo = rumbo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
