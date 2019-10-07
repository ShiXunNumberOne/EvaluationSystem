package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

@TableName("normitem")
public class Normitem {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    @TableField(value="sort_code")
    private Integer sort;             //排序码
    private Long normtargetId;       //指标ID
    private double score;            //分值
    private Integer status;

    private List<Normtarget> normtargets;

    public List<Normtarget> getNormtargets() {
        return normtargets;
    }

    public void setNormtargets(List<Normtarget> normtargets) {
        this.normtargets = normtargets;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getNormtargetId() {
        return normtargetId;
    }

    public void setNormtargetId(Long normtargetId) {
        this.normtargetId = normtargetId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
