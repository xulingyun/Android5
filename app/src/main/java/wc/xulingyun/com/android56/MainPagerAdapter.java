package wc.xulingyun.com.android56;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 徐玲郓 on 2017/3/10.
 * 描述：
 */

public class MainPagerAdapter extends FragmentPagerAdapter {


    Context mContext;
    ArrayList<Fragment> list;
    ArrayList<String> titleList;

    public MainPagerAdapter(FragmentManager fm,Context context, ArrayList<Fragment> list,ArrayList<String> titles) {
        super(fm);
        this.mContext = context;
        this.list = list;
        this.titleList = titles;
    }

    public MainPagerAdapter(FragmentManager fm,Context context, ArrayList<Fragment> list) {
        super(fm);
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titleList==null)
            return null;
        return titleList.get(position);
    }
}
