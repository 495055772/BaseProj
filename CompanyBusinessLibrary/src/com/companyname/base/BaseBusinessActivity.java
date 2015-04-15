package com.companyname.base;

import android.content.Context;
import android.os.Bundle;
import com.companyname.analytics.LogAgent;
import com.projectzero.library.base.BaseActivity;

/**
 * AndroidLibrary项目中BaseActivity，存放基本逻辑，不允许存放业务相关逻辑<br>
 * CompanyBusinessLibrary项目中BaseBusinessActivity，存放公司业务公共部分相关逻辑
 * 具体项目中BaseProjectActivity，存放项目相关需要的逻辑
 */
public class BaseBusinessActivity extends BaseActivity {

    public Context mContext;

    /** 页面名称 用于统计 */
    protected String pageLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        pageLable = this.getClass().getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogAgent.onResume(this, pageLable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogAgent.onPause(this, pageLable);
    }
}
