package com.example.mvvm_firebase.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_firebase.modelo.dao.DaoUsuario;
import com.example.mvvm_firebase.modelo.entidades.Usuario;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Usuario>> mUsuarios;
    private DaoUsuario daoUsuario;
    private final String COLECCION = "usuarios";
    private final String DOCUMENTO = "usuario_";

    public HomeViewModel() {
        daoUsuario = new DaoUsuario();
        crearBD();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mUsuarios = new MutableLiveData<List<Usuario>>();
       // mUsuarios.setValue(daoUsuario.ver(DOCUMENTO, COLECCION));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Usuario>> getUsuarios() {
        return mUsuarios;
    }

    private void crearBD() {
        daoUsuario.crear(new Usuario(1, "Test"), DOCUMENTO + 1, COLECCION);
    }
}
