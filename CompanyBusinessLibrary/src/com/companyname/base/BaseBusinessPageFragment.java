package com.companyname.base;

import android.app.Fragment;
import android.os.Bundle;
import com.companyname.analytics.LogAgent;

/**
 * 如果页面的实现是基于fragment的，需继承该类
 */
public abstract class BaseBusinessPageFragment extends Fragment {

    /** 页面标识 */
    protected String pageLable = "default";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPageLable();
    }

    /**
     * 设置页面标识
     */
    protected abstract void setPageLable();

    @Override
    public void onResume() {
        super.onResume();
        LogAgent.onPageStart(pageLable);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogAgent.onPageEnd(pageLable);
    }
}
