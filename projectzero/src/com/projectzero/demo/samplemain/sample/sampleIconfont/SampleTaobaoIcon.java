package com.projectzero.demo.samplemain.sample.sampleIconfont;

import com.projectzero.demo.R;
import com.projectzero.library.util.iconfont.depend.Icon;
import com.projectzero.library.util.iconfont.depend.TypefaceManager;

public enum SampleTaobaoIcon implements Icon {
    ANDROID(0x3605),
    GITHUB(0x3606);

    private final int mIconUtfValue;

    private SampleTaobaoIcon(int iconUtfValue) {
        mIconUtfValue = iconUtfValue;
    }

    @Override
    public TypefaceManager.IconicTypeface getIconicTypeface() {
        return TypefaceManager.IconicTypeface.getInstance(R.raw.smaple_taobao);
    }

    @Override
    public int getIconUtfValue() {
        return mIconUtfValue;
    }
}
