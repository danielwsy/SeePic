package com.seeme.daniel.seepic.mvp_news.new_detail;

import android.util.Log;

import com.airbnb.lottie.L;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;
import com.seeme.daniel.seepic.network.NewsApi;
import com.seeme.daniel.seepic.network.RxSchedulers;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.utils.LogUtils;
import com.seeme.daniel.seepic.utils.NewsTypeUtils;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author danielwang
 * @Description: 获取新闻详情
 * @date 2018/11/8 15:59
 */
public class NewDetailPresenter extends BasePresenter<NewsDetailModel, NewDetailView> {

    public static final String TAG = "NewDetailPresenter";

    public void getNewsDetails(String id, final String action, int pullStatus) {
        if (model != null) {
            Observable<List<NewsDetail>> observable = model.getNewDetail(id, action, pullStatus);

            observable.compose(RxSchedulers.<List<NewsDetail>>applySchedulers())
                    .map(new Function<List<NewsDetail>, NewsDetail>() {
                        @Override
                        public NewsDetail apply(List<NewsDetail> newsDetails) throws Exception {

                            for (NewsDetail newsDetail : newsDetails) {
                                if (NewsTypeUtils.isBannerNews(newsDetail)) {
                                    getView().loadBannerData(newsDetail);
                                }
                                if (NewsTypeUtils.isTopNews(newsDetail)) {
                                    getView().loadTopNewsData(newsDetail);
                                }
                            }
                            return newsDetails.get(0);
                        }
                    })
                    .map(new Function<NewsDetail, List<NewsDetail.ItemBean>>() {
                        @Override
                        public List<NewsDetail.ItemBean> apply(NewsDetail newsDetail) throws Exception {
                            Iterator<NewsDetail.ItemBean> iterator = newsDetail.getItem().iterator();
                            try {
                                while (iterator.hasNext()) {
                                    NewsDetail.ItemBean bean = iterator.next();
                                    String type = bean.getType();
                                    if (NewsTypeUtils.TYPE_DOC.equals(type)) {
                                        setType_DOC(bean);
                                    } else if (NewsTypeUtils.TYPE_ADVERT.equals(type)) {
                                        setType_ADVERT(bean);

                                    } else if (NewsTypeUtils.TYPE_SLIDE.equals(type)) {
                                        setType_SLIDE(bean);
                                    } else if (NewsTypeUtils.TYPE_PHVIDEO.equals(type)) {
                                        setType_PHVIDEO(bean);
                                    } else {
                                        iterator.remove();
                                    }
                                }
                            } catch (Exception e) {
                                iterator.remove();
                            }


                            return null;
                        }
                    })
                    .subscribe(new Observer<List<NewsDetail.ItemBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<NewsDetail.ItemBean> itemBeans) {

                            if (!action.equals(UrlConfig.ACTION_UP)) {
                                getView().loadData(itemBeans);
                            } else {
                                getView().loadMoreData(itemBeans);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                            Log.i(TAG, "onFail: " + e.getMessage().toString());
                            if (!action.equals(UrlConfig.ACTION_UP)) {
                                getView().loadData(null);
                            } else {
                                getView().loadMoreData(null);
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

//
//            observable.observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Observer<List<NewsDetail>>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            LogUtils.showLogs(TAG, "onSubscribe");
//                        }
//
//                        @Override
//                        public void onNext(List<NewsDetail> newsDetails) {
//                            if (newsDetails != null) {
//
//                            }
//                            LogUtils.showLogs(TAG, "onNext");
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            LogUtils.showLogs(TAG, "onError");
//
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            LogUtils.showLogs(TAG, "onComplete");
//
//                        }
//                    });
        }
    }

    private void setType_PHVIDEO(NewsDetail.ItemBean bean) {
        bean.itemType = NewsDetail.ItemBean.TYPE_PHVIDEO;
    }

    private void setType_SLIDE(NewsDetail.ItemBean bean) {
        if (bean.getLink().getType().equals("doc")) {
            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_SLIDEIMG)) {
                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
            } else {
                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
            }
        } else {
            bean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
        }
    }

    private void setType_ADVERT(NewsDetail.ItemBean bean) {
        if (bean.getStyle() != null) {
            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_TITLEIMG)) {
                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
            } else if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_SLIDEIMG)) {
                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
            } else {
                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
            }
        }
    }

    private void setType_DOC(NewsDetail.ItemBean bean) {
        if (bean.getStyle().getView() != null) {
            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_TITLEIMG)) {
                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
            } else {
                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
            }
        }
    }


    @Override
    protected void onViewDestroy() {

    }
}
