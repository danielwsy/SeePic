package com.seeme.daniel.seepic.ui.news_mvp.new_detail;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.NewsDetail;

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
     * 下拉刷新新闻数据
     *
     * @param itemBeanList
     */
    void xPullMoreData(List<NewsDetail.ItemBean> itemBeanList);

    /**
     * 上拉加载更多新闻数据
     *
     * @param itemBeanList
     */
    void sPushMoreData(List<NewsDetail.ItemBean> itemBeanList);

}
