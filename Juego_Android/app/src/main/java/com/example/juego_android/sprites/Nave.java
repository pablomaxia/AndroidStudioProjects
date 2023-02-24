package com.example.juego_android.sprites;

import android.graphics.Canvas;

import com.example.juego_android.game.EsquivarObstaculos;
import com.example.juego_android.game.GameView;
import com.example.juego_android.utilidades.UtilidadesSprites;

public class Nave extends Sprite {
    private final float rozamiento = 0.9f;
    private EsquivarObstaculos game;
    private float x, y, radio;
    private int color;

    public Nave(GameView game, int x, int y, int radio, int color) {
        super(game);
        this.game = (EsquivarObstaculos) game;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        EsquivarObstaculos.estadisticas.setxNave(x);
        velInicialX = 10f;
        velInicialY = 0f;
        velActualX = velInicialX;
        velActualY = velInicialY;

    }

    @Override
    public void onFireColisionBorder() {
        if (this.x - radio - game.getMargenPantalla() < 0)
            onColisionBorderEvent(LEFT);
        if (this.x + radio + game.getMargenPantalla() > game.getmScreenX())
            onColisionBorderEvent(RIGHT);
        if (this.x - radio - game.getMargenPantalla() < 0)
            onColisionBorderEvent(TOP);
        if (this.x + radio + game.getMargenPantalla() > game.getmScreenY())
            onColisionBorderEvent(BOTTOM);
    }

    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Obstaculo) {
            Obstaculo obs = (Obstaculo) s;
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
                x -= game.getMargenPantalla();
                break;
            case LEFT:
                velActualX *= -1;
                x += game.getMargenPantalla();
                break;
            default:

                break;
        }
    }

    @Override
    public boolean colision(Sprite s) {
        if (s instanceof Obstaculo) {
            Obstaculo obs = (Obstaculo) s;
            return UtilidadesSprites.colisionCirculos(x, y, radio, obs.getX(), obs.getY(), obs.getRadio());
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
        velActualY = 0;
    }

    @Override
    public void update() {
        //Se actualiza la posicion de la nave según la anterior
        velActualX *= rozamiento;
        x += velActualX;
        EsquivarObstaculos.estadisticas.setxNave(x);
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
