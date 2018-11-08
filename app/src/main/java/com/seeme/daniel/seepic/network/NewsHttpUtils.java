package com.seeme.daniel.seepic.network;

import android.content.Context;

import com.seeme.daniel.seepic.HiApplication;
import com.seeme.daniel.seepic.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.internal.schedulers.IoScheduler;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 16:44
 */
public class NewsHttpUtils {
    public static final int CACHE_SIZE = 4 * 1024 * 1024; //cache size
    public static final int NETWORK_TIME_OUT = 60; //network time out

    private static OkHttpClient okHttpClient;
    private String mServerUrl;
    private Context mContext;
    private Retrofit mRetrofit;

    public NewsHttpUtils(String url) {
        mContext = HiApplication.getContext();
        mServerUrl = url;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (BaseHttpUtils.class) {
                if (mRetrofit == null) {
                    mRetrofit = initDefault();
                }
            }
        }
        return mRetrofit;
    }


    /**
     * Retrofit 和 OKHttp进行结合
     *
     * @return
     */
    private Retrofit initDefault() {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (okHttpClient == null) {
            OkHttpClient.Builder okBuilder = buildDefalutClient(mContext);
            okHttpClient = okBuilder.build();
        }
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(mServerUrl);
        return builder.build();
    }

    /**
     * 初始化OKHttp
     *
     * @param context
     * @return
     */
    private static OkHttpClient.Builder buildDefalutClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(sQueryParameterInterceptor);
        builder.cache(new Cache(context.getCacheDir(), CACHE_SIZE));
        builder.connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        return builder;
    }

    /**
     * 公共参数
     */
    public static final Interceptor sQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("uid", Constants.uid)
                    .addQueryParameter("devid", Constants.uid)
                    .addQueryParameter("proid", "ifengnews")
                    .addQueryParameter("vt", "5")
                    .addQueryParameter("publishid", "6103")
                    .addQueryParameter("screen", "1080x1920")
                    .addQueryParameter("df", "androidphone")
                    .addQueryParameter("os", "android_22")
                    .addQueryParameter("nw", "wifi")
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
    };

}
