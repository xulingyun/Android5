package wc.xulingyun.com.android56;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<ItemDao> list;
    LayoutInflater mInflater;


    void addData(ArrayList<ItemDao> list){
        this.list = list;
    }


    public MyRecyclerViewAdapter(Context $Context) {
        mContext = $Context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("onCreateViewHolder-LOL","viewType:"+viewType);
        if(viewType==ItemDao.TYPE_ONE){
            return new OneViewHolder(mInflater.inflate(R.layout.one_item_layout,parent,false));
        }else if(viewType==ItemDao.TYPE_TWO){
            return new TwoViewHolder(mInflater.inflate(R.layout.two_item_layout,parent,false));
        }else{
            return new OneViewHolder(mInflater.inflate(R.layout.one_item_layout,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ParentViewHolder<ItemDao>)holder).bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }
}
