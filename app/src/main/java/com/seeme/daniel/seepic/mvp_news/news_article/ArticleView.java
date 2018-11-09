package com.seeme.daniel.seepic.mvp_news.news_article;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.NewsArticleBean;
import com.seeme.daniel.seepic.entity.PhotoBean;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/9 17:15
 */
public interface ArticleView extends MyView {

    void GetArticleSuccess(NewsArticleBean articleBean);

    void GetArticleError(Throwable e);

}
