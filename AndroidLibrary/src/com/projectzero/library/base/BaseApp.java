package com.projectzero.library.base;

import android.app.Application;

public abstract class BaseApp extends Application{

    /** 初始化非耗时操作 */
    protected abstract void init();

    /** 耗时操作初始化后台进行 */
    protected abstract void initInBackground();

    @Override public void onCreate() {
        super.onCreate();
        init();
        initInBackground();
    }
}
