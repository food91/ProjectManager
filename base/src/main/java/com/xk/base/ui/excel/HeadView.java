package com.xk.base.ui.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class HeadView extends View {

    private int screenWidth;
    Paint paint;
    Paint textPaint;
    Paint monPaint;

    public HeadView(Context context) {
        super(context);
        init(context);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.parseColor("#797979"));
        // 设置画笔其他属性，如粗细、抗锯齿等
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE); // 设置填充样式

        textPaint = new Paint();
        textPaint.setTextSize(spToPx(getContext(), 12)); // 设置字体大小为12sp
        textPaint.setColor(Color.parseColor("#797979"));
        monPaint = new Paint();
        monPaint.setTextSize(spToPx(getContext(), 12)); // 设置字体大小为12sp
        monPaint.setColor(Color.parseColor("#333333"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = dpToPx(getContext(),104+52*12);
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY);
        setMeasuredDimension(newWidthMeasureSpec, dpToPx(getContext(),30));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, dpToPx(getContext(),104), dpToPx(getContext(),30), paint);
        canvas.drawLine(0,0,dpToPx(getContext(),104),dpToPx(getContext(),30),paint);
        canvas.drawText("日期",dpToPx(getContext(),60),dpToPx(getContext(),15),textPaint);
        canvas.drawText("名称",dpToPx(getContext(),15),dpToPx(getContext(),26),textPaint);
        for(int i=0;i<12;i++){
            canvas.drawRect(dpToPx(getContext(),104+52*i),0, dpToPx(getContext(),
                    156+52*i), dpToPx(getContext(),30), paint);
            int mon=i+1;
            canvas.drawText(mon+"月",dpToPx(getContext(),
                    117+52*i),dpToPx(getContext(),20),monPaint);
        }
        Log.d("tag","ondraw");
    }

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
    public static int spToPx(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return Math.round(sp * scaledDensity);
    }
}
