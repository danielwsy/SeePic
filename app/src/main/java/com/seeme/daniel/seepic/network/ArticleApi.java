package com.seeme.daniel.seepic.network;

import com.seeme.daniel.seepic.entity.NewsArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author danielwang
 * @Description: 获取具体文章
 * @date 2018/11/9 16:51
 */
public interface ArticleApi {

    @GET("api_vampire_article_detail")
    Observable<NewsArticleBean> getNewsArticleWithSub(@Query("aid") String aid);

    @GET("ipadtestdoc")
    Observable<NewsArticleBean> getNewsArticle(@Query("aid") String aid);
}
