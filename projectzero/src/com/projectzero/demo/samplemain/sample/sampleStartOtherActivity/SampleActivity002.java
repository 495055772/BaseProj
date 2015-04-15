package com.projectzero.demo.samplemain.sample.sampleStartOtherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.entity.DataTest;
import org.androidannotations.annotations.*;

@EActivity(R.layout.sample__activity_samplestartotheractivity002)
public class SampleActivity002 extends BaseProjectActivity {
    
    @Extra
    DataTest mDataTest;

    @ViewById(R.id.content_tv)
    TextView contectTv;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews void init() {
        if (mDataTest != null) {
            String strExtra = mDataTest.getStr();
            contectTv.setText(strExtra);
        }
    }

    @Click(R.id.for_result_finish_bt) void forResultFinish() {
        //数据是使用Intent返回
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra("result", "the result: done");
        //设置返回数据
        this.setResult(RESULT_OK, intent);
        //关闭Activity
        this.finish();
    }


}
