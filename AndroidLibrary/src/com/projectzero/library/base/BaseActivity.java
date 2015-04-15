package com.projectzero.library.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.projectzero.library.R;
import com.projectzero.library.base.depend.ActivityManager;
import com.projectzero.library.constant.Config;
import com.projectzero.library.util.DevUtil;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;


/**
 * AndroidLibrary项目中BaseActivity，存放基本逻辑，不允许存放业务相关逻辑<br>
 * CompanyBusinessLibrary项目中BaseBusinessActivity，存放公司业务公共部分相关逻辑
 * 具体项目中BaseProjectActivity，存放项目相关需要的逻辑
 */
@EActivity
public class BaseActivity extends Activity {
    /** 数据加载progress */
    ProgressDialog                    mLoadingProgress;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerReceiver(receiverExitAction, filterExitAction);
        super.onCreate(savedInstanceState);
        mContext = this;
        // 取消title显示
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //Activity栈自行管理
        ActivityManager.getInstance().addActivity(this);

    }

    @Override
    protected void onDestroy() {
        closeLoadingProgress();
        ActivityManager.getInstance().finishActivity(this);
        super.onDestroy();
        unregisterReceiver(receiverExitAction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DevUtil.v("jackzhou", String.format("onResume:%s", ((Object) this).getClass().getName()));
    }

    @Override
    protected void onPause() {
        if (null != alertDialog) {
            alertDialog.dismiss();
            alertDialog = null;
        }
        super.onPause();
    }

    /**
     * 控制view的可见和不可见
     *
     * @param v
     * @param visibility
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void renderViewVisble(View v, int visibility) {
        v.setVisibility(visibility);
    }

    /**
     * 显示等待progress
     *
     * @param warningId
     *            提示信息的资源id
     * @param backgroundNames
     *            后台线程名
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showLoadingProgress(int warningId, final String... backgroundNames) {
        closeLoadingProgress();

        if (null == mLoadingProgress) {
            mLoadingProgress = new ProgressDialog(this);
            mLoadingProgress.setMessage(getString(warningId));
            // 不让外部点击取消，可按返回键取消
            mLoadingProgress.setCanceledOnTouchOutside(false);

        }
        mLoadingProgress.show();
    }

    /**
     * 关闭数据加载progress
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void closeLoadingProgress() {
        if (null != mLoadingProgress)
            mLoadingProgress.dismiss();
        mLoadingProgress = null;
    }

    public boolean isLoadingProgressShowing() {
        if (null == mLoadingProgress)
            return false;
        return mLoadingProgress.isShowing();
    }

    public void showToast(int msgId) {
        showToast(getString(msgId));
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg) {
        showToast(toastMsg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showToast(String toastMsg, int duration) {
        Toast.makeText(this, toastMsg, duration).show();
    }

    /**
     * 显示提示dialog
     *
     * @param warnignMsg
     * @param onOkBtnClickListener
     */
    public void showWaringDialog(String warnignMsg, DialogInterface.OnClickListener onOkBtnClickListener) {
        showWarningDialog(getString(R.string.warning), warnignMsg, onOkBtnClickListener);
    }

    private AlertDialog alertDialog;

    /**
     * 显示提示dialog
     *
     * @param title
     * @param warningMsg
     *            提示信息
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showWarningDialog(String title, String warningMsg, DialogInterface.OnClickListener onOkBtnClickListener) {
        if (isFinishing()) {
            return;
        }
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
        try {
            alertDialog = new AlertDialog.Builder(this).setTitle(title).setMessage(warningMsg)
                    .setNegativeButton(R.string.ok, onOkBtnClickListener).create();
            alertDialog.show();
        } catch (Exception e) {
        }
    }

    /**
     * 显示提示dialog
     *
     * @param title
     * @param warningMsg
     *            提示信息
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showWarningDialog(String title, String warningMsg, DialogInterface.OnClickListener onOkBtnClickListener,
            DialogInterface.OnClickListener onCancleBtnClickListener) {
        if (isFinishing()) {
            return;
        }
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
        try {
            alertDialog = new AlertDialog.Builder(this).setTitle(title).setMessage(warningMsg)
                    .setNegativeButton(R.string.cancel, onCancleBtnClickListener)
                    .setPositiveButton(R.string.ok, onOkBtnClickListener).create();
            alertDialog.show();
        } catch (Exception e) {
        }
    }

    /**
     * 关闭提示dialog
     */
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void closeWarningDialog() {
        if (isFinishing()) {
            return;
        }
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }

    }

    /**
     * 关闭键盘
     */
    public void closeKeyboard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressLint("NewApi")
    public boolean isActivityFinished(BaseActivity bc) {
        return (DevUtil.hasJellyBean4_2() && bc.isDestroyed()) || bc.isFinishing();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void onUiFinish() {
        finish();
    }

    private class ExitActionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    public void exitApp() {
        Intent inExit = new Intent(Config.ACTION_APP_EXIT);
        sendBroadcast(inExit);
    }

    private IntentFilter filterExitAction = new IntentFilter(Config.ACTION_APP_EXIT);
    private ExitActionReceiver   receiverExitAction     = new ExitActionReceiver();
}
