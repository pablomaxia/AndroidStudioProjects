package com.example.juego_android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.juego_android.interfaces.OnTouchEventListener;
import com.example.juego_android.sprites.Nave;
import com.example.juego_android.sprites.Obstaculo;
import com.example.juego_android.sprites.base.Sprite;
import com.example.juego_android.utilidades.Constantes;

public class Juego extends GameView implements OnTouchEventListener {
    private final Context context;
    private final int x;
    private final int y;
    //variables del juego
    public static int puntuacion = 0;
    public static int vidas = 5;

    float lineX1, lineY1, lineX2, lineY2;
    boolean estaDentro = false;
    boolean apunta = false;
    boolean activo = false;

    // Actores del juego
    private Nave nave1;
    private final Obstaculo[] OBSTACULOS = new Obstaculo[]{
            new Obstaculo(this, 90, 65, 15, Color.WHITE),
            new Obstaculo(this, 180, 90, 18, Color.WHITE),
            new Obstaculo(this, 270, 115, 15, Color.WHITE),
            new Obstaculo(this, 360, 90, 18, Color.WHITE),
            new Obstaculo(this, 450, 140, 24, Color.WHITE),
            new Obstaculo(this, 540, 65, 15, Color.WHITE),
            new Obstaculo(this, 630, 90, 18, Color.WHITE),
            new Obstaculo(this, 720, 115, 15, Color.WHITE),
            new Obstaculo(this, 810, 90, 18, Color.WHITE),
            new Obstaculo(this, 900, 140, 24, Color.WHITE),
    };

    public Juego(Context context, int x, int y) {
        super(context, x, y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {

        ponerObstaculos();

        nave1 = new Nave(this, Constantes.POSICION_X_INICIAL_NAVE, Constantes.POSICION_Y_INICIAL_NAVE, 50, Color.WHITE);
        actores.add(nave1);
        nave1.setup();

        resetGameVariables();
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
        canvas.drawText("Puntuacion: " + puntuacion + "  Vidas: " + vidas, 10, 50, paint);
        paint.setTextSize(10);

    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    protected void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if (actor.isVisible()) {
                actor.update();
            }
        }
        if (vidas == 0) {
            Looper.prepare();
            Toast.makeText(context, "Has perdido...", Toast.LENGTH_SHORT).show();
            actores.clear();
            setupGame();
        } else {
            ponerObstaculos();
        }
    }

    //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1 = event.getX();
        lineY1 = event.getY();
        estaDentro = true;
        lineX1 = nave1.getX();
        lineY1 = nave1.getY();
        lineX2 = nave1.getX();
        lineY2 = nave1.getY();

        Log.d("billar", "X: " + lineX1 + " Y: " + lineY1);

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar", "X: " + event.getX() + " Y: " + event.getY());
        lineX2 = event.getX();
        lineY2 = event.getY();
        nave1.setVelActualX((lineX1 - lineX2) / 10);
        nave1.setVelActualY((lineY1 - lineY2) / 10);
        estaDentro = false;
        apunta = false;
        Log.d("billar", nave1.getVelActualX() + "----" + nave1.getVelActualY());
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        //Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        apunta = true;
        lineX2 = event.getX();
        lineY2 = event.getY();

    }

    private void resetGameVariables() {
        vidas = 5;
        puntuacion = 1_000;
    }

    private void ponerObstaculos() {
        int ale = 0;
        for (int i = 0; i < OBSTACULOS.length; i++) {
            ale = (int) (Math.random() + 10);
            if (i % 2 == 0 || ale == 0) {
                actores.add(OBSTACULOS[i]);
                OBSTACULOS[i].setup();
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
