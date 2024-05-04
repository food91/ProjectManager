package com.xk.base.ui.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class NormalHeadView extends View {

    private int screenWidth;
    Paint paint;
    Paint textPaint;
    Paint monPaint;
    private int textheight;
    private int textwidth;

    public NormalHeadView(Context context) {
        super(context);
        init(context);
    }

    public NormalHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NormalHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NormalHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private List<String> data;
    private List<Integer> dataWeight;

    public void setData(List<String> dataList){
        this.data = dataList;
        dataWeight = new ArrayList<>();
        for(int i=0;i<data.size();i++){
             int  h = (int) textPaint.measureText(data.get(i)); // 测量文本的宽度
            dataWeight.add(h);
        }
        requestLayout();
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
        // 获取字体的度量信息
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textheight = (int) (-fontMetrics.ascent + fontMetrics.descent);
        monPaint = new Paint();
        monPaint.setTextSize(spToPx(getContext(), 12)); // 设置字体大小为12sp
        monPaint.setColor(Color.parseColor("#333333"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = dpToPx(getContext(),(cell_width*data.size()));
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY);
        setMeasuredDimension(newWidthMeasureSpec, dpToPx(getContext(),cell_height));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private int cell_one_width =100;
    private int cell_width = 100;

    private int cell_height =30;

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<data.size();i++){
            canvas.drawRect(dpToPx(getContext(),cell_width*i),0, dpToPx(getContext(),
                    cell_width+cell_width+cell_width*i), dpToPx(getContext(),cell_height), paint);
            int cenX =cell_width*i+cell_width/2;
            int len = (int) monPaint.measureText(data.get(i))/3; // 测量文本的宽度
            cenX = cenX-len+5;
            int ceny = cell_height/2+textheight/5;
            canvas.drawText(data.get(i),dpToPx(getContext(),
                    cenX),dpToPx(getContext(),ceny),monPaint);
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
