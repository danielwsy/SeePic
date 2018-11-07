package com.seeme.daniel.seepic.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author danielwang
 * @Description: Activity基类，具体的实现Model、View的绑定，
 * 我们自己的Activity可直接继承于此类或者自行实现BaseActivity继承于此类
 * @date 2018/11/7 16:15
 */
public abstract class BaseMvpActivity<M extends Model, V extends MyView, P extends BasePresenter>
        extends AppCompatActivity implements BaseMvp<M, V, P> {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        presenter = createPresenter();
        if (presenter != null) {
            //将Model层注册到Presenter中
            presenter.registerModel(createModel());
            //将View层注册到Presenter中
            presenter.registerView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destroy();
        }
    }
}
