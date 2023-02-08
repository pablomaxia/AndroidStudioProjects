package com.example.videojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.videojuego.sprites.Bola;
import com.example.videojuego.sprites.Sprite;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1, bola2, bola3, bola4, bola5, bola6, bola7, agujero1, agujero2, agujero3, agujero4;
    Bola[] bolas;
    //

    float lineX1, lineY1, lineX2, lineY2;
    boolean estaDentro = false;
    boolean apunta = false;


    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;


    public Billar(Context context, int x, int y) {
        super(context, x, y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {

        bola5 = new Bola(this, 300, 200, 50, Color.BLACK);
        actores.add(bola5);
        bola5.setup();

        bola2 = new Bola(this, 300, 400, 50, Color.RED);
        actores.add(bola2);
        bola2.setup();

        bola6 = new Bola(this, 300, 600, 50, Color.GREEN);
        actores.add(bola6);
        bola6.setup();

        bola3 = new Bola(this, 300, 800, 50, Color.BLUE);
        actores.add(bola3);
        bola3.setup();

        bola4 = new Bola(this, 500, 300, 50, Color.YELLOW);
        actores.add(bola4);
        bola4.setup();

        bola7 = new Bola(this, 500, 700, 50, Color.CYAN);
        actores.add(bola7);
        bola7.setup();

        bola1 = new Bola(this, 700, 500, 50, Color.WHITE);
        actores.add(bola1);
        bola1.setup();

        // agujeros
        agujero1 = new Bola(this, 50, 50, 65, Color.BLACK, true);
        actores.add(agujero1);
        agujero1.setup();

        agujero2 = new Bola(this, 2050, 50, 65, Color.BLACK, true);
        actores.add(agujero2);
        agujero2.setup();

        agujero3 = new Bola(this, 50, 1025, 65, Color.BLACK, true);
        actores.add(agujero3);
        agujero3.setup();

        agujero4 = new Bola(this, 2050, 1025, 65, Color.BLACK, true);
        actores.add(agujero4);
        agujero4.setup();


    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if (actor.isVisible())
                actor.update();
        }
        if (actores.size() == 5) {
            actores.clear();
            setupGame();
        }
    }

    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        canvas.drawColor(Color.argb(255, 20, 128, 60)); // FONDO VERDE

        // borde
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setColor(Color.rgb(85, 44, 22)); // MARRÓN

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);

        synchronized (actores) {
            for (Sprite actor : actores) {
                actor.pinta(canvas);
            }
        }
        //dibujamos puntuacion y vidas
        paint.setTextSize(30);
        canvas.drawText("Factor_mov: " + this.factor_mov + "  Vidas: " + actores.size(), 10, 50, paint);
        paint.setTextSize(10);
        if (estaDentro) {
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(5);
            canvas.drawLine(bola1.centroX, bola1.centroY, lineX2, lineY2, paint);
            if (apunta) {
                paint.setColor(Color.RED);
                canvas.drawLine(bola1.centroX, bola1.centroY, (bola1.centroX - lineX2) * 1000, (bola1.centroY - lineY2) * 1000, paint);

            }

        }

    }

    //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1 = event.getX();
        lineY1 = event.getY();
        if (Utilidades.distancia(lineX1, lineY1, bola1.centroX, bola1.centroY) < bola1.radio) {
            estaDentro = true;
            lineX1 = bola1.centroX;
            lineY1 = bola1.centroY;
            lineX2 = bola1.centroX;
            lineY2 = bola1.centroY;

        }
        Log.d("billar", "X: " + lineX1 + " Y: " + lineY1);

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar", "X: " + event.getX() + " Y: " + event.getY());
        if (estaDentro) {
            lineX2 = event.getX();
            lineY2 = event.getY();
            bola1.setVelActualX((lineX1 - lineX2) / 10);
            bola1.setVelActualY((lineY1 - lineY2) / 10);
            estaDentro = false;
            apunta = false;
        }
        Log.d("billar", bola1.getVelActualX() + "----" + bola1.getVelActualY());
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        //Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        apunta = true;
        lineX2 = event.getX();
        lineY2 = event.getY();

    }


}
