package com.seeme.daniel.seepic.video_mvp.video_detail;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.entity.VideoDetailBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 12:21
 */
public interface VideoDetailModel extends Model {
    /**
     * 获取视频详情
     *
     * @param page
     * @param listtype
     * @param typeid
     * @return
     */
    Observable<List<VideoDetailBean>> getVideoDetail(int page, String listtype, String typeid);

}
