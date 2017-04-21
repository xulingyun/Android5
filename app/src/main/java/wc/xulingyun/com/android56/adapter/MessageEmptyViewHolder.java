package wc.xulingyun.com.android56.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.ArrayList;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/4/11.
 * 描述：
 */

public class MessageEmptyViewHolder extends BaseViewHolder<String> {

    AppCompatTextView message_empty_text;

    MessageEmptyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    void initView(View itemView) {
        message_empty_text = (AppCompatTextView) itemView.findViewById(R.id.message_empty_text);
    }

    @Override
    void initData(ArrayList<String> list, int position) {
        message_empty_text.setText("打算打打群哇所大按时大大");
        message_empty_text.setWidth(500);
        message_empty_text.setHeight(500);
    }

}
