package com.king.turman.downloadingbutton;

import android.view.animation.Interpolator;

/**
 * Created by diaoqf on 2017/6/23.
 */

public class AccelerateDecelerateInterpolator implements Interpolator {

    public AccelerateDecelerateInterpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
