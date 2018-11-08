package com.seeme.daniel.seepic.mvp_news.new_total;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.mvp_news.adapter.NewsAdapter;
import com.seeme.daniel.seepic.mvp_news.entity.Channel;
import com.seeme.daniel.seepic.utils.ToastUtils;
import com.seeme.daniel.seepic.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author danielwang
 * @Description: 新闻总页面
 * @date 2018/11/8 11:20
 */
public class NewsFragment extends BaseMvpFragment<NewModel, NewView, NewPresenter> implements NewView {

    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.iv_edit)
    ImageView mIvEdit;
    @BindView(R.id.SlidingTabLayout)
    SlidingTabLayout SlidingTabLayout;

    private NewsAdapter newsAdapter;

    private int selectedIndex;
    private String selectedChannel;

    private List<Channel> channels = new ArrayList<>();

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public NewModel createModel() {
        return new NewModelImpl();
    }

    @Override
    public NewView createView() {
        return this;
    }

    @Override
    public NewPresenter createPresenter() {
        return new NewPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                selectedIndex = position;
//                selectedChannel = channels.get(position).getChannelName();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {
        presenter.getChannels();

    }

    @Override
    public void GetChannelSuccess(List<Channel> channels) {
        newsAdapter = new NewsAdapter(getChildFragmentManager(), channels);
        mViewpager.setAdapter(newsAdapter);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setCurrentItem(0, false);
        SlidingTabLayout.setViewPager(mViewpager);
    }

    @Override
    public void GetChannelError() {
        ToastUtils.toast("获取频道失败");
    }
}
