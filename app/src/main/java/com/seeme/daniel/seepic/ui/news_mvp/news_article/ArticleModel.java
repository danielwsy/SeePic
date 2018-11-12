package com.seeme.daniel.seepic.ui.news_mvp.news_article;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.entity.NewsArticleBean;

import io.reactivex.Observable;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/9 16:54
 */
public interface ArticleModel  extends Model{

    Observable<NewsArticleBean> getArticle(String uid);

    Observable<NewsArticleBean> getArticle(String url, String uid);
}
