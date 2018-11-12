package com.seeme.daniel.seepic.ui.video_mvp.video_total;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.VideoChannelBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 10:32
 */
public class VideoPresenter extends BasePresenter<VideoModel, VideoView> {


    public void getVideoChannel(int page) {
        Observable<List<VideoChannelBean>> videoChannels = model.getVideoChannel(page);
        videoChannels.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<VideoChannelBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<VideoChannelBean> channelBeans) {
                        if(getView()!= null){
                            getView().GetChannelSuccess(channelBeans);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().GetError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }





    @Override
    protected void onViewDestroy() {

    }
}
