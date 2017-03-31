package wc.xulingyun.com.android56;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class SecondRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<SecondItemDao> list;
    LayoutInflater mInflater;


    void addData(ArrayList<SecondItemDao> list){
        this.list = list;
    }


    public SecondRecyclerViewAdapter(Context $Context) {
        mContext = $Context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SecondViewHolder(mInflater.inflate(R.layout.second_fragment_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ParentViewHolder<SecondItemDao>)holder).bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
