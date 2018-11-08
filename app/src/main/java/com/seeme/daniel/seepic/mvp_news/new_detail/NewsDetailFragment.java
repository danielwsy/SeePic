package com.seeme.daniel.seepic.mvp_news.new_detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;
import com.seeme.daniel.seepic.mvp_news.new_total.NewPresenter;
import com.seeme.daniel.seepic.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danielwang
 * @Description: 每个栏目的新闻
 * @date 2018/11/8 15:07
 */
public class NewsDetailFragment extends BaseMvpFragment<NewsDetailModel, NewDetailView, NewDetailPresenter> implements NewDetailView {

    /**
     * 新闻实体
     */
    private List<NewsDetail.ItemBean> mBannerList;

    /**
     * 频道Id
     */
    private String channelid;
    private int position;

    public static NewsDetailFragment newInstance(String newsid, int position) {
        Bundle args = new Bundle();
        args.putString("newsid", newsid);
        args.putInt("position", position);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public NewsDetailModel createModel() {
        return new NewsDetailModelImpl();
    }

    @Override
    public NewDetailView createView() {
        return this;
    }

    @Override
    public NewDetailPresenter createPresenter() {
        return new NewDetailPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        mBannerList = new ArrayList<>();
        channelid = getArguments().getString("newsid");
        position = getArguments().getInt("position");
        presenter.getNewsDetails(channelid, "default", 1);
    }


    @Override
    public void loadBannerData(NewsDetail newsDetail) {
        if (newsDetail == null) return;
        for (NewsDetail.ItemBean bean : newsDetail.getItem()) {
            if (!TextUtils.isEmpty(bean.getThumbnail())) {
                mBannerList.add(bean);
            }
        }
    }

    @Override
    public void loadTopNewsData(NewsDetail newsDetail) {

    }

    @Override
    public void loadData(List<NewsDetail.ItemBean> itemBeanList) {

    }

    @Override
    public void loadMoreData(List<NewsDetail.ItemBean> itemBeanList) {

    }
}
