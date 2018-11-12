package com.seeme.daniel.seepic.video_mvp;

import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.video_mvp.adapter.VideoPagerAdapter;
import com.seeme.daniel.seepic.video_mvp.video_total.VideoModel;
import com.seeme.daniel.seepic.video_mvp.video_total.VideoModelImpl;
import com.seeme.daniel.seepic.video_mvp.video_total.VideoPresenter;
import com.seeme.daniel.seepic.video_mvp.video_total.VideoView;
import com.seeme.daniel.seepic.view.CustomViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 10:11
 */
public class VideoFragment extends BaseMvpFragment<VideoModel, VideoView, VideoPresenter> implements VideoView {
    private static final String TAG = "VideoFragment";

    @BindView(R.id.SlidingTabLayout)
    SlidingTabLayout SlidingTabLayout;

    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    private VideoPagerAdapter mVideoPagerAdapter;

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(args);
        return videoFragment;
    }


    @Override
    public VideoModel createModel() {
        return new VideoModelImpl();
    }

    @Override
    public VideoView createView() {
        return this;
    }

    @Override
    public VideoPresenter createPresenter() {
        return new VideoPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        presenter.getVideoChannel(1);

    }

    @Override
    public void GetChannelSuccess(List<VideoChannelBean> channelBeans) {
        if (channelBeans == null) return;
        mVideoPagerAdapter = new VideoPagerAdapter(getChildFragmentManager(), channelBeans.get(0));
        mViewpager.setAdapter(mVideoPagerAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0, false);
        SlidingTabLayout.setViewPager(mViewpager);
    }

    @Override
    public void GetError(Throwable e) {

    }
}
