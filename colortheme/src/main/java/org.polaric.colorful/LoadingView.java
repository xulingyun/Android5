package org.polaric.colorful;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 徐玲郓 on 2017/4/5.
 * 描述：
 */

public class LoadingView extends View {

    int circl_default_color = 0xffffff;
    int circl_fill_color = 0x0000ff;
    int text_default_color = 0xff0000;
    int text_fill_color = 0xff0000;
    int progress = 0;
    int max_progress = 100;
    int radius;
    Paint circlPaint;
    Paint textPaint;
    private Rect mRect;
    int angle = 0;


    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingView,defStyleAttr,0);
        circl_default_color = ta.getColor(ta.getIndex(R.styleable.LoadingView_circl_default_color),0xffffff);
        circl_fill_color = ta.getColor(ta.getIndex(R.styleable.LoadingView_circl_fill_color),0x0000ff);
        text_default_color = ta.getColor(ta.getIndex(R.styleable.LoadingView_text_default_color),0xff0000);
        text_fill_color = ta.getColor(ta.getIndex(R.styleable.LoadingView_text_fill_color),0xff0000);
        progress = ta.getInt(ta.getIndex(R.styleable.LoadingView_progress),progress);
        max_progress = ta.getInt(ta.getIndex(R.styleable.LoadingView_max_progress),max_progress);
        init();
    }

    private void init() {
        circlPaint = new Paint();
        circlPaint.setAntiAlias(true);
        circlPaint.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(18);
        textPaint.setColor(Color.RED);
        textPaint.setFakeBoldText(true);
        mRect = new Rect();
        angle = (int) (progress*1.0f/max_progress*360);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int max = Math.min(widthSize,heightSize);
        radius = max/2;
        setMeasuredDimension(max,max);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircler(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        textPaint.getTextBounds("下载",0,2,mRect);
        canvas.drawText("下载",radius-mRect.width()/2,radius+mRect.height()/2,textPaint);
    }

    private void drawCircler(Canvas canvas) {
        circlPaint.setColor(Color.CYAN);
        canvas.drawCircle(radius,radius,radius,circlPaint);
        circlPaint.setColor(Color.BLUE);
        RectF rect = new RectF(0,0,2*radius,2*radius);
        canvas.drawArc(rect,270,10,true,circlPaint);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int $Progress) {
        progress = $Progress;
        angle = (int) (progress*1.0f/max_progress*360);
        postInvalidate();
//        invalidate();
    }
}
