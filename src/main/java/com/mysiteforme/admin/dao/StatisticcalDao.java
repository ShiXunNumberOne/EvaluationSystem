package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Eresult;
import com.mysiteforme.admin.entity.GradScore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public interface StatisticcalDao{
    /**
     * @param map
     * @return 整理流水表信息
     */
    List<GradScore> clearUpScore(HashMap map);
    List<HashMap> queryTeacherStats(HashMap map);
    /**
     * @param map
     * @return 查询评教老师评教的科目
     */
    List<HashMap> queryTeacherCourse(HashMap map);
    int insertBatchScore(List<Eresult> list);
    /**
     * @param map
     * @return 统计参与评教学院
     */
    List<HashMap> queryScoreDepartment(HashMap map);
    List<HashMap> queryPapers(HashMap map);
    /**
     * @param map
     * @return 查询参与评教老师
     */
    List<HashMap> queryScoreTeacher(HashMap map);
    /**
     * @param map
     * @return 查询评价人数
     */
    List<HashMap> queryScoreMan(HashMap map);
    /**
     * @param map
     * @return 查询老师评价高于平均分的老师
     */
    List<HashMap> queryAverageTeacherDayu(HashMap map);

    /**
     *  生成 好坏饼图
     */
    List<HashMap> queryTeacherDaYu90(HashMap map);
    List<HashMap> queryTeacherDaYu60XiaoYu90(HashMap map);
    List<HashMap> queryTeacherXiaoyu60(HashMap map);

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
    List<HashMap> queryTeacherScoreList(HashMap map);

//    /**
//     * 查询被评分人具体评教详情
//     */
//    List<HashMap> querydetail(HashMap map);
//    List<HashMap> querydetailSize(HashMap map);
//
//    List<Fraction> selectEvaluationAnswers(int rater, int gradeds, int papers_id, int courses_id);
//    List<HashMap> selectFractions(int rater,int gradeds,int papers_id,int courses_id);
}
