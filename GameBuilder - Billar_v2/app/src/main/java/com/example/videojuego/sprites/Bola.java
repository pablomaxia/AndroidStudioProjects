package com.example.videojuego.sprites;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.videojuego.Billar;
import com.example.videojuego.GameView;
import com.example.videojuego.OnColisionListener;
import com.example.videojuego.Utilidades;

public class Bola extends Sprite implements OnColisionListener {

    private Billar game;
    public float centroX, centroY, radio;
    public boolean activa = true;
    public float rozamiento = (float) 0.98;
    private boolean agujero;

    public Bola(GameView game, int x, int y, int r, int color) {
        super(game);
        this.game = (Billar) game;
        centroX = x;
        centroY = y;
        radio = r;
        this.color = color;
        velInicialX = (float) (Math.random() * 20);
        velInicialY = (float) (Math.random() * 20);
        velActualX = velInicialX;
        velActualY = velInicialY;

        this.agujero = false;
    }

    public Bola(GameView game, int x, int y, int r, int color, boolean agujero) {
        super(game);
        this.game = (Billar) game;
        centroX = x;
        centroY = y;
        radio = r;
        this.color = color;
        velInicialX = (float) (Math.random() * 20);
        velInicialY = (float) (Math.random() * 20);
        velActualX = velInicialX;
        velActualY = velInicialY;

        this.agujero = agujero;
    }

    @Override
    public void setup() {
        this.velActualX = 0;
        this.velActualY = 0;
       /* this.velActualX=velInicialX* game.factor_mov;
        this.velActualY=velInicialY* game.factor_mov;
        */

    }

    @Override
    public boolean colision(Sprite s) {
        Bola b = (Bola) s;
        boolean col = Utilidades.colisionCirculos(centroX, centroY, radio, b.centroX, b.centroY, b.radio);
        if (!col) activa = true;
        return col;
    }

    @Override
    public void update() {
        //Se actualiza la posicion de la bola según la anterior
        velActualX *= rozamiento;
        //  if (velActualX==0);velActualX=0;
        centroX += velActualX;
        velActualY *= rozamiento;
        //   if (velActualY==0)velActualY=0;
        centroY += velActualY;
        //Log.d("billar",this.getVelActualX()+"----"+this.getVelActualX());
        //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
        //Se actualizan otras variables internas

    }


    @Override
    public void onFireColisionBorder() {
        if (this.centroX - radio < 0)
            onColisionBorderEvent(OnColisionListener.LEFT);
        if (this.centroX + radio > game.getmScreenX())
            onColisionBorderEvent(OnColisionListener.RIGHT);
        if (this.centroY - radio < 0)
            onColisionBorderEvent(OnColisionListener.TOP);
        if (this.centroY + radio > game.getmScreenY())
            onColisionBorderEvent(OnColisionListener.BOTTOM);
    }

    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Bola) {
            Bola b = (Bola) s;
            if (activa) {
                float dy = (float) (b.centroY - centroY);
                float dx = (float) (b.centroX - centroX);
                float ang = (float) Math.atan2(dy, dx);
                double cosa = Math.cos(ang);
                double sina = Math.sin(ang);
                float vx2 = (float) (cosa * b.velActualX + sina * b.velActualY);
                float vy1 = (float) (cosa * b.velActualY - sina * b.velActualX);
                float vx1 = (float) (cosa * velActualX + sina * velActualY);
                float vy2 = (float) (cosa * velActualY - sina * velActualX);
                b.velActualX = (float) (cosa * vx1 - sina * vy1);
                b.velActualY = (float) (cosa * vy1 + sina * vx1);
                velActualX = (float) (cosa * vx2 - sina * vy2);
                velActualY = (float) (cosa * vy2 + sina * vx2);
            }
            if (b.isAgujero()) {
                b.velActualX = 0f;
                b.velActualY = 0f;
                if (getColor() == Color.WHITE) {
                    centroX = 700;
                    centroY = 500;
                } else {
                    setVisible(false);
                }
            }
        }
    }


    @Override
    public void onColisionBorderEvent(int border) {

        switch (border) {
            case OnColisionListener.TOP:
                velActualY = -velActualY;
                break;
            case OnColisionListener.BOTTOM:
                velActualY = -velActualY;
                break;
            case OnColisionListener.RIGHT:
                velActualX = -velActualX;
                break;
            case OnColisionListener.LEFT:
                velActualX = -velActualX;
                break;
            default:

                break;
        }
    }


    @Override
    public void pinta(Canvas canvas) {
        paint.setColor(color);
        paint.setStrokeWidth(8);
        // paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centroX, centroY, radio, paint);
    }

    public void recoloca() {
        this.centroX *= -1;
        this.centroY *= -1;
    }

    public boolean isAgujero() {
        return agujero;
    }

    public void setAgujero(boolean agujero) {
        this.agujero = agujero;
    }
}
