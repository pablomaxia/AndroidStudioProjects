package com.example.examen_adat_pablo_maxia_2ev.modelo.bd;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseBD {
    private static FireBaseBD instance = null;
    private final FirebaseFirestore bd;
    private final DatabaseReference reference;

    private FireBaseBD() {
        bd = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public synchronized static FireBaseBD getInstance() {
        if (instance == null) {
            instance = new FireBaseBD();
        }

        return instance;
    }

    public FirebaseFirestore getBd() {
        return bd;
    }

    public DatabaseReference getReference() {
        return reference;
    }
}
