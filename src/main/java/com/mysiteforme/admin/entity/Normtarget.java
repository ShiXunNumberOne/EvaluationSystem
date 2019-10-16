package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;

import java.util.List;
@TableName("normtarget")
public class Normtarget {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long pid;         //父节点
    @TableField(value="sort")
    private Integer sort;  //排序码
    @TableField(value="r_id")
    private Long rid;
    private String name;
    private double entropy;  //权重

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @TableField(value="isNodelast")
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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public Normtarget() {
            super();
          this.sort = 30;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
