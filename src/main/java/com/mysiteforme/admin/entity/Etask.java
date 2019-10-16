package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysiteforme.admin.base.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class Etask{
    private Integer id;
    private String name;
    private Integer createuserId;
    @TableField(value = "start_data", fill = FieldFill.INSERT)
    private Date startData;
    @TableField(value = "end_data", fill = FieldFill.INSERT)
    private Date endData;
    private Integer status;
    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    //    @DateTimeFormat(pattern = "y-M-d")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "y-M-d",timezone = "GMT+8")
    public Date getStartData() {
        return startData;
    }

    public void setStartData(Date startData) {
        this.startData = startData;
    }
//    @DateTimeFormat(pattern = "y-M-d")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "y-M-d",timezone = "GMT+8")
    public Date getEndData() {
        return endData;
    }

    public void setEndData(Date endData) {
        this.endData = endData;
    }

    @TableField(exist = false)
    private List<User> users;
    @TableField(exist = false)
    private User user;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getCreateuserId() {
        return createuserId;
    }

    public void setCreateuserId(Integer createuserId) {
        this.createuserId = createuserId;
    }

    public User getUser() {
        return user;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
