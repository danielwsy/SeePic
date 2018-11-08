package com.seeme.daniel.seepic.network;

import android.content.Context;

import com.seeme.daniel.seepic.HiApplication;

import java.util.concurrent.TimeUnit;

import io.reactivex.internal.schedulers.IoScheduler;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:50
 */
public class BaseHttpUtils {
    public static final int CACHE_SIZE = 4 * 1024 * 1024; //cache size
    public static final int NETWORK_TIME_OUT = 60; //network time out

    private static OkHttpClient okHttpClient;
    private String mServerUrl;
    private Context mContext;
    private Retrofit mRetrofit;

    public BaseHttpUtils(String serverUrl) {
        mContext = HiApplication.getContext();
        mServerUrl = serverUrl;
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
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(new IoScheduler()));
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
        builder.cache(new Cache(context.getCacheDir(), CACHE_SIZE));
        builder.connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS);
        return builder;
    }


}
