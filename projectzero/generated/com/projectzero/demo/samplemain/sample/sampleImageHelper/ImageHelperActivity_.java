//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.projectzero.demo.samplemain.sample.sampleImageHelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.projectzero.demo.ProjectApp_;
import com.projectzero.demo.R.id;
import com.projectzero.demo.R.layout;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ImageHelperActivity_
    extends ImageHelperActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private Handler handler_ = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.sample__activity_sampleimagehelperactivity);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        app = ProjectApp_.getInstance();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static ImageHelperActivity_.IntentBuilder_ intent(Context context) {
        return new ImageHelperActivity_.IntentBuilder_(context);
    }

    public static ImageHelperActivity_.IntentBuilder_ intent(Fragment fragment) {
        return new ImageHelperActivity_.IntentBuilder_(fragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        mIv = ((ImageView) hasViews.findViewById(id.content_iv));
        afterView();
    }

    @Override
    public void showWarningDialog(final String title, final String warningMsg, final OnClickListener onOkBtnClickListener) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.showWarningDialog(title, warningMsg, onOkBtnClickListener);
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.showWarningDialog(title, warningMsg, onOkBtnClickListener);
            }

        }
        );
    }

    @Override
    public void closeLoadingProgress() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.closeLoadingProgress();
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.closeLoadingProgress();
            }

        }
        );
    }

    @Override
    public void showLoadingProgress(final int warningId, final String[] backgroundNames) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.showLoadingProgress(warningId, backgroundNames);
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.showLoadingProgress(warningId, backgroundNames);
            }

        }
        );
    }

    @Override
    public void showToast(final String toastMsg, final int duration) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.showToast(toastMsg, duration);
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.showToast(toastMsg, duration);
            }

        }
        );
    }

    @Override
    public void closeWarningDialog() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.closeWarningDialog();
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.closeWarningDialog();
            }

        }
        );
    }

    @Override
    public void showWarningDialog(final String title, final String warningMsg, final OnClickListener onOkBtnClickListener, final OnClickListener onCancleBtnClickListener) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.showWarningDialog(title, warningMsg, onOkBtnClickListener, onCancleBtnClickListener);
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.showWarningDialog(title, warningMsg, onOkBtnClickListener, onCancleBtnClickListener);
            }

        }
        );
    }

    @Override
    public void renderViewVisble(final View v, final int visibility) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.renderViewVisble(v, visibility);
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.renderViewVisble(v, visibility);
            }

        }
        );
    }

    @Override
    public void onUiFinish() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ImageHelperActivity_.super.onUiFinish();
            return ;
        }
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.onUiFinish();
            }

        }
        );
    }

    @Override
    public void showUI(final Bitmap bitmap) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                ImageHelperActivity_.super.showUI(bitmap);
            }

        }
        );
    }

    @Override
    public void loadBitmap() {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    ImageHelperActivity_.super.loadBitmap();
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void loadBitmapInSampleBitmap(final int w, final int h) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    ImageHelperActivity_.super.loadBitmapInSampleBitmap(w, h);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    @Override
    public void loadBitmapInSampleBitmapUsePath(final int w, final int h, final boolean isCut) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0, "") {


            @Override
            public void execute() {
                try {
                    ImageHelperActivity_.super.loadBitmapInSampleBitmapUsePath(w, h, isCut);
                } catch (Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragment_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, ImageHelperActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragment_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, ImageHelperActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public ImageHelperActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragment_!= null) {
                fragment_.startActivityForResult(intent_, requestCode);
            } else {
                if (context_ instanceof Activity) {
                    ((Activity) context_).startActivityForResult(intent_, requestCode);
                } else {
                    context_.startActivity(intent_);
                }
            }
        }

    }

}
