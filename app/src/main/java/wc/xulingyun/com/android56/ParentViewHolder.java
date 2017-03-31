package wc.xulingyun.com.android56;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public abstract class ParentViewHolder<T> extends RecyclerView.ViewHolder {

    public ParentViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T $T);

}
