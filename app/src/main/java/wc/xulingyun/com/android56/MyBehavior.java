package wc.xulingyun.com.android56;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 徐玲郓 on 2017/3/3.
 * 描述：
 */

public class MyBehavior extends AppBarLayout.Behavior {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static final String TAG = "MyBehavior";

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
                                  View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout,child,target,dx,dy,consumed);
        Log.e(TAG, "onNestedPreScroll: dy:"+dy);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        boolean b = super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);

        Log.e(TAG, "onNestedPreScroll: b:"+b);
        return b;
    }
}
