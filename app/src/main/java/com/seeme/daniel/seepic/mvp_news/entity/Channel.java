package com.seeme.daniel.seepic.mvp_news.entity;


import java.io.Serializable;

/**
 * @author danielwang
 * @Description: 栏目实体类
 * @date 2018/10/11 15:28
 */
public class Channel implements Serializable {

    public static final int TYPE_MY = 1;
    public static final int TYPE_OTHER = 2;
    public static final int TYPE_MY_CHANNEL = 3;
    public static final int TYPE_OTHER_CHANNEL = 4;

    public int itemtype;

    private String channelId;
    private String channelName;
    /**
     * 0 可移除，1不可移除
     */
    private int channelType;

    /**
     * 0 未选中 1 选中
     */
    private boolean isChannelSelect;


    public int getItemType() {
        return itemtype;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public boolean isChannelSelect() {
        return isChannelSelect;
    }

    public void setChannelSelect(boolean channelSelect) {
        isChannelSelect = channelSelect;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

}
