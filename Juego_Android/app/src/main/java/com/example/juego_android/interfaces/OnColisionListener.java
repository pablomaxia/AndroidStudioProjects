package com.example.juego_android.interfaces;

import com.example.juego_android.sprites.Sprite;

public interface OnColisionListener {

    int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3;

    void onColisionEvent(Sprite s);

    void onColisionBorderEvent(int border);
}
