package com.example.juego_android.modelo.dao;

import android.util.Log;

import com.example.juego_android.bd.FireBaseBD;
import com.example.juego_android.game.EsquivarObstaculos;
import com.example.juego_android.interfaces.IDaoEstadisticas;
import com.example.juego_android.modelo.Estadisticas;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class DaoEstadisticas implements IDaoEstadisticas {
    private FireBaseBD fireBaseBD;

    @Override
    public void guardarEstadisticas(Estadisticas estadisticas, String nombreColeccion, String nombreDocumento) {
        FirebaseFirestore firestore = EsquivarObstaculos.fireBaseBD.getBd();
        DocumentReference documentReference = firestore.collection(nombreColeccion).document(nombreDocumento);
        documentReference
                .set(estadisticas, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully created!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error creating document", e));
    }

    @Override
    public void cargarEstadisticas(String nombreColeccion, String nombreDocumento) {
        FirebaseFirestore firestore = EsquivarObstaculos.fireBaseBD.getBd();
        DocumentReference documentReference = firestore.collection(nombreColeccion).document(nombreDocumento);

        documentReference.get()
                .addOnSuccessListener((documentSnapshot) -> {
                    Log.d(":::FIREBASE", "DocumentSnapshot successfully read!");

                    Estadisticas est = documentSnapshot.toObject(Estadisticas.class);
                    if (est != null) {
                        EsquivarObstaculos.estadisticas.setVidas(est.getVidas());
                        EsquivarObstaculos.estadisticas.setPuntuacion(est.getPuntuacion());
                        EsquivarObstaculos.nave1.setX(est.getxNave());
                        Log.d(":::FIREBASE", est.toString());
                    } else {
                        Log.d(":::FIREBASE", "Estadísticas nulas");
                    }
                })
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error reading document", e));
    }
}
