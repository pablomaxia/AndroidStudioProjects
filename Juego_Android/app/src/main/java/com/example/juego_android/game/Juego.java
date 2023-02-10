package com.example.juego_android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.juego_android.constantes.Constantes;
import com.example.juego_android.interfaces.OnTouchEventListener;
import com.example.juego_android.sprites.Nave;
import com.example.juego_android.sprites.Obstaculo;
import com.example.juego_android.sprites.base.Sprite;

public class Juego extends GameView implements OnTouchEventListener {
    private final Context context;
    private final int x;
    private final int y;
    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;
    float lineX1, lineY1, lineX2, lineY2;
    boolean estaDentro = false;
    boolean apunta = false;
    // Actores del juego
    private Nave nave1;
    private Obstaculo[] obstaculos;

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

        obstaculos = new Obstaculo[]{
                new Obstaculo(this, 90, 90, 15, Color.WHITE),
                new Obstaculo(this, 180, 180, 18, Color.WHITE),
                new Obstaculo(this, 270, 270, 21, Color.WHITE),
        };

        for (int i = 1; i < 4; i++){
            for (int j = 1; j < obstaculos.length; j++) {
                actores.add(obstaculos[j]);
                obstaculos[j].setup();
            }
        }
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
        canvas.drawText("Puntuacion: " + this.puntuacion + "  Vidas: " + vidas, 10, 50, paint);
        paint.setTextSize(10);

/*        if (estaDentro) {
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(5);
            canvas.drawLine(nave1.getX(), nave1.getY(), lineX2, lineY2, paint);
            if (apunta) {
                paint.setColor(Color.RED);
                canvas.drawLine(nave1.getX(), nave1.getY(), (nave1.getX() - lineX2) * 1000, (nave1.getY() - lineY2) * 1000, paint);
            }
        }
*/

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

}
