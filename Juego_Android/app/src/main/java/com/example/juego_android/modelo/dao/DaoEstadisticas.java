package com.example.juego_android.modelo.dao;

import android.util.Log;

import com.example.juego_android.bd.FireBaseBD;
import com.example.juego_android.game.Juego;
import com.example.juego_android.interfaces.IDaoEstadisticas;
import com.example.juego_android.modelo.Estadisticas;
import com.example.juego_android.utilidades.Utilidades;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class DaoEstadisticas implements IDaoEstadisticas {
    private FireBaseBD fireBaseBD;

    @Override
    public void guardarEstadisticas(Estadisticas estadisticas, String nombreColeccion, String nombreDocumento) {
        FirebaseFirestore firestore = Juego.fireBaseBD.getBd();
        DocumentReference documentReference = firestore.collection(nombreColeccion).document(nombreDocumento);
        documentReference
                .set(estadisticas, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully created!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error creating document", e));
    }

    @Override
    public Estadisticas cargarEstadisticas(String nombreColeccion, String nombreDocumento) {
        FirebaseFirestore firestore = Juego.fireBaseBD.getBd();
        DocumentReference documentReference = firestore.collection(nombreColeccion).document(nombreDocumento);

        DocumentSnapshot document = null;
        Task<DocumentSnapshot> task = null;
        final Estadisticas[] est = {Juego.estadisticas};
        task = documentReference.get()
                .addOnSuccessListener((aVoid) -> {
                    Log.d(":::FIREBASE", "DocumentSnapshot successfully read!");
                    est[0] = est[0] == null ? document.toObject(Estadisticas.class) : new Estadisticas(Utilidades.TOTAL_VIDAS + 100, Utilidades.PUNTUACION_INICIAL + 1000);
                    if (est[0] != null) {
                        Log.d(":::FIREBASE", est[0].toString());
                    } else {
                        Log.d(":::FIREBASE", "Estadísticas nulas");
                    }
                })
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error reading document", e));
        return est[0];
    }
}
