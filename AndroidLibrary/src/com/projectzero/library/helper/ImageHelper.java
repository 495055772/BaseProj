package com.projectzero.library.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import com.projectzero.library.util.DevUtil;
import com.projectzero.library.util.EncryptUtils;
import com.projectzero.library.util.http.HttpUtil;
import com.projectzero.library.util.http.depend.HttpRequest;
import org.apache.http.client.ClientProtocolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 
 * 基础图片工具类
 * 
 * 
 */
public class ImageHelper {
    private Context            mContext;
    private static ImageHelper _instance        = null;
    public static int          COMPRESS_QUALITY = 70;  // 图片质量

    public synchronized static ImageHelper getInstance(Context context) {

        if (_instance == null) {
            _instance = new ImageHelper(context);
        }
        return _instance;
    }

    private ImageHelper(Context context) {
        mContext = context;
        DevUtil.initialize(context);
    }

    /**
     * 载入图片
     * 
     * @param filePathOrUrl
     *            图片路径或网络url
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public Bitmap loadImage(String filePathOrUrl) throws ClientProtocolException, IOException, HttpRequest.HttpRequestException {
        Bitmap ret = null;

        if (filePathOrUrl.startsWith("http")) {

            // 是否有缓存
            String fileName = generateDiskCacheKey(filePathOrUrl, 0, 0, false);
            File file = null;
            file = new File(mContext.getCacheDir(), fileName);// 保存文件

            if (!file.exists()) {
                HttpUtil.download(filePathOrUrl, file);
                ret = BitmapFactory.decodeFile(file.getAbsolutePath());
            } else {
                String imagePath = mContext.getCacheDir() + "/" + fileName;
                ret = BitmapFactory.decodeFile(imagePath);
            }

        } else {
            ret = BitmapFactory.decodeFile(filePathOrUrl);
        }

        return ret;
    }

    /**
     * 加载图片 按比例缩放保证图片宽和高不大于width和height
     * 
     * @param filePathOrUrl
     *            网络图片url或者本地路径
     * @param width
     *            宽
     * @param height
     *            高
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    public Bitmap loadImage(String filePathOrUrl, int width, int height) throws ClientProtocolException, IOException,
            HttpRequest.HttpRequestException {
        return loadImage(filePathOrUrl, width, height, false);
    }

    final static int Nomal          = 0;
    final static int InSample       = 1;
    final static int InSampleAndCut = 2;

    /**
     * 加载图片<br>
     * <br>
     * 重要：本方法中isNeedCut=true使用了sdk level8的特性。使用本方法，需要保证你的app中android:minSdkVersion大于等于8，否则在低版本中会异常
     * 
     * @param filePathOrUrl
     *            网络图片url或者本地路径
     * @param width
     *            宽
     * @param height
     *            高
     * @param isNeedCut
     *            true:缩放后将大于指定width和height的裁剪掉 false:仅按比例缩放保证图片宽和高不大于width和height
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    @TargetApi(8)
    public Bitmap loadImage(String filePathOrUrl, int width, int height, boolean isNeedCut)
            throws ClientProtocolException, IOException, HttpRequest.HttpRequestException {
        if (filePathOrUrl == null) {
            return null;
        }

        Bitmap ret = null;

        int imageLoadType = Nomal;
        if (width != 0 && height != 0) {
            if (isNeedCut) {
                imageLoadType = InSampleAndCut;
            } else {
                imageLoadType = InSample;
            }
        }

        switch (imageLoadType) {
        case Nomal:
            DevUtil.v("jackzhou", String.format("loadImage:Nomal task:%s", this));
            ret = loadImage(filePathOrUrl);
            break;

        case InSample:
            DevUtil.v("jackzhou", String.format("loadImage:InSample task:%s", this));
            ret = getInSampleBitmapUseFilePathOrUrl(filePathOrUrl, width, height);
            break;

        case InSampleAndCut:
            DevUtil.v("jackzhou", String.format("loadImage:InSampleAndCut task:%s", this));
            ret = getInSampleAndCutBitmap(filePathOrUrl, width, height);

            break;
        }

        return ret;
    }

    /**
     * 重要：本方法中使用了sdk level8的特性。使用本方法，需要保证你的app中android:minSdkVersion大于等于8，否则在低版本中会异常 获得按比例的略缩图，图片为指定大小（先缩放再裁剪）
     * 
     * @param filePathOrUrl
     * @param cutWidth
     * @param cutHeight
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    @TargetApi(8)
    private Bitmap getInSampleAndCutBitmap(String filePathOrUrl, int cutWidth, int cutHeight)
            throws ClientProtocolException, IOException {

        if (!DevUtil.hasFroyo()) {// 小于2.2的系统无法使用
            return BitmapFactory.decodeByteArray(new byte[] {}, 0, 0);
        }

        Bitmap ret = null;
        if (filePathOrUrl.startsWith("http")) {// 线上图片

            if (cutWidth == 0 && cutHeight == 0) {// 都不限制？
                ret = loadImage(filePathOrUrl);
            } else {
                Bitmap bitmap = loadImage(filePathOrUrl);
                ret = ThumbnailUtils.extractThumbnail(bitmap, cutWidth, cutHeight);

                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        } else {// 本地图片

            if (cutWidth == 0 && cutHeight == 0) {// 都不限制？
                ret = BitmapFactory.decodeFile(filePathOrUrl);
            } else {
                Bitmap bitmap = BitmapFactory.decodeFile(filePathOrUrl);
                ret = ThumbnailUtils.extractThumbnail(bitmap, cutWidth, cutHeight);

                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }

        return ret;
    }

    /**
     * 获取网络或本地的图片的略缩图
     * @param filePathOrUrl
     * @param maxWidth
     * @param maxHeight
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private Bitmap getInSampleBitmapUseFilePathOrUrl(String filePathOrUrl, int maxWidth, int maxHeight)
            throws ClientProtocolException, IOException {
        Bitmap ret = null;

        if (filePathOrUrl.startsWith("http")) {
            ret = getRemotePicInSampleBitmap(filePathOrUrl, maxWidth, maxHeight);
        } else {
            ret = getInSampleBitmap(filePathOrUrl, maxWidth, maxHeight);
        }

        return ret;
    }

    // http://stackoverflow.com/questions/2641726/decoding-bitmaps-in-android-with-the-right-size
    /**
     * 取得图片的略缩图<br>
     * <br>
     * 缓存算法：<br>
     * 以字符串maxWidth + maxHeight + filePatch拼接字符串Md5为缓存文件名<br>
     * 缓存在mContext.getCacheDir()目录中,下次取直接可取得缓存图片。<br>
     * 
     * @param filePath
     * @param maxWidth
     *            0表示不限
     * @param maxHeight
     *            0表示不限
     * @return
     * @throws java.io.FileNotFoundException
     */
    private Bitmap getInSampleBitmap(String filePath, int maxWidth, int maxHeight) throws FileNotFoundException {

        Bitmap ret = null;

        if (maxWidth == 0 && maxHeight == 0) {// 都不限制？
            ret = BitmapFactory.decodeFile(filePath);
        } else {
            if (maxWidth == 0) {
                maxWidth = -1;
            }
            if (maxHeight == 0) {
                maxHeight = -1;
            }

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, opts);
            if (opts.outWidth == -1) {// error
                return null;
            }
            int width = opts.outWidth;// 图片宽
            int height = opts.outHeight;// 图片高
            if (maxWidth >= width && maxHeight >= height) {// 略缩图比原图还大？！！
                ret = BitmapFactory.decodeFile(filePath);
            } else {
                // 计算到maxWidth的压缩比
                float inSampleSizeWidthFloat = (float) width / (float) maxWidth;
                int inSampleSizeWidth = Math.round(inSampleSizeWidthFloat);
                // 计算到maxHeight的压缩比
                float inSampleSizeHeightFloat = (float) height / (float) maxHeight;
                int inSampleSizeHeight = Math.round(inSampleSizeHeightFloat);

                int inSampleSize = Math.max(inSampleSizeWidth, inSampleSizeHeight);

                opts.inJustDecodeBounds = false;
                opts.inSampleSize = inSampleSize;
                ret = BitmapFactory.decodeFile(filePath, opts);
            }
        }

        return ret;
    }

    /**
     * 取得图片的略缩图
     * 
     * @param url
     *            图片地址
     * @param maxWidth
     * @param maxHeight
     * @return
     * @throws java.io.IOException
     * @throws org.apache.http.client.ClientProtocolException
     */
    private Bitmap getRemotePicInSampleBitmap(String url, int maxWidth, int maxHeight) throws ClientProtocolException,
            IOException {

        // 下载图片
        Bitmap temp = loadImage(url);
        if (temp != null && !temp.isRecycled()) {
            temp.recycle();
        }

        // 缩放下载后的图片
        return getInSampleBitmap(diskCachedFilePath(url), maxWidth, maxHeight);
    }

    /**
     * 获取已经磁盘缓存图片的路径
     * 
     * @param url
     * @return
     */
    public String diskCachedFilePath(String url) {
        return diskCachedFilePath(url, 0, 0);
    }

    /**
     * 获取已经磁盘缓存图片的路径
     * 
     * @param url
     * @param width
     * @param height
     * @return
     */
    public String diskCachedFilePath(String url, int width, int height) {
        return diskCachedFilePath(url, width, height, false);
    }

    /**
     * 获取已经磁盘缓存图片的路径
     * @param url
     * @param width
     * @param height
     * @param isNeedCut
     * @return
     */
    public String diskCachedFilePath(String url, int width, int height, boolean isNeedCut) {

        String bitmapFileNameMd5 = generateDiskCacheKey(url, width, height, isNeedCut);
        if (bitmapFileNameMd5 == null) {
            return "";
        }

        File file = new File(mContext.getCacheDir(), bitmapFileNameMd5);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        } else {
            return "";
        }

    }

    /**
     * 判断图片是否已经磁盘缓存
     * 
     * @param url
     *            图片的url
     * @return
     */
    public boolean isDiskCached(String url) {
        String bitmapFileNameMd5 = generateDiskCacheKey(url, 0, 0, false);
        if (bitmapFileNameMd5 == null) {
            return false;
        }

        File file = new File(mContext.getCacheDir(), bitmapFileNameMd5);
        if (file.exists() && file.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将图片写入文件
     * 
     * @param bitmap
     * @param imageType
     * @param file
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean writeToFile(Bitmap bitmap, CompressFormat imageType, File file) throws FileNotFoundException {
        return writeToFile(bitmap, imageType, -1, file);
    }

    /**
     * 将图片写入文件
     * 
     * @param bitmap
     * @param imageType
     * @param compressquality
     *            压缩质量
     * @param file
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean writeToFile(Bitmap bitmap, CompressFormat imageType, int compressquality, File file)
            throws FileNotFoundException {
        boolean ret = false;

        if (bitmap == null || file == null) {
            return false;
        }

        FileOutputStream fileOutputStreamTemp = null;
        try {

            fileOutputStreamTemp = new FileOutputStream(file);

            if (imageType == CompressFormat.PNG) {
                // 判断是否png 避免透明背景被弄成黑色
                ret = bitmap.compress(CompressFormat.PNG, 100, fileOutputStreamTemp);
            } else {
                if (compressquality != -1) {
                    ret = bitmap.compress(CompressFormat.JPEG, compressquality, fileOutputStreamTemp);
                } else {
                    ret = bitmap.compress(CompressFormat.JPEG, COMPRESS_QUALITY, fileOutputStreamTemp);
                }

            }
            if (!bitmap.isRecycled()) {// 图片存成文件后，直接回收掉. 防止out of memory
                bitmap.recycle();
            }
        } finally {
            if (fileOutputStreamTemp != null) {
                try {
                    fileOutputStreamTemp.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        return ret;
    }

    /**
     * 判断图片类型
     * 
     * @param b
     * @return
     */
    public CompressFormat getImageType(byte[] b) {
        if (b[1] == (byte) 'P' && b[2] == (byte) 'N' && b[3] == (byte) 'G') {
            return CompressFormat.PNG;
        } else if (b[6] == (byte) 'J' && b[7] == (byte) 'F' && b[8] == (byte) 'I' && b[9] == (byte) 'F') {
            return CompressFormat.JPEG;
        } else {
            return CompressFormat.JPEG;
        }
    }

    /**
     * 通过url或文件名来判断图片类型
     * 
     * @param url
     * @return
     */
    public static CompressFormat getImageType(String url) {
        String name = url.substring(url.lastIndexOf(".") + 1);
        if (name.equalsIgnoreCase("png")) {
            return CompressFormat.PNG;
        } else if (name.equalsIgnoreCase("jpg")) {
            return CompressFormat.JPEG;
        } else {
            return CompressFormat.JPEG;
        }
    }

    /**
     * 生成disk缓存的key 算法：key = 参数md5后字符串.filePathOrUrl的后缀名
     * @param filePathOrUrl
     * @param width
     * @param height
     * @param isNeedCut
     * @return
     */
    public static String generateDiskCacheKey(String filePathOrUrl, int width, int height, boolean isNeedCut) {
        String preName = EncryptUtils.Md5(String.format("%s%s%s%s", String.valueOf(filePathOrUrl), width, height, isNeedCut));

        // 扩展名位置(特殊处理动态图片地址：http://t0.gstatic.com/images?q=tbn:ANd9GcT85rK6eFEyTw8PT2IaLHGC3ZmFS5yeaTmQ7Adqi0s0yDnv1yMCuw)
        // mock数据时url返回不包含"/"时会异常
        int lastIndex = filePathOrUrl.lastIndexOf("/") > 0 ? filePathOrUrl.lastIndexOf("/") : 0;
        String fileNameArea = filePathOrUrl.substring(lastIndex, filePathOrUrl.length());
        int index = fileNameArea.lastIndexOf('.');
        if (index == -1) {
            return preName;
        }

        StringBuilder sbUrlKey = new StringBuilder("");
        sbUrlKey.append(preName);
        sbUrlKey.append(fileNameArea.substring(index));
        return sbUrlKey.toString();

    }

    /**
     * 压缩Bitmap至指定大小
     * 
     * @param bmp
     * @param destWidth
     * @param destHeight
     * @param isSameScaleRate
     *            是否保持一致的压缩比, true:缩放后将大于指定width和height的裁剪掉 false:仅按比例缩放保证图片宽和高不大于width和height
     * @param isRecyle
     * @return
     */
    public static Bitmap getInSampleBitmap(Bitmap bmp, int destWidth, int destHeight, boolean isSameScaleRate,
            boolean isRecyle) {
        if (bmp == null) {
            return null;
        }
        final int width = bmp.getWidth();
        final int height = bmp.getHeight();
        // 压缩后的图片比原图大, 则返回原图
        if (width < destWidth && height < destHeight) {
            return bmp;
        }

        if (destWidth == 0) {
            destWidth = -1;
        }

        if (destHeight == 0) {
            destHeight = -1;
        }
        Bitmap b = null;
        if (isSameScaleRate) {
            float widthScale = width * 1.0f / destWidth;
            float heightScale = height * 1.0f / destHeight;
            float scale = widthScale > heightScale ? widthScale : heightScale;
            b = Bitmap.createScaledBitmap(bmp, (int) (width / scale), (int) (height / scale), true);
        } else {
            b = Bitmap.createScaledBitmap(bmp, destWidth, destHeight, true);
        }
        if (isRecyle && !bmp.isRecycled()) {
            bmp.recycle();
            bmp = null;
        }
        return b;
    }

}
