package com.example.juego_android.sprites;

import android.graphics.Canvas;

import com.example.juego_android.game.EsquivarObstaculos;
import com.example.juego_android.game.GameView;
import com.example.juego_android.utilidades.UtilidadesSprites;

public class Obstaculo extends Sprite {
    private final float rozamiento = 0.98f;
    public float xInicial = 0;
    public float yInicial = 0;
    private EsquivarObstaculos game;
    private float x, y, radio;
    private int color;

    public Obstaculo(GameView game, int x, int y, int radio, int color) {
        super(game);
        this.game = (EsquivarObstaculos) game;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        velInicialX = 0f;
        velInicialY = UtilidadesSprites.GRAVEDAD;
        this.xInicial = x;
        this.yInicial = y;
        velActualX = velInicialX;
        velActualY = velInicialY;

    }

    @Override
    public void onFireColisionBorder() {
        if (this.x - radio < 0)
            onColisionBorderEvent(LEFT);
        if (this.x + radio > game.getmScreenX())
            onColisionBorderEvent(RIGHT);
        if (this.x - radio < 0)
            onColisionBorderEvent(TOP);
        if (this.x + radio > game.getmScreenY())
            onColisionBorderEvent(BOTTOM);
    }

    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Nave) {
            Nave nave = (Nave) s;
            EsquivarObstaculos.estadisticas.reducirVidas();
            y = yInicial;
            radio += 5;
        }
    }

    @Override
    public void onColisionBorderEvent(int border) {

        switch (border) {
            case TOP:
                velActualY *= -1;
                break;
            case BOTTOM:
                velActualY *= -1;
                break;
            case RIGHT:
                velActualX *= -1;
                break;
            case LEFT:
                velActualX *= -1;
                break;
            default:

                break;
        }
    }

    @Override
    public boolean colision(Sprite s) {
        if (s instanceof Nave) {
            Nave n = (Nave) s;
            return UtilidadesSprites.colisionCirculos(x, y, radio, n.getX(), n.getY(), n.getRadio());
        }
        return false;
    }

    @Override
    public void pinta(Canvas canvas) {
        paint.setColor(color);
        paint.setStrokeWidth(8);
        // paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radio, paint);
    }

    @Override
    public void setup() {
        velActualX = 0;
        velActualY = UtilidadesSprites.GRAVEDAD;

    }

    @Override
    public void update() {
        //x += (int) (Math.random() + 1) == 0 ? (int) (Math.random() + .1) : (int) (Math.random() - .1);
        velActualY = (int) (Math.random() * 10);
        y += velActualY;
        if (game.getmScreenY() <= y) {
            y = yInicial;
            int puntuacion = EsquivarObstaculos.estadisticas.getPuntuacion() + (int) (radio) * 10;
            EsquivarObstaculos.estadisticas.setPuntuacion(puntuacion);
            if (puntuacion > EsquivarObstaculos.estadisticas.getPuntuacionMaxima())
                EsquivarObstaculos.estadisticas.setPuntuacionMaxima(puntuacion);
            radio += 1.5;
        }
        //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
    }

    /*SET Y GET*/

    @Override
    public EsquivarObstaculos getGame() {
        return game;
    }

    public void setGame(EsquivarObstaculos game) {
        this.game = game;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
