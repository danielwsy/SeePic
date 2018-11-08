package com.seeme.daniel.seepic.mvp_news.new_total;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.PhotoBean;
import com.seeme.daniel.seepic.mvp_news.entity.Channel;

import java.util.List;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 15:16
 */
public interface NewView extends MyView {

    void GetChannelSuccess(List<Channel> channels);

    void GetChannelError();
}
