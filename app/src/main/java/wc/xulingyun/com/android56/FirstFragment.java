package wc.xulingyun.com.android56;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/3/10.
 * 描述：
 */

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager mLayoutManager;

    ArrayList<ItemDao> listData;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lView = inflater.inflate(R.layout.content_main,container,false);

        recyclerView = (RecyclerView) lView.findViewById(R.id.recycle_view);
        mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        initData();
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int type = recyclerView.getAdapter().getItemViewType(position);
                return mLayoutManager.getSpanCount();

            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.left = 10;
//                outRect.top = 10;
                outRect.set(10, 10, 10, 10);
//                super.getItemOffsets(outRect, view, parent, state);
            }
        });


        return lView;
    }

    private void initData() {
        listData = new ArrayList<>();
        ItemDao dao;
        for (int i = 0; i < 20; i++) {
            dao = new ItemDao();
            dao.setIcon("");
            dao.setTitle("全新模式血月杀" + i);
            dao.setContent("对决血月之下，畅享失忆激战。摆脱兵线束缚，团战一触即发……");
            dao.setDate("2016-12-12");
            dao.setCount(10500000);
            dao.setSecondType(20 % 3);
            if (i == 0) {
                dao.setType(0);
            } else {
                dao.setType(1);
            }
            listData.add(dao);
        }
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.addData(listData);
        adapter.notifyDataSetChanged();

    }

}
