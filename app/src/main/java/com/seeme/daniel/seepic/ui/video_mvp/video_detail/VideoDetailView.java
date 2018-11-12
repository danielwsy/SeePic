package com.seeme.daniel.seepic.ui.video_mvp.video_detail;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.VideoDetailBean;

import java.util.List;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 12:24
 */
public interface VideoDetailView extends MyView {

    /**
     * 获取视频详情
     *
     * @param detailBeans
     */
    void GetVideoDetailSuccess(List<VideoDetailBean> detailBeans);

    /**
     * 下拉加载
     *
     * @param detailBeans
     */
    void loadMoreVideoDetails(List<VideoDetailBean> detailBeans);

    void GetError(Throwable e);
}
