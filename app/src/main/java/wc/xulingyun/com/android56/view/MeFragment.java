package wc.xulingyun.com.android56.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import wc.xulingyun.com.android5.R;

import wc.xulingyun.com.android56.FirstFragment;
import wc.xulingyun.com.android56.MainPagerAdapter;
import wc.xulingyun.com.android56.SecondFragment;
import wc.xulingyun.com.android56.interfaces.ToolbarNavigationListen;

/**
 * Created by 徐玲郓 on 2017/4/12.
 * 描述：
 */

public class MeFragment extends BaseFragment{

    MainPagerAdapter mPagerAdapter;
    ArrayList<Fragment> listFragment;
    ArrayList<String> listTitle;

    AppBarLayout appbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    public MeFragment(ToolbarNavigationListen $ToolbarNavigationListen) {
        super($ToolbarNavigationListen);
    }
    public MeFragment() {
        super();
    }

    @Override
    void setLayout(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.me_fragmet,null);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        ViewPager lViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        listFragment = new ArrayList<>();
        listFragment.add(new FirstFragment());
        listFragment.add(new SecondFragment());
        listFragment.add(new FirstFragment());
        listTitle = new ArrayList<>();
        listTitle.add("最新");
        listTitle.add("官方");
        listTitle.add("活动");
        mPagerAdapter = new MainPagerAdapter(getChildFragmentManager(),getActivity(),listFragment,listTitle);
        lViewPager.setAdapter(mPagerAdapter);
        TabLayout lTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        lTabLayout.setupWithViewPager(lViewPager);

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
        return view;
    }
}
