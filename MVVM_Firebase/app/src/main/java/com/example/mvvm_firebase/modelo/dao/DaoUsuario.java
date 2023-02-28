package com.example.mvvm_firebase.modelo.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvvm_firebase.modelo.bd.FireBaseBD;
import com.example.mvvm_firebase.modelo.entidades.Usuario;
import com.example.mvvm_firebase.modelo.interfaces.IDaoUsuario;
import com.example.mvvm_firebase.ui.usuarios.MyUsuarioRecyclerViewAdapter;
import com.example.mvvm_firebase.ui.usuarios.UsuarioFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DaoUsuario implements IDaoUsuario {
    private FireBaseBD fireBaseBD;
    private FirebaseFirestore db;
    private List<Usuario> usuarios;
    private MyUsuarioRecyclerViewAdapter adapter;

    public DaoUsuario() {
        fireBaseBD = FireBaseBD.getInstance();
        db = fireBaseBD.getBd();
        usuarios = new ArrayList<>();
        adapter = new MyUsuarioRecyclerViewAdapter(usuarios);
    }

    @Override
    public void crear(Usuario usuario, String nombreDocumento, String nombreColeccion) {
        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("id", usuario.getId());
        usuarioMap.put("nombre", usuario.getNombre());

        db.collection(nombreColeccion).document(nombreDocumento).set(usuarioMap); // Create
    }

    @Override
    public Usuario verUno(String nombreDocumento, String nombreColeccion) {
        AtomicReference<Usuario> usuario = null;
        db.collection(nombreColeccion).document(nombreDocumento).get() // Read
                .addOnSuccessListener(aVoid -> {
                    usuario.set(aVoid.toObject(Usuario.class));
                    Log.d(":::FIREBASE", usuario.get().getId() + " => " + usuario.get().getNombre());
                }).addOnFailureListener(e -> Log.w(":::FIREBASE", "Error leyendo documento: " + nombreDocumento, e));
        return usuario.get();
    }

    @Override
    public List<Usuario> ver(String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion).get() // Read
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Usuario usuario = document.toObject(Usuario.class);
                                usuarios.add(usuario);
                                adapter.notifyDataSetChanged();
//                                Log.d(":::FIREBASE", usuario.getId() + " => " + usuario.getNombre());
                            }
                        } else {
                            Log.w(":::FIREBASE", "Error leyendo documento: " + nombreDocumento);
                        }
                    }
                });
        return usuarios;
    }

    @Override
    public void editar(Usuario usuario, String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion)// Update
                .document(nombreDocumento).update("id", usuario.getId(), "nombre", usuario.getNombre()).addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "Documento: " + usuario.getId() + "actualizado")).addOnFailureListener(e -> Log.w(":::FIREBASE", "Error actualizando documento: " + usuario.getId(), e));
    }

    @Override
    public void borrarUno(String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion)// Delete
                .document(nombreDocumento).delete().addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "Documento " + nombreDocumento + "borrado")).addOnFailureListener(e -> Log.w(":::FIREBASE", "Error borrando documento: " + nombreDocumento, e));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public MyUsuarioRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MyUsuarioRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }
}
