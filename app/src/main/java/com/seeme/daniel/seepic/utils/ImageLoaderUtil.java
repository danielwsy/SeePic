package com.seeme.daniel.seepic.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;


/**
 * @author danielwang
 * @Description: 图片加载工具类
 * @date 2018/10/10 16:37
 */
public class ImageLoaderUtil {

    /**
     * 常规使用
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 目标view
     */
    public static void LoadImage(Context context, Object url, ImageView imageView) {
        if (imageView == null) return;
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageView);
    }
}
