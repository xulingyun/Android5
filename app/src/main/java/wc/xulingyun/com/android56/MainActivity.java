package wc.xulingyun.com.android56;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.polaric.colorful.Colorful;
import org.polaric.colorful.ColorfulActivity;

import java.util.ArrayList;
import java.util.List;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.adapter.DialogRecyclerAdapter;
import wc.xulingyun.com.android56.interfaces.ToolbarNavigationListen;
import wc.xulingyun.com.android56.view.BottomMenu;
import wc.xulingyun.com.android56.view.FindFragment;
import wc.xulingyun.com.android56.view.FriendFragment;
import wc.xulingyun.com.android56.view.MainFragment;

public class MainActivity extends ColorfulActivity
        implements NavigationView.OnNavigationItemSelectedListener ,DialogRecyclerAdapter.OnItemLister,ToolbarNavigationListen {

    String str1 = "wc.xulingyun.com.android5.xulingyun";
    String str2 = "wc.xulingyun.com.android5.xulingxia";
    public static final String TAG = "MainActivity";
    DrawerLayout drawer;
    AppCompatDialog dialog;
    BottomMenu mBottomMenu;
    ViewPager mViewPager;

    ArrayList<Fragment> listFragment;
    MainPagerAdapter mPagerAdapter;


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
//        transparentStatusBar();
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        listFragment = new ArrayList<>();
        listFragment.add(new FriendFragment(this));
        listFragment.add(new MainFragment(this));
        listFragment.add(new FindFragment());
        listFragment.add(new FindFragment());
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),this,listFragment);
        mViewPager.setAdapter(mPagerAdapter);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mBottomMenu = (BottomMenu) findViewById(R.id.bottom_menu);
        mBottomMenu.setData(
                new String[]{"微信","通讯录","发现","我"},
                new int[]{R.drawable.ic_menu_camera,R.drawable.ic_menu_camera,
                        R.drawable.ic_menu_camera,R.drawable.ic_menu_camera});
        mBottomMenu.setUpWithViewPager(mViewPager);
        mBottomMenu.setOnClickBottomMenu(new BottomMenu.OnClickBottomMenu() {
            @Override
            public void clickMenu(int index) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
//            setIcon(str1);
        } else if (id == R.id.nav_gallery) {
//            setIcon(str2);
        } else if (id == R.id.nav_slideshow) {
            drawer.closeDrawer(Gravity.LEFT,true);
        } else if (id == R.id.nav_manage) {
            Intent lIntent = new Intent(this,SettingActivity.class);
            startActivity(lIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setIcon(String str) {
        Context ctx = getApplication();
        PackageManager pm = ctx.getPackageManager();
        if (!str.equals(str1)) {
            //删除第一个图标
            pm.setComponentEnabledSetting(getComponentName(), str.equals(str1) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            //出现第二个图标
            pm.setComponentEnabledSetting(new ComponentName(ctx, str), str.equals(str2) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        } else {
            pm.setComponentEnabledSetting(new ComponentName(ctx, str), str.equals(str1) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            pm.setComponentEnabledSetting(new ComponentName(ctx, str2), str.equals(str2) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
        ActivityManager am = (ActivityManager) ctx.getSystemService(Activity.ACTIVITY_SERVICE);
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(i, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }

    @Override
    public void itemClick(final int index) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Colorful.config(MainActivity.this)
                        .primaryColor(Colorful.ThemeColor.values()[index])
                        .accentColor(Colorful.ThemeColor.values()[index])
                        .translucent(false)
                        .night(false)
                        .apply();
                recreate();
            }
        }, 1000);
        dialog.dismiss();
    }

    @Override
    public void onNavigationListen() {
        drawer.openDrawer(GravityCompat.START);
    }
}
