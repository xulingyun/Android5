package wc.xulingyun.com.android56;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;

import org.polaric.colorful.Colorful;
import org.polaric.colorful.ColorfulActivity;

import java.util.Timer;
import java.util.TimerTask;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.adapter.DialogRecyclerAdapter;
import wc.xulingyun.com.android56.view.LoadingView;

import static org.polaric.colorful.Colorful.getThemeDelegate;

public class SettingActivity extends ColorfulActivity implements View.OnClickListener,DialogRecyclerAdapter.OnItemLister ,LoadingView.LoadListener{

    public static final int MAIN_COLOR = 0;
    public static final int OTHER_COLOR = 1;
    private static final String TAG = "SettingActivity";
    private int nowColor;
    AppCompatImageView mainColor;
    AppCompatImageView otherColor;
    AppCompatDialog dialog;
    FloatingActionButton mFab;
    private Toolbar toolbar;
    AppCompatImageView toolbar_image;
    SwitchCompat day_night;
    boolean isDark;
    LoadingView mLoadingView;
    private Timer timer;

    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transparentStatusBar();
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainColor = (AppCompatImageView) findViewById(R.id.main_color);
        isDark = Colorful.getThemeDelegate().isNight();
        if(!isDark){
            mainColor.setVisibility(View.VISIBLE);
            mainColor.setOnClickListener(this);
            mainColor.setImageDrawable(createDrawable(R.drawable.xz, getThemeDelegate().getPrimaryColor().getColorRes()));
        }else{
            mainColor.setVisibility(View.INVISIBLE);
        }
        otherColor = (AppCompatImageView) findViewById(R.id.other_color);
        otherColor.setOnClickListener(this);
        otherColor.setImageDrawable(createDrawable(R.drawable.ld, getThemeDelegate().getAccentColor().getColorRes()));
        toolbar_image = (AppCompatImageView) findViewById(R.id.toolbar_image);
        day_night = (SwitchCompat) findViewById(R.id.day_night);
        day_night.setChecked(isDark);
        day_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    toolbar_image.setImageResource(R.drawable.pic_night);
                }else{
                    toolbar_image.setImageResource(R.drawable.pic_day);
                }
                Colorful.config(SettingActivity.this).night(isChecked).apply();
            }
        });
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setImageDrawable(createDrawable(R.drawable.svg_ic_windmill,Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
        playFabAnimation();
        mLoadingView = (LoadingView) findViewById(R.id.test_loading);
        mLoadingView.setListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_color:
                nowColor = MAIN_COLOR;
                openDialg();
                break;
            case R.id.other_color:
                nowColor = OTHER_COLOR;
                openDialg();
                break;
        }

    }

    private void openDialg() {
        if(dialog==null) {
            dialog = new AppCompatDialog(this, 0);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.dialog_layout, null, false);
            dialog.setContentView(view);
            RecyclerView dialogRecyclerView = (RecyclerView) view.findViewById(R.id.dialog_recyclerView);

            dialogRecyclerView.setLayoutManager(new GridLayoutManager(SettingActivity.this, 4));
            DialogRecyclerAdapter lDialogRecyclerAdapter = new DialogRecyclerAdapter(SettingActivity.this);
            dialogRecyclerView.setAdapter(lDialogRecyclerAdapter);
            lDialogRecyclerAdapter.setLister(this);
            dialogRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.set(20, 20, 20, 20);
                }

            });
        }
        dialog.show();
    }

    protected ColorStateList createColorStateList(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(this, colorRes);
        int[] colors = new int[]{color, color, color, color, color, color};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    @Override
    public void itemClick(int index) {
        if(nowColor==MAIN_COLOR) {
            Colorful.config(this)
                    .primaryColor(Colorful.ThemeColor.values()[index])
                    .apply();
            mFab.setBackgroundTintList(createColorStateList(Colorful.ThemeColor.values()[index].getColorRes()));
            mainColor.setImageDrawable(createDrawable(R.drawable.xz,Colorful.ThemeColor.values()[index].getColorRes()));
        }else{
            Colorful.config(this)
                    .accentColor(Colorful.ThemeColor.values()[index])
                    .apply();
            otherColor.setImageDrawable(createDrawable(R.drawable.ld,Colorful.ThemeColor.values()[index].getColorRes()));
            mFab.setImageDrawable(createDrawable(R.drawable.svg_ic_windmill,Colorful.ThemeColor.values()[index].getColorRes()));
        }
        playFabAnimation();
        dialog.dismiss();
    }

    private Drawable createDrawable(int drawableRes, int colorRes) {
        Drawable drawable = ContextCompat.getDrawable(this, drawableRes);
        DrawableCompat.setTint(drawable,ContextCompat.getColor(this,colorRes));
        return drawable;
    }

    private void playFabAnimation() {
        mFab.setScaleX(0f);
        mFab.setScaleY(0f);
        mFab.setAlpha(0f);

        mFab.animate().cancel();
        mFab.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mFab.animate()
                                .rotation(360 * 2)
                                .setDuration(1500)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        mFab.setRotation(0);
                                        mFab.animate()
                                                .scaleX(0f)
                                                .scaleY(0f)
                                                .alpha(0f)
                                                .setDuration(200)
                                                .setListener(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        mFab.animate().cancel();
                                                    }
                                                });
                                    }
                                });
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mLoadingView.setProgress(mLoadingView.getProgress()+1);
            }
        },100,200);
    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public void complete(){
        timer.cancel();
        Log.e("TAG", "onClick: 完成");
    }

    @Override
    public void reStart() {
        mLoadingView.setProgress(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mLoadingView.setProgress(mLoadingView.getProgress()+1);
            }
        },100,200);
    }


}
