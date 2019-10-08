package com.mysiteforme.admin.entity.VO;

public class IndexOptionVO {
    private Long tid;       //指标ID
    private Integer oid;    //选项ID
    private String tname;   //指标名称
    private String oname;   //选项名称
    private double score;            //分值

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }
}
