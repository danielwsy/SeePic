package com.seeme.daniel.seepic.video_mvp.video_total;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.entity.VideoChannelBean;
import com.seeme.daniel.seepic.entity.VideoDetailBean;

import java.util.List;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/12 10:30
 */
public interface VideoView extends MyView {

    void GetChannelSuccess(List<VideoChannelBean> channelBeans);

    void GetError(Throwable e);

}
