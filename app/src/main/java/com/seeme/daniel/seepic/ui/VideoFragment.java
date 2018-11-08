package com.seeme.daniel.seepic.ui;

import android.os.Bundle;
import android.view.View;

import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 11:20
 */
public class VideoFragment extends BaseMvpFragment {

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Model createModel() {
        return null;
    }

    @Override
    public MyView createView() {
        return null;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_photo;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }
}
