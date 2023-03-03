package com.example.examen_adat_pablo_maxia_2ev.modelo.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.examen_adat_pablo_maxia_2ev.modelo.bd.FireBaseBD;
import com.example.examen_adat_pablo_maxia_2ev.modelo.entidades.Ruta;
import com.example.examen_adat_pablo_maxia_2ev.modelo.interfaces.IDaoRuta;
import com.example.examen_adat_pablo_maxia_2ev.modelo.utilidades.Utilidades;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DaoRuta implements IDaoRuta {
    private FireBaseBD fireBaseBD;
    private FirebaseFirestore db;
    private List<Ruta> rutas;

    public DaoRuta() {
        fireBaseBD = FireBaseBD.getInstance();
        db = fireBaseBD.getBd();
        rutas = new ArrayList<>();
    }

    @Override
    public void addRuta(Ruta ruta) {
        db.collection(Utilidades.NOMBRE_COLECCION).add(ruta); // Create
    }

    @Override
    public List<Ruta> getRutas() {
        db.collection(Utilidades.NOMBRE_COLECCION).get() // Read
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ruta ruta = document.toObject(Ruta.class);
                                rutas.add(ruta);
                                Log.d(Utilidades.ETIQUETA, "Longitud inicial: " + ruta.getLongInicial() +
                                        " Latitud inicial: " + ruta.getLatInicial() +
                                        " Rumbo: " + ruta.getRumbo() +
                                        " Distancia: " + ruta.getDistancia());
                            }
                        } else {
                            Log.w(":::FIREBASE", "Error leyendo documento: " + rutas.toString());
                        }
                    }
                });
        return rutas;
    }
}
