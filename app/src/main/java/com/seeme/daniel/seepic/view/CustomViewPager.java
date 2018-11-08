package com.seeme.daniel.seepic.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @author danielwang
 * @Description: 取消viewpager 切换动画.
 * @date 2018/10/11 14:04
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    //去除页面切换时的滑动翻页效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
