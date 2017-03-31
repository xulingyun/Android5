package wc.xulingyun.com.android56.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.dao.Team;

/**
 * Created by 徐玲郓 on 2017/3/31.
 * 描述：
 */

public class HeardRecyclerAdapter extends RecyclerView.Adapter<HeardRecyclerAdapter.MyViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<Team> list;

    public HeardRecyclerAdapter(Context $Context, ArrayList<Team> list){
        this.mContext = $Context;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.heard_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("onBindViewHolder", "onBindViewHolder: "+position);
        holder.mCircleImageView.setImageResource(list.get(position).getBitmapResId());
        holder.mTextView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView mCircleImageView;
        AppCompatTextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mCircleImageView = (CircleImageView) itemView.findViewById(R.id.heard_recycler_item_image);
            mTextView = (AppCompatTextView) itemView.findViewById(R.id.heard_recycler_item_text);
        }
    }
}
