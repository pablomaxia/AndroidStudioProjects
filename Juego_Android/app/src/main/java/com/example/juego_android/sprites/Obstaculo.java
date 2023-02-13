package com.example.juego_android.sprites;

import android.graphics.Canvas;

import com.example.juego_android.game.GameView;
import com.example.juego_android.game.Juego;
import com.example.juego_android.interfaces.OnColisionListener;
import com.example.juego_android.sprites.base.Sprite;
import com.example.juego_android.utilidades.Constantes;
import com.example.juego_android.utilidades.Utilidades;

public class Obstaculo extends Sprite {
    private Juego game;
    private float x, y, radio;
    private int color;
    private final float rozamiento = 0.98f;

    public Obstaculo(GameView game, int x, int y, int radio, int color) {
        super(game);
        this.game = (Juego) game;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        velInicialX = 0f;
        velInicialY = Constantes.GRAVEDAD;
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
        if (s instanceof Obstaculo) {
            Obstaculo obs = (Obstaculo) s;
            /*float dy = (float) (obs.y - y);
            float dx = (float) (obs.x - x);
            float ang = (float) Math.atan2(dy, dx);
            double cosa = Math.cos(ang);
            double sina = Math.sin(ang);

            float vy1 = (float) (cosa * obs.velActualY - sina * obs.velActualX);
            float vx1 = (float) (cosa * velActualX + sina * velActualY);

            float vx2 = (float) (cosa * obs.velActualX + sina * obs.velActualY);
            float vy2 = (float) (cosa * velActualY - sina * velActualX);

            obs.velActualX = (float) (cosa * vx1 - sina * vy1);
            obs.velActualY = (float) (cosa * vy1 + sina * vx1);*/

        }
        if (s instanceof Nave) {
            Nave nave = (Nave) s;
            setVisible(false);
            Juego.vidas--;

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
        if (s instanceof Nave) {
            Nave n = (Nave) s;
            return Utilidades.colisionCirculos(x, y, radio, n.getX(), n.getY(), n.getRadio());
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
        velActualY = Constantes.GRAVEDAD;

    }

    @Override
    public void update() {
        //velActualY *= rozamiento;
        velActualY = (int) (Math.random() + 20);
        y += 1;
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
