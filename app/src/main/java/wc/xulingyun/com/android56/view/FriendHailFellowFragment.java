package wc.xulingyun.com.android56.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/3/29.
 * 描述：
 */

public class FriendHailFellowFragment extends Fragment {

    View view;
    LayoutInflater mInflater;
    ExpandableListView mExpandableListView;
    ExpandableListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        view = inflater.inflate(R.layout.friend_hail_fellow_layout,null);
        adapter = new BaseExpandableListAdapter() {

            private String[] generalsTypes = new String[] { "魏", "蜀", "吴" };
            private String[][] generals = new String[][] {
                    { "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
                    { "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
                    { "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" }
                };

            @Override
            public int getGroupCount() {
                return generalsTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return generals[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return generalsTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return generals[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView = mInflater.inflate(R.layout.group_item1,null);
                    AppCompatTextView lViewById = (AppCompatTextView) convertView.findViewById(R.id.group_name);
                    lViewById.setText(getGroup(groupPosition).toString());
                    AppCompatImageView iv = (AppCompatImageView) convertView.findViewById(R.id.group_down);
                    iv.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_500_18dp);
                }else{

                }
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                if(convertView==null) {
                    convertView = mInflater.inflate(R.layout.expandable_item, null);
                }
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        mExpandableListView.setScrollContainer(false);
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(parent.isGroupExpanded(groupPosition)){
                    parent.collapseGroup(groupPosition);
                    ((AppCompatImageView) v.findViewById(R.id.group_down)).setImageResource(R.drawable.ic_keyboard_arrow_down_grey_500_18dp);
                }else{
                    parent.expandGroup(groupPosition,false);
                    ((AppCompatImageView) v.findViewById(R.id.group_down)).setImageResource(R.drawable.ic_keyboard_arrow_up_grey_500_18dp);
                }
                return true;
            }
        });
        mExpandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return view;
    }
}
