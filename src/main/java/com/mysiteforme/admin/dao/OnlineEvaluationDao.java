package com.mysiteforme.admin.dao;

import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.entity.ScoreSum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public interface OnlineEvaluationDao {
    List<HashMap> selectStudentEvaluation(Long u_id);
    List<HashMap> selectColleagueEvaluation(Long u_id);
    List<HashMap> selectOneselfEvaluation(Long u_id);
    List<Etask>  selectBatchName();
    List<HashMap> selectBatchIdStudentEvaluation(Long user_id,int batch_id);
    List<HashMap> selectBatchIdColleagueEvaluation(Long user_id,int batch_id);
    List<HashMap> selectBatchIdOneselfEvaluation(Long user_id,int batch_id);
   List<ScoreSum> StudentOnlineEvaluationFraction(@Param("oid") int oid, @Param("tid") int tid);
   int insertOnlineEvaluation(@Param("eavaluationId") Long eavaluationId,@Param("earnedId")Long earnedId,@Param("questionnaireId")Integer questionnaireId,@Param("courseId") Integer course_id,@Param("score")float score);
    List<HashMap> selectIfEvaluation(@Param("eavaluationId") Long eavaluationId,@Param("earnedId")Long earnedId,@Param("courseId") Integer courseId,@Param("questionnaireId")Integer questionnaireId);
    List<Etask> selectIfStartEvaluation(Integer batch_id);
}
