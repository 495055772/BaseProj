package com.projectzero.demo.samplemain.sample.sampleImageLoader;

import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.library.helper.ImageLoader.ImageLoader;
import com.projectzero.library.util.DevUtil;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ImageLoader 图片加载实例代码
 */
@EActivity(R.layout.sample__activity_sampleimageloaderactivity)
public class ImageLoaderActivity extends BaseProjectActivity {

    @ViewById(R.id.sample_gv)
    GridView mGv;
    ArrayList<String>                allFile          = new ArrayList<String>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterView() {
        getdata();
        GvAdpter mAdpter = new GvAdpter(allFile, this, ImageLoader.getInstance(this));
        mGv.setAdapter(mAdpter);
    }

    /**
     * 获取相册文件夹下的文件
     *
     * @return
     */
    private void getdata() {
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            getFileList(path);
            DevUtil.v("DaleLiu", path);
        } catch (Exception e) {
            DevUtil.v("DaleLiu", e.toString());
        }

    }


    /**
     * 根据给定的路径，取得path下所有文件的路径
     *
     * @param path
     * @return
     */
    public void getFileList(String path) {
        allFile.clear();
        File file = new File(path);
        if (file.exists()) {
            getFilePath(file);
            Collections.sort(allFile, comparator);
        }
    }

    public void getFilePath(File file) {
        String path = file.getAbsolutePath();
        if (!file.isDirectory() && (path.endsWith(".jpg") || path.endsWith(".png"))) {
            allFile.add(file.getAbsolutePath());
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                String pp = file1.getAbsolutePath();
                if (!file1.isDirectory() && (pp.endsWith(".jpg") || pp.endsWith(".png"))) {
                    allFile.add(pp);
                } else if (file1.isDirectory()) {
                    getFilePath(file1);
                }

            }
        }
    }
    // @formatter:off
    Comparator<String> comparator = new Comparator<String>() {
        public int compare(String f1, String f2) {
            if (f1 == null || f2 == null) {// 先比较null
                if (f1 == null) {
                    return -1;
                } else {
                    return 1;
                }
            } else {// 比较文件
                return f2.compareToIgnoreCase(f1);
            }
        }
    };
    // @formatter:on
}
