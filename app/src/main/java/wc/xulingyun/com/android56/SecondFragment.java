package wc.xulingyun.com.android56;

import android.Manifest;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import wc.xulingyun.com.android5.R;

/**
 * Created by 徐玲郓 on 2017/3/10.
 * 描述：
 */

public class SecondFragment extends Fragment implements OnRequestPermissionsResultCallback{

    private RecyclerView recyclerView;
    private GridLayoutManager mLayoutManager;

    ArrayList<SecondItemDao> listData;
    View lView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lView = inflater.inflate(R.layout.second_content_main,container,false);

        recyclerView = (RecyclerView) lView.findViewById(R.id.second_recycle_view);
        mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        initData();
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int type = recyclerView.getAdapter().getItemViewType(position);
                return mLayoutManager.getSpanCount();

            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.left = 10;
//                outRect.top = 10;
                outRect.set(10, 10, 10, 10);
//                super.getItemOffsets(outRect, view, parent, state);
            }
        });


        return lView;
    }

    private void initData() {
        listData = new ArrayList<>();
        SecondItemDao dao;
        for (int i = 0; i < 20; i++) {
            dao = new SecondItemDao();
            dao.setIcon("");
            dao.setTitle("全新模式血月杀" + i);
            dao.setAuthor("起小点");
            dao.setContent("对决血月之下，畅享失忆激战……");
            dao.setCount(10500000);
            listData.add(dao);
        }
        SecondRecyclerViewAdapter adapter = new SecondRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.addData(listData);
        adapter.notifyDataSetChanged();
        PermissionGen.needPermission(this,100,new String[]{Manifest.permission.WRITE_CONTACTS,Manifest.permission.CAMERA});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSuccess(){
        Snackbar.make(lView,"权限获取成功",Snackbar.LENGTH_INDEFINITE).show();
    }

    @PermissionFail(requestCode = 100)
    public void doFail(){
        Snackbar.make(lView,"权限获取失败",Snackbar.LENGTH_INDEFINITE).show();
    }
}
