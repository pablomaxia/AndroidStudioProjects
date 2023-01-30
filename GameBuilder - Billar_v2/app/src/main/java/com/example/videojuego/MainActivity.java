package com.example.videojuego;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

/*

Implementación del esquema general de un videojuego.
También lo convertiremos en un motor de videojuegos para futuros proyectos
En Android los juegos se programan directamente "a pelo"

*/

public class MainActivity extends Activity {

   GameView juego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtenemos la pantalla
        Display display= getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);

        juego=new Billar(this,size.x,size.y);

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