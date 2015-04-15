package com.projectzero.demo.base;

import android.content.Context;
import android.os.Bundle;
import com.companyname.base.BaseBusinessActivity;
import com.projectzero.demo.ProjectApp;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

/**
 * AndroidLibrary项目中BaseActivity，存放基本逻辑，不允许存放业务相关逻辑<br>
 * CompanyBusinessLibrary项目中BaseBusinessActivity，存放公司业务公共部分相关逻辑
 * 具体项目中BaseProjectActivity，存放项目相关需要的逻辑
 */
@EActivity
public class BaseProjectActivity extends BaseBusinessActivity {

    @App
    protected ProjectApp app;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
