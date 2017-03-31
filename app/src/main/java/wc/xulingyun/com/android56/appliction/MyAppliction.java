package wc.xulingyun.com.android56.appliction;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.polaric.colorful.Colorful;

/**
 * Created by 徐玲郓 on 2017/3/3.
 * 描述：
 */

public class MyAppliction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Colorful.init(this);
    }
}
