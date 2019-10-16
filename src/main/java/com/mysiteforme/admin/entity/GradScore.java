package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

@TableName("grad_score")
public class GradScore {
    private int id;
    private Long eavaluationId;  //评分人ID
    private Long earnedId;          //被评人id
    private Integer questionnaireId;//批次ID
    private Integer course_id;      //课程ID
    private float score;          //得分

    @TableField(exist = false)
    private String rname;        //角色
    @TableField(exist = false)
    private List<Role> role;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getEavaluationId() {
        return eavaluationId;
    }

    public void setEavaluationId(Long eavaluationId) {
        this.eavaluationId = eavaluationId;
    }

    public Long getEarnedId() {
        return earnedId;
    }

    public void setEarnedId(Long earnedId) {
        this.earnedId = earnedId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
