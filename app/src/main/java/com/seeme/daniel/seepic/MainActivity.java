package com.seeme.daniel.seepic;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.seeme.daniel.seepic.base.BaseMvpActivity;
import com.seeme.daniel.seepic.base.SupportFragment;
import com.seeme.daniel.seepic.mvp_news.new_total.NewsFragment;
import com.seeme.daniel.seepic.photo_mvp.PhotoFragment;
import com.seeme.daniel.seepic.photo_mvp.model.PhotoModel;
import com.seeme.daniel.seepic.photo_mvp.presenter.PhotoPresenter;
import com.seeme.daniel.seepic.photo_mvp.view.PhotoMyView;
import com.seeme.daniel.seepic.video_mvp.VideoFragment;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class MainActivity extends BaseMvpActivity<PhotoModel, PhotoMyView, PhotoPresenter> {

    /**
     * 底部控制栏
     */
    @BindView(R2.id.rg_bottoms)
    RadioGroup radioGroup;
    @BindView(R2.id.rb_news)
    RadioButton rbNews;
    @BindView(R2.id.rb_videos)
    RadioButton rbVideos;
    @BindView(R2.id.rb_gilrs)
    RadioButton rbGirls;
    @BindView(R2.id.rb_music)
    RadioButton rbMusic;
    /***
     * 顶部控制栏
     */
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_setting)
    TextView tvSet;

    /**
     * 中间内容
     */
    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    private SupportFragment[] mFragments = new SupportFragment[4];

    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PhotoModel createModel() {
        return null;
    }

    @Override
    public PhotoMyView createView() {
        return null;
    }


    @Override
    public PhotoPresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[0] = NewsFragment.newInstance();
            mFragments[1] = VideoFragment.newInstance();
            mFragments[2] = PhotoFragment.newInstance();
            mFragments[3] = VideoFragment.newInstance();
            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            mFragments[0] = findFragment(NewsFragment.class);
            mFragments[1] = findFragment(VideoFragment.class);
            mFragments[2] = findFragment(NewsFragment.class);
            mFragments[3] = findFragment(VideoFragment.class);
        }
    }

    @Override
    public void initData() {
        rbNews.setChecked(true);
        // RadioGroup 和Fragment建立连接
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_news:
                        getSupportDelegate().showHideFragment(mFragments[0]);//show一个Fragment,hide其他同栈所有Fragment
                        break;
                    case R.id.rb_videos:
                        getSupportDelegate().showHideFragment(mFragments[1]);
                        break;
                    case R.id.rb_gilrs:
                        getSupportDelegate().showHideFragment(mFragments[2]);
                        break;
                    case R.id.rb_music:
                        getSupportDelegate().showHideFragment(mFragments[3]);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}



