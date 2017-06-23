package com.king.turman.downloadingbutton;

import android.animation.TypeEvaluator;

/**
 * Created by diaoqf on 2017/6/23.
 */

public class MyFloatEvaluate implements TypeEvaluator<Float> {

    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float startFloat = startValue;
        return startFloat + fraction * (endValue - startFloat);
    }
}
