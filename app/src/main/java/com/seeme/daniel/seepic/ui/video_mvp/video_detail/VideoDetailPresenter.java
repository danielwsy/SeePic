package com.seeme.daniel.seepic.ui.video_mvp.video_detail;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.VideoDetailBean;
import com.seeme.daniel.seepic.network.RxSchedulers;
import com.seeme.daniel.seepic.utils.LogUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 12:23
 */
public class VideoDetailPresenter extends BasePresenter<VideoDetailModel, VideoDetailView> {

    public static final String TAG = "VideoDetailPresenter";

    public void getVedeoDetail(final int page, String listtype, String typeid) {
        Observable<List<VideoDetailBean>> videoDetailBean = model.getVideoDetail(page, listtype, typeid);
        videoDetailBean.compose(RxSchedulers.<List<VideoDetailBean>>applySchedulers())
                .subscribe(new Observer<List<VideoDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<VideoDetailBean> videoDetailBean) {
                        if (getView() == null) return;
                        if (page > 1) {
                            getView().loadMoreVideoDetails(videoDetailBean);
                        } else {
                            getView().GetVideoDetailSuccess(videoDetailBean);
                        }
                        LogUtils.showLogs(TAG, "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.showLogs(TAG, "onError");
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
