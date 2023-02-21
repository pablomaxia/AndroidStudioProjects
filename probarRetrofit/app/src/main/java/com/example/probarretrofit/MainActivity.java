package com.example.probarretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.probarretrofit.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    GestionUsuariosAPI  api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("/retrofit","probando retrofit");

        Retrofit retrofit=RetrofitCliente.getClient("http://127.0.0.1:8080/api/");
        api = retrofit.create(GestionUsuariosAPI.class);

        Call<List<Usuario>> call=api.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuarios=response.body();
                for(Usuario usuario:usuarios){
                    Log.d("/retrofit","nombres");
                    Log.d("/retrofit",usuario.getNombre());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.d("/retrofit","error: "+t.toString());
            }
        });


    }
}