package wc.xulingyun.com.android56.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.adapter.FirendMessageAdapter;
import wc.xulingyun.com.android56.dao.UserInfo;

/**
 * Created by 徐玲郓 on 2017/3/29.
 * 描述：
 */

public class FriendMessageFragment extends Fragment {

    View view;
    LayoutInflater mInflater;
    RecyclerView mRecyclerView;
    ArrayList<UserInfo> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        view = inflater.inflate(R.layout.firend_message_fragment,null);
        mRecyclerView = (RecyclerView) view;
        getData();
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(),LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new FirendMessageAdapter(list,getContext()));
        return view;
    }

    void getData(){
        list = new ArrayList<>();
        UserInfo userInfo;
        for (int i = 0; i <10 ; i++) {
            userInfo = new UserInfo("张三开飞机"+i,"","2017-01-10 10:15:15","德玛西亚"+(i*3));
            list.add(userInfo);
        }
    }
}
