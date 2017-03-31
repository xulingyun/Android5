package wc.xulingyun.com.android56;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/2/20.
 * 描述：
 */

public class OneViewHolder extends ParentViewHolder<ItemDao> {

    AppCompatImageView team_icon1;
    AppCompatTextView team1;
    TextView match_title;
    TextView match_score;
    TextView match_content;
    AppCompatImageView team_icon2;
    AppCompatTextView team2;

    public OneViewHolder(View itemView) {
        super(itemView);
        team_icon1 = (AppCompatImageView) itemView.findViewById(R.id.team_icon1);
        team1 = (AppCompatTextView) itemView.findViewById(R.id.team1);

        match_title = (AppCompatTextView) itemView.findViewById(R.id.match_title);
        match_score = (AppCompatTextView) itemView.findViewById(R.id.match_score);
        match_content = (AppCompatTextView) itemView.findViewById(R.id.match_content);

        team_icon2 = (AppCompatImageView) itemView.findViewById(R.id.team_icon2);
        team2 = (AppCompatTextView) itemView.findViewById(R.id.team2);
    }


    @Override
    public void bindData(ItemDao itemDao) {
        team_icon1.setImageResource(R.drawable.ic_menu_camera);
        team1.setText("YM");
        match_title.setText(R.string.match_title);
        match_score.setText("0:9");
        match_content.setText("观看比赛");
        team_icon2.setImageResource(R.drawable.ic_menu_camera);
        team2.setText("LD");
    }
}
