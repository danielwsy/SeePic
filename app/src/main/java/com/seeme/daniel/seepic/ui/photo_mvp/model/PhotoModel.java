package com.seeme.daniel.seepic.ui.photo_mvp.model;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.entity.PhotoBean;

import io.reactivex.Observable;


/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 16:26
 */
public interface PhotoModel extends Model {

    /**
     * 从网络获取数据
     *
     * @return
     */
    Observable<PhotoBean> getDataFromNet(int page, int number, String type1, String type2);

}
