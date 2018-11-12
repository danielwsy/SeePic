package com.seeme.daniel.seepic.ui.news_mvp.new_total;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.entity.Channel;

import java.util.List;

/**
 * @author danielwang
 * @Description: 获取栏目
 * @date 2018/11/8 15:18
 */
public class NewPresenter extends BasePresenter<NewModel, NewView> {

    public void getChannels() {
        List<Channel> channels = model.getChannal();
        if (channels != null) {
            getView().GetChannelSuccess(channels);
        } else {
            getView().GetChannelError();
        }
    }

    @Override
    protected void onViewDestroy() {

    }
}
