package com.seeme.daniel.seepic.ui.music_mvp;

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
 * @date 2018/11/12 16:50
 */
public class RankListFragment extends BaseMvpFragment {


    public static RankListFragment newInstance() {
        Bundle bundle = new Bundle();
        RankListFragment fragment = new RankListFragment();
        fragment.setArguments(bundle);
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
        return R.layout.fragment_music_rank;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }
}
