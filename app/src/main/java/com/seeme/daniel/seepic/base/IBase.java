package com.seeme.daniel.seepic.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author danielwang
 * @Description:
 * @date 2018/10/11 14:51
 */
public interface IBase {
    /**
     * 初始化页面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 获取view的ID
     *
     * @return
     */
    int getContentView();

    /**
     * 初始化页面
     *
     * @param view
     * @param savedInstanceState
     */
    void initView(View view, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData();
}
