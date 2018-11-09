package com.seeme.daniel.seepic.mvp_news.new_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.flyco.animation.SlideEnter.SlideRightEnter;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.seeme.daniel.seepic.HiApplication;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.base.BaseMvpFragment;
import com.seeme.daniel.seepic.mvp_news.adapter.NewsDetailAdapter;
import com.seeme.daniel.seepic.entity.NewsDetail;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.utils.ContextUtils;
import com.seeme.daniel.seepic.utils.ImageLoaderUtil;
import com.seeme.daniel.seepic.utils.NewsTypeUtils;
import com.seeme.daniel.seepic.view.CustomLoadMoreView;
import com.seeme.daniel.seepic.view.NewsDelPop;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

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

    private View view_Focus;//顶部banner
    private Banner mBanner;

    /**
     * 删除个别新闻
     */
    private NewsDelPop newsDelPop;
    private String channelid;
    private int position;

    /**
     * 新闻数据
     */
    private List<NewsDetail.ItemBean> beanList;
    /**
     * 顶部Banner数据
     */
    private List<NewsDetail.ItemBean> mBannerList;
    private NewsDetailAdapter detailAdapter;
    /**
     * 上拉加载
     */
    private int upPullNum = 1;

    /**
     * 下拉刷新
     */
    private int downPullNum = 1;
    private boolean isRemoveHeaderView = false;

    public static final String TAG = "NewsDetailFragment";

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
        initPtr();
        initRecy();
        initBanner();
        initDeletePop();
    }

    private void initDeletePop() {
        newsDelPop = new NewsDelPop(mContext)
                .alignCenter(false)
                .widthScale(0.95f)
                .showAnim(new SlideRightEnter())
                .dismissAnim(new SlideRightExit())
                .offset(-100, 0)
                .dimEnabled(true);

        newsDelPop.setClickListener(new NewsDelPop.onClickListener() {
            @Override
            public void onClick(int position) {
                newsDelPop.dismiss();
                detailAdapter.remove(position);
                showToast(0, false);
            }
        });
    }

    private void initBanner() {
        view_Focus = getView().inflate(mContext, R.layout.news_detail_headerview, null);
        mBanner = (Banner) view_Focus.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        ImageLoaderUtil.LoadImage(mContext, path, imageView);
                    }
                })
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT);

        //Banner点击监听
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mBannerList.size() < 1) return;
                bannerToRead(mBannerList.get(position));
            }
        });
    }

    private void initRecy() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailAdapter = new NewsDetailAdapter(beanList, mContext);
        mRecyclerView.setAdapter(detailAdapter);
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //上拉加载回调
        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getNewsDetails(channelid, UrlConfig.ACTION_UP, upPullNum);

            }
        }, mRecyclerView);

        //点击item监听
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NewsDetail.ItemBean itemBean = (NewsDetail.ItemBean) baseQuickAdapter.getItem(i);
                toRead(itemBean);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NewsDetail.ItemBean itemBean = (NewsDetail.ItemBean) baseQuickAdapter.getItem(i);
                switch (view.getId()) {
                    case R.id.iv_close:
                        view.getHeight();
                        int[] location = new int[2];
                        view.getLocationInWindow(location);
                        Log.i("JdDetailFragment", "点击的item的高度:" + view.getHeight() + "x值:" + location[0] + "y值" + location[1]);
                        if (itemBean.getStyle() == null) return;
                        if (ContextUtils.getSreenWidth(HiApplication.getContext()) - 50 - location[1] < ContextUtils.dip2px(HiApplication.getContext(), 80)) {
                            newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.TOP)
                                    .setBackReason(itemBean.getStyle().getBackreason(), true, i)
                                    .show();
                        } else {
                            newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.BOTTOM)
                                    .setBackReason(itemBean.getStyle().getBackreason(), false, i)
                                    .show();
                        }
                        break;
                }
            }
        });
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
                isRemoveHeaderView = true;
                Log.v("checkCanDoRefresh", channelid);
                presenter.getNewsDetails(channelid, UrlConfig.ACTION_DOWN, downPullNum);
            }
        });
    }

    private void bannerToRead(NewsDetail.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getType()) {
            case NewsTypeUtils.TYPE_DOC:
////                Intent intent = new Intent(getActivity(), ArticleReadActivity.class);
//                intent.putExtra("aid", itemBean.getDocumentId());
//                startActivity(intent);
                break;
            case NewsTypeUtils.TYPE_SLIDE:
//                ImageBrowseActivity.launch(getActivity(), itemBean);
                break;
            case NewsTypeUtils.TYPE_ADVERT:
//                AdvertActivity.launch(getActivity(), itemBean.getLink().getWeburl());
                break;
            case NewsTypeUtils.TYPE_PHVIDEO:
//                T("TYPE_PHVIDEO");
                break;
        }
    }

    private void toRead(NewsDetail.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getItemType()) {
            case NewsDetail.ItemBean.TYPE_DOC_TITLEIMG:
            case NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG:
//                Intent intent = new Intent(getActivity(), ArticleReadActivity.class);
//                intent.putExtra("aid", itemBean.getDocumentId());
//                startActivity(intent);
                break;
            case NewsDetail.ItemBean.TYPE_SLIDE:
//                ImageBrowseActivity.launch(getActivity(), itemBean);
                break;
            case NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG:
//                AdvertActivity.launch(getActivity(), itemBean.getLink().getWeburl());
                break;
            case NewsDetail.ItemBean.TYPE_PHVIDEO:
//                T("TYPE_PHVIDEO");
                break;
        }
    }

    @Override
    public void initData() {
        if (getArguments() == null) return;
        beanList = new ArrayList<>();
        mBannerList = new ArrayList<>();
        channelid = getArguments().getString("newsid");
        position = getArguments().getInt("position");
        presenter.getNewsDetails(channelid, UrlConfig.ACTION_DEFAULT, 1);
    }


    /**
     * 加载顶部Banner数据
     *
     * @param newsDetail
     */
    @Override
    public void loadBannerData(NewsDetail newsDetail) {
        List<String> mTitleList = new ArrayList<>();
        List<String> mUrlList = new ArrayList<>();
        mBannerList.clear();
        for (NewsDetail.ItemBean bean : newsDetail.getItem()) {
            if (!TextUtils.isEmpty(bean.getThumbnail())) {
                mTitleList.add(bean.getTitle());
                mBannerList.add(bean);
                mUrlList.add(bean.getThumbnail());
            }
        }
        if (mUrlList.size() > 0) {
            mBanner.setImages(mUrlList);
            mBanner.setBannerTitles(mTitleList);
            mBanner.start();
            if (detailAdapter.getHeaderLayoutCount() < 1) {
                detailAdapter.addHeaderView(view_Focus);
            }
        }
    }

    /**
     * 下拉加载
     *
     * @param itemBeanList
     */
    @Override
    public void xPullMoreData(List<NewsDetail.ItemBean> itemBeanList) {
        if (itemBeanList == null || itemBeanList.size() == 0) {
            mPtrFrameLayout.refreshComplete();
        } else {
            downPullNum++;
            if (isRemoveHeaderView) {
                detailAdapter.removeAllHeaderView();
            }
            detailAdapter.setNewData(itemBeanList);
            hideLoadingDialog();
            showToast(itemBeanList.size(), true);
            mPtrFrameLayout.refreshComplete();
        }

    }

    /**
     * 上拉刷新
     *
     * @param itemBeanList
     */
    @Override
    public void sPushMoreData(List<NewsDetail.ItemBean> itemBeanList) {
        if (itemBeanList == null || itemBeanList.size() == 0) {
            detailAdapter.loadMoreFail();
        } else {
            upPullNum++;
            detailAdapter.addData(itemBeanList);
            detailAdapter.loadMoreComplete();
        }

    }

    private void showToast(int size, boolean isRefresh) {
        if (isRefresh) {
            mTvToast.setText(String.format(getResources().getString(R.string.news_toast), size + ""));
        } else {
            mTvToast.setText("将为你减少此类内容");
        }
        mRlTopToast.setVisibility(View.VISIBLE);
        ViewAnimator.animate(mRlTopToast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        ViewAnimator.animate(mRlTopToast)
                                .bounceOut()
                                .duration(2000)
                                .start();
                    }
                });
    }
}
