package com.example.juego_android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.juego_android.constantes.Constantes;
import com.example.juego_android.interfaces.OnTouchEventListener;
import com.example.juego_android.sprites.Nave;
import com.example.juego_android.sprites.base.Sprite;

public class Juego extends GameView implements OnTouchEventListener {
    private final Context context;
    private final int x;
    private final int y;

    // Actores del juego
    private Nave nave1;
    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;

    public Juego(Context context, int x, int y) {
        super(context, x, y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {
        nave1 = new Nave(this, Constantes.POSICION_X_INICIAL_NAVE, Constantes.POSICION_Y_INICIAL_NAVE, 50, Color.WHITE);
        actores.add(nave1);
        nave1.setup();
    }

    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        canvas.drawColor(Color.argb(255, 20, 20, 20));


        synchronized (actores) {
            for (Sprite actor : actores) {
                actor.pinta(canvas);
            }
        }

        paint.setColor(Color.WHITE);
        //dibujamos puntuacion y vidas
        paint.setTextSize(40);
        canvas.drawText("Puntuacion: " + this.puntuacion + "  Vidas: " + actores.size(), 10, 50, paint);
        paint.setTextSize(10);

    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    protected void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if (actor.isVisible())
                actor.update();
        }
    }

    //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {

    }

    @Override
    public void ejecutaMove(MotionEvent event) {

    }
}
