package wc.xulingyun.com.android56.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.interfaces.ToolbarNavigationListen;

/**
 * Created by 徐玲郓 on 2017/3/29.
 * 描述：
 */

public abstract class BaseFragment extends Fragment {

    Toolbar toolbar;
    View view;
    ToolbarNavigationListen mToolbarNavigationListen;

    public BaseFragment(ToolbarNavigationListen $ToolbarNavigationListen) {
        mToolbarNavigationListen = $ToolbarNavigationListen;
    }
    public BaseFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(inflater);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_manage);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbarNavigationListen.onNavigationListen();
            }
        });
        return view;
    }

    abstract void setLayout(LayoutInflater inflater);

}
