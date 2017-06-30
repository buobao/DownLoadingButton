package com.king.turman.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by diaoqf on 2017/6/30.
 */

public class HorizontalScrollView extends ViewGroup {

    private int mChildrenCount = 0;
    private int mChildrenIndex = 0;
    private int mChildrenWidth = 0;

    private int mLastXIntercept = 0;//记录上次滑动的坐标（onInterceptTouchEvent）
    private int mLastYIntercept = 0;

    private int mLastX = 0;
    private int mLastY = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public HorizontalScrollView(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mMeasuredWidth = 0;
        int mMeasuredHeight = 0;
        int childCount = getChildCount();

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthMeasureSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (childCount == 0) {
            setMeasuredDimension(0,0);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            View childView = getChildAt(0);
            mMeasuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(mMeasuredWidth, heightMeasureSpaceSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            View childView = getChildAt(0);
            mMeasuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthMeasureSpaceSize, mMeasuredHeight);
        } else {
            View childView = getChildAt(0);
            mMeasuredWidth = childView.getMeasuredWidth();
            mMeasuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(mMeasuredWidth, mMeasuredHeight);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mChildrenCount = getChildCount();
        int left = 0;

        for (int index = 0;index < mChildrenCount;index ++){
            View v = getChildAt(index);
            mChildrenWidth = v.getMeasuredWidth();
            v.layout(left,0,left + v.getMeasuredWidth(),v.getMeasuredHeight());
            left += v.getMeasuredWidth();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //父容器拦截水平滑动事件
                    intercepted = true;
                } else {
                    //竖直方向上不拦截
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int)getX();
        int y = (int)getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);

                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildrenWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildrenIndex = xVelocity > 0 ? mChildrenIndex - 1 : mChildrenIndex + 1;
                } else {
                    mChildrenIndex = (scrollX + mChildrenWidth / 2) / mChildrenWidth;
                }
                mChildrenIndex = Math.max(0, Math.min(mChildrenIndex, mChildrenCount - 1));
                int dx = mChildrenIndex * mChildrenWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }


}

























































