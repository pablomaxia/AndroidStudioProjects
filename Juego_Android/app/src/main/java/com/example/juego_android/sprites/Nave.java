package com.example.juego_android.sprites;

import android.graphics.Canvas;

import com.example.juego_android.game.GameView;
import com.example.juego_android.game.Juego;
import com.example.juego_android.interfaces.OnColisionListener;
import com.example.juego_android.sprites.base.Sprite;

public class Nave extends Sprite {
    private Juego game;
    private float x, y, radio;
    private int color;
    private final float rozamiento = 0.98f;

    public Nave(GameView game, int x, int y, int radio, int color) {
        super(game);
        this.game = (Juego) game;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        velInicialX = 1f;
        velInicialY = 0f;
        velActualX = velInicialX;
        velActualY = velInicialY;

    }

    @Override
    public void onFireColisionBorder() {
        if (this.x - radio < 0)
            onColisionBorderEvent(OnColisionListener.LEFT);
        if (this.x + radio > game.getmScreenX())
            onColisionBorderEvent(OnColisionListener.RIGHT);
        if (this.x - radio < 0)
            onColisionBorderEvent(OnColisionListener.TOP);
        if (this.x + radio > game.getmScreenY())
            onColisionBorderEvent(OnColisionListener.BOTTOM);
    }

    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Nave) {
            Nave nave = (Nave) s;
        }
    }

    @Override
    public void onColisionBorderEvent(int border) {

        switch (border) {
            case OnColisionListener.TOP:
                velActualY *= -1;
                break;
            case OnColisionListener.BOTTOM:
                velActualY *= -1;
                break;
            case OnColisionListener.RIGHT:
                velActualX *= -1;
                break;
            case OnColisionListener.LEFT:
                velActualX *= -1;
                break;
            default:

                break;
        }
    }

    @Override
    public boolean colision(Sprite s) {
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
        /*velActualY *= rozamiento;
        //   if (velActualY==0)velActualY=0;
        y += velActualY;*/
        //Log.d("billar",this.getVelActualX()+"----"+this.getVelActualX());
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
