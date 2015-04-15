package com.projectzero.demo.samplemain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EActivity(R.layout.sample__activity_samplelist)
public class SampleListActivity extends BaseProjectActivity {

    @ViewById(R.id.samplelist_listview)
    ExpandableListView              mListview;

    SimpleExpandableListAdapter     adapter;
    List<Map<String, String>>       groups     = new ArrayList<Map<String, String>>();
    List<List<Map<String, Object>>> childsList = new ArrayList<List<Map<String, Object>>>();
    List<Map<String, Object>>       child      = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>>       child02    = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>>       child03    = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>>       child04    = new ArrayList<Map<String, Object>>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterView() {
        init();

        addChild2Util("DeviceInfo", com.projectzero.demo.samplemain.sample.sampleDeviceInfo.DeviceInfoActivity_.class);
        addChild2Util("HttpUtil", com.projectzero.demo.samplemain.sample.sampleHttp.HttpUtilActivity_.class);
        addChild2Util("ImageHelper", com.projectzero.demo.samplemain.sample.sampleImageHelper.ImageHelperActivity_.class);
        addChild2Util("ImageLoader", com.projectzero.demo.samplemain.sample.sampleImageLoader.ImageLoaderActivity_.class);
        addChild2Util("IconFont", com.projectzero.demo.samplemain.sample.sampleIconfont.SampleIconfontActivity_.class);

        addChild2Component("启动其他activity", com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.SampleActivity001_.class);
        addChild2Component("sqlite数据库操作", com.projectzero.demo.samplemain.sample.sampleDB.SampleDbActivity_.class);


//        addChild2Util("ImageImageLoader", com.pinganfang.business.sampleImageLoader.ImageLoaderActivity_.class);


//        addChild2Util("SnsShareUtil", com.pinganfang.business.sampleSns.SampleSnsActivity_.class);
//
//        // addChild2View("ImageViewFlipperDemo", ImageViewFlipperDemo.class);
//        addChild2View("popupwindow", com.pinganfang.business.samplePopupwindow.SimplePopupWindow_.class);
//

//        addChild2Component("haopanapi操作", com.pinganfang.business.sampleHaofangApi.HaofangapiActivity_.class);
//        addChild2Component("图片viewpager循环滑动",
//                com.pinganfang.business.sampleimageviewpager.ImageViewPagerActivity_.class);
//        addChild2Component("环状饼图",
//                com.pinganfang.business.sampleChartEngine.SampleChartActivity_.class);
//
//        addChild2Component("相册浏览", com.pinganfang.business.sampleAlbum.SampleAlumActivity_.class);
//        addChild2Component("二维码扫描", com.pinganfang.business.sampleQRcode.MainActivity_.class);
//        addChild2Component("滚轮组件", com.pinganfang.business.samplewheelview.WheelViewActivity_.class);
//        addChild2Component("listview组件", com.pinganfang.business.sampleListView.MainListviewActivity_.class);
//        addChild2Component("拍照", com.pinganfang.business.sampleTakePic.TakePicByCameralActivity_.class);
//        addChild2Component("多选相册", com.pinganfang.business.sampleTakePic.SampleMultipleAlbumActivity_.class);
//        addChild2Component("带动画效果的ExpandableListView组件",
//                com.pinganfang.business.sampleAnimatedExpandableListView.SampleAnimatedExpandableListView_.class);
//        addChild2Component("通讯录首字母导航IndexableLayout",
//                com.pinganfang.business.sampleIndexableLayout.SampleIndexableLayoutActivity_.class);
//
//        addProcressView("upgrade", com.pinganfang.business.sampleUpgrade.UpgradeActivity_.class);
//        addProcressView("backdoor", com.pinganfang.palibrary.backdoor.AppBackdoorActivity.class);
    }

    private void init() {
        Map<String, String> group1 = new HashMap<String, String>();
        group1.put("root", "util");
        groups.add(group1);

        Map<String, String> group2 = new HashMap<String, String>();
        group2.put("root", "view");
        groups.add(group2);

        Map<String, String> group3 = new HashMap<String, String>();
        group3.put("root", "component");
        groups.add(group3);

        Map<String, String> group4 = new HashMap<String, String>();
        group4.put("root", "process");
        groups.add(group4);

        childsList.add(child);
        childsList.add(child02);
        childsList.add(child03);
        childsList.add(child04);

        adapter = new SimpleExpandableListAdapter(this, groups, R.layout.sample__view__listitem_demo_group,
                new String[] { "root" }, new int[] { R.id.groupto }, childsList,
                R.layout.sample__view__listitem_demo_child, new String[] { "child" }, new int[] { R.id.childto });
        mListview.setAdapter(adapter);

        mListview.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                @SuppressWarnings("unchecked")
                Map<String, Object> childMap = (Map<String, Object>) adapter.getChild(groupPosition, childPosition);
                Class<?> link = (Class<?>) childMap.get("link");

                Intent intent = new Intent(SampleListActivity.this, link);
                SampleListActivity.this.startActivity(intent);
                return false;
            }
        });
    }

    private void addChild2Util(String name, final Class<?> link) {
        Map<String, Object> childdata = new HashMap<String, Object>();
        childdata.put("child", name);
        childdata.put("link", link);
        child.add(childdata);

        adapter.notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    private void addChild2View(String name, final Class<?> link) {
        Map<String, Object> childdata = new HashMap<String, Object>();
        childdata.put("child", name);
        childdata.put("link", link);
        child02.add(childdata);

        adapter.notifyDataSetChanged();
    }

    private void addChild2Component(String name, Class<?> link) {
        Map<String, Object> childdata = new HashMap<String, Object>();
        childdata.put("child", name);
        childdata.put("link", link);
        child03.add(childdata);

        adapter.notifyDataSetChanged();
    }

    private void addProcressView(String name, final Class<?> link) {
        Map<String, Object> childdata = new HashMap<String, Object>();
        childdata.put("child", name);
        childdata.put("link", link);
        child04.add(childdata);

        adapter.notifyDataSetChanged();
    }
}
