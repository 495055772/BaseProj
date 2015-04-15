package com.projectzero.library.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class FileUtil {

    /**
     * Check if OS version has built-in external cache dir method.
     *
     * @return
     */
    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }


    /**
     * Get the external app cache directory.
     *
     * @param context
     *            The context to use
     * @return The external cache dir
     */
    @SuppressLint("NewApi")
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false otherwise.
     */
    @SuppressLint("NewApi")
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * 获取缓存目录<br>
     * Check if media is mounted or storage is built-in, if so, try and use external cache dir otherwise use internal cache dir
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        File extCache = getExternalCacheDir(context);
        boolean isSdcardOk = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !isExternalStorageRemovable();
        return (isSdcardOk && null != extCache) ? extCache.getPath() : context.getCacheDir().getPath();
    }
}
