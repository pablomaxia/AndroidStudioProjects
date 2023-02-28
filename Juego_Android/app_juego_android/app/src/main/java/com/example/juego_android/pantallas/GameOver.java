package com.example.juego_android.pantallas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.juego_android.MainActivity;
import com.example.juego_android.R;
import com.example.juego_android.game.EsquivarObstaculos;

public class GameOver extends AppCompatActivity {

    TextView tvPoints, tvMaxPoints;
    EsquivarObstaculos juego = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getExtras().getInt("puntos");
        //juego = (EsquivarObstaculos) getIntent().getExtras().getSerializable("juego");
        juego = (EsquivarObstaculos) MainActivity.juego;
        tvPoints = findViewById(R.id.tvPoints);
        tvMaxPoints = findViewById(R.id.tvMaxPoints);
        tvPoints.setText("" + points);
        tvMaxPoints.setText("" + EsquivarObstaculos.estadisticas.getPuntuacionMaxima());
        if (points > EsquivarObstaculos.estadisticas.getPuntuacionMaxima()){
            EsquivarObstaculos.estadisticas.setPuntuacionMaxima(points);
        }
    }

    public void restart(View view) {
        reiniciarVariables();
        Intent intent = new Intent(getApplicationContext(), StartUp.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        reiniciarVariables();
        finish();
    }

    private void reiniciarVariables() {
        juego.reiniciarEstadisticas();
        EsquivarObstaculos.guardarVariables();
    }
}