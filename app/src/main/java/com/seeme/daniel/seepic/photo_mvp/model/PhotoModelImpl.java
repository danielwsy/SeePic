package com.seeme.daniel.seepic.photo_mvp.model;

import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.network.BaseHttpUtils;
import com.seeme.daniel.seepic.network.PhotoApi;
import com.seeme.daniel.seepic.network.UrlConfig;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:47
 */
public class PhotoModelImpl implements PhotoModel {

    @Override
    public Observable<PhotoBean> getDataFromNet(int page, int number, String type1, String type2) {
        BaseHttpUtils httpUtils = new BaseHttpUtils(UrlConfig.URL_PHOTO);
        Retrofit retrofit = httpUtils.getRetrofit();
        PhotoApi api = retrofit.create(PhotoApi.class);
        Observable<PhotoBean> photoBean = api.LoadPhoto_RxJava(page, number, type1, type2);
        return photoBean;
    }
}
