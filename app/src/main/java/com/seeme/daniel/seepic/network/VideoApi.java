package com.seeme.daniel.seepic.network;

import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.entity.VideoDetailBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author danielwang
 * @Description: 获取视频
 * @date 2018/11/12 10:11
 */
public interface VideoApi {

    @GET("ifengvideoList")
    Observable<List<VideoChannelBean>> getVideoChannel(@Query("page") int page);

    @GET("ifengvideoList")
    Observable<List<VideoDetailBean>> getVideoDetail(@Query("page") int page,
                                                     @Query("listtype") String listtype,
                                                     @Query("typeid") String typeid);
}
