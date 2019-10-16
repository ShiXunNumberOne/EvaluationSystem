package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("eresult")
public class Eresult {
    private int id;
    private Integer scores_Id;
    private Long teacher_Id;
    private Double self_score;
    private Integer etask_id;
    private double teahcer_score;
    private double student_score;
    private double teahcer_count;
    private double student_count;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getScores_Id() {
        return scores_Id;
    }

    public void setScores_Id(Integer scores_Id) {
        this.scores_Id = scores_Id;
    }

    public Long getTeacher_Id() {
        return teacher_Id;
    }

    public void setTeacher_Id(Long teacher_Id) {
        this.teacher_Id = teacher_Id;
    }

    public Double getSelf_score() {
        return self_score;
    }

    public void setSelf_score(Double self_score) {
        this.self_score = self_score;
    }

    public Integer getEtask_id() {
        return etask_id;
    }

    public void setEtask_id(Integer etask_id) {
        this.etask_id = etask_id;
    }

    public double getTeahcer_score() {
        return teahcer_score;
    }

    public void setTeahcer_score(double teahcer_score) {
        this.teahcer_score = teahcer_score;
    }

    public double getStudent_score() {
        return student_score;
    }

    public void setStudent_score(double student_score) {
        this.student_score = student_score;
    }

    public double getTeahcer_count() {
        return teahcer_count;
    }

    public void setTeahcer_count(double teahcer_count) {
        this.teahcer_count = teahcer_count;
    }

    public double getStudent_count() {
        return student_count;
    }

    public void setStudent_count(double student_count) {
        this.student_count = student_count;
    }
}
