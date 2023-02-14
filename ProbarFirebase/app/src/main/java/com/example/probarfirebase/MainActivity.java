package com.example.probarfirebase;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        /*create(new Grupo("5a","Grupo 5a"));

        readAll();

        update("GRUPO - 3A","uAZ0bfOyYMAHayzoC1tW");

        deleteOne("1A");*/
        insertarMerge();

    }

    private void ejemplo() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> Log.d(":::FIREBASE", "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error adding document", e));
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(":::FIREBASE", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.w(":::FIREBASE", "Error getting documents.", task.getException());
                    }
                });
    }

    private void create(Grupo gr) {
        //db.collection("grupo").add(gr); // Create
        db.collection("grupo")
                .document(gr.getIdGrupo())
                .set(gr); // Create
    }

    private void readAll() {
        db.collection("grupo").get() // Read
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(":::FIREBASE", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.w(":::FIREBASE", "Error getting documents.", task.getException());
                    }
                });
    }

    private void update(String nombre, String id) {
        db.collection("grupo")// Update
                .document(nombre)
                .update("nombre", nombre)
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully updated!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error updating document", e));
    }

    private void deleteOne(String idGrupo) {
        db.collection("grupo")// Delete
                .document(idGrupo)
                .delete()
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully deleted!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error deleting document", e));
    }

    private void insertarMerge() {
        // Update one field, creating the document if it does not already exist.
        Map<String, Object> data = new HashMap<>();
        data.put("capital", true);
        data.put("population", 100_000_000);

        db.collection("cities").document("BJ")
                .set(data, SetOptions.merge())
                .addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "DocumentSnapshot successfully updated!"))
                .addOnFailureListener(e -> Log.w(":::FIREBASE", "Error updating document", e));


    }
}