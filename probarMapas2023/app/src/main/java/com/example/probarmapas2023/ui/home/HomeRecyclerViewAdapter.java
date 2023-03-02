package com.example.probarmapas2023.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.probarmapas2023.databinding.FragmentHomeBinding;
import com.example.probarmapas2023.modelo.Posicion;

import java.util.List;

public class HomeRecyclerViewAdapter  extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<Posicion> mValues;

    public HomeRecyclerViewAdapter(List<Posicion> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(FragmentHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        /*for (Usuario usuario : mValues) {
            Log.d(":::FIREBASE", usuario.getId() + ": " + usuario.getNombre());
        }*/

        return view;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mAddressView.setText("Dirección: " + holder.mItem.getDireccion());
        holder.mLatitudeView.setText("Latitud: " + holder.mItem.getLatLng().latitude);
        holder.mLongitudeView.setText("Longitud: " + holder.mItem.getLatLng().longitude);

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
        public Posicion mItem;
        public final TextView mAddressView;
        public final TextView mLatitudeView;
        public final TextView mLongitudeView;

        public ViewHolder(FragmentHomeBinding binding) {
            super(binding.getRoot());
            mAddressView = binding.address;
            mLatitudeView = binding.latitude;
            mLongitudeView = binding.longitude;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mLongitudeView.getText() + "'";
        }
    }
}
