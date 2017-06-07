package com.king.turman.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by diaoqf on 2017/6/7.
 */

public class DownloadButton extends View {

    private Paint mPaint;

    private int baseArcColor;
    private float baseArcWidth;

    private int loadingArcColor;
    private float loadingArcWidth;

    private int centerBtnColor;
    private float centerBtnWidth;

    private boolean isLoading;

    private double currentPercent;


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
        isLoading = false;
        currentPercent = 0;
        mPaint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DownloadButton);
        baseArcColor = a.getColor(R.styleable.DownloadButton_base_arc_color,getResources().getColor(R.color.base_arc_color));
        baseArcWidth = a.getDimension(R.styleable.DownloadButton_base_arc_width,2);

        loadingArcColor = a.getColor(R.styleable.DownloadButton_loading_arc_color,getResources().getColor(R.color.load_arc_color));
        loadingArcWidth = a.getDimension(R.styleable.DownloadButton_loading_arc_width,2);

        centerBtnColor = a.getColor(R.styleable.DownloadButton_center_btn_color,getResources().getColor(R.color.base_arc_color));
        centerBtnWidth = a.getDimension(R.styleable.DownloadButton_center_btn_width,-1);
        a.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoading) {
                    isLoading = false;
                } else {
                    isLoading = true;
                }
            }
        });
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
        canvas.drawArc(new RectF(loadingArcWidth/2,loadingArcWidth/2,getMeasuredWidth()-loadingArcWidth/2,getMeasuredHeight()-loadingArcWidth/2),-90, (float) (360 * currentPercent),false,mPaint);

        if (centerBtnWidth < 0) {
            centerBtnWidth = getMeasuredWidth() / 3;
        }

        mPaint.setColor(centerBtnColor);
        mPaint.setStyle(Paint.Style.FILL);
        if (isLoading) {
            canvas.drawRect(new RectF(getMeasuredWidth() / 2 - centerBtnWidth / 2,
                    getMeasuredHeight() / 2 - centerBtnWidth / 2,
                    getMeasuredWidth() / 2 + centerBtnWidth / 2,
                    getMeasuredWidth() / 2 + centerBtnWidth / 2), mPaint);
        } else {
            Path path = new Path();
            path.moveTo((float) (getMeasuredWidth()/2 -  centerBtnWidth*Math.sqrt(3)/8), getMeasuredHeight()/2 - centerBtnWidth/2);
            path.lineTo((float) (getMeasuredWidth()/2 + centerBtnWidth*Math.sqrt(3)*3/8), getMeasuredHeight()/2);
            path.lineTo((float) (getMeasuredWidth()/2 -  centerBtnWidth*Math.sqrt(3)/8), getMeasuredHeight()/2 + centerBtnWidth/2);
            canvas.drawPath(path, mPaint);
        }

    }


    public boolean isLoading() {
        return isLoading;
    }

    public boolean isLoadingFinished() {
        if (currentPercent >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setCurrentPercent(double currentPercent) {
        if (isLoading) {
            this.currentPercent = currentPercent;
            invalidate();
        }

        if (currentPercent >= 1.0) {
            isLoading = false;
        }
    }
}
