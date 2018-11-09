package com.seeme.daniel.seepic.mvp_news.new_detail;

import android.util.Log;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.NewsDetail;
import com.seeme.daniel.seepic.network.RxSchedulers;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.utils.NewsTypeUtils;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

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
                            }
                            return newsDetails.get(0);
                        }
                    })
                    .map(new Function<NewsDetail, List<NewsDetail.ItemBean>>() {
                        @Override
                        public List<NewsDetail.ItemBean> apply(NewsDetail newsDetail) throws Exception {
                            Iterator<NewsDetail.ItemBean> iterator = newsDetail.getItem().iterator();
                            while (iterator.hasNext()) {
                                try {
                                    NewsDetail.ItemBean bean = iterator.next();
                                    if (bean.getType().equals(NewsTypeUtils.TYPE_DOC)) {
                                        if (bean.getStyle().getView() != null) {
                                            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_TITLEIMG)) {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                            } else {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                            }
                                        }
                                    } else if (bean.getType().equals(NewsTypeUtils.TYPE_ADVERT)) {
                                        if (bean.getStyle() != null) {
                                            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_TITLEIMG)) {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                            } else if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_SLIDEIMG)) {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                            } else {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
                                            }
                                        } else {
                                            iterator.remove();
                                        }
                                    } else if (bean.getType().equals(NewsTypeUtils.TYPE_SLIDE)) {
                                        if (bean.getLink().getType().equals(NewsTypeUtils.TYPE_DOC)) {
                                            if (bean.getStyle().getView().equals(NewsTypeUtils.VIEW_SLIDEIMG)) {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                            } else {
                                                bean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                            }
                                        } else {
                                            bean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
                                        }
                                    } else if (bean.getType().equals(NewsTypeUtils.TYPE_PHVIDEO)) {
                                        bean.itemType = NewsDetail.ItemBean.TYPE_PHVIDEO;
                                    } else {
                                        // TODO: 2018/11/9 类型太多了，将其他的类型干掉
                                        iterator.remove();
                                    }
                                } catch (Exception e) {
                                    iterator.remove();
                                    e.printStackTrace();
                                }
                            }
                            return newsDetail.getItem();
                        }
                    })
                    .subscribe(new Observer<List<NewsDetail.ItemBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<NewsDetail.ItemBean> itemBeans) {
                            if (!action.equals(UrlConfig.ACTION_UP)) {
                                getView().xPullMoreData(itemBeans);
                            } else {
                                getView().sPushMoreData(itemBeans);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                            Log.i(TAG, "onFail: " + e.getMessage().toString());
                            if (!action.equals(UrlConfig.ACTION_UP)) {
                                if (getView() == null) return;
                                getView().xPullMoreData(null);
                            } else {
                                if (getView() == null) return;
                                getView().sPushMoreData(null);
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }

    @Override
    protected void onViewDestroy() {

    }
}
