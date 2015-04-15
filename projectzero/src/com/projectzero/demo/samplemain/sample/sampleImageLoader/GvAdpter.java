package com.projectzero.demo.samplemain.sample.sampleImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.projectzero.demo.R;
import com.projectzero.library.helper.ImageLoader.ImageLoader;

import java.util.ArrayList;

/**
 * 可多选的相册
 * 
 * @author DaleLiu
 * 
 */
public class GvAdpter extends BaseAdapter {
    private ArrayList<String> imageList;
    private boolean           isChoice[];
    private Context           context;
    private ImageLoader load;

    private int               height;
    private int               width;

    public GvAdpter(ArrayList<String> imageList, Context context, ImageLoader load) {
        this.imageList = imageList;
        this.load = load;
        int size = imageList.size();
        isChoice = new boolean[size];
        for (int i = 0; i < size; i++) {
            isChoice[i] = false;
        }
        this.context = context;
        width = (getWindowWidth(context) - dip2px(context, 20)) / 3;
        height = width;
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public String getItem(int arg0) {
        return imageList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        GetView getView = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sample_item_gridview, null);
            getView = new GetView();
            getView.mImage = (ImageView) view.findViewById(R.id.image);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
            getView.mImage.setLayoutParams(layoutParams);
            view.setTag(getView);
        } else {
            getView = (GetView) view.getTag();
        }
        load.loadImage(getView.mImage, getItem(position), R.drawable.lib_default_img_big, width, height, true);
        return view;
    }

    class GetView {
        ImageView mImage;
    }
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
