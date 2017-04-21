package wc.xulingyun.com.android56.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 徐玲郓 on 2017/4/11.
 * 描述：
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    abstract void initView(View itemView);

    abstract void initData(ArrayList<T> list,int position);


}
