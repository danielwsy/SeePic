package com.seeme.daniel.seepic.photo_mvp.presenter;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.photo_mvp.model.PhotoModel;
import com.seeme.daniel.seepic.photo_mvp.view.PhotoMyView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:27
 */
public class PhotoPresenter extends BasePresenter<PhotoModel, PhotoMyView> {
    public void getData(int page, int number, String type1, String type2) {
        if (model != null) {
            Observable<PhotoBean> observable = model.getDataFromNet(page, number, type1, type2);
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<PhotoBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(PhotoBean photoBean) {
                            getView().GetPhotoSuccess(photoBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().GetPhotoError(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }


    @Override
    protected void onViewDestroy() {

    }
}
