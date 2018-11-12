package com.seeme.daniel.seepic.ui.video_mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.ui.video_mvp.VideoDetailFragment;

/**
 * @author danielwang
 * @Description: 视频页adapter
 * @date 2018/11/12 11:11
 */
public class VideoPagerAdapter extends FragmentStatePagerAdapter {

    private VideoChannelBean videoChannelBean;

    public VideoPagerAdapter(FragmentManager fm, VideoChannelBean channelBean) {
        super(fm);
        videoChannelBean = channelBean;
    }


    @Override
    public Fragment getItem(int position) {
        return VideoDetailFragment.newInstance("clientvideo_" + videoChannelBean.getTypes().get(position).getId());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return videoChannelBean.getTypes().get(position).getName();
    }

    @Override
    public int getCount() {
        return videoChannelBean != null ? videoChannelBean.getTypes().size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
