package com.projectzero.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.projectzero.library.R;
import com.projectzero.library.util.iconfont.IconfontUtil;
import com.projectzero.library.util.iconfont.depend.Icon;

/***
 * 
 * 上面带有图片的TextView，主要用于图片使用IconFont样式引用的情况
 * <ul>
 * <li>该组件由 "Text + icon"组成, 其中"Text"和"icon"的位置由oriention属性决定, oriention属性可选范围为{"top", "right", "bottom", "left"}, 类似于TextView的drawableTop, drawableRight,
 * drawableBottom, drawableLeft属性方向</li>
 * <li>支持iconText, iconColor, iconSize, text, textColor, textSize等自定义属性，以icon开头的表示icon属性, 其他为text属性</li>
 * <li>"Text" 和 "icon"之间的距离由spacing属性决定</li>
 * </ul>
 * 
 * tips: 必须包含raw/haofang.ttf文件
 * 
 * <p>
 * Example: <br>
 * {@code
 *       <com.projectzero.library.widget.IconTextView
 *          android:layout_width="wrap_content"
 *          android:layout_height="wrap_content"
 *          iconfont:iconColor="@color/default_orange_color"
 *          iconfont:iconSize="30dp"
 *          iconfont:iconText="&#xe60a;"
 *          iconfont:oriention="left"
 *          iconfont:spacing="20dp"
 *          iconfont:text="hello"
 *          iconfont:textColor="@color/default_green_color"
 *          iconfont:textSize="20sp" />
 *  }
 * 
 * </p>
 * 
 */

public class IconTextView extends FrameLayout {

    public static final int    DEFAULT_SPACING    = 20;

    public static final float  DEFAULT_ICON_SIZE  = 60;
    public static final float  DEFAULT_TEXT_SIZE  = 18;

    public static final int    DEFAULT_ICON_COLOR = Color.GRAY;
    public static final int    DEFAULT_TEXT_COLOR = Color.BLACK;

    public static final String TOP                = "top";

    public static final String LEFT               = "left";

    public static final String BOTTOM             = "bottom";
    public static final String RIGHT              = "right";
    public static final String CENTER              = "center";

    private TextView           tvIcon;
    private TextView           tvName;

    private String             text;
    private String             oriention          = TOP;               // 方向right left bottom top
    private int                spacing            = DEFAULT_SPACING;
    private float              iconSize           = DEFAULT_ICON_SIZE;
    private float              textSize           = DEFAULT_TEXT_SIZE;
    private int                iconColor          = DEFAULT_ICON_COLOR;
    private int                textColor          = DEFAULT_TEXT_COLOR;
    private int                textStyle          = 0;
    private String             iconText;

    public IconTextView(Context context) {
        super(context);
        initView(context);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getSelfAttr(context, attrs);
        initView(context);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getSelfAttr(context, attrs);
        initView(context);
    }

    private void getSelfAttr(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.IconTextView);
        iconSize = typeArray.getDimension(R.styleable.IconTextView_iconSize, DEFAULT_ICON_SIZE);
        textSize = typeArray.getDimension(R.styleable.IconTextView_textSize, DEFAULT_TEXT_SIZE);
        iconColor = typeArray.getColor(R.styleable.IconTextView_iconColor, DEFAULT_ICON_COLOR);
        textColor = typeArray.getColor(R.styleable.IconTextView_textColor, DEFAULT_TEXT_COLOR);
        spacing = (int) typeArray.getDimension(R.styleable.IconTextView_spacing, DEFAULT_SPACING);
        text = typeArray.getString(R.styleable.IconTextView_text);
        oriention = typeArray.getString(R.styleable.IconTextView_oriention);
        textStyle = typeArray.getInteger(R.styleable.IconTextView_textStyle, 0);
        iconText = typeArray.getString(R.styleable.IconTextView_iconText);
        typeArray.recycle();
    }

    private void initView(Context context) {
        View v = null;
        if (oriention == null) {
            oriention = TOP;
        }
        int leftSpacing = 0, rightSpacing = 0, topSpacing = 0, bottomSpacing = 0;

        if (oriention.equals(RIGHT)) {
            rightSpacing = spacing;
            v = LayoutInflater.from(context).inflate(R.layout.lib_layout_right_iconfont_item, null);
        } else if (oriention.equals(LEFT)) {
            leftSpacing = spacing;
            v = LayoutInflater.from(context).inflate(R.layout.lib_layout_left_iconfont_item, null);
        } else if (oriention.equals(CENTER)) {
            bottomSpacing = spacing;
            v = LayoutInflater.from(context).inflate(R.layout.lib_layout_center_iconfront_item, null);
        }
        else if (oriention.equals(BOTTOM)) {
            bottomSpacing = spacing;
            v = LayoutInflater.from(context).inflate(R.layout.lib_layout_bottom_iconfont_item, null);
        } else {
            topSpacing = spacing;
            v = LayoutInflater.from(context).inflate(R.layout.lib_layout_share_item, null);
        }
        tvIcon = (TextView) v.findViewById(R.id.share_platform_icon_tv);
        tvName = (TextView) v.findViewById(R.id.share_platform_name_tv);

        tvIcon.setTextSize(TypedValue.COMPLEX_UNIT_PX, iconSize);
        tvIcon.setTextColor(iconColor);
        tvIcon.setText(iconText);
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvName.setTextColor(textColor);
        tvName.setText(text);
        tvName.setPadding(leftSpacing, topSpacing, rightSpacing, bottomSpacing);
        if (textStyle > 0) {
            Typeface type = Typeface.create("", textStyle);
            tvName.setTypeface(type);
        }

//        initIconTypeface();
        addView(v);
    }

    public TextView getIconView() {
        return tvIcon;
    }

    public void setOriention(String str) {
        oriention = str;
    }

    public TextView getTextView() {
        return tvName;
    }

    public void setIconSize(float size) {
        tvIcon.setTextSize(size);
    }

    public void setIconColor(int color) {
        tvIcon.setTextColor(color);
    }

    public void setTextSize(float size) {
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTextColor(int color) {
        tvName.setTextColor(color);
    }

    public void setText(CharSequence text) {
        tvName.setText(text);
    }

    public void setIcons(Icon... icons) {
        IconfontUtil.setIcon(getContext(), tvIcon, icons);
    }
}