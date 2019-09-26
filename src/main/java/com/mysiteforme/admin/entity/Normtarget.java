package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;

import java.util.List;
@TableName("nomtarget")
public class Normtarget {
    private Long id;
    private long pid;         //父节点
    private Integer sortCode;  //排序码
    private String name;
    private double entropy;
    private Integer isNodelast;
    private Integer status;
    /**
     * 节点层次（第一层，第二层，第三层....）
     */
    protected Integer level;
    @TableField(exist = false)
    protected List<Normtarget> children;

    @TableField(exist = false)
    protected Normtarget parentTree;
    /**
     * 是否显示
     */
    @TableField(value="is_show",strategy= FieldStrategy.IGNORED)
    private Boolean isShow;

    public Normtarget() {
        super();
        this.sortCode = 30;
    }
    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }
    public Normtarget(Long id) {
        this.id = id;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Normtarget> getChildren() {
        return children;
    }

    public void setChildren(List<Normtarget> children) {
        this.children = children;
    }

    public Normtarget getParentTree() {
        return parentTree;
    }

    public void setParentTree(Normtarget parentTree) {
        this.parentTree = parentTree;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public Integer getIsNodelast() {
        return isNodelast;
    }

    public void setIsNodelast(Integer isNodelast) {
        this.isNodelast = isNodelast;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
