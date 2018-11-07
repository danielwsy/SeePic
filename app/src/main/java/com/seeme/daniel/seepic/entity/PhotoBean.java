package com.seeme.daniel.seepic.entity;

import java.util.List;

/**
 * @author danielwang
 * @Description: 照片实体类
 * @date 2018/11/7 16:34
 */
public class PhotoBean {

    private int return_number;
    private int start_index;
    private String tag1;
    private String tag2;
    private int totalNum;
    private List<DataBean> data;

    public int getReturn_number() {
        return return_number;
    }

    public void setReturn_number(int return_number) {
        this.return_number = return_number;
    }

    public int getStart_index() {
        return start_index;
    }

    public void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * abs : 鎬ф劅缇庡コ澶х涔  绉€榄旈韬潗
         * album_di :
         * album_obj_num : 8
         * app_id :
         * can_album_id :
         * children_vote :
         * collect_num : 0
         * colum : 缇庡コ
         * date : 2016-10-25
         * desc :
         * desc_info :
         * dislike_num :
         * download_num : 0
         * download_url : http://f.hiphotos.baidu.com/image/pic/item/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg
         * dress_buy_link :
         * dress_discount :
         * dress_extend_name :
         * dress_extend_type :
         * dress_id :
         * dress_num :
         * dress_other :
         * dress_price :
         * dress_tag :
         * fashion_id :
         * from_name : 0
         * from_url : http://www.4493.com/xingganmote/4213/1.htm
         * fushi_obj_array : null
         * fushi_obj_num :
         * hostname : www.4493.com
         * id : 21018241933
         * image_height : 935
         * image_url : http://f.hiphotos.baidu.com/image/pic/item/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg
         * image_width : 620
         * isAdapted : 1
         * is_album : 0
         * is_single :
         * is_vip : 0
         * like_num :
         * obj_url : http://img.1985t.com/uploads/attaches/2011/12/4213-zATB1Y.jpg
         * other_urls : []
         * parent_tag :
         * photo_id : 21018241933
         * pn : 0
         * return_number : 30
         * setId : 95157
         * share_url : http://f.hiphotos.baidu.com/image/s%3D550%3Bc%3Dwantu%2C8%2C95/sign=1ef8789ace8065387feaa416a7e6c279/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg?referer=acd470ea7fec54e718fb2e2eff0a
         * site_logo :
         * site_name :
         * site_url : http://www.4493.com
         * start_index : 0
         * tag : 鎬ф劅
         * tags : ["鎬ф劅"]
         * thumb_large_height : 467
         * thumb_large_url : http://f.hiphotos.baidu.com/image/w%3D310/sign=ef3b3fe7958fa0ec7fc7620c1695594a/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg
         * thumb_large_width : 310
         * thumbnail_height : 346
         * thumbnail_url : http://f.hiphotos.baidu.com/image/w%3D230/sign=eeccaa34f303918fd7d13ac9613f264b/5ab5c9ea15ce36d358d27ee43ef33a87e850b114.jpg
         * thumbnail_width : 230
         * user_id : 274610020
         */

        private String abs;
        private String album_di;
        private String album_obj_num;
        private String app_id;
        private String can_album_id;
        private String children_vote;
        private int collect_num;
        private String colum;
        private String date;
        private String desc;
        private String desc_info;
        private String dislike_num;
        private int download_num;
        private String download_url;
        private String dress_buy_link;
        private String dress_discount;
        private String dress_extend_name;
        private String dress_extend_type;
        private String dress_id;
        private String dress_num;
        private String dress_other;
        private String dress_price;
        private String dress_tag;
        private String fashion_id;
        private int from_name;
        private String from_url;
        private String fushi_obj_array;
        private String fushi_obj_num;
        private String hostname;
        private String id;
        private int image_height;
        private String image_url;
        private int image_width;
        private int isAdapted;
        private int is_album;
        private String is_single;
        private int is_vip;
        private String like_num;
        private String obj_url;
        private String parent_tag;
        private String photo_id;
        private int pn;
        private int return_number;
        private String setId;
        private String share_url;
        private String site_logo;
        private String site_name;
        private String site_url;
        private int start_index;
        private String tag;
        private int thumb_large_height;
        private String thumb_large_url;
        private int thumb_large_width;
        private int thumbnail_height;
        private String thumbnail_url;
        private int thumbnail_width;
        private String user_id;
        private List<?> other_urls;
        private List<String> tags;

        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }

        public String getAlbum_di() {
            return album_di;
        }

        public void setAlbum_di(String album_di) {
            this.album_di = album_di;
        }

        public String getAlbum_obj_num() {
            return album_obj_num;
        }

        public void setAlbum_obj_num(String album_obj_num) {
            this.album_obj_num = album_obj_num;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getCan_album_id() {
            return can_album_id;
        }

        public void setCan_album_id(String can_album_id) {
            this.can_album_id = can_album_id;
        }

        public String getChildren_vote() {
            return children_vote;
        }

        public void setChildren_vote(String children_vote) {
            this.children_vote = children_vote;
        }

        public int getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(int collect_num) {
            this.collect_num = collect_num;
        }

        public String getColum() {
            return colum;
        }

        public void setColum(String colum) {
            this.colum = colum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc_info() {
            return desc_info;
        }

        public void setDesc_info(String desc_info) {
            this.desc_info = desc_info;
        }

        public String getDislike_num() {
            return dislike_num;
        }

        public void setDislike_num(String dislike_num) {
            this.dislike_num = dislike_num;
        }

        public int getDownload_num() {
            return download_num;
        }

        public void setDownload_num(int download_num) {
            this.download_num = download_num;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public String getDress_buy_link() {
            return dress_buy_link;
        }

        public void setDress_buy_link(String dress_buy_link) {
            this.dress_buy_link = dress_buy_link;
        }

        public String getDress_discount() {
            return dress_discount;
        }

        public void setDress_discount(String dress_discount) {
            this.dress_discount = dress_discount;
        }

        public String getDress_extend_name() {
            return dress_extend_name;
        }

        public void setDress_extend_name(String dress_extend_name) {
            this.dress_extend_name = dress_extend_name;
        }

        public String getDress_extend_type() {
            return dress_extend_type;
        }

        public void setDress_extend_type(String dress_extend_type) {
            this.dress_extend_type = dress_extend_type;
        }

        public String getDress_id() {
            return dress_id;
        }

        public void setDress_id(String dress_id) {
            this.dress_id = dress_id;
        }

        public String getDress_num() {
            return dress_num;
        }

        public void setDress_num(String dress_num) {
            this.dress_num = dress_num;
        }

        public String getDress_other() {
            return dress_other;
        }

        public void setDress_other(String dress_other) {
            this.dress_other = dress_other;
        }

        public String getDress_price() {
            return dress_price;
        }

        public void setDress_price(String dress_price) {
            this.dress_price = dress_price;
        }

        public String getDress_tag() {
            return dress_tag;
        }

        public void setDress_tag(String dress_tag) {
            this.dress_tag = dress_tag;
        }

        public String getFashion_id() {
            return fashion_id;
        }

        public void setFashion_id(String fashion_id) {
            this.fashion_id = fashion_id;
        }

        public int getFrom_name() {
            return from_name;
        }

        public void setFrom_name(int from_name) {
            this.from_name = from_name;
        }

        public String getFrom_url() {
            return from_url;
        }

        public void setFrom_url(String from_url) {
            this.from_url = from_url;
        }

        public String getFushi_obj_array() {
            return fushi_obj_array;
        }

        public void setFushi_obj_array(String fushi_obj_array) {
            this.fushi_obj_array = fushi_obj_array;
        }

        public String getFushi_obj_num() {
            return fushi_obj_num;
        }

        public void setFushi_obj_num(String fushi_obj_num) {
            this.fushi_obj_num = fushi_obj_num;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getImage_height() {
            return image_height;
        }

        public void setImage_height(int image_height) {
            this.image_height = image_height;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getImage_width() {
            return image_width;
        }

        public void setImage_width(int image_width) {
            this.image_width = image_width;
        }

        public int getIsAdapted() {
            return isAdapted;
        }

        public void setIsAdapted(int isAdapted) {
            this.isAdapted = isAdapted;
        }

        public int getIs_album() {
            return is_album;
        }

        public void setIs_album(int is_album) {
            this.is_album = is_album;
        }

        public String getIs_single() {
            return is_single;
        }

        public void setIs_single(String is_single) {
            this.is_single = is_single;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getObj_url() {
            return obj_url;
        }

        public void setObj_url(String obj_url) {
            this.obj_url = obj_url;
        }

        public String getParent_tag() {
            return parent_tag;
        }

        public void setParent_tag(String parent_tag) {
            this.parent_tag = parent_tag;
        }

        public String getPhoto_id() {
            return photo_id;
        }

        public void setPhoto_id(String photo_id) {
            this.photo_id = photo_id;
        }

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public int getReturn_number() {
            return return_number;
        }

        public void setReturn_number(int return_number) {
            this.return_number = return_number;
        }

        public String getSetId() {
            return setId;
        }

        public void setSetId(String setId) {
            this.setId = setId;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getSite_logo() {
            return site_logo;
        }

        public void setSite_logo(String site_logo) {
            this.site_logo = site_logo;
        }

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getSite_url() {
            return site_url;
        }

        public void setSite_url(String site_url) {
            this.site_url = site_url;
        }

        public int getStart_index() {
            return start_index;
        }

        public void setStart_index(int start_index) {
            this.start_index = start_index;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getThumb_large_height() {
            return thumb_large_height;
        }

        public void setThumb_large_height(int thumb_large_height) {
            this.thumb_large_height = thumb_large_height;
        }

        public String getThumb_large_url() {
            return thumb_large_url;
        }

        public void setThumb_large_url(String thumb_large_url) {
            this.thumb_large_url = thumb_large_url;
        }

        public int getThumb_large_width() {
            return thumb_large_width;
        }

        public void setThumb_large_width(int thumb_large_width) {
            this.thumb_large_width = thumb_large_width;
        }

        public int getThumbnail_height() {
            return thumbnail_height;
        }

        public void setThumbnail_height(int thumbnail_height) {
            this.thumbnail_height = thumbnail_height;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public int getThumbnail_width() {
            return thumbnail_width;
        }

        public void setThumbnail_width(int thumbnail_width) {
            this.thumbnail_width = thumbnail_width;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<?> getOther_urls() {
            return other_urls;
        }

        public void setOther_urls(List<?> other_urls) {
            this.other_urls = other_urls;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
