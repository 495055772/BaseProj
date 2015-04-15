package com.projectzero.demo.samplemain.sample.sampleDB.entity;

import com.lidroid.xutils.db.annotation.Table;
import com.projectzero.library.base.BaseDbEntity;


@Table(name="tgroup")
public class Group extends BaseDbEntity {
    private String name;
    private Integer size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
