package com.projectzero.demo.samplemain.sample.sampleIconfont;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.library.util.iconfont.IconfontUtil;
import com.projectzero.library.widget.IconTextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.sample_activity_iconfont)
public class SampleIconfontActivity extends BaseProjectActivity {
    @ViewById(R.id.iconfont_tv)
    TextView     mTextView;

    @ViewById(R.id.iconfont_checkbox)
    CheckBox     mCheckBox;

    @ViewById(R.id.iconfont_checkbox2)
    CheckBox     mCheckBox2;

    @ViewById(R.id.iconfont_checkbox3)
    CheckBox     mCheckBox3;

    @ViewById(R.id.icontextview)
    IconTextView mIcontextview;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews void initView() {
        IconfontUtil.setIcon(this, mTextView, SampleTaobaoIcon.GITHUB);

        IconfontUtil.addIcon(this, mCheckBox, SampleTaobaoIcon.GITHUB);
        IconfontUtil.addIcon(this, mCheckBox, "#3593AB", SampleTaobaoIcon.GITHUB);
        IconfontUtil.addIcon(this, mCheckBox, "#4E4EE6", SampleTaobaoIcon.ANDROID);

        IconfontUtil.addIcon(this, mCheckBox2, SampleTaobaoIcon.GITHUB);
        IconfontUtil.addIcon(this, mCheckBox2, "#4E4EE6", 30, SampleTaobaoIcon.GITHUB);
        IconfontUtil.addIcon(this, mCheckBox2, "#4E4EE6", 50, SampleTaobaoIcon.GITHUB);

        IconfontUtil.addIcon(this, mCheckBox3, SampleTaobaoIcon.GITHUB, SampleTaobaoIcon.ANDROID);

        //直接在代码里赋值，不在xml中使用iconfont:iconText属性
        mIcontextview.setIcons(SampleTaobaoIcon.GITHUB, SampleTaobaoIcon.ANDROID);
    }
}
