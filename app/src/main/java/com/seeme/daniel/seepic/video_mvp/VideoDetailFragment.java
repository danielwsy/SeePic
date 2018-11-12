package com.seeme.daniel.seepic.video_mvp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.entity.VideoDetailBean;
import com.seeme.daniel.seepic.video_mvp.adapter.VideoDetailAdapter;
import com.seeme.daniel.seepic.video_mvp.video_detail.VideoDetailModel;
import com.seeme.daniel.seepic.video_mvp.video_detail.VideoDetailModelImpl;
import com.seeme.daniel.seepic.video_mvp.video_detail.VideoDetailPresenter;
import com.seeme.daniel.seepic.video_mvp.video_detail.VideoDetailView;
import com.seeme.daniel.seepic.view.CustomLoadMoreView;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 11:14
 */
public class VideoDetailFragment extends BaseMvpFragment<VideoDetailModel, VideoDetailView, VideoDetailPresenter> implements VideoDetailView {
    public static final String TYPEID = "typeId";
    private String typeId;
    private int pageNum = 1;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;
    private VideoDetailBean videoDetailBean;
    private VideoDetailAdapter detailAdapter;

    public static VideoDetailFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        args.putCharSequence(TYPEID, typeId);
        VideoDetailFragment fragment = new VideoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public VideoDetailModel createModel() {
        return new VideoDetailModelImpl();
    }

    @Override
    public VideoDetailView createView() {
        return this;
    }

    @Override
    public VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        initPtr();
        initRec();
    }

    private void initRec() {
        videoDetailBean = new VideoDetailBean();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        detailAdapter = new VideoDetailAdapter(mContext, R.layout.item_detail_video, videoDetailBean.getItem());
        mRecyclerView.setAdapter(detailAdapter);
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getVedeoDetail(pageNum, "list", typeId);
            }
        }, mRecyclerView);
    }

    private void initPtr() {
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getVedeoDetail(pageNum, "list", typeId);
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() == null) return;

        typeId = getArguments().getString(TYPEID);
        if (presenter != null) {
            presenter.getVedeoDetail(pageNum, "list", typeId);
        }
    }

    @Override
    public void GetVideoDetailSuccess(List<VideoDetailBean> detailBeans) {
        if (detailBeans == null) {
            return;
        }
        pageNum++;
        if (detailAdapter == null) return;
        detailAdapter.setNewData(detailBeans.get(0).getItem());
        mPtrFrameLayout.refreshComplete();
        hideLoadingDialog();
    }

    @Override
    public void loadMoreVideoDetails(List<VideoDetailBean> detailBeans) {
        if (detailBeans == null) {
            detailAdapter.loadMoreEnd();
            return;
        }
        pageNum++;
        detailAdapter.addData(detailBeans.get(0).getItem());
        detailAdapter.loadMoreComplete();
    }

    @Override
    public void GetError(Throwable e) {

    }
}
