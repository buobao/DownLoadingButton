package com.king.turman.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by diaoqf on 2017/6/30.
 */

public class MovableView extends View {

    private float lastX;
    private float lastY;

    public MovableView(Context context) {
        super(context);
    }

    public MovableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - lastX;
                float deltaY = y - lastY;

                setTranslationX(getTranslationX() + deltaX);
                setTranslationY(getTranslationY() + deltaY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        lastX = x;
        lastY = y;

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}




























