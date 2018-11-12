package com.seeme.daniel.seepic.video_mvp.video_detail;

import com.seeme.daniel.seepic.entity.VideoDetailBean;
import com.seeme.daniel.seepic.network.NewsHttpUtils;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.network.VideoApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 12:22
 */
public class VideoDetailModelImpl implements VideoDetailModel {
    @Override
    public Observable<List<VideoDetailBean>> getVideoDetail(int page, String listtype, String typeid) {
        NewsHttpUtils httpUtils = new NewsHttpUtils(UrlConfig.sIFengApi);
        Retrofit retrofit = httpUtils.getRetrofit();
        VideoApi api = retrofit.create(VideoApi.class);
        Observable<List<VideoDetailBean>> videoDetailBean = api.getVideoDetail(page, listtype, typeid);
        return videoDetailBean;
    }
}
