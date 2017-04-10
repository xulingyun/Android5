package wc.xulingyun.com.android56.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.dao.UserInfo;

/**
 * Created by 徐玲郓 on 2017/4/7.
 * 描述：
 */

public class FirendMessageAdapter extends RecyclerView.Adapter<FirendMessageAdapter.MyViewHolder> {

    ArrayList<UserInfo> list;
    LayoutInflater mInflater;

    public FirendMessageAdapter(ArrayList<UserInfo> $List, Context $Context) {
        list = $List;
        mInflater = LayoutInflater.from($Context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.message_item,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.message.setText("来撸呀");
        holder.name.setText("天天一起坑");
        holder.time.setText("昨天");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView mCircleImageView;
        AppCompatTextView name;
        AppCompatTextView message;
        AppCompatTextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCircleImageView = (CircleImageView) itemView.findViewById(R.id.circle_image_view);
            name = (AppCompatTextView) itemView.findViewById(R.id.name);
            message = (AppCompatTextView) itemView.findViewById(R.id.message_content);
            time = (AppCompatTextView) itemView.findViewById(R.id.last_time);
        }
    }
}
