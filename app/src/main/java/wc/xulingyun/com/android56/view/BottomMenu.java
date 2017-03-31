package wc.xulingyun.com.android56.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;

import static wc.xulingyun.com.android56.MainActivity.TAG;

/**
 * Created by 徐玲郓 on 2017/3/27.
 * 描述：
 */

public class BottomMenu extends LinearLayout{

    int[] color = new int[]{0xffffff,0xbd9955,0xff0000};
    int selectColor;
    int unSelectColor;
    int superscript;
    int superscriptColor;
    Context mContext;
    OnClickBottomMenu mOnClickBottomMenu;
    ArrayList<ImageView> ivList;
    ArrayList<TextView> tvList;
    ViewPager mViewPager;


    public BottomMenu(Context context) {
        this(context,null);
    }

    public BottomMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomMenu);
        selectColor = ta.getColor(ta.getIndex(R.styleable.BottomMenu_select_color),color[0]);
        unSelectColor = ta.getColor(ta.getIndex(R.styleable.BottomMenu_unSelect_color),color[1]);
        superscriptColor = ta.getColor(ta.getIndex(R.styleable.BottomMenu_superscript_color),color[2]);
        superscript = ta.getInt(ta.getIndex(R.styleable.BottomMenu_superscript),0);
        ta.recycle();
        this.mContext = context;
        this.setPadding(0,dpToPx(context,10),0,dpToPx(mContext,10));

        init();
    }

    public void setData(String[] menuName,int[] menuIcon) {
        if (menuIcon.length != menuName.length)
            return;
        int length = menuIcon.length;
        for (int i = 0; i < length; i++) {
            final int index = i;
            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);

            ImageView iv = new ImageView(mContext);
            iv.setImageResource(menuIcon[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            ViewGroup.LayoutParams ivLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.addView(iv,ivLayoutParams);
            ivList.add(iv);

            TextView tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);
            tv.setText(menuName[i]);
            ViewGroup.LayoutParams tvLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.addView(tv,tvLayoutParams);
            tvList.add(tv);

            LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            lp.setLayoutDirection(LinearLayout.HORIZONTAL);
            addView(layout,lp);
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuItemChange(index);
                    if(mViewPager!=null){
                        mViewPager.setCurrentItem(index,false);
                    }
                }
            });
        }
    }

    public void setUpWithViewPager(ViewPager viewPager){
        this.mViewPager = viewPager;

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position<0||position>=ivList.size()-1)
                    return;
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                int value1 = (int) argbEvaluator.evaluate(positionOffset,getResources().getColor(R.color.md_red_900),getResources().getColor(R.color.black));
                int value2 = (int) argbEvaluator.evaluate(positionOffset,getResources().getColor(R.color.black),getResources().getColor(R.color.md_red_900));
                Log.e(TAG, "onPageScrolled: "+position );
                ivList.get(position).setColorFilter(value1);
                tvList.get(position).setTextColor(value1);
                ivList.get(position+1).setColorFilter(value2);
                tvList.get(position+1).setTextColor(value2);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void menuItemChange(int index) {
        cleanAllMenuColor();
        ivList.get(index).setColorFilter(Color.RED);
        tvList.get(index).setTextColor(Color.RED);
        if(mOnClickBottomMenu!=null) {
            mOnClickBottomMenu.clickMenu(index);
        }
    }

    private void cleanAllMenuColor() {
        for (ImageView iv: ivList) {
            iv.setColorFilter(null);
        }
        for (TextView tv: tvList) {
            tv.setTextColor(Color.BLACK);
        }
    }


    private void init() {
        ivList = new ArrayList<>();
        tvList = new ArrayList<>();
    }

    public static int dpToPx(Context context,int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    public interface OnClickBottomMenu{
        void clickMenu(int index);
    }

    public OnClickBottomMenu getOnClickBottomMenu() {
        return mOnClickBottomMenu;
    }

    public void setOnClickBottomMenu(OnClickBottomMenu $OnClickBottomMenu) {
        mOnClickBottomMenu = $OnClickBottomMenu;
    }
}
