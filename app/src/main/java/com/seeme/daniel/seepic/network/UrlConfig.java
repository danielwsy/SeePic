package com.seeme.daniel.seepic.network;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/8 9:55
 */
public class UrlConfig {

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    /**
     * 新闻地址
     */
    public static final String sIFengApi = "http://api.iclient.ifeng.com/";


    /**
     * 音乐地址
     */
    public static final String MUSIC_BASEURL = "https://netease.api.zzsun.cc/";

    /**
     * 具体的新闻详情页面有时候地址不同，要根据uid来动态设置
     */
    public static final String sGetNewsArticleCmppApi = "http://api.3g.ifeng.com/";

    public static final String sGetNewsArticleDocCmppApi = "ipadtestdoc";


    /**
     * 闪屏页壁纸
     */
    public static final String SPLASH_URL = "http://api.dujin.org/bing/1920.php";

    /**
     * 图片地址
     */
    public static final String URL_PHOTO = "http://image.baidu.com/channel/";

}
