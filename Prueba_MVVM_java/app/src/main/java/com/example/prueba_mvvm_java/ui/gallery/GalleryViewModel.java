package com.example.prueba_mvvm_java.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private MutableLiveData<ArrayList<String>> mNombres;

    public GalleryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

        mNombres = new MutableLiveData<>();
        mNombres.setValue(new ArrayList<String>());


    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<String>> getNombres() {
        return mNombres;
    }

    public void addNombre(String nombre) {

        ArrayList<String> nombres = mNombres.getValue();

        nombres.add(nombre);

    }
}