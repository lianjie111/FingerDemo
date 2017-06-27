package com.example.dell.fingerdemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jie on 2017/6/27.
 */

public class LineView extends View {

    private int color;
    private Context context;
    private Paint paint;
    private int width;
    private int height;



    public LineView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = widthMeasureSpec;
        height = heightMeasureSpec;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,0,width,0,paint);
    }

    void initView() {
        color = Color.BLUE;
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(10);
    }

    public void startAnim(int height) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY", 0, height);
        animator.setDuration(4000);
        animator.setRepeatCount(200);
        animator.start();
    }


}
