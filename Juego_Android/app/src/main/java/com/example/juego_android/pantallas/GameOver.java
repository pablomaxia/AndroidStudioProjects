package com.example.juego_android.pantallas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.juego_android.R;
import com.example.juego_android.game.EsquivarObstaculos;

public class GameOver extends AppCompatActivity {

    TextView tvPoints;
    EsquivarObstaculos juego;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getExtras().getInt("puntos");
        juego = (EsquivarObstaculos) getIntent().getExtras().getCharSequence("juego");
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("" + points);
    }

    public void restart(View view) {
        Intent intent = new Intent(this, StartUp.class);
        startActivity(intent);
        finish();
        juego.resetGameVariables();
        juego.setupGame();

    }

    public void exit(View view) {
        finish();
    }
}