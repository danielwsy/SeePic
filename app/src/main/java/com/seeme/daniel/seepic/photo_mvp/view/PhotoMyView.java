package com.seeme.daniel.seepic.photo_mvp.view;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.PhotoBean;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:24
 */
public interface PhotoMyView extends MyView {
    void GetPhotoSuccess(PhotoBean photoBean);

    void GetPhotoError(Throwable e);
}
