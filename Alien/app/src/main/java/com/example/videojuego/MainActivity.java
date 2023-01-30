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

    Pong pong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtenemos la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //gameView=new GameView(this,area.right-area.left,area.bottom-area.top);

        pong = new Pong(this, size.x, size.y);

        setContentView(pong);

    }


    @Override
    protected void onResume() {
        super.onResume();
        pong.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pong.pause();
    }
}