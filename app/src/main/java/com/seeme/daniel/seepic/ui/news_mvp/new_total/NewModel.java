package com.seeme.daniel.seepic.ui.news_mvp.new_total;

import com.seeme.daniel.seepic.base.Model;
import com.seeme.daniel.seepic.entity.Channel;

import java.util.List;

/**
 * @author danielwang
 * @Description: 获取channelmodle
 * @date 2018/11/8 15:13
 */
public interface NewModel extends Model {
    List<Channel> getChannal();
}
