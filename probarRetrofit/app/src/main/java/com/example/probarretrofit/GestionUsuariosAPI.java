package com.example.probarretrofit;

import com.example.probarretrofit.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GestionUsuariosAPI {


    @GET("/api/usuarios")
    Call<List<Usuario>> getUsuarios();

}
