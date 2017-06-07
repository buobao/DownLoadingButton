package com.king.turman.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by diaoqf on 2017/6/7.
 */

public class DownloadButton extends View {

    private Paint mPaint;

    private int baseArcColor;
    private float baseArcWidth;

    private int loadingArcColor;
    private float loadingArcWidth;


    public DownloadButton(Context context) {
        super(context);
        init(context,null);
    }

    public DownloadButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DownloadButton);
        baseArcColor = a.getColor(R.styleable.DownloadButton_base_arc_color,getResources().getColor(R.color.base_arc_color));
        baseArcWidth = a.getDimension(R.styleable.DownloadButton_base_arc_width,2);

        loadingArcColor = a.getColor(R.styleable.DownloadButton_loading_arc_color,getResources().getColor(R.color.load_arc_color));
        loadingArcWidth = a.getDimension(R.styleable.DownloadButton_loading_arc_width,2);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(baseArcColor);
        mPaint.setStrokeWidth(baseArcWidth);
        canvas.drawArc(new RectF(baseArcWidth/2,baseArcWidth/2,getMeasuredWidth()-baseArcWidth/2,getMeasuredHeight()-baseArcWidth/2),0,360,false,mPaint);

        mPaint.setColor(loadingArcColor);
        mPaint.setStrokeWidth(loadingArcWidth);
        canvas.drawArc(new RectF(loadingArcWidth/2,loadingArcWidth/2,getMeasuredWidth()-loadingArcWidth/2,getMeasuredHeight()-loadingArcWidth/2),-90,90,false,mPaint);

    }
}
