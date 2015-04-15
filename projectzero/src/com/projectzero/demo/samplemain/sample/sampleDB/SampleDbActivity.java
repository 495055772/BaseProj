package com.projectzero.demo.samplemain.sample.sampleDB;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.projectzero.demo.R;
import com.projectzero.demo.base.BaseProjectActivity;
import com.projectzero.demo.samplemain.sample.sampleDB.entity.Group;
import com.projectzero.demo.samplemain.sample.sampleDB.entity.User;
import com.projectzero.library.util.DevUtil;
import com.projectzero.library.util.JsonUtil;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.sample__activity_sampledb)
public class SampleDbActivity extends BaseProjectActivity {

    @ViewById
    TextView temp_tv;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews void afterViews() {
        try {
            if (!app.getDb().tableIsExist(User.class)) {
                insertData();
            }

            // 查找
            User user = app.getDb().findById(User.class, 1);
            DevUtil.v("jackzhou", JsonUtil.toJSONString(user));

            List<User> list = app.getDb().findAll(User.class);// 通过类型查找
            DevUtil.v("jackzhou", JsonUtil.toJSONString(list));

            User user002 = app.getDb().findFirst(Selector.from(User.class).where("name", "=", "name002"));
            DevUtil.v("jackzhou", JsonUtil.toJSONString(user002));

            // 复杂条件查询
            List<Group> list002 = app.getDb().findAll(
                    Selector.from(Group.class).where("id", "<=", 3)
                            .and(WhereBuilder.b("size", "<", 60).or("size", ">", 100)).orderBy("id", true).limit(0)
                            .offset(10));
            DevUtil.v("jackzhou", JsonUtil.toJSONString(list002));

            // 跨表查询
            String sql = "select tuser.name as name,tuser.password as pwd from tuser,tgroup where tuser.group_id = tgroup.id and tgroup.name = 'group001'";
            List<DbModel> dbModel = app.getDb().findDbModelAll(new SqlInfo(sql));
            DevUtil.v("jackzhou", JsonUtil.toJSONString(dbModel));
            DevUtil.v("jackzhou", dbModel.get(0).getString("pwd"));

        } catch (DbException e) {
            e.printStackTrace();
        }

        temp_tv.setText("done");
    }

    private void insertData() throws DbException {
        // User
        User user001 = new User();
        user001.setName("name001");
        user001.setPassword("password001");
        user001.setGroupId(1);
        app.getDb().save(user001);

        User user002 = new User();
        user002.setName("name002");
        user002.setPassword("password002");
        user002.setGroupId(1);
        app.getDb().save(user002);

        User user003 = new User();
        user003.setName("name003");
        user003.setPassword("password003");
        user003.setGroupId(2);
        app.getDb().save(user003);

        // Group
        Group group001 = new Group();
        group001.setName("group001");
        group001.setSize(100);
        app.getDb().save(group001);

        Group group002 = new Group();
        group002.setName("group002");
        group002.setSize(50);
        app.getDb().save(group002);

        Group group003 = new Group();
        group003.setName("group003");
        group003.setSize(150);
        app.getDb().save(group003);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
