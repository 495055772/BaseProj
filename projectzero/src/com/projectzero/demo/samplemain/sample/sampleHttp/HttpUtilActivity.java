package com.projectzero.demo.samplemain.sample.sampleHttp;

import android.os.Bundle;
import android.widget.TextView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.library.util.DevUtil;
import com.projectzero.library.util.http.HttpUtil;
import com.projectzero.library.util.http.depend.HttpDownloadHandle;
import com.projectzero.library.util.http.depend.HttpResponseHandle;
import com.projectzero.library.util.http.depend.HttpUploadHandle;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.UiThread.Propagation;

import javax.net.ssl.SSLHandshakeException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;



@EActivity(R.layout.sample__activity_httputil)
public class HttpUtilActivity extends BaseProjectActivity {

    // moco服务器ip，更改为你启动服务的ip地址。服务在本项目的sample_moco这个目录中
    private String ip = "http://172.16.64.146:8888";

    @ViewById(R.id.temp_tv)
    TextView       textView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterView() {
//        testGetMethod();
//        testGetMethodParamter();
//        testGetMethodHeader();
//
//        testPostMethod();
//        testPostMethodParamter();
//        testPostMethodForm();
//        testPostMethodHeader();
//
//        testGetMethodTimeOut();
//        testPostMethodTimeOut();
//
//        testDownloadFile();
//        testDownloadFile002();
//        testDownloadFileHandle();
//        testuploadFile();
//        testuploadFileHandle();

        testHttps();
    }

    @Background
    void testHttps() {

        String url = "https://github.com";//白名单服务器
//        String url = "https://mail.qq.com";// 非白名单https服务器

        String ret = HttpUtil.getMethod(url, new HttpResponseHandle() {

            @Override
            public void onTimeOut() {
                updateUI("timeout");
            }

            @Override
            public void onSuccess(String responseBody, int statusCode) {
                updateUI(responseBody);
            }

            @Override
            public void onFailure(Exception e) {
                if (SSLHandshakeException.class.isInstance(e)) {
                    // javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
                    updateUI("与https服务器握手失败。请将.crt证书文件放入assets目录，并在HttpUtil.httpsInit初始化时将域名加入白名单:" + e.toString());

                } else if (IOException.class.isInstance(e)) {
                    // java.io.IOException: Hostname 'www.github.com' was not verified
                    updateUI("域名不在白名单中:" + e.toString());

                } else {
                    updateUI(e.toString());
                }

            }
        });
    }

    @UiThread(propagation = Propagation.REUSE)
    void updateUI(String ret) {
        textView.setText(String.valueOf(ret));
    }

    @Background
    void testGetMethod() {
        String url = ip + "/";
        String ret = HttpUtil.getMethod(url);
        updateUI(ret);
    }

    @Background
    void testGetMethodParamter() {
        String url = ip + "/get";
        HashMap<String, String> paramter = new HashMap<String, String>();
        paramter.put("test", "123");
        paramter.put("test002", "abc");

        String ret = HttpUtil.getMethod(url, paramter);
        updateUI(ret);
    }

    @Background
    void testGetMethodTimeOut() {
        String url = ip + "/timeout";
        updateUI("连接超时时间=3s, 读取超时时间=20s, 请等待......");

        HttpUtil.getMethod(url, new HttpResponseHandle() {

            @Override
            public void onTimeOut() {
                updateUI("onTimeOut");
            }

            @Override
            public void onSuccess(String responseBody, int statusCode) {
                updateUI("onSuccess");

            }

            @Override
            public void onFailure(Exception e) {
                updateUI("onFailure");
            }
        });

    }

    @Background
    void testGetMethodHeader() {
        String url = ip + "/getshowheader";
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("header001", "headddddddd");

        String ret = HttpUtil.getMethod(url, header, null, true, true, null);
        updateUI(ret);
    }

    @Background
    void testPostMethod() {
        String url = ip + "/post";
        String ret = HttpUtil.postMethod(url, null, null, null, false, null);
        updateUI(ret);
    }

    @Background
    void testPostMethodParamter() {
        String url = ip + "/postparamter";
        HashMap<String, String> paramter = new HashMap<String, String>();
        paramter.put("patamter", "123");
        paramter.put("patamter002", "dddddasdf");
        String ret = HttpUtil.postMethod(url, null, paramter, null, false, null);
        updateUI(ret);
    }

    @Background
    void testPostMethodForm() {
        String url = ip + "/postform";
        HashMap<String, String> form = new HashMap<String, String>();
        form.put("form", "form1111");
        form.put("form002", "form22222");
        String ret = HttpUtil.postMethod(url, null, null, form, false, null);
        updateUI(ret);
    }

    @Background
    void testPostMethodHeader() {
        String url = ip + "/postheader";
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("header000", "header-effvf");
        String ret = HttpUtil.postMethod(url, header, null, null, false, null);
        updateUI(ret);
    }

    @Background
    void testPostMethodTimeOut() {
        String url = ip + "/posttimeout";
        updateUI("连接超时时间=3s, 读取超时时间=20s, 请等待......");

        HttpUtil.postMethod(url, null, null, null, false, new HttpResponseHandle() {

            @Override
            public void onTimeOut() {
                updateUI("onTimeOut");
            }

            @Override
            public void onSuccess(String responseBody, int statusCode) {
                updateUI("onSuccess");

            }

            @Override
            public void onFailure(Exception e) {
                updateUI("onFailure");
            }
        });
    }

    @Background
    void testDownloadFile() {
        String url = "http://www.baidu.com";
        File file = HttpUtil.download(url, "test.html", this);
        updateUI(file.getAbsolutePath());
    }

    @Background
    void testDownloadFile002() {
        String url = "http://www.baidu.com";
        File file = HttpUtil.download(url, new File(this.getCacheDir(), "cache.html"));
        updateUI(file.getAbsolutePath());
    }

    @Background
    void testDownloadFileHandle() {
        String url = "http://down.gfan.com/gfan/product/a/gfanmobile/beta/GfanMobile_web321.apk";

        HttpUtil.download(url, "GfanMobile_web321.apk", this, new HttpDownloadHandle() {

            File mfile;

            @Override
            public void onSuccess(File file) {
                DevUtil.v("jackzhou", "testDownloadFileHandle - onSuccess");
            }

            @Override
            public void onStart(File file) {
                DevUtil.v("jackzhou", "testDownloadFileHandle - onStart");
                mfile = file;
                updateUI(String.format("下载文件：%s", file.getAbsoluteFile()));
            }

            @Override
            public void onFailure(Exception e) {
                DevUtil.v("jackzhou", "testDownloadFileHandle - onFailure:" + e.toString());
            }

            @Override
            public void onDownLoading(long downloaded, long total) {
                DevUtil.v("jackzhou",
                        String.format("testDownloadFileHandle - onDownLoading: %s / %s", downloaded, total));
                updateUI(String.format("下载文件(%sk / %sk)：%s", downloaded / 1000, total / 1000, mfile.getAbsoluteFile()));
            }
        });
    }

    @Background
    void testuploadFile() {
        String url = "http://upd.release.dev.ipo.com/upload/";
        File temp = new File("/storage/emulated/0/528x297.jpg");

        String ret = HttpUtil.upload(url, "imageKey", temp);
        updateUI("ret:" + ret);
    }

    @Background
    void testuploadFileHandle() {
        // http://pms.ipo.com/pages/viewpage.action?pageId=1802424
        String url = "http://upd.release.dev.ipo.com/upload/";
        final File temp = new File("/storage/emulated/0/1.png");

        HashMap<String, String> head = new HashMap<String, String>();
        head.put("Referer", "http://androidapp.pinganfang.com/app");
        String ret = HttpUtil.upload(url, "imageKey", temp, head, null, new HttpUploadHandle() {

            @Override
            public void onUpLoading(long uploaded, long total) {
                updateUI(String.format("onUpLoading: %s / %s: %s", uploaded, total, temp.getAbsolutePath()));
            }

            @Override
            public void onSuccess(String ret) {
                DevUtil.v("jackzhou", String.format("onSuccess: %s", ret));
            }

            @Override
            public void onStart() {
                DevUtil.v("jackzhou", "onStart");

            }

            @Override
            public void onFailure(Exception e) {
                DevUtil.v("jackzhou", "testDownloadFileHandle - onFailure:" + e.toString());

            }
        });

        updateUI("ret:" + ret);
    }
}
