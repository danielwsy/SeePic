package com.seeme.daniel.seepic.ui.news_mvp.new_total;

import com.seeme.daniel.seepic.HiApplication;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.entity.Channel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author danielwang
 * @Description: 获取channal 实现类
 * @date 2018/11/8 15:16
 */
public class NewModelImpl implements NewModel {
    @Override
    public List<Channel> getChannal() {
        List<String> channelName = Arrays.asList(HiApplication.getContext().getResources()
                .getStringArray(R.array.news_channel));
        List<String> channelId = Arrays.asList(HiApplication.getContext().getResources()
                .getStringArray(R.array.news_channel_id));
        List<Channel> channels = new ArrayList<>();

        for (int i = 0; i < channelName.size(); i++) {
            Channel channel = new Channel();
            channel.setChannelId(channelId.get(i));
            channel.setChannelName(channelName.get(i));
            channel.setChannelType(i < 1 ? 1 : 0);
            channels.add(channel);
        }

        return channels;
    }
}
