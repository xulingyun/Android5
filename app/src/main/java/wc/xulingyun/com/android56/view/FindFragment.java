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
import wc.xulingyun.com.android56.adapter.HeardRecyclerAdapter;
import wc.xulingyun.com.android56.dao.Team;

/**
 * Created by 徐玲郓 on 2017/3/28.
 * 描述：
 */

public class FindFragment extends Fragment {

    View view;
    RecyclerView mRecyclerView1;
    RecyclerView mRecyclerView2;
    RecyclerView mRecyclerView3;
    ArrayList<Team> list;
    int[] bitmapResId = new int[]{
            R.drawable.lol1, R.drawable.lol2, R.drawable.lol3, R.drawable.lol4,
            R.drawable.lol5, R.drawable.lol6, R.drawable.lol7, R.drawable.lol8,
            R.drawable.lol9, R.drawable.lol10, R.drawable.lol11, R.drawable.lol12,
            R.drawable.lol3
        };
    String[] name = new String[]{
            "Newbee", "IG", "WE", "IM", "QG", "LGD", "RNG", "VG", "Snake", "OMG", "EDG", "OMD", "凌云"
    };

    public FindFragment() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_fragment, null);
        initData();
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.heard_recycler);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.item1_recycler);
        mRecyclerView3 = (RecyclerView) view.findViewById(R.id.item2_recycler);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView1.setAdapter(new HeardRecyclerAdapter(getActivity(),list));
        mRecyclerView2.setAdapter(new HeardRecyclerAdapter(getActivity(),list));
        mRecyclerView3.setAdapter(new HeardRecyclerAdapter(getActivity(),list));
        return view;
    }

    void initData() {
        list = new ArrayList<>();
        Team lTeam;
        for (int i = 0; i < bitmapResId.length; i++) {
            lTeam = new Team();
            lTeam.setBitmapResId(bitmapResId[i]);
            lTeam.setName(name[i]);
            list.add(lTeam);
        }
    }
}
