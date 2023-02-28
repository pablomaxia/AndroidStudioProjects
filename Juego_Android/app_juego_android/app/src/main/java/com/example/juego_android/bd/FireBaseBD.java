package com.example.juego_android.bd;

import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseBD {
    private static FireBaseBD instance = null;
    private final FirebaseFirestore bd;

    private FireBaseBD() {
        bd = FirebaseFirestore.getInstance();
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
}
