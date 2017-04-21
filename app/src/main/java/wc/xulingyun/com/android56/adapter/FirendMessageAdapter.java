package wc.xulingyun.com.android56.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.dao.UserInfo;

/**
 * Created by 徐玲郓 on 2017/4/7.
 * 描述：
 */

public class FirendMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<UserInfo> list;
    LayoutInflater mInflater;
    boolean isEmpty = false;

    public FirendMessageAdapter(ArrayList<UserInfo> $List, Context $Context) {
        list = $List;
        mInflater = LayoutInflater.from($Context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(isEmpty){
            return new MessageEmptyViewHolder(mInflater.inflate(R.layout.message_empty,null));
        }
        return new MessageViewHolder(mInflater.inflate(R.layout.message_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!isEmpty) {
            ((MessageViewHolder)holder).initData(list,position);
        }else{
            ((MessageEmptyViewHolder)holder).initData(null,position);
        }
    }

    @Override
    public int getItemCount() {
        if(list==null||list.size()==0){
            isEmpty = true;
            return 1;
        }
        isEmpty = false;
        return list.size();
    }
}
