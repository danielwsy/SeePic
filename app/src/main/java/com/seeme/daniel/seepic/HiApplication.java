package com.seeme.daniel.seepic;

import android.app.Application;
import android.content.Context;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 9:58
 */
public class HiApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
