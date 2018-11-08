package com.seeme.daniel.seepic.utils;

import android.widget.Toast;

import com.seeme.daniel.seepic.HiApplication;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 15:33
 */
public class ToastUtils {

    public static void toast(String key) {
        Toast.makeText(HiApplication.getContext(), key, Toast.LENGTH_SHORT).show();
    }

}
