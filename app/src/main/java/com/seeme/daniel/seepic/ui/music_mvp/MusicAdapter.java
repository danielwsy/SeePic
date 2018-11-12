package com.seeme.daniel.seepic.ui.music_mvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 17:09
 */
public class MusicAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;

    public MusicAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MyMusicFragment.newInstance();
        } else {
            return RankListFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
