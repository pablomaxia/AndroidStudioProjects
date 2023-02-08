package com.example.juego_android;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.juego_android.game.GameView;
import com.example.juego_android.game.Juego;

public class MainActivity extends Activity {

    GameView juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtenemos la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        juego = new Juego(this, size.x, size.y);

        setContentView(juego);
    }


    @Override
    protected void onResume() {
        super.onResume();
        juego.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        juego.pause();
    }
}