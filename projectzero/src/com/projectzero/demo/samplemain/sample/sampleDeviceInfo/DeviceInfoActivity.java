package com.projectzero.demo.samplemain.sample.sampleDeviceInfo;

import android.os.Bundle;
import android.widget.TextView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.library.DeviceInfo;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.sample__activity_deviceinfo)
public class DeviceInfoActivity extends BaseProjectActivity {

    @ViewById(R.id.temp_tv)
    TextView mTv;

    @AfterViews
    void afterView() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("AppName = %s\r\n", DeviceInfo.AppName));
        sb.append(String.format("VersionName = %s\r\n", DeviceInfo.VersionName));
        sb.append(String.format("VersionCode = %s\r\n", DeviceInfo.VersionCode));
        sb.append(String.format("AppCHANNEL = %s\r\n", DeviceInfo.AppCHANNEL));
        sb.append(String.format("UmengKey = %s\r\n", DeviceInfo.UmengKey));
        sb.append(String.format("OSModel = %s\r\n", DeviceInfo.OSModel));
        sb.append(String.format("OSVersion = %s\r\n", DeviceInfo.OSVersion));
        sb.append(String.format("OSDesc = %s\r\n", DeviceInfo.OSDesc));
        sb.append(String.format("uuid = %s\r\n", DeviceInfo.uuid));
        sb.append(String.format("DeviceID = %s\r\n", DeviceInfo.DeviceID));
        sb.append(String.format("MAC = %s\r\n", DeviceInfo.MAC));
        sb.append(String.format("AndroidID = %s\r\n", DeviceInfo.AndroidID));
        sb.append(String.format("UniqueId = %s\r\n", DeviceInfo.UniqueId));
        sb.append(String.format("phoneNum = %s\r\n", DeviceInfo.phoneNum));
        sb.append(String.format("isTablet = %s\r\n", DeviceInfo.isTablet));
        sb.append(String.format("packageName = %s\r\n", DeviceInfo.getPackageName()));

        mTv.setText(sb.toString());
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
