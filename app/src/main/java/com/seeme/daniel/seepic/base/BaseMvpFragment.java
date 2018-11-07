package com.seeme.daniel.seepic.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * @author danielwang
 * @Description: Fragment基类，具体作用和BaseMvpActivity相同
 * @date 2018/11/7 16:18
 */
public abstract class BaseMvpFragment<M extends Model, V extends MyView, P extends BasePresenter> extends Fragment implements BaseMvp<M, V, P> {

    protected P presenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
