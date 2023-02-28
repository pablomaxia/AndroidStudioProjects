package com.example.mvvm_firebase.ui.usuarios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_firebase.databinding.FragmentUsuarioBinding;
import com.example.mvvm_firebase.modelo.bd.FireBaseBD;
import com.example.mvvm_firebase.modelo.entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Usuario}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyUsuarioRecyclerViewAdapter extends RecyclerView.Adapter<MyUsuarioRecyclerViewAdapter.ViewHolder> {

    private final List<Usuario> mValues;
    private FireBaseBD fireBaseBD;
    private FirebaseFirestore db;
    private final String COLECCION = "usuarios";
    private final String DOCUMENTO = "usuario_";

    public MyUsuarioRecyclerViewAdapter(List<Usuario> items) {
        mValues = items;
        fireBaseBD = FireBaseBD.getInstance();
        db = fireBaseBD.getBd();

        for (int i = 0; i < 10; i++) {
            crear(new Usuario(i, "TEST_" + i), DOCUMENTO + i, COLECCION);
        }
        ver(DOCUMENTO, COLECCION);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(FragmentUsuarioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        for (Usuario usuario : mValues) {
            Log.d(":::FIREBASE", usuario.getId() + ": " + usuario.getNombre());
        }

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("Id usuario: " + holder.mItem.getId());
        holder.mNombreView.setText("Nombre: " + holder.mItem.getNombre());

        //TODO: Hacer que funcione el onClickListener
        holder.mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarUno(holder.mItem.getId(), DOCUMENTO + holder.mItem.getId(), COLECCION);
            }
        });

        //En este punto escuchamos los eventos y se lo pasamos al listener que teniamos referenciado desde el principio
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mNombreView;
        public Usuario mItem;
        public final Button mButtonDelete;
        public final Button mButtonUpdate;

        public ViewHolder(FragmentUsuarioBinding binding) {
            super(binding.getRoot());
            mIdView = binding.idUsuario;
            mNombreView = binding.nombre;
            mButtonDelete = binding.buttonDelete;
            mButtonUpdate = binding.buttonUpdate;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombreView.getText() + "'";
        }
    }

    public void crear(Usuario usuario, String nombreDocumento, String nombreColeccion) {
        Map<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("id", usuario.getId());
        usuarioMap.put("nombre", usuario.getNombre());

        db.collection(nombreColeccion).document(nombreDocumento).set(usuarioMap); // Create
    }

    public Usuario verUno(String nombreDocumento, String nombreColeccion) {
        AtomicReference<Usuario> usuario = null;
        db.collection(nombreColeccion).document(nombreDocumento).get() // Read
                .addOnSuccessListener(aVoid -> {
                    usuario.set(aVoid.toObject(Usuario.class));
                    Log.d(":::FIREBASE", usuario.get().getId() + " => " + usuario.get().getNombre());
                }).addOnFailureListener(e -> Log.w(":::FIREBASE", "Error leyendo documento: " + nombreDocumento, e));
        return usuario.get();
    }

    public void ver(String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion).get() // Read
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Usuario usuario = document.toObject(Usuario.class);
                                mValues.add(usuario);
                                notifyDataSetChanged();
//                                Log.d(":::FIREBASE", usuario.getId() + " => " + usuario.getNombre());
                            }
                        } else {
                            Log.w(":::FIREBASE", "Error leyendo documento: " + nombreDocumento);
                        }
                    }
                });
    }

    public void editar(Usuario usuario, String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion)// Update
                .document(nombreDocumento).update("id", usuario.getId(), "nombre", usuario.getNombre()).addOnSuccessListener(aVoid -> Log.d(":::FIREBASE", "Documento: " + usuario.getId() + "actualizado")).addOnFailureListener(e -> Log.w(":::FIREBASE", "Error actualizando documento: " + usuario.getId(), e));
    }

    public void borrarUno(int position, String nombreDocumento, String nombreColeccion) {
        db.collection(nombreColeccion)// Delete
                .document(nombreDocumento).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mValues.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, mValues.size());
                        } else {
                            Log.w(":::FIREBASE", "Error borrando documento: " + nombreDocumento);
                        }
                    }

                });
    }
}