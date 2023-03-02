package com.example.probarmapas2023.modelo;

import com.google.android.gms.maps.model.LatLng;

public class Posicion {
    private String direccion;
    private LatLng latLng;

    public Posicion(String direccion, LatLng latLng) {
        this.direccion = direccion;
        this.latLng = latLng;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
