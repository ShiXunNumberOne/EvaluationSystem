package com.mysiteforme.admin.service;

import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.entity.ScoreSum;

import java.util.HashMap;
import java.util.List;

public interface OnlineEvaluationService {
    List<HashMap> selectStudentEvaluation(Long u_id);
    List<HashMap> selectColleagueEvaluation(Long u_id);
    List<HashMap> selectOneselfEvaluation(Long u_id);
    List<Etask>  selectBatchName();
    List<HashMap> selectBatchIdStudentEvaluation(Long u_id,int batch_id);
    List<HashMap> selectBatchIdColleagueEvaluation(Long user_id,int batch_id);
    List<HashMap> selectBatchIdOneselfEvaluation(Long user_id,int batch_id);
    List<ScoreSum> StudentOnlineEvaluationFraction(int oid, int tid);
    boolean insertOnlineEvaluation(Long eavaluationId, Long earnedId, Integer questionnaireId, Integer course_id,float score);
}
