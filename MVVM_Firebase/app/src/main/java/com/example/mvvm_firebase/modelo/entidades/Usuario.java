package com.example.mvvm_firebase.modelo.entidades;

public class Usuario {
    private int id;
    private String nombre;

    public Usuario() {
        this.id = 0;
        this.nombre = "";
    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
