package wc.xulingyun.com.android56.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.polaric.colorful.Colorful;

import wc.xulingyun.com.android5.R;
import wc.xulingyun.com.android56.view.CircleView;

import static android.content.ContentValues.TAG;

/**
 * Created by 徐玲郓 on 2017/3/13.
 * 描述：
 */

public class DialogRecyclerAdapter extends RecyclerView.Adapter<DialogRecyclerAdapter.MyViewHolder> {

    private OnItemLister lister;

    public OnItemLister getLister() {
        return lister;
    }

    public void setLister(OnItemLister $Lister) {
        lister = $Lister;
    }

    public interface OnItemLister{
      void itemClick(int index);
    }

    LayoutInflater inflater;
    public DialogRecyclerAdapter(Context $Context) {
        inflater = LayoutInflater.from($Context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dialog_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.cv.setColor(Colorful.ThemeColor.values()[position].getColorRes());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lister!=null){
                    lister.itemClick(position);
                }
            }
        });
        Log.e(TAG, "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return Colorful.ThemeColor.values().length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleView cv;
        MyViewHolder(View itemView) {
            super(itemView);
            cv = (CircleView) itemView.findViewById(R.id.dialog_item);
        }
    }



}
