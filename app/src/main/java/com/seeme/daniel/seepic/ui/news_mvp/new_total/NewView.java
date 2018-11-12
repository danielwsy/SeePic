package com.seeme.daniel.seepic.ui.news_mvp.new_total;

import com.seeme.daniel.seepic.base.MyView;
import com.seeme.daniel.seepic.entity.Channel;

import java.util.List;

/**
 * @author danielwang
 * @Description: 获取栏目
 * @date 2018/11/8 15:16
 */
public interface NewView extends MyView {

    void GetChannelSuccess(List<Channel> channels);

    void GetChannelError();
}
