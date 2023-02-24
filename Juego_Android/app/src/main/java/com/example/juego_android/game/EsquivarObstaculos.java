package com.example.juego_android.game;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.juego_android.bd.FireBaseBD;
import com.example.juego_android.interfaces.OnTouchEventListener;
import com.example.juego_android.modelo.Estadisticas;
import com.example.juego_android.modelo.dao.DaoEstadisticas;
import com.example.juego_android.sprites.Nave;
import com.example.juego_android.sprites.Obstaculo;
import com.example.juego_android.sprites.Sprite;
import com.example.juego_android.utilidades.UtilidadesJuego;
import com.example.juego_android.utilidades.UtilidadesSprites;

public class EsquivarObstaculos extends GameView implements OnTouchEventListener {
    //variables del juego
    public static FireBaseBD fireBaseBD;
    public static DaoEstadisticas daoEstadisticas = new DaoEstadisticas();
    public static Estadisticas estadisticas = new Estadisticas();
    private int margenPantalla = 0;

    // Actores del juego
    public static Nave nave1;
    private final Context context;
    private final int x;
    private final int y;
    private final Obstaculo[] OBSTACULOS = new Obstaculo[]{
            new Obstaculo(this, 65, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.WHITE),
            new Obstaculo(this, 245, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.RED),
            new Obstaculo(this, 425, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.BLUE),
            new Obstaculo(this, 605, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.WHITE),
            new Obstaculo(this, 785, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.RED),
            new Obstaculo(this, 965, UtilidadesSprites.POSICION_Y_INICIAL_OBSTACULOS, UtilidadesSprites.RADIO_OBSTACULOS, Color.BLUE)
    };
    float lineX1, lineY1, lineX2, lineY2;

    public EsquivarObstaculos(Context context, int x, int y) {
        super(context, x, y);
        fireBaseBD = FireBaseBD.getInstance();
        cargarEstadisticas();
        this.context = context;
        this.x = x;
        this.y = y;
        margenPantalla = (int) (UtilidadesJuego.PORCENTAJE_MARGEN_PANTALLA * getmScreenX());
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {
        nave1 = new Nave(this, UtilidadesSprites.POSICION_X_INICIAL_NAVE, UtilidadesSprites.POSICION_Y_INICIAL_NAVE, 50, Color.WHITE);
        actores.add(nave1);
        nave1.setup();
        ponerObstaculos();
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

        //dibujamos puntuacion y vidas
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText("Puntuación Máxima: " + estadisticas.getPuntuacionMaxima() + " Puntos: " + estadisticas.getPuntuacion() + "  Vidas: " + estadisticas.getVidas(), 10, 50, paint);
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
        if (estadisticas.getVidas() == 0) {
            pausado = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actores.clear();
            reiniciarEstadisticas();
            guardarVariables();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setupGame();
            pausado = false;
        }
    }

    //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1 = event.getX();
        lineX1 = nave1.getX();
        lineX2 = nave1.getX();
    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        lineX2 = event.getX();
        nave1.setX(lineX2);
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        lineX2 = event.getX();
        nave1.setX(lineX2);
    }

    public void reiniciarEstadisticas() {
        estadisticas.setVidas(UtilidadesJuego.TOTAL_VIDAS);
        estadisticas.setPuntuacion(UtilidadesJuego.PUNTUACION_INICIAL);
        estadisticas.setxNave(UtilidadesSprites.POSICION_X_INICIAL_NAVE);
    }

    public static void guardarVariables() {
        daoEstadisticas.guardarEstadisticas(EsquivarObstaculos.estadisticas, UtilidadesJuego.NOMBRE_COLECCION_ESTADISTICAS, UtilidadesJuego.NOMBRE_DOCUMENTO_ESTADISTICAS);
    }

    public static void cargarEstadisticas() {
        daoEstadisticas.cargarEstadisticas(UtilidadesJuego.NOMBRE_COLECCION_ESTADISTICAS, UtilidadesJuego.NOMBRE_DOCUMENTO_ESTADISTICAS);
    }

    private void ponerObstaculos() {
        for (Obstaculo obstaculo : OBSTACULOS) {
            actores.add(obstaculo);
            obstaculo.setup();
        }
    }

    /*SET Y GET*/
    public int getMargenPantalla() {
        return margenPantalla;
    }

    public void setMargenPantalla(int margenPantalla) {
        this.margenPantalla = margenPantalla;
    }

}
