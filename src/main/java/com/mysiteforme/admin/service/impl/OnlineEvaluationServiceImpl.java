package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.dao.OnlineEvaluationDao;
import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.service.OnlineEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class OnlineEvaluationServiceImpl implements OnlineEvaluationService {
    @Autowired
    public OnlineEvaluationDao onlineEvaluationDao;
    @Override
    public List<HashMap> selectStudentEvaluation(Long u_id) {
        return onlineEvaluationDao.selectStudentEvaluation(u_id);
    }

    @Override
    public List<HashMap> selectColleagueEvaluation(Long u_id) {
        return onlineEvaluationDao.selectColleagueEvaluation(u_id);
    }

    @Override
    public List<HashMap> selectOneselfEvaluation(Long u_id) {
        return onlineEvaluationDao.selectOneselfEvaluation(u_id);
    }

    @Override
    public List<Etask> selectBatchName() {
        return onlineEvaluationDao.selectBatchName();
    }

    @Override
    public List<HashMap> selectBatchIdStudentEvaluation(Long u_id, int batch_id) {
        return onlineEvaluationDao.selectBatchIdStudentEvaluation(u_id,batch_id);
    }

    @Override
    public List<HashMap> selectBatchIdColleagueEvaluation(Long user_id, int batch_id) {
        return onlineEvaluationDao.selectBatchIdColleagueEvaluation(user_id,batch_id);
    }

    @Override
    public List<HashMap> selectBatchIdOneselfEvaluation(Long user_id, int batch_id) {
        return onlineEvaluationDao.selectBatchIdOneselfEvaluation(user_id,batch_id);
    }
}