package wc.xulingyun.com.android56;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class SecondViewHolder extends ParentViewHolder<SecondItemDao> {

    private AppCompatImageView head_icon;
    private AppCompatTextView title;
    private AppCompatTextView author;
    AppCompatTextView content;
    private AppCompatTextView order;

    public SecondViewHolder(View itemView) {
        super(itemView);
        head_icon = (AppCompatImageView) itemView.findViewById(R.id.s_head_icon);
        title = (AppCompatTextView) itemView.findViewById(R.id.s_title);
        author = (AppCompatTextView) itemView.findViewById(R.id.s_author);
        content = (AppCompatTextView) itemView.findViewById(R.id.s_content);
        order = (AppCompatTextView) itemView.findViewById(R.id.s_order);
    }


    @Override
    public void bindData(SecondItemDao itemDao) {
//        head_icon.setImageResource(R.drawable.ic_menu_camera);
        title.setText(itemDao.getTitle());
        author.setText(itemDao.getAuthor());
        content.setText(itemDao.getContent());
        order.setText("+订阅");
    }
}
