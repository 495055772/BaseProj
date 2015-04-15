package com.projectzero.demo.samplemain.sample.sampleImageHelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.library.helper.ImageHelper;
import org.androidannotations.annotations.*;



@EActivity(R.layout.sample__activity_sampleimagehelperactivity)
public class ImageHelperActivity extends BaseProjectActivity {

    @ViewById(R.id.content_iv)
    ImageView mIv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterView() {
        // loadBitmap();
        // loadBitmapInSampleBitmap(300, 300);
        loadBitmapInSampleBitmapUsePath(300, 300, true);
    }

    @Background
    void loadBitmap() {
        try {
            Bitmap bitmap = ImageHelper.getInstance(this).loadImage(ImageData.bigimages[0]);
            showUI(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Background
    void loadBitmapInSampleBitmap(int w, int h) {
        try {
            Bitmap bitmap = ImageHelper.getInstance(this).loadImage(ImageData.bigimages[0], w, h);
            showUI(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Background
    void loadBitmapInSampleBitmapUsePath(int w, int h, boolean isCut) {
        try {
            String url = ImageData.bigimages[0];
            Bitmap bitmap = ImageHelper.getInstance(this).loadImage(url, w, h, isCut);
            showUI(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    void showUI(Bitmap bitmap) {
        mIv.setImageBitmap(bitmap);
    }

}
