package com.seeme.daniel.seepic.mvp_news.new_detail;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.mvp_news.entity.Channel;
import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;

import java.util.List;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 15:57
 */
public interface NewDetailView extends MyView {

    /**
     * 加载顶部banner数据
     *
     * @param newsDetail
     */
    void loadBannerData(NewsDetail newsDetail);

    /**
     * 加载置顶新闻数据
     *
     * @param newsDetail
     */
    void loadTopNewsData(NewsDetail newsDetail);

    /**
     * 加载新闻数据
     *
     * @param itemBeanList
     */
    void loadData(List<NewsDetail.ItemBean> itemBeanList);

    /**
     * 加载更多新闻数据
     *
     * @param itemBeanList
     */
    void loadMoreData(List<NewsDetail.ItemBean> itemBeanList);

}
