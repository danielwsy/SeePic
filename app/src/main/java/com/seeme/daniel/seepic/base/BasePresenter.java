package com.seeme.daniel.seepic.base;

import com.seeme.daniel.seepic.network.PhotoApi;

import java.lang.ref.WeakReference;


/**
 * @author danielwang
 * @Description: 所有Presenter层的抽象类，负责Model、View层的引用和销毁
 * @date 2018/11/7 15:56
 */
public abstract class BasePresenter<M extends Model, V extends MyView> implements Presenter<M, V> {

    /**
     * 使用弱引用来防止内存泄漏
     */
    private WeakReference<V> myView;
    protected M model;

    /**
     * 注册Model
     *
     * @param model
     */
    @Override
    public void registerModel(M model) {
        this.model = model;
    }

    /**
     * 注册 View
     *
     * @param view
     */
    @Override
    public void registerView(V view) {
        myView = new WeakReference<V>(view);
    }

    /**
     * 获取View
     *
     * @return
     */
    @Override
    public V getView() {
        return myView == null ? null : myView.get();
    }


    /**
     * 在Activity或Fragment卸载时调用View结束的方法
     */
    @Override
    public void destroy() {
        if (myView != null) {
            myView.clear();
            myView = null;
        }
        onViewDestroy();
    }

    protected abstract void onViewDestroy();
}
