package com.example.juego_android.interfaces;

import android.view.MotionEvent;

public interface OnTouchEventListener {
    void ejecutaActionDown(MotionEvent event);
    void ejecutaActionUp(MotionEvent event);
    void ejecutaMove(MotionEvent event);
}
