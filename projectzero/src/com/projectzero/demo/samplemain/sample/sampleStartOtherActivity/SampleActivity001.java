package com.projectzero.demo.samplemain.sample.sampleStartOtherActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.entity.DataTest;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.sample__activity_samplestartotheractivity001)
public class SampleActivity001 extends BaseProjectActivity {
    
    @ViewById(R.id.content_tv)
    TextView contentTV;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.startother_bt)
    void startotherBt(){//简单启动
        com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.SampleActivity002_.intent(this).start();

        Intent intent = new Intent();
        intent.setData(Uri.parse("hujiangcctalk://hjcctalk.hujiang.com/roomid/1"));
        startActivity(intent);
    }

    @Click(R.id.temp_bt)
    void tempBt(){//传递数据
        DataTest dataTest = new DataTest();
        dataTest.setStr("done~");
        com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.SampleActivity002_.intent(this).mDataTest(dataTest).start();
    }

    @Click(R.id.start_for_result_bt)
    void startForResult(){//startActivityForResult
        com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.SampleActivity002_.intent(this).startForResult(123);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        String str = String.format("requestCode: %s   - resultCode:%s   -   data:%s", requestCode, resultCode, data.getStringExtra("result"));
        contentTV.setText(str);
    }
}
