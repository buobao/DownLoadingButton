package com.king.turman.library.block;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by diaoqf on 2017/6/23.
 */

public class BlockView extends View {

    private Paint mPaint;
    private int fillColor;
    private int size;

    private Point location;

    public BlockView(Context context, int fillColor, Point location, int size) {
        super(context);
        this.fillColor = fillColor;
        this.location = location;
        this.size = size;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setLayoutParams(new ViewGroup.LayoutParams(size,size));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0.5f);

        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),getWidth()*0.1f,getHeight()*0.1f,mPaint);

        mPaint.setColor(fillColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0.5f,0.5f,getWidth()-0.5f,getHeight()-0.5f),getWidth()*0.1f,getHeight()*0.1f,mPaint);
    }


    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getFillColor() {
        return fillColor;
    }
}
