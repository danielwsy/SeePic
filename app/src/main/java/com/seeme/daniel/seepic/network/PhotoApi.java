package com.seeme.daniel.seepic.network;


import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.mvp_Photo.model.PhotoModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by QZS
 */

public interface PhotoApi {

    //baseUrl
    String API_SERVER_URL = "https://way.jd.com/jisuapi/";

    //图片
    @GET("listjson")
    Observable<PhotoBean> LoadPhoto_RxJava(@Query("pn") int page, @Query("rn") int number, @Query("tag1") String type1, @Query("tag2") String type2);
}
