package com.mysiteforme.admin.entity.VO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.mysiteforme.admin.entity.Course;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.entity.User;

import java.io.Serializable;
import java.util.List;

public class Schedules implements Serializable {
    private Integer id;
    private Integer courseId; //课程id
    private Integer teacherId;  //教师id
    private Integer clazzId; //班级id
    private String address;     //上课地点
    private String hour;       //课时
    private String credit;     //学分
    private Integer status;    //状态

    @TableField(exist = false)
    private List<Course> courses;
    @TableField(exist = false)
    private List<Class> clazzs;
    @TableField(exist = false)
    private List<User> users;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Class> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<Class> clazzs) {
        this.clazzs = clazzs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
