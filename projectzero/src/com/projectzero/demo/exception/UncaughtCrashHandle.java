package com.projectzero.demo.exception;

import android.content.Context;
import com.projectzero.library.util.DevUtil;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtCrashHandle implements UncaughtExceptionHandler {
    private static UncaughtCrashHandle      instance;
    private        Context                  context;
    private        UncaughtExceptionHandler defaultExceptionHandler;

    private UncaughtCrashHandle() {
    }

    public static UncaughtCrashHandle getInstance() {
        if (null == instance)
            instance = new UncaughtCrashHandle();
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex != null) {
            // 组装异常日志
            StackTraceElement[] stackTraces = ex.getStackTrace();
            StringBuilder sb = new StringBuilder();
            Exception e = new Exception(ex);
            sb.append(e.getMessage()).append("\n");
            if (null != stackTraces && 0 != stackTraces.length) {
                int size = stackTraces.length;
                for (int i = 0; i < size; i++) {
                    sb.append(stackTraces[i].toString()).append("\n");
                }
            }
            String error = sb.toString();
            DevUtil.e("uncaughtException", error);

//            if (Looper.myLooper() != Looper.getMainLooper()) {
//                Looper.prepare();
//                Toast.makeText(context, String.format("发生异常了,具体信息查看logcat\n%s", error), Toast.LENGTH_LONG).show();
//                Looper.loop();
//            } else {
//                Toast.makeText(context, String.format("发生异常了,具体信息查看logcat\n%s", error), Toast.LENGTH_LONG).show();
//            }
            //jackzhoutodo 上报异常
        }

        if (null != defaultExceptionHandler) {
            defaultExceptionHandler.uncaughtException(thread, ex);
        }
    }
}
