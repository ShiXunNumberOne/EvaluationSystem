package com.mysiteforme.admin.service;

import com.mysiteforme.admin.entity.GradScore;

import java.util.HashMap;
import java.util.List;

public interface StatisticalService {
    /**整理流水表信息*/
    List<GradScore> clearUpFraction(String batchId);
    /**统计平价*/
    int statisticalFraction(List<GradScore> list,String batchId);
    /**统计参与评教学院*/
    List<HashMap> queryScoreDepartment(HashMap map);
    /**查询参与评教老师*/
    List<HashMap> queryScoreTeacher(HashMap map);
    /**查询评价人数*/
    List<HashMap> queryScoreMan(HashMap map);
    /**查询老师评价高于平均分的老师*/
    List<HashMap> queryAverageTeacherDayu(HashMap map);
    /**好坏饼图*/
    List<HashMap> queryPie(HashMap map);
    /**
     *二级联动查询批次
     */
    List<HashMap> queryBatchsList(HashMap map);

    /**
     * 二级联动查询问卷
     */
    List<HashMap> queryPapersList(HashMap map);

    /***
     *取评教成绩前5
     */
    List<HashMap> querySocoreLimit5(HashMap map);
    /**
     * 查询成绩
     */
    List<HashMap> querySocore(HashMap map);
    List<HashMap> queryScoreSize(HashMap map);

    /***
     *查询某老师 课程成绩
     */
    List<HashMap> queryTeacherScore(HashMap map);
    List<HashMap> queryTeacherScoreSize(HashMap map);
//    /**
//     * 查询被评分人具体评教详情
//     */
//    List<HashMap> querydetail(HashMap map);
//    List<HashMap> querydetailSize(HashMap map);
//    List<Fraction> selectEvaluationAnswers(int rater, int gradeds, int papers_id, int courses_id);
//    List<HashMap> selectFractions(int rater, int gradeds, int papers_id, int courses_id);
}
