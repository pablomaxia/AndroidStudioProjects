package com.example.examen_adat_pablo_maxia_2ev.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen_adat_pablo_maxia_2ev.databinding.FragmentMainBinding;
import com.example.examen_adat_pablo_maxia_2ev.modelo.entidades.Ruta;
import com.example.examen_adat_pablo_maxia_2ev.modelo.interfaces.Observer;
import com.example.examen_adat_pablo_maxia_2ev.modelo.utilidades.Utilidades;

import java.util.List;

public class MainFragment extends Fragment implements Observer {

    private MainViewModel mViewModel;
    private FragmentMainBinding binding;
    public static Observer observer;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        observer = this;
        View root = binding.getRoot();
        Button botonCrear = binding.buttonSubmit;
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ruta ruta;
                double longInicial = Double.parseDouble(binding.longInicialValue.getText().toString());
                double latInicial = Double.parseDouble(binding.latInicialValue.getText().toString());
                double rumbo = Double.parseDouble(binding.rumboValue.getText().toString());
                double distancia = Double.parseDouble(binding.distanciaValue.getText().toString());
                ruta = new Ruta(longInicial, latInicial, rumbo, distancia);
                ruta.registerObserver(observer);
                mViewModel.addRuta(ruta);
            }
        });
        // return inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void update(double longInicial, double latInicial, double rumbo, double distancia) {
        Log.d(Utilidades.ETIQUETA, "Longitud inicial: " + longInicial +
                " Latitud inicial: " + latInicial +
                " Rumbo: " + rumbo +
                " Distancia: " + distancia);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}