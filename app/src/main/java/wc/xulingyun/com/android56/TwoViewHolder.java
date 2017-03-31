package wc.xulingyun.com.android56;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class TwoViewHolder extends ParentViewHolder<ItemDao> {

    TextView title;
    TextView content;
    ImageView icon;
    TextView data;
    TextView count;

    public TwoViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.context);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        data = (TextView) itemView.findViewById(R.id.data);
        count = (TextView) itemView.findViewById(R.id.count);
    }


    @Override
    public void bindData(ItemDao itemDao) {
        title.setText(itemDao.getTitle());
        content.setText(itemDao.getContent());
//        icon.setImageResource();
        data.setText(itemDao.getDate());
        count.setText(itemDao.getCount()+"订阅");
    }
}
