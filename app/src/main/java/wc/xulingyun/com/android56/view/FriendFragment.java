package wc.xulingyun.com.android56.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.QuoteSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.MainPagerAdapter;
import wc.xulingyun.com.android56.interfaces.ToolbarNavigationListen;

/**
 * Created by 徐玲郓 on 2017/3/28.
 * 描述：
 */

public class FriendFragment extends BaseFragment {

    private ViewPager mViewPager;
    MainPagerAdapter mPagerAdapter;
    ArrayList<Fragment> listFragment;
    AppCompatTextView firend;
    AppCompatTextView message;
    List<String> firendList;

    public FriendFragment(ToolbarNavigationListen $ToolbarNavigationListen) {
        super($ToolbarNavigationListen);
    }

    public FriendFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        toolbar.setTitle("");
        firendList = Arrays.asList(getResources().getStringArray(R.array.firend));
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position==0) {
                    detailText(firend,message,0);
                }else if(position==1) {
                    detailText(message,firend,1);
                }
            }
        });
        firend = (AppCompatTextView) view.findViewById(R.id.firend);
        firend.setText(firendList.get(0));
        firend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailText(firend,message,0);
                message.setText(firendList.get(1));
            }
        });
        message = (AppCompatTextView) view.findViewById(R.id.message);
        message.setText(firendList.get(1));
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailText(message,firend,1);
                firend.setText(firendList.get(0));
            }
        });
        listFragment = new ArrayList<>();
        listFragment.add(new FriendHailFellowFragment());
        listFragment.add(new FriendMessageFragment());
        mPagerAdapter = new MainPagerAdapter(getChildFragmentManager(),getActivity(),listFragment);
        mViewPager.setAdapter(mPagerAdapter);
        detailText(firend,message,0);
        return view;
    }

    @Override
    void setLayout(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.friend_fragment_layout,null);
    }

    void detailText(AppCompatTextView tv1,AppCompatTextView tv2,int index){
        QuoteSpan span = new QuoteSpan(Color.RED);
        SpannableString spannableString = new SpannableString(tv1.getText().toString());
        spannableString.setSpan(span,0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv1.setText(spannableString);
        tv2.setText(tv2.getText().toString());
        mViewPager.setCurrentItem(index,true);
    }
}
