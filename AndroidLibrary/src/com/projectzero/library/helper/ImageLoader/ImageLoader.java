package com.projectzero.library.helper.ImageLoader;
import android.content.Context;
import android.widget.ImageView;
import com.projectzero.library.util.DevUtil;
import com.projectzero.library.helper.ImageLoader.depend.Callback;
import com.projectzero.library.helper.ImageLoader.depend.Picasso;

/**
 * 图片加载代理类。<br>
 * 需要换加载库或者添加方法 直接在这个类里面添加 方便以后修改
 * @author dale.liu
 */

public class ImageLoader {
    private static ImageLoader singleton;
    private Context   mContext;

    public static ImageLoader getInstance(Context context) {
        if (singleton == null) {
            synchronized (ImageLoader.class) {
                if (singleton == null) {
                    singleton = new ImageLoader(context);
                }
            }
        }
        return singleton;
    }

    private ImageLoader(Context context) {
        this.mContext = context;
        DevUtil.initialize(mContext);
    }


    public void loadImage(final ImageView imageView, String filePathOrUrl, int loadingBitmapResId,
            Callback callback) {
        if(callback != null){
            Picasso.with(mContext)
                    .load(getUrl(filePathOrUrl))
                    .placeholder(loadingBitmapResId)
                    .error(loadingBitmapResId)
                    .tag(mContext)
                    .into(imageView,callback);
        }else{
            Picasso.with(mContext)
                    .load(getUrl(filePathOrUrl))
                    .placeholder(loadingBitmapResId)
                    .error(loadingBitmapResId)
                    .tag(mContext)
                    .into(imageView);
        }

    }

    /**
     * 多图模式下建议不使用该方法 最好传入width和height
     * @param imageView
     * @param filePathOrUrl
     * @param loadingBitmapResId
     */
    public void loadImage(final ImageView imageView, String filePathOrUrl, int loadingBitmapResId) {
        Picasso.with(mContext)
                .load(getUrl(filePathOrUrl))
                .placeholder(loadingBitmapResId)
                .error(loadingBitmapResId)
                .tag(mContext)
                .into(imageView);
    }

    public void loadImage(final ImageView imageView, String filePathOrUrl, int loadingBitmapResId, int width, int height) {
        Picasso.with(mContext)
                .load(getUrl(filePathOrUrl))
                .placeholder(loadingBitmapResId)
                .error(loadingBitmapResId)
                .resize(width, height)
                .centerInside()
                .tag(mContext)
                .into(imageView);
    }

    public void loadImage(ImageView imageView, String filePathOrUrl, int loadingBitmapResId, int width, int height, boolean isNeedCut) {
        if(!isNeedCut){
            Picasso.with(mContext)
                    .load(getUrl(filePathOrUrl))
                    .placeholder(loadingBitmapResId)
                    .error(loadingBitmapResId)
                    .resize(width, height)
                    .centerInside()
                    .tag(mContext)
                    .into(imageView);
        }else{
            Picasso.with(mContext)
                    .load(getUrl(filePathOrUrl))
                    .placeholder(loadingBitmapResId)
                    .error(loadingBitmapResId)
                    .resize(width, height)
                    .centerCrop()
                    .tag(mContext)
                    .into(imageView);
        }


    }
    public void cancleTask(){
        Picasso.with(mContext).cancelTag(mContext);
    }

    /**
     * Start an image request using the specified path. This is a convenience method for calling
     * This path may
     * be a remote URL,
     * file resource (prefixed with {@code file:}),
     * content resource (prefixed with {@code content:}),
     * or android resource (prefixed with {@code android.resource:}
     * Passing {@code null} as a {@code path} will not trigger any request but will set a
     * placeholder, if one is specified.
     * @throws IllegalArgumentException if {@code path} is empty or blank string.
     */
    private String getUrl(String filePathOrUrl){
        if(!filePathOrUrl.startsWith("http") && !filePathOrUrl.startsWith("content:")
                && !filePathOrUrl.startsWith("android.resource:") && !filePathOrUrl.startsWith("file:")){
            return "file://"+filePathOrUrl;//默认是本地图片
        }else{
            return  filePathOrUrl;
        }
    }
}
