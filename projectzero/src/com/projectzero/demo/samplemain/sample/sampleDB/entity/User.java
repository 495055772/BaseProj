package com.projectzero.demo.samplemain.sample.sampleDB.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.projectzero.library.base.BaseDbEntity;

@Table(name = "tuser")
public class User extends BaseDbEntity {

    private String name;

    private String password;
    
    @Column(column="group_id")
    private Integer groupId;

    @Column(column = "user_father_name")
    private Integer userFatherName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserFatherName() {
        return userFatherName;
    }

    public void setUserFatherName(Integer userFatherName) {
        this.userFatherName = userFatherName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
}
