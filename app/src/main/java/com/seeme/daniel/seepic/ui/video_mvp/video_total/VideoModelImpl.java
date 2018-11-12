package com.seeme.daniel.seepic.ui.video_mvp.video_total;

import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.network.NewsHttpUtils;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.network.VideoApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author danielwang
 * @Description: 获取视频信息实现类
 * @date 2018/11/12 10:36
 */
public class VideoModelImpl implements VideoModel {
    @Override
    public Observable<List<VideoChannelBean>> getVideoChannel(int page) {
        NewsHttpUtils httpUtils = new NewsHttpUtils(UrlConfig.sIFengApi);
        Retrofit retrofit = httpUtils.getRetrofit();
        VideoApi api = retrofit.create(VideoApi.class);
        Observable<List<VideoChannelBean>> channelBean = api.getVideoChannel(page);
        return channelBean;
    }
}
