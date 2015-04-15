package com.projectzero.library.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
* 开发工具类
*
*/
public class DevUtil {

    private static Context               mContext;

    private static boolean               isDebug = false;
    private static boolean               isAllowedKey                = false;                          // 是否合法签名
    private static HashMap<String, Long> oldTimeMap                  = new HashMap<String, Long>();

    private static final String          NOT_INITIALIZE_ERROR_STRING = DevUtil.class.getSimpleName()
                                                                             + " not initialize. Please run "
                                                                             + DevUtil.class.getSimpleName()
                                                                             + ".initialize() first !";

    public static void initialize(Context context) {
        mContext = context;
        debugAccess();
    }

    /**
     * 设置debug 一般情况下不要调用 主要用于测试
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        DevUtil.isDebug = isDebug;
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName
     *            你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void v(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Log.v(yourName, message + " - tag:" + yourName);
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName
     *            你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void e(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Log.e(yourName, message + " - tag:" + yourName);
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName
     *            你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void w(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Log.w(yourName, message + " - tag:" + yourName);
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName
     *            你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void i(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Log.i(yourName, message + " - tag:" + yourName);
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出每步花费的时间。<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName
     *            你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void timePoint(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Long currentTime = System.currentTimeMillis();
            Long oldTimeTemp = oldTimeMap.get(yourName);
            if (oldTimeTemp == null) {
                oldTimeTemp = System.currentTimeMillis();
            }
            Long durationTime = currentTime - oldTimeTemp;
            Log.v(yourName, message + " durationTime:" + durationTime + " - tag:" + yourName);
            oldTimeTemp = currentTime;
            oldTimeMap.put(yourName, oldTimeTemp);
        }
    }

    /**
     * 通过签名判断是否为开发版，开发版keystore见项目根目录的debug.keystore，用这个文件覆盖用户目录中.android目录的同名文件
     *
     * @return
     * @throws Exception
     */
    public static boolean isDebug() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return isDebug;
    }

    /**
     * 通过签名判断是否为合法的key签名: 线下debug keystore和线上 keystore
     *
     * @return
     */
    public static boolean isAllowedKey() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return isAllowedKey;
    }

    /**
     * 判断是否开发版本、是否合法的签名
     */
    private static void debugAccess() {

        final int DEBUG_SIGNATURE_HASH = -545290802;
        final int ONLINE_SIGNATURE_HASH = 111111111;//jackzhoutodo  更换为线上key

        // 判断是否为调试状态
        // http://stackoverflow.com/questions/3029819/android-automatically-choose-debug-release-maps-api-key
        PackageManager manager = mContext.getPackageManager();

        try {

            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature sig : info.signatures) {

                if (sig.hashCode() == DEBUG_SIGNATURE_HASH) {
                    isDebug = true;
                    isAllowedKey = true;
                }

                if (sig.hashCode() == ONLINE_SIGNATURE_HASH) {
                    isDebug = false;
                    isAllowedKey = true;
                }
            }

        } catch (NameNotFoundException e) {
            isDebug = false;
        }
    }

    /**
     * 判断是否模拟器。如果返回TRUE，则当前是模拟器
     *
     * @param context
     * @return
     */
    public static boolean isEmulator(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (imei == null || imei.equals("000000000000000")) {
            return true;
        }
        return false;
    }

    /**
     * 手机操作系统是否>= level5 2.0
     *
     * @return
     */
    public static boolean hasAndroid2_0() {
        return Build.VERSION.SDK_INT >= 5;// Build.VERSION_CODES.ECLAIR;
    }

    /**
     * 手机操作系统是否>= level7 2.1
     *
     * @return
     */
    public static boolean hasAndroid2_1() {
        return Build.VERSION.SDK_INT >= 7;// Build.VERSION_CODES.ECLAIR_MR1
    }

    /**
     * 手机操作系统是否>=Froyo level8 2.2
     *
     * @return
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;// Build.VERSION_CODES.FROYO;
    }

    /**
     * 手机操作系统是否>=Gingerbread level9 2.3.1
     *
     * @return
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;// 低版本Build.VERSION_CODES.GINGERBREAD未定义
    }

    /**
     * 手机操作系统是否>=Honeycomb level11 3.0
     *
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;// Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 手机操作系统是否>=HoneycombMR1 level12 3.1
     *
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;// Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * 手机操作系统是否>=HoneycombMR2 level12 3.2
     *
     * @return
     */
    public static boolean hasHoneycombMR2() {
        return Build.VERSION.SDK_INT >= 13;// Build.VERSION_CODES.HONEYCOMB_MR2;
    }

    /**
     * 手机操作系统是否>=HoneycombMR1 level14 4.0
     *
     * @return
     */
    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= 14;// Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * 手机操作系统是否>=HoneycombMR1 level16 4.1
     *
     * @return
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= 16;// Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * 手机操作系统是否>=HoneycombMR1 level17 4.2
     *
     * @return
     */
    public static boolean hasJellyBean4_2() {
        return Build.VERSION.SDK_INT >= 17;// Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * 返回当前程序版本名称
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // Get the package info
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    /**
     * 返回当前程序versioncode
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            // Get the package info
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (Exception e) {
        }
        return versionCode;
    }

    /**
     * 开启StrickMode
     */
    @TargetApi(9)
    public static void enableStrictMode() {

        if (hasGingerbread()) {

            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().detectAll()
                    .penaltyLog();

            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();

            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    /**
     * 关闭StrickMode
     */
    @TargetApi(9)
    public static void disableStrictMode() {

        if (hasGingerbread()) {

            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().permitAll()
                    .penaltyLog();

            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
        }
    }

    /**
     * 获取一个全局的Context
     *
     * @return
     */
    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return mContext;
    }

    /**
     * 获取sim卡手机号
     *
     * @return String
     */
    public static String getSIMCardPhoneNumber(Context context) {
        String SIMCardPhoneNumber = null;
        if (null != context) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            SIMCardPhoneNumber = tm.getLine1Number();
        }
        return TextUtils.isEmpty(SIMCardPhoneNumber) ? "" : SIMCardPhoneNumber;
    }

}
