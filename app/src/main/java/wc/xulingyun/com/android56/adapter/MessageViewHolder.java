package wc.xulingyun.com.android56.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.dao.UserInfo;

/**
 * Created by 徐玲郓 on 2017/4/11.
 * 描述：
 */

public class MessageViewHolder extends BaseViewHolder<UserInfo> {

    CircleImageView mCircleImageView;
    AppCompatTextView name;
    TextView message;
    AppCompatTextView time;

    public MessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    void initView(View itemView) {
        mCircleImageView = (CircleImageView) itemView.findViewById(R.id.circle_image_view);
        name = (AppCompatTextView) itemView.findViewById(R.id.name);
        message = (TextView) itemView.findViewById(R.id.message_content);
        time = (AppCompatTextView) itemView.findViewById(R.id.last_time);
    }

    @Override
    void initData(ArrayList<UserInfo> list,int position) {
        message.setText(list.get(position).getMessage());
        name.setText(list.get(position).getName());
        time.setText(list.get(position).getLastTime());
    }
}
