package com.mysiteforme.admin.entity;

import com.mysiteforme.admin.base.DataEntity;

public class etask extends DataEntity<etask> {

    private String name;
    private String start_data;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_data() {
        return start_data;
    }

    public void setStart_data(String start_data) {
        this.start_data = start_data;
    }
}
