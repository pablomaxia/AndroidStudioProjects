package com.example.juego_android.modelo.dao;

import android.util.Log;

import com.example.juego_android.bd.FireBaseBD;
import com.example.juego_android.game.Juego;
import com.example.juego_android.interfaces.IDaoEstadisticas;
import com.example.juego_android.modelo.Estadisticas;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class DaoEstadisticas implements IDaoEstadisticas {
    private FireBaseBD fireBaseBD;

    @Override
    public void guardarEstadisticas(Estadisticas estadisticas, String nombreColeccion, String nombreDocumento) {
        FirebaseFirestore firestore = Juego.fireBaseBD.getBd();
        firestore.collection(nombreColeccion)
                .document(nombreDocumento)
                .set(estadisticas, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully created!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error creating document", e));
    }
}
