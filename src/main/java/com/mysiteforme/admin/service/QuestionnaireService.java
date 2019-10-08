package com.mysiteforme.admin.service;

import java.util.List;
import java.util.Map;

public interface QuestionnaireService {
    List<Map<String, Object>> selectOnlineEvaluation();
    List<Map<String, Object>> selectColleagueOnlineEvaluation();
    List<Map<String, Object>> selectOneselfOnlineEvaluation();
}
