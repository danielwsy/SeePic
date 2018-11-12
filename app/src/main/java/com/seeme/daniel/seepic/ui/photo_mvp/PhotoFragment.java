package com.seeme.daniel.seepic.ui.photo_mvp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.R2;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.ui.photo_mvp.model.PhotoModel;
import com.seeme.daniel.seepic.ui.photo_mvp.model.PhotoModelImpl;
import com.seeme.daniel.seepic.ui.photo_mvp.presenter.PhotoPresenter;
import com.seeme.daniel.seepic.ui.photo_mvp.view.PhotoMyView;
import com.seeme.daniel.seepic.view.DividerItemDecoration;
import com.seeme.daniel.seepic.view.StaggeredRecycleViewAdapter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 11:06
 */
public class PhotoFragment extends BaseMvpFragment<PhotoModel, PhotoMyView, PhotoPresenter> implements PhotoMyView {

    private List<PhotoBean.DataBean> photolist = new ArrayList<PhotoBean.DataBean>();

    @BindView(R2.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private StaggeredRecycleViewAdapter mRecyclerViewAdapter;
    private String[] arr = {"长腿", "素颜", "气质", "诱惑", "唯美"};

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
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
    public int getContentView() {
        return R.layout.fragment_photo;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.cardview_shadow_end_color);
        mPullLoadMoreRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_blue_dark);

        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerViewAdapter = new StaggeredRecycleViewAdapter(mContext);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    public void initData() {
        presenter.getData(0, 20, "美女", "性感");
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
        }
    }

    @Override
    public void GetPhotoError(Throwable e) {
        Toast.makeText(mContext, "加载错误", Toast.LENGTH_SHORT).show();
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
}
