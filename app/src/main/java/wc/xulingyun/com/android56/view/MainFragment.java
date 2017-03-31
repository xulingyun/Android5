package wc.xulingyun.com.android56.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.FirstFragment;
import wc.xulingyun.com.android56.FrescoImageLoader;
import wc.xulingyun.com.android56.MainPagerAdapter;
import wc.xulingyun.com.android56.SecondFragment;
import wc.xulingyun.com.android56.interfaces.ToolbarNavigationListen;

import static wc.xulingyun.com.android56.MainActivity.TAG;

/**
 * Created by 徐玲郓 on 2017/3/28.
 * 描述：
 */

public class MainFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    MainPagerAdapter mPagerAdapter;
    ArrayList<Fragment> listFragment;
    ArrayList<String> listTitle;

    AppBarLayout appbar;
    int statusBarHeight1 = -1;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    ArrayList<String> images;
    ArrayList<String> titles;
    Banner mBanner;

    public MainFragment(ToolbarNavigationListen $ToolbarNavigationListen) {
        super($ToolbarNavigationListen);
    }

    public MainFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        listFragment = new ArrayList<>();
        listFragment.add(new FirstFragment());
        listFragment.add(new SecondFragment());
        listFragment.add(new FirstFragment());
        listTitle = new ArrayList<>();
        listTitle.add("最新");
        listTitle.add("官方");
        listTitle.add("活动");
        listTitle.add("攻略");
        mPagerAdapter = new MainPagerAdapter(getChildFragmentManager(),getActivity(),listFragment,listTitle);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        Log.e(TAG, "mTabLayout: "+mTabLayout );
        mTabLayout.setupWithViewPager(mViewPager);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("英雄联盟");
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.RED);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT);
        mCollapsingToolbarLayout.setTitleEnabled(false);

        appbar = (AppBarLayout) view.findViewById(R.id.appbar);
//        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//
//                int alpha = (int) (127 * (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
//                toolbar.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    getWindow().setStatusBarColor(Color.argb(alpha,0,0,0));
//                }
//                if(Math.abs(verticalOffset)*2>=appBarLayout.getTotalScrollRange()){
//                    if(state == CollapsingState.Colse_Collapsing) {
//                        mBanner.stopAutoPlay();
//                        mBanner.isAutoPlay(false);
//                        mBanner.onPageSelected(3);
//                        mBanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
//                        state = CollapsingState.Open_Collapsing;
//                    }
//                }else if(CollapsingState.Open_Collapsing==state){
//                    mBanner.startAutoPlay();
//                    mBanner.isAutoPlay(true);
//                    mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//                    state = CollapsingState.Colse_Collapsing;
//                }
//            }
//        });

        images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488536510208&di=001c0223eb1ccb51f2cbf5b551b6e21c&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F141114%2F318762-1411140J63541.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488536533738&di=38fcf0bb01a28471923880fb3cabfbc3&imgtype=0&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201302%2F1-130201105133.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488536549488&di=c028dad8432e1308e77b7b040422e18d&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1210%2F31%2Fc1%2F14846414_1351668808849.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488536565077&di=e7860d4db06bef5150da65e7752d46fe&imgtype=0&src=http%3A%2F%2Fimage81.360doc.com%2FDownloadImg%2F2015%2F01%2F2911%2F49614670_20.jpg");
        titles = new ArrayList<>();
        titles.add("小猫");
        titles.add("小狗");
        titles.add("猪猪侠");
        titles.add("大熊猫");
        mBanner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new FrescoImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Stack);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        return view;
    }

    @Override
    void setLayout(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.main_fragment,null);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
