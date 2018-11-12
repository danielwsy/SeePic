package com.seeme.daniel.seepic.ui.news_mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.seeme.daniel.seepic.entity.Channel;
import com.seeme.daniel.seepic.ui.news_mvp.new_detail.NewsDetailFragment;

import java.util.List;

/**
 * @author danielwang
 * @Description:  新闻总页面adapter
 * @date 2018/11/8 14:52
 */
public class NewsAdapter extends FragmentStatePagerAdapter {

    private List<Channel> mChannels;

    public NewsAdapter(FragmentManager fm, List<Channel> channels) {
        super(fm);
        this.mChannels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsDetailFragment.newInstance(mChannels.get(position).getChannelId(), position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).getChannelName();
    }

    @Override
    public int getCount() {
        return mChannels != null ? mChannels.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
