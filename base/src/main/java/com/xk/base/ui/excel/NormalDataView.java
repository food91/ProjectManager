package com.xk.base.ui.excel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class NormalDataView extends View {

    private List<String> data;
    private Paint paint;
    private Paint Blackpaint;
    private Paint textPaint;
    private Paint greenPaint;
    private Paint redPaint;
    public NormalDataView(Context context) {
        super(context);
        init();
    }

    public NormalDataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NormalDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NormalDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.parseColor("#797979"));
        // 设置画笔其他属性，如粗细、抗锯齿等
        paint.setStrokeWidth(dpToPx(getContext(),1));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE); // 设置填充样式

        greenPaint = new Paint();
        greenPaint.setColor(Color.parseColor("#00cc00"));
        greenPaint.setStrokeWidth(dpToPx(getContext(),24));

        redPaint = new Paint();
        redPaint.setColor(Color.parseColor("#ff0000"));
        redPaint.setStrokeWidth(dpToPx(getContext(),24));

        textPaint = new Paint();
        textPaint.setTextSize(spToPx(getContext(), 13)); // 设置字体大小为12sp
        textPaint.setColor(Color.parseColor("#333333"));
        Blackpaint = new Paint();
        Blackpaint.setTextSize(spToPx(getContext(), 13)); // 设置字体大小为12sp
        Blackpaint.setColor(Color.BLACK);
    }

    private float greenend=0;
    private float greenlen=0;
    private float greenstart=0;

    private float redend=0;
    private float redlen=0;
    private float redstart=0;

    public void setData(List<String> data) {
        this.data = data;
        requestLayout();
    }

    private int texth=0;



    float greencenterX=0;
    float redcenterX=0;
    private int height=60;
    private int cell_width=100;
    private int cell_small_width = 100;
    public static void extractDates(String str) {


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = dpToPx(getContext(),cell_width*data.size());
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY);
        setMeasuredDimension(newWidthMeasureSpec, dpToPx(getContext(),height));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<data.size();i++){
            canvas.drawRect(dpToPx(getContext(),cell_small_width*i),0, dpToPx(getContext(),
                    cell_width+cell_small_width*i), dpToPx(getContext(),height), paint);
            int cenX =cell_small_width*i+cell_small_width/2;
            int len = (int) Blackpaint.measureText(data.get(i))/3; // 测量文本的宽度
            cenX = cenX-len+5;
            canvas.drawText(data.get(i),
                    dpToPx(getContext(), (int) cenX),
                    dpToPx(getContext(),
                            height/2-texth/2+5),Blackpaint);
        }

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
