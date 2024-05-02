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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DataView extends View {

    private Data data;
    private Paint paint;
    private Paint Witepaint;
    private Paint textPaint;
    private Paint greenPaint;
    private Paint redPaint;
    public DataView(Context context) {
        super(context);
        init();
    }

    public DataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        Witepaint = new Paint();
        Witepaint.setTextSize(spToPx(getContext(), 9)); // 设置字体大小为12sp
        Witepaint.setColor(Color.BLUE);
    }

    private float greenend=0;
    private float greenlen=0;
    private float greenstart=0;

    private float redend=0;
    private float redlen=0;
    private float redstart=0;

    public void setData(Data data) {
        this.data = data;
         setValue();
        requestLayout();
    }

    private int texth=0;

    private void setValue(){
        String act = data.getActualProgress();
        Pattern pattern = Pattern.compile("(\\d{2})/(\\d{2})-(\\d{2})/(\\d{2})");
        Matcher matcher = pattern.matcher(act);
        if (matcher.find()) {
            // 提取开始月份
            String startMonth = matcher.group(1);
            // 提取开始日期
            String actstartDay = matcher.group(2);
            // 提取结束月份
            String actendMonth = matcher.group(3);
            // 提取结束日期
            String actendDay = matcher.group(4);
            int startmonth = Integer.parseInt(startMonth);
            int startday = Integer.parseInt(actstartDay);
            greenstart = startmonth-1+ (float) startday /30;
            Log.d("tag","xx=="+greenstart);
            int endmonth = Integer.parseInt(actendMonth);
            int endday = Integer.parseInt(actendDay);
            greenend = endmonth-1+ (float) endday /30;
            greenlen = textPaint.measureText(data.getActualProgress()); // 测量文本的宽度
            // 获取字体的度量信息
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            texth = (int) (-fontMetrics.ascent + fontMetrics.descent);
            float len=(cell_width+cell_small_width*greenend)-(cell_width+cell_small_width*greenstart);
            if(greenlen/2>len){
                greencenterX = cell_width+cell_small_width*greenstart;
            }else{
                greencenterX = cell_width+cell_small_width*greenend-len/2-greenlen/2;
            }

        }
        String all = data.getAllProgress();
        Matcher matcher2 = pattern.matcher(all);
        if (matcher2.find()) {
            // 提取开始月份
            String startMonth = matcher.group(1);
            // 提取开始日期
            String actstartDay = matcher.group(2);
            // 提取结束月份
            String actendMonth = matcher.group(3);
            // 提取结束日期
            String actendDay = matcher.group(4);
            int startmonth = Integer.parseInt(startMonth);
            int startday = Integer.parseInt(actstartDay);
            greenstart = startmonth-1+ (float) startday /30;
            Log.d("tag","xx=="+greenstart);
            int endmonth = Integer.parseInt(actendMonth);
            int endday = Integer.parseInt(actendDay);
            redend = endmonth-1+ (float) endday /30;
            redlen = textPaint.measureText(data.getActualProgress()); // 测量文本的宽度
            float len=(cell_width+cell_small_width*greenend)-(cell_width+cell_small_width*greenstart);
            if(redlen/2>len){
                redcenterX = cell_width+cell_small_width*greenstart;
            }else{
                redcenterX = cell_width+cell_small_width*greenend-len/2-greenlen/2;
            }

        }
    }

    float greencenterX=0;
    float redcenterX=0;
    private int height=60;
    private int cell_width=104;
    private int cell_small_width = 52;
    public static void extractDates(String str) {


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = dpToPx(getContext(),104+52*12);
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY);
        setMeasuredDimension(newWidthMeasureSpec, dpToPx(getContext(),height));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, dpToPx(getContext(),cell_width), dpToPx(getContext(),height), paint);
        canvas.drawText(data.getText(),dpToPx(getContext(),10),dpToPx(getContext(),height/2+5),textPaint);
        for(int i=0;i<12;i++){
            canvas.drawRect(dpToPx(getContext(),cell_width+cell_small_width*i),0, dpToPx(getContext(),
                    cell_width+cell_small_width+cell_small_width*i), dpToPx(getContext(),height), paint);
        }
        canvas.drawRect(dpToPx(getContext(), (int) (cell_width+cell_small_width*greenstart)), 0, dpToPx(getContext(), (int) (cell_width+cell_small_width*greenend)), dpToPx(getContext(),height/2), greenPaint);
        canvas.drawRect(dpToPx(getContext(), (int) (cell_width+cell_small_width*greenstart)),  dpToPx(getContext(),height/2), dpToPx(getContext(), (int) (cell_width+cell_small_width*greenend)), dpToPx(getContext(),height), redPaint);
        canvas.drawText(data.getActualProgress(),
                dpToPx(getContext(), (int) greencenterX),dpToPx(getContext(),height/2-texth/2+5),Witepaint);
        canvas.drawText(data.getAllProgress(),
                dpToPx(getContext(), (int) greencenterX),dpToPx(getContext(),height-texth/2+5),Witepaint);
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
