package com.seeme.daniel.seepic.mvp_news.news_article;

import com.seeme.daniel.seepic.entity.NewsArticleBean;
import com.seeme.daniel.seepic.network.ArticleApi;
import com.seeme.daniel.seepic.network.NewsApi;
import com.seeme.daniel.seepic.network.NewsHttpUtils;
import com.seeme.daniel.seepic.network.UrlConfig;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/9 16:55
 */
public class ArticleModelImpl implements ArticleModel {

    @Override
    public Observable<NewsArticleBean> getArticle(String uid) {
        NewsHttpUtils httpUtils = new NewsHttpUtils(UrlConfig.sIFengApi);
        Retrofit retrofit = httpUtils.getRetrofit();
        ArticleApi api = retrofit.create(ArticleApi.class);
        Observable<NewsArticleBean> newsArticle = api.getNewsArticleWithSub(uid);
        return newsArticle;
    }

    @Override
    public Observable<NewsArticleBean> getArticle(String url, String uid) {
        NewsHttpUtils httpUtils = new NewsHttpUtils(UrlConfig.sGetNewsArticleCmppApi);
        Retrofit retrofit = httpUtils.getRetrofit();
        ArticleApi api = retrofit.create(ArticleApi.class);
        Observable<NewsArticleBean> newsArticle = api.getNewsArticle(uid);
        return newsArticle;
    }
}
