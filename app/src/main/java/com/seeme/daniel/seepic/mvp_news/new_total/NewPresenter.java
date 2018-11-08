package com.seeme.daniel.seepic.mvp_news.new_total;

import com.seeme.daniel.seepic.base.BasePresenter;
import com.seeme.daniel.seepic.mvp_news.entity.Channel;

import java.util.List;

/**
 * @author danielwang
 * @Description:
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
