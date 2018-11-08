package com.seeme.daniel.seepic.mvp_news.new_detail;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author danielwang
 * @Description: 获取新闻详情model
 * @date 2018/11/8 15:55
 */
public interface NewsDetailModel extends Model {

    Observable<List<NewsDetail>> getNewDetail(String id, String action, int pullStatus);

}
