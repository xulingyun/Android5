package wc.xulingyun.com.android56.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.common.LoadingState;

/**
 * Created by 徐玲郓 on 2017/4/5.
 * 描述：
 */

public class LoadingView extends View{

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
    LoadingState state;
    LoadListener listener;
    String showText = getContext().getResources().getString(R.string.download);


    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingView,defStyleAttr,0);
        circl_default_color = ta.getColor(R.styleable.LoadingView_circl_default_color,0xffffff);
        circl_fill_color = ta.getColor(R.styleable.LoadingView_circl_fill_color,0x0000ff);
        text_default_color = ta.getColor(R.styleable.LoadingView_text_default_color,0xff0000);
        text_fill_color = ta.getColor(R.styleable.LoadingView_text_fill_color,0xff0000);
        progress = ta.getInt(R.styleable.LoadingView_progress,progress);
        max_progress = ta.getInt(R.styleable.LoadingView_max_progress,max_progress);
        init();
    }

    private void init() {
        circlPaint = new Paint();
        circlPaint.setAntiAlias(true);
        circlPaint.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(18);
        textPaint.setColor(text_default_color);
        textPaint.setFakeBoldText(true);
        mRect = new Rect();
        textPaint.getTextBounds(showText,0,showText.length(),mRect);
        angle = (int) (progress*1.0f/max_progress*360);
        state = LoadingState.INIT;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int max;
        if(widthSize==0||heightSize==0){
            max = Math.max(widthSize, heightSize);
            try {
                throw new Exception("不支持wrap_content和match_parent");
            } catch (Exception $E) {
                $E.printStackTrace();
            }

        }else {
            max = Math.min(widthSize, heightSize);
        }
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
        if(state== LoadingState.INIT){
            showText = getContext().getResources().getString(R.string.download);
        }else if(state== LoadingState.RUNNING){
            showText = String.format(getContext().getResources().getString(R.string.running),progress*100/max_progress);
        }else if(state== LoadingState.STOP){
            showText = getContext().getResources().getString(R.string.stop);
        }else if(state== LoadingState.COMPLETE){
            showText = getContext().getResources().getString(R.string.complete);
        }
        textPaint.getTextBounds(showText,0,showText.length(),mRect);
        canvas.drawText(showText,radius-mRect.width()/2,radius+mRect.height()/2,textPaint);
    }

    private void drawCircler(Canvas canvas) {
        circlPaint.setColor(circl_default_color);
        canvas.drawCircle(radius,radius,radius,circlPaint);
        circlPaint.setColor(circl_fill_color);
        RectF rect = new RectF(0,0,2*radius,2*radius);
        canvas.drawArc(rect,270,angle,true,circlPaint);
        if(angle>=360){
            textPaint.setColor(text_fill_color);
            state = LoadingState.COMPLETE;
            if(listener!=null){
                listener.complete();
            }
        }
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

    public LoadListener getListener() {
        return listener;
    }

    public void setListener(LoadListener $Listener) {
        listener = $Listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                if(listener!=null){
                    if(state== LoadingState.INIT){
                        state = LoadingState.RUNNING;
                        listener.start();
                    }else if(state== LoadingState.RUNNING){
                        state = LoadingState.STOP;
                        listener.stop();
                        postInvalidate();
                    }else if(state== LoadingState.STOP){
                        state = LoadingState.RUNNING;
                        listener.start();
                    }else if(state== LoadingState.COMPLETE){
                        Log.e("TAG", "onClick: 完成后重新下载");
                        state = LoadingState.RUNNING;
                        listener.reStart();
                    }
                }
                break;
        }

        return true;
    }

    public interface LoadListener{
        void start();
        void stop();
        void reStart();
        void complete();
    }

}
