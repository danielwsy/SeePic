package com.seeme.daniel.seepic;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.seeme.daniel.seepic.base.BaseMvpActivity;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.mvp_Photo.model.PhotoModel;
import com.seeme.daniel.seepic.mvp_Photo.model.PhotoModelImpl;
import com.seeme.daniel.seepic.mvp_Photo.presenter.PhotoPresenter;
import com.seeme.daniel.seepic.mvp_Photo.view.PhotoMyView;
import com.seeme.daniel.seepic.view.DividerItemDecoration;
import com.seeme.daniel.seepic.view.StaggeredRecycleViewAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseMvpActivity<PhotoModel, PhotoMyView, PhotoPresenter> implements PhotoMyView {

    private List<PhotoBean.DataBean> photolist = new ArrayList<PhotoBean.DataBean>();

    Toolbar toolbar;
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;


    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private StaggeredRecycleViewAdapter mRecyclerViewAdapter;
    private String[] arr = {"长腿", "素颜", "气质", "诱惑", "唯美"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToobar();
        initView();
        initData();
    }

    private void initToobar() {
//        setSupportActionBar(toolbar);
//        final ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
//            ab.setTitle("图片");
//            ab.setDisplayHomeAsUpEnabled(true);
//        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        mPullLoadMoreRecyclerView = findViewById(R.id.pullLoadMoreRecyclerView);

        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.cardview_shadow_end_color);
        mPullLoadMoreRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_blue_dark);


        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerViewAdapter = new StaggeredRecycleViewAdapter(MainActivity.this);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {

        }

        @Override
        public void onLoadMore() {
            if (mCount < 3) {
                presenter.getData(0, 20, "美女", arr[mCount]);
                mCount = mCount + 1;
            } else {
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                Snackbar.make(mRecyclerView.getRootView(), "无数据加载...", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void initData() {
        presenter.getData(0, 20, "美女", "性感");
    }

    @Override
    public PhotoModel createModel() {
        return new PhotoModelImpl();
    }

    @Override
    public PhotoMyView createView() {
        return this;
    }

    @Override
    public PhotoPresenter createPresenter() {
        return new PhotoPresenter();
    }

    @Override
    public void GetPhotoSuccess(PhotoBean photoBean) {

        if (photoBean.getData() != null) {

            try {
                photolist.clear();
                photolist.addAll(photoBean.getData());
                mRecyclerViewAdapter.addAllNewsData(photolist);
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //Snackbar.make(view,photoModel.getMsg(),Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void GetPhotoError(Throwable e) {
        Toast.makeText(this, "加载错误", Toast.LENGTH_SHORT).show();
    }
}
