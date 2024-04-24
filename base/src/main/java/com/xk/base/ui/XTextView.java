package com.xk.base.ui;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import com.xk.base.R;


public class XTextView extends androidx.appcompat.widget.AppCompatTextView {
    private GradientDrawable backgroundDrawable;

    public XTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public XTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

    private void init(Context context, AttributeSet attrs) {
        // 从XML属性中读取圆角大小
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedButton, 0, 0);
        float cornerRadius = a.getDimension(R.styleable.RoundedButton_cornerRadius, 0);
        a.recycle();

        if (cornerRadius > 0) {
            setCornerRadius(cornerRadius);
        }
    }

    private void setCornerRadius(final float radius) {
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        });
        setClipToOutline(true);
    }
}
