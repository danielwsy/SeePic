package com.seeme.daniel.seepic.base;

/**
 * @author danielwang
 * @Description: 用于创建Model、View和Presente
 * @date 2018/11/7 16:08
 */
public interface BaseMvp<M extends Model, V extends MyView, P extends BasePresenter> {
    M createModel();

    V createView();

    P createPresenter();
}
