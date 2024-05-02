package com.xk.base.ui.excel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

import java.util.List;

public class ExcelView extends ViewGroup {
    private HeadView headView;

    private Scroller scroller;

    private float offsetX;
    private float offsetY;
    private int contentWidth;

    private int screenWidth=0;

    private List<Data> data;


    public ExcelView(Context context) {
        super(context);
    }

    public ExcelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExcelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ExcelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setData(List<Data> list){
        this.data = list;
        for(int i=0;i<list.size();i++){
            DataView dataView =new DataView(getContext());
            dataView.setData(list.get(i));
            addView(dataView);
        }
        resetLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int totalHeight = 0;
        int totalWidth = 0;
        // 计算所有子视图（数据项）的总高度和宽度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                // 测量每个子视图的大小，这里假设每个子视图的宽度与ViewGroup相同
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                totalHeight += child.getMeasuredHeight();
                totalWidth = Math.max(totalWidth, child.getMeasuredWidth());
            }
        }
        // 加上额外的空间，如padding等
        totalHeight += getPaddingTop() + getPaddingBottom();
        totalWidth += getPaddingLeft() + getPaddingRight();
        // 根据测量模式和视图的需求设置宽度和高度
        int measuredWidth;
        int measuredHeight;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                Logger.d("EXACTLY");
                measuredWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                Logger.d("AT_MOST");
            case MeasureSpec.UNSPECIFIED:
                Logger.d("UNSPECIFIED");
                measuredWidth = totalWidth;
                break;
            default:
                measuredWidth = 0;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                measuredHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                measuredHeight = totalHeight;
                break;
            default:
                measuredHeight = 0;
                break;
        }
        contentWidth = measuredWidth-screenWidth;
        Log.d("tag","measuredWidth=="+measuredWidth);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
    View getVirtualChildAt(int index) {
        return getChildAt(index);
    }
    void layoutVertical(int left, int top, int right, int bottom) {
        final int paddingLeft = 0;
        int childTop=0;
        int childLeft;
        // Where right end of child should go
        final int width = right - left;
        int childRight = width - 0;
        // Space available for child
        int childSpace = width - paddingLeft - 0;

        final int count = data.size()+1;


        for (int i = 0; i < count; i++) {
            final View child = getVirtualChildAt(i);
            if (child == null) {
                childTop += 0;
            } else if (child.getVisibility() != GONE) {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();
                childLeft = paddingLeft + (childSpace - childWidth) / 2;
                setChildFrame(child, childLeft, childTop ,
                        childWidth, childHeight);
                Log.d("tag","childtop=="+childTop);
                childTop += childHeight ;
            }
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }
    private void init() {
        headView = new HeadView(getContext());
        addView(headView);
        scroller = new Scroller(getContext());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutVertical(l,t,r,b);
    }
    private int scrollOffset = 0;
    private float downX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 记录初始位置和偏移量
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 根据移动的距离更新偏移量
                float moveX = event.getX();
                int deltaX = (int) (downX - moveX);
                // 计算新的偏移量，并确保它在有效范围内
                if(scrollOffset+deltaX>=contentWidth){
                deltaX = contentWidth-scrollOffset;
                scrollOffset = contentWidth;
                scrollBy(deltaX, 0);
                return true;
            }
                scrollOffset +=deltaX;
                Log.d("tag",scrollOffset+"");
                if(scrollOffset<0){
                    deltaX -=scrollOffset;
                    scrollOffset=0;
                    scrollBy(deltaX, 0); // 使用deltaX来滚动内容
                } else{
                    scrollBy(deltaX, 0); // 使用deltaX来滚动内容
                }
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                // 手指离开屏幕时，可以考虑实现平滑滚动或回弹效果
                // 例如，使用Scroller类来实现平滑滚动
                int scrollX = (int) offsetX;
                int scrollY = (int) offsetY;
                scroller.startScroll(scrollX, scrollY, -scrollX, -scrollY, 500); // 500ms内平滑滚动到目标位置
                invalidate(); // 重绘视图，开始滚动动画
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return true;
    }

    private int getScrollRange() {
        // 假设您有一种方法来获取内容的宽度，比如通过测量子视图或预设值
        return Math.max(0, contentWidth - getWidth());
    }

    private int getContentWidth() {
        int totalWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                totalWidth += child.getMeasuredWidth();
            }
        }
        return totalWidth-screenWidth;
    }

    public void resetLayout() {
        scroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
        invalidate();
    }
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            // 重新布局和绘制视图以反映新的滚动位置
            requestLayout();
            invalidate();
        }
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return true;
    }
}
