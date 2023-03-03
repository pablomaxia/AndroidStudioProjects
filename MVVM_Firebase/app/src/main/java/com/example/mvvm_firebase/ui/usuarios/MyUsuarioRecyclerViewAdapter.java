package com.example.mvvm_firebase.ui.usuarios;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_firebase.R;
import com.example.mvvm_firebase.databinding.FragmentUsuarioBinding;
import com.example.mvvm_firebase.modelo.bd.FireBaseBD;
import com.example.mvvm_firebase.modelo.entidades.Usuario;
import com.example.mvvm_firebase.modelo.interfaces.Observer;
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
public class MyUsuarioRecyclerViewAdapter extends RecyclerView.Adapter<MyUsuarioRecyclerViewAdapter.ViewHolder> implements Observer {

    private final List<Usuario> mValues;
    private FireBaseBD fireBaseBD;
    private FirebaseFirestore db;
    private final String COLECCION = "usuarios";
    private final String DOCUMENTO = "usuario_";
    private ViewHolder view;

    public MyUsuarioRecyclerViewAdapter(List<Usuario> items) {
        mValues = items;
        fireBaseBD = FireBaseBD.getInstance();
        db = fireBaseBD.getBd();

        /*if (mValues.size() == 0) {
            for (int i = 0; i < 10; i++) {
                crear(new Usuario(i, "TEST_" + i), DOCUMENTO + i, COLECCION);
            }
        }*/
        ver(DOCUMENTO, COLECCION);
        for (Usuario usuario : mValues) {
            usuario.registerObserver(this);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = new ViewHolder(FragmentUsuarioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        /*for (Usuario usuario : mValues) {
            Log.d(":::FIREBASE", usuario.getId() + ": " + usuario.getNombre());
        }*/

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
                borrarUno(holder.mItem.getId(), obtenerDocumento(holder.mItem), COLECCION);
            }
        });
        holder.mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Title");
                // I'm using fragment here so I'm using getView() to provide ViewGroup
                // but you can provide here any other instance of ViewGroup from your Fragment / Activity
                View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.form_update, (ViewGroup) view.getParent(), false);
                // Set up the input
                final EditText input = viewInflated.findViewById(R.id.nombre_update);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                builder.setView(viewInflated);

                // Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        holder.nombre = input.getText().toString();
                        holder.mItem.setNombre(holder.nombre);
                        editar(holder.mItem, obtenerDocumento(holder.mItem), COLECCION);
                        holder.mItem.notifyObservers();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
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

    @Override
    public void update(int id, String nombre) {
        mValues.get(id).setNombre(nombre);
        Toast.makeText(view.itemView.getContext(), mValues.get(id).toString(), Toast.LENGTH_SHORT).show();
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
                                notifyItemInserted(usuario.getId());
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
                .document(nombreDocumento).set(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyItemChanged(usuario.getId());
                            Log.d(":::FIREBASE", usuario.getId() + " => " + usuario.getNombre());
                        } else {
                            Log.w(":::FIREBASE", "Error actualizando documento: " + nombreDocumento);
                        }
                    }
                });
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

    private String obtenerDocumento(Usuario usuario) {
        return DOCUMENTO + usuario.getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mNombreView;
        public Usuario mItem;
        public final Button mButtonDelete;
        public final Button mButtonUpdate;
        public String nombre;

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
}