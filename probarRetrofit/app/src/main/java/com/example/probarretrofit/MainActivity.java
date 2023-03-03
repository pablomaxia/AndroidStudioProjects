package com.example.probarretrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.probarretrofit.entities.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    GestionUsuariosAPI api;
    final String URL_INSTITUTO = "http://127.0.0.1:8080";
    final String URL = "http://192.168.1.39:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("/retrofit", "probando retrofit");

        Retrofit retrofit = RetrofitCliente.getClient(URL);
        api = retrofit.create(GestionUsuariosAPI.class);

        Call<List<Usuario>> call = api.getUsuarios();

/*        try {
            Response<List<Usuario>> response = call.execute();
            for (Usuario u: response.body()) {
                Log.d("/retrofit", u.getNombre());
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("/retrofit","error: "+e.toString());
        }
*/
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                List<Usuario> usuarios = response.body();
                for (Usuario usuario : usuarios) {
                    Log.d("/retrofit", "nombres");
                    Log.d("/retrofit", usuario.getNombre());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                call.cancel();
                Log.d("/retrofit","error: " + t.toString());
            }
        });


    }
}