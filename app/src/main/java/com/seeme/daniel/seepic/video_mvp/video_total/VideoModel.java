package com.seeme.daniel.seepic.video_mvp.video_total;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.entity.VideoDetailBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author danielwang
 * @Description: 获取视频信息
 * @date 2018/11/12 10:29
 */
public interface VideoModel extends Model {


    /**
     * 获取视频频道
     *
     * @param page
     * @return
     */
    Observable<List<VideoChannelBean>> getVideoChannel(int page);



}
