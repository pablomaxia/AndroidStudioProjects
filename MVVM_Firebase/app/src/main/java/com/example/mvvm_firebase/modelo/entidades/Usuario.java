package com.example.mvvm_firebase.modelo.entidades;


import java.util.ArrayList;
import com.example.mvvm_firebase.modelo.interfaces.Observer;

public class Usuario {
    private int id;
    private String nombre;
    private ArrayList<Observer> observers;

    public Usuario() {
        this.id = 0;
        this.nombre = "";
        observers = new ArrayList<Observer>();
    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
            observer.update(id, nombre);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
