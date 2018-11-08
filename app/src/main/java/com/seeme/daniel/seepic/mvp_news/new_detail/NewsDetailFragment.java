package com.seeme.daniel.seepic.mvp_news.new_detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.mvp_news.adapter.NewDetailAdapter;
import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;
import com.seeme.daniel.seepic.mvp_news.new_total.NewPresenter;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.utils.LogUtils;
import com.seeme.daniel.seepic.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author danielwang
 * @Description: 每个栏目的新闻
 * @date 2018/11/8 15:07
 */
public class NewsDetailFragment extends BaseMvpFragment<NewsDetailModel, NewDetailView, NewDetailPresenter> implements NewDetailView {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrFrameLayout mPtrFrameLayout;
    @BindView(R.id.tv_toast)
    TextView mTvToast;
    @BindView(R.id.rl_top_toast)
    RelativeLayout mRlTopToast;
    /**
     * 新闻实体
     */
    private List<NewsDetail.ItemBean> mBannerList;

    private NewDetailAdapter adapter;

    private int upPullNum = 1;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(adapter != null){
            adapter.setEnableLoadMore(true);
            adapter.setLoadMoreView(new CustomLoadMoreView());
            adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {

                    presenter.getNewsDetails(channelid, UrlConfig.ACTION_UP, upPullNum);

                }
            }, mRecyclerView);
        }


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
        if(mBannerList != null){
            adapter = new NewDetailAdapter(mContext, mBannerList);
            mRecyclerView.setAdapter(adapter);
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
