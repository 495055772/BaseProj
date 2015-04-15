package com.companyname.analytics;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;

/**
 * 统计日志代理类。目前仅umeng，后续增加公司自己统计代码更改此处。<br>
 */
public class LogAgent {

    public static void init(){
        MobclickAgent.openActivityDurationTrack(false);
    }

    public static void onResume(Context context, String pageLable) {
        if (null != context) {
            //http://blog.umeng.com/?p=2928
            MobclickAgent.onPageStart(pageLable);
            MobclickAgent.onResume(context);
        }
    }

    public static void onPause(Context context, String pageLable) {
        if (null != context) {
            //http://blog.umeng.com/?p=2928
            MobclickAgent.onPageEnd(pageLable);
            MobclickAgent.onPause(context);
        }
    }

    public static void onPageStart(String pageLabel) {
        MobclickAgent.onPageStart(pageLabel);
    }

    public static void onPageEnd(String pageLabel) {
        MobclickAgent.onPageEnd(pageLabel);
    }
}
