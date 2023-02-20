package com.example.juego_android.sprites;

import android.graphics.Canvas;

import com.example.juego_android.game.GameView;
import com.example.juego_android.game.Juego;
import com.example.juego_android.utilidades.UtilidadesSprites;

public class Nave extends Sprite {
    private Juego game;
    private float x, y, radio;
    private int color;
    private final float rozamiento = 0.9f;

    public Nave(GameView game, int x, int y, int radio, int color) {
        super(game);
        this.game = (Juego) game;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        velInicialX = 10f;
        velInicialY = 0f;
        velActualX = velInicialX;
        velActualY = velInicialY;

    }

    @Override
    public void onFireColisionBorder() {
        if (this.x - radio - 15 < 0)
            onColisionBorderEvent(LEFT);
        if (this.x + radio + 15 > game.getmScreenX())
            onColisionBorderEvent(RIGHT);
        if (this.x - radio - 15 < 0)
            onColisionBorderEvent(TOP);
        if (this.x + radio + 15 > game.getmScreenY())
            onColisionBorderEvent(BOTTOM);
    }

    @Override
    public void onColisionEvent(Sprite s) {
    }

    @Override
    public void onColisionBorderEvent(int border) {

        switch (border) {
            case TOP:
                velActualY *= -1;
                y += 15;
                break;
            case BOTTOM:
                velActualY *= -1;
                y -= 15;
                break;
            case RIGHT:
                velActualX *= -1;
                x -= 15;
                break;
            case LEFT:
                velActualX *= -1;
                x += 15;
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
        velActualY = 0;
    }

    @Override
    public void update() {
        //Se actualiza la posicion de la nave según la anterior
        velActualX *= rozamiento;
        x += velActualX;
        //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
    }

    /*SET Y GET*/

    @Override
    public Juego getGame() {
        return game;
    }

    public void setGame(Juego game) {
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
