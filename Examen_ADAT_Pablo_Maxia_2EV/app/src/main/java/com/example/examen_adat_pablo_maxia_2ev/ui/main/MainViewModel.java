package com.example.examen_adat_pablo_maxia_2ev.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_adat_pablo_maxia_2ev.modelo.dao.DaoRuta;
import com.example.examen_adat_pablo_maxia_2ev.modelo.entidades.Ruta;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DaoRuta daoRuta;
    private final MutableLiveData<List<Ruta>> mRutas;
    private List<Ruta> rutas;

    public MainViewModel() {
        daoRuta = new DaoRuta();
        mRutas = new MutableLiveData<>();
        rutas = daoRuta.getRutas().isEmpty() ? new ArrayList<>() : daoRuta.getRutas();
        mRutas.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Ruta>> getmRutas() {
        return mRutas;
    }

    public void addRuta(Ruta ruta) {
        rutas = new ArrayList<>();
        daoRuta.addRuta(ruta);
        rutas.add(ruta);
        mRutas.setValue(rutas);
        ruta.notifyObservers();

    }

    public void getRutas() {
        for (Ruta ruta : rutas) {
            ruta.registerObserver(MainFragment.observer);
        }
        mRutas.setValue(rutas);
    }

}