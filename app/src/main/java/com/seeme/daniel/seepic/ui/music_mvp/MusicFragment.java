package com.seeme.daniel.seepic.ui.music_mvp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.base.SupportFragment;
import com.seeme.daniel.seepic.ui.news_mvp.new_total.NewsFragment;
import com.seeme.daniel.seepic.ui.photo_mvp.PhotoFragment;
import com.seeme.daniel.seepic.ui.video_mvp.VideoFragment;
import com.seeme.daniel.seepic.view.CustomViewPager;

import butterknife.BindView;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 16:35
 */
public class MusicFragment extends BaseMvpFragment {

    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.SlidingTabLayout)
    TabLayout SlidingTabLayout;
    MusicAdapter adapter;

    public static MusicFragment newInstance() {
        Bundle bundle = new Bundle();
        MusicFragment fragment = new MusicFragment();
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
        return R.layout.fragment_music;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        adapter = new MusicAdapter(getChildFragmentManager(), "我的", "排行榜");
        mViewpager.setAdapter(adapter);
        mViewpager.setCurrentItem(0);
        SlidingTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void initData() {

    }
}
