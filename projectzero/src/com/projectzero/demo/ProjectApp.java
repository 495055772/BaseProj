package com.projectzero.demo;

import android.util.Log;
import com.companyname.analytics.LogAgent;
import com.lidroid.xutils.DbUtils;
import com.projectzero.demo.constant.Config;
import com.projectzero.demo.exception.DevUncaughtCrashHandle;
import com.projectzero.demo.exception.UncaughtCrashHandle;
import com.projectzero.library.DeviceInfo;
import com.projectzero.library.base.BaseApp;
import com.projectzero.library.util.DevUtil;
import com.projectzero.library.util.FileUtil;
import com.projectzero.library.util.http.HttpUtil;
import org.androidannotations.annotations.EApplication;

import java.io.File;

@EApplication
public class ProjectApp extends BaseApp {

    private DbUtils db;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override protected void init() {

        // 开发用工具
        DevUtil.initialize(this);

        if (!DevUtil.isDebug()) {
            UncaughtCrashHandle.getInstance().init(this);
        } else {
            DevUtil.enableStrictMode();
            DevUncaughtCrashHandle.getInstance().init(this);
        }

        // DeviceInfo工具
        DeviceInfo.initialize(this, "demo");

        //日志记录工具
        LogAgent.init();

        //https工具
        HttpUtil.httpsInit(this, new String[]{"github.com"});

    }

    @Override protected void initInBackground() {
        initDB();
    }

    private void initDB() {
        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(this);
        daoConfig.setDbName(Config.DB_NAME);
        daoConfig.setDbVersion(Config.DB_VERSION);
        String filePath = FileUtil.getCacheDir(this) + File.separator + Config.DB_FILE_NAME;
        Log.v("jackzhou", "DB:"+filePath);
        daoConfig.setDbDir(filePath);

        //jackzhoutodo 处理数据库升级问题方案

        this.db = DbUtils.create(daoConfig);
    }

    public DbUtils getDb() {
        return db;
    }
}
