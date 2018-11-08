package com.seeme.daniel.seepic.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seeme.daniel.seepic.MainActivity;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.R2;
import com.seeme.daniel.seepic.base.BaseMvpActivity;
import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.network.UrlConfig;
import com.seeme.daniel.seepic.utils.ImageLoaderUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 14:06
 */
public class SplashActivity extends BaseMvpActivity {

    /**
     * 底部动画
     */
    @BindView(R2.id.gifImageView)
    GifImageView gifImageView;

    /**
     * 壁纸
     */
    @BindView(R2.id.iv_ad)
    ImageView ivAd;
    @BindView(R2.id.ll_bottom)
    RelativeLayout llBottom;

    /**
     * 跳转按钮
     */
    @BindView(R2.id.tv_skip)
    TextView tvSkip;
    @BindView(R2.id.fl_skip)
    FrameLayout flSkip;

    /**
     * 点击跳转
     */
    @OnClick(R2.id.fl_skip)
    public void onViewClicked() {
        toMain();
    }

    private CompositeDisposable mCompositeDisposable;

    private void toMain() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public Model createModel() {
        return null;
    }

    @Override
    public MyView createView() {
        return null;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        final GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.setLoopCount(1);
        gifImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                gifDrawable.start();
            }
        }, 100);
        ImageLoaderUtil.LoadImage(this, UrlConfig.SPLASH_URL, ivAd);
    }

    @Override
    public void initData() {
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(countDown(3).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                tvSkip.setText("跳过 4");
            }
        }).subscribeWith(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                tvSkip.setText("跳过 " + (integer + 1));
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                toMain();
            }
        }));

    }

    public Observable<Integer> countDown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(@NonNull Long aLong) throws Exception {
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime + 1);
    }
}
