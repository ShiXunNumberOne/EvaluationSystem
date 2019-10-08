package com.mysiteforme.admin.dao;

import com.mysiteforme.admin.entity.VO.IndexOptionVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuestionnaireDao {

    List<IndexOptionVO>  selectOnlineEvaluation();
    List<IndexOptionVO> selectColleagueOnlineEvaluation();
    List<IndexOptionVO> selectOneselfOnlineEvaluation();
}
