package com.seeme.daniel.seepic.mvp_Photo.model;

import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.network.PhotoApi;

import io.reactivex.Observable;
import io.reactivex.internal.schedulers.IoScheduler;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:47
 */
public class PhotoModelImpl implements PhotoModel {

    @Override
    public Observable<PhotoBean> getDataFromNet(int page, int number, String type1, String type2) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://image.baidu.com/channel/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(new IoScheduler()))
                .build();
        PhotoApi api = retrofit.create(PhotoApi.class);
        return api.LoadPhoto_RxJava(page, number, type1, type2);
    }
}
