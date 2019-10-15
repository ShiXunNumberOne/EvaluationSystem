package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.dao.StatisticcalDao;
import com.mysiteforme.admin.entity.Eresult;
import com.mysiteforme.admin.entity.GradScore;
import com.mysiteforme.admin.entity.Group;
import com.mysiteforme.admin.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private StatisticcalDao statsMapper;

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/4 10:22
     * @param
     * @Description
     */
    @Override
    public List<GradScore> clearUpFraction(String batchId) {
        HashMap map = new HashMap();
        map.put("batchId",batchId);
        List<GradScore> list = statsMapper.clearUpScore(map);
        int result = statisticalFraction(list,batchId);
        return list;
    }

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/4 11:33
     * @param
     * @Description 统计平价
     */
    @Override
    public int statisticalFraction(List<GradScore> list,String batchId) {
        HashMap mapOne = new HashMap();
        mapOne.put("batchId",batchId);
        double studentFraction = 0;
        double teacherFraction = 0;
        double num = 0;
        List<Eresult> statisticalDemo = new ArrayList<Eresult>();

//        List<HashMap> papers_IdList =  statsMapper.queryPapers(mapOne);
//        Integer papersId;
//        for (HashMap papers_Idmap :papers_IdList ){

//            papersId = Integer.parseInt(papers_Idmap.get("eavaluationId").toString());
            HashMap mapTwo = new HashMap();
            mapTwo.put("batchId",batchId);
            List<HashMap> ID = statsMapper.queryTeacherStats(mapTwo);
//            List<GradScore> listPapers  = list.stream().filter(o -> o.getQuestionnaireId().equals(papers_Idmap.get("questionnaireId"))).collect(Collectors.toList());
            for (HashMap map : ID){
                //TODO 某老师 的评教流水
                List<GradScore> listA  = list.stream().filter(o -> o.getEarnedId().equals(map.get("earnedId"))).collect(Collectors.toList());
                HashMap hashMap = new HashMap();
                hashMap.put("batchId",batchId);
                hashMap.put("earnedId",map.get("earnedId"));
                    List<GradScore> listStudent  = listA.stream().filter(o -> o.getRname().equals("学生")).collect(Collectors.toList());

                    int studetnCount = 0;
                    int teacherCount = 0;
                    int teacherSelfCount = 0;

                    double studentScore = 0.0;
                    double teacherScore = 0.0;
                    double teacherSelfScore = 0.0;
                    double score = 0.0;

                    for (GradScore demo1 :listStudent){
                        studentScore = studentScore + demo1.getScore();
                        studetnCount++;
                    }

                    List<GradScore> listTeacher  = listA.stream().filter(o -> o.getRname().equals("教师")).collect(Collectors.toList());

                    for (GradScore demo1 : listTeacher){
                        // TODO 自己给自己 的某一课程评教 当自己的ID 和 循环的ID 就代表是自评
                        if (demo1.getEavaluationId() ==  map.get("earnedId")){
                            teacherSelfScore = teacherSelfScore + demo1.getScore();
                            teacherSelfCount++;
                        }
                        else{
                            teacherScore = teacherScore+demo1.getScore();
                            teacherCount++;
                        }
                    }

                    if (teacherSelfCount != 0){
                        teacherSelfScore = teacherSelfScore/teacherSelfCount*0.2;
                    }

                    if (teacherCount != 0){
                        teacherScore = teacherScore/teacherCount*0.5;
                    }

                    if (studetnCount != 0){
                        studentScore = studentScore/studetnCount*0.3;
                    }
                    score = teacherSelfScore + teacherScore + studentScore;
                    Eresult eresult = new Eresult();
                    eresult.setScore(score);
                    eresult.setEtask_id(Integer.parseInt(batchId.toString()));
                    eresult.setTeahcer_score(teacherScore);
                    eresult.setTeahcer_count(teacherCount);
                    eresult.setStudent_score(studentScore);
                    eresult.setStudent_count(studetnCount);
                    eresult.setSelf_score(teacherSelfScore);
                    eresult.setTeacher_Id(Long.parseLong(map.get("earnedId").toString()));
                    statisticalDemo.add(eresult);

                    studetnCount = 0;
                    teacherCount = 0;
                    teacherSelfCount = 0;

                    studentScore = 0.0;
                    teacherScore = 0.0;
                    teacherSelfScore = 0.0;
                    score = 0.0;
            }
        int i =0;
        int result = statsMapper.insertBatchScore(statisticalDemo);
        return result;
    }

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/4 14:29
     * @param
     * @Description 统计参与评教学院
     */
    @Override
    public List<HashMap> queryScoreDepartment(HashMap map) {
        List<HashMap> list = statsMapper.queryScoreDepartment(map);
        return list;
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/4 14:20
     * @param
     * @Description 查询参与评教老师
     */
    @Override
    public List<HashMap> queryScoreTeacher(HashMap map) {
        List<HashMap> list = statsMapper.queryScoreTeacher(map);
        return list;
    }

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/5 10:08
     * @param
     * @Description 查询评价人数
     */
    @Override
    public List<HashMap> queryScoreMan(HashMap map) {
        List<HashMap> list = statsMapper.queryScoreMan(map);
        return list;
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/5 10:44
     * @param
     * @Description 查询老师评价高于平均分的老师
     */
    @Override
    public List<HashMap> queryAverageTeacherDayu(HashMap map) {
        List<HashMap> list = statsMapper.queryAverageTeacherDayu(map);
        return list;
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/5 10:44
     * @param
     * @Description queryPie
     */
    @Override
    public List<HashMap> queryPie(HashMap map) {
        List<HashMap> listA = statsMapper.queryTeacherDaYu90(map);
        List<HashMap> listB = statsMapper.queryTeacherDaYu60XiaoYu90(map);
        List<HashMap> listC = statsMapper.queryTeacherXiaoyu60(map);
        List<HashMap> list = new ArrayList<HashMap>();
        HashMap map1 = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map1.put("size",listA.size());
        map1.put("name","优秀");
        map2.put("size",listB.size());
        map2.put("name","中等");
        map3.put("name","良好");
        map3.put("size",listC.size());
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return  list;
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/10 9:50
     * @param
     * @Description 二级联动查询批次
     *
     */
    @Override
    public List<HashMap> queryBatchsList(HashMap map) {
        return statsMapper.queryBatchsList(map);
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/10 9:59
     * @param
     * @Description  二级联动查询问卷
     */
    @Override
    public List<HashMap> queryPapersList(HashMap map) {
        return statsMapper.queryPapersList(map);
    }

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/10 14:56
     * @param
     * @Description 取评教成绩前5
     */
    @Override
    public List<HashMap> querySocoreLimit5(HashMap map) {
        return statsMapper.querySocoreLimit5(map);
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/10 16:44
     * @param
     * @Description 查询成绩
     */
    @Override
    public List<HashMap> querySocore(HashMap map) {
        return statsMapper.querySocore(map);
    }

    @Override
    public List<HashMap> queryScoreSize(HashMap map) {
        return statsMapper.queryScoreSize(map);
    }

    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/12 9:17
     * @param
     * @Description 查询某老师 课程成绩
     */
    @Override
    public List<HashMap> queryTeacherScore(HashMap map) {
        List<HashMap> res = new ArrayList<HashMap>();
        List<HashMap> list = statsMapper.queryTeacherScore(map);
        List<HashMap> courseList = statsMapper.queryTeacherScoreList(map);
        String teacherName= list.get(0).get("user_name").toString();
        String teacherID= list.get(0).get("user_id").toString();
        String paperId= list.get(0).get("papers_id").toString();
        String couresName = null;
        String couresNID = null;
        for (HashMap coursID :courseList){
            double studentFraction = 0.0;
            double teacherFraction = 0.0;
            double stuSize = 1;
            double teaSize = 1;
            List<HashMap> listCourse  = list.stream().filter(o -> o.get("courses_id").equals(coursID.get("courses_id"))).collect(Collectors.toList());
            couresName = listCourse.get(0).get("courses_name").toString();
            couresNID =   listCourse.get(0).get("courses_id").toString();
            List<HashMap>  listStudent =listCourse.stream().filter(o -> o.get("role_name").equals("学生")).collect(Collectors.toList());
            if (listStudent.size() != 0){
                stuSize = listStudent.size();
            }

            for(HashMap demo:listStudent){
                studentFraction = studentFraction + Double.parseDouble(demo.get("fractions").toString());
            }
            List<HashMap>  listTeacher =listCourse.stream().filter(o -> o.get("role_name").equals("教师")).collect(Collectors.toList());
            if (listTeacher.size() != 0){
                teaSize = listTeacher.size();
            }
            for(HashMap demo:listTeacher){

                teacherFraction = teacherFraction + Double.parseDouble(demo.get("fractions").toString());
            }
            HashMap map1 = new HashMap();
            double score  = studentFraction*0.4/stuSize+teacherFraction*0.6/teaSize;
            map1.put("studentScore",(double) Math.round(studentFraction/stuSize * 100) / 100);
            map1.put("teacherScore",(double) Math.round(teacherFraction/teaSize * 100) / 100);
            map1.put("score",(double) Math.round(score * 100) / 100);
            map1.put("teacherID",teacherID);
            map1.put("teacherName",teacherName);
            map1.put("couresName",couresName);
            map1.put("couresID",couresNID);
            map1.put("paperId",paperId);
            res.add(map1);
            teacherFraction = 0.0;
            studentFraction = 0.0;

        }
        return res;
    }

    @Override
    public List<HashMap> queryTeacherScoreSize(HashMap map) {
        return statsMapper.queryTeacherScoreSize(map);
    }
    /**
     * @Author xiaoyi
     * @Return
     * @Date 2019/10/12 11:54
     * @param
     * @Description 查询被评分人具体评教详情
     */
//    @Override
//    public List<HashMap> querydetail(HashMap map) {
//        return statsMapper.querydetail(map);
//    }
//
//    @Override
//    public List<HashMap> querydetailSize(HashMap map) {
//        return statsMapper.querydetailSize(map);
//    }
//    @Override
//    public List<Fraction> selectEvaluationAnswers(int rater, int gradeds, int papers_id, int courses_id) {
//        return statsMapper.selectEvaluationAnswers(rater, gradeds, papers_id, courses_id);
//    }
//
//    @Override
//    public List<HashMap> selectFractions(int rater, int gradeds, int papers_id, int courses_id) {
//        return statsMapper.selectFractions(rater, gradeds, papers_id, courses_id);
//    }
}
