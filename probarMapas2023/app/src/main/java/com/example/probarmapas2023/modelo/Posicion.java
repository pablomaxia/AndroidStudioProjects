package com.example.probarmapas2023.modelo;

import com.google.android.gms.maps.model.LatLng;

public class Posicion {
    private String calle;
    private LatLng latLng;

    public Posicion(String calle, LatLng latLng) {
        this.calle = calle;
        this.latLng = latLng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
