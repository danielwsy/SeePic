package com.seeme.daniel.seepic.ui.news_mvp.new_detail;

import com.seeme.daniel.seepic.entity.NewsDetail;
import com.seeme.daniel.seepic.network.NewsApi;
import com.seeme.daniel.seepic.network.NewsHttpUtils;
import com.seeme.daniel.seepic.network.UrlConfig;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 15:57
 */
public class NewsDetailModelImpl implements NewsDetailModel {
    @Override
    public Observable<List<NewsDetail>> getNewDetail(String id, String action, int pullStatus) {
        NewsHttpUtils httpUtils = new NewsHttpUtils(UrlConfig.sIFengApi);
        Retrofit retrofit = httpUtils.getRetrofit();
        NewsApi api = retrofit.create(NewsApi.class);
        Observable<List<NewsDetail>> NewsDetails = api.getNewsDetail(id, action, pullStatus);
        return NewsDetails;
    }
}
