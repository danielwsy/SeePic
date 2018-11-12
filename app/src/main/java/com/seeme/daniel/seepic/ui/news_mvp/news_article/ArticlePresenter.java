package com.seeme.daniel.seepic.ui.news_mvp.news_article;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.NewsArticleBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/9 17:15
 */
public class ArticlePresenter extends BasePresenter<ArticleModel, ArticleView> {

    public void getNewsArticle(String uid) {
        if (model == null) return;
        Observable<NewsArticleBean> observable = null;
        if (uid.startsWith("sub")) {
            observable = model.getArticle(uid);
        } else {
            observable = model.getArticle("", uid);
        }
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NewsArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsArticleBean newsArticleBean) {
                        getView().GetArticleSuccess(newsArticleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().GetArticleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    @Override
    protected void onViewDestroy() {

    }
}
