package com.mysiteforme.admin.service;

import com.mysiteforme.admin.entity.Etask;

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
}
