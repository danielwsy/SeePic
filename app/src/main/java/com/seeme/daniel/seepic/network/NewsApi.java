package com.seeme.daniel.seepic.network;

import android.support.v4.media.session.PlaybackStateCompat;

import com.seeme.daniel.seepic.mvp_news.entity.NewsDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author danielwang
 * @Description: 获取新闻详情
 * @date 2018/11/8 15:53
 */
public interface NewsApi {


    /**
     * 获取新闻详情
     *
     * @param id      频道ID值
     * @param action  用户操作方式
     *                1：下拉 down
     *                2：上拉 up
     *                3：默认 default
     * @param pullNum 操作次数 累加
     * @return
     */
    @GET("ClientNews")
    Observable<List<NewsDetail>> getNewsDetail(@Query("id") String id,
                                               @Query("action") String action,
                                               @Query("pullNum") int pullNum
    );
}
