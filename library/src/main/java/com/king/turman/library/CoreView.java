package com.king.turman.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by diaoqf on 2017/6/29.
 */

public class CoreView extends View {
    public CoreView(Context context) {
        super(context);
        init();
    }

    public CoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private VelocityTracker velocityTracker;
    private GestureDetector gestureDetector;

    private void init() {
        gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {  //按下
                Log.i("CoreView","onDown");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) { //按住的时候
                Log.i("CoreView","onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {  //单击
                Log.i("CoreView","onSingleTapUp");

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  //滑动
                Log.i("CoreView","onScroll");

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {  //长摁
                Log.i("CoreView","onLongPress");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  //滑动结束时调用
                Log.i("CoreView","onFling");
                return true;
            }
        });
        gestureDetector.setIsLongpressEnabled(true);  //单开or关闭长按手势监听
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.computeCurrentVelocity(1000);
                velocityTracker.addMovement(event);
                Log.i("CoreView","velocity:x:"+velocityTracker.getXVelocity()+",y:"+velocityTracker.getYVelocity());
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                velocityTracker.clear();
                velocityTracker.recycle();
                break;
        }
        boolean consume = gestureDetector.onTouchEvent(event);
        return consume;
    }
}
