package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.dao.QuestionnaireDao;
import com.mysiteforme.admin.entity.VO.IndexOptionVO;
import com.mysiteforme.admin.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    public QuestionnaireDao questionnaireDao;
    @Override
    public List<Map<String, Object>> selectOnlineEvaluation() {
        List<IndexOptionVO> list = questionnaireDao.selectOnlineEvaluation();
        Map<Long, List<IndexOptionVO>> map = list.stream().collect(Collectors.groupingBy(IndexOptionVO::getTid));
        List<Map<String, Object>> ret = new LinkedList<Map<String, Object>>();
        map.forEach( (k,v)->{
            Map<String, Object> tmap = new LinkedHashMap<>();
            tmap.put("target_id", k);

            List<Map<String, Object>> optionList = new LinkedList<>();
            v.stream().forEach(item->{
                Map<String, Object> omap = new LinkedHashMap<>();
                tmap.put("target_name", item.getTname());

                omap.put("options_id", item.getOid());
                omap.put("options_content", item.getOname());
                omap.put("options_weight", item.getScore());
                optionList.add(omap);
            });
            tmap.put("options", optionList);

            ret.add(tmap);
        });

        return ret;
    }

    @Override
    public List<Map<String, Object>> selectColleagueOnlineEvaluation() {
        List<IndexOptionVO> list = questionnaireDao.selectColleagueOnlineEvaluation();
        Map<Long, List<IndexOptionVO>> map = list.stream().collect(Collectors.groupingBy(IndexOptionVO::getTid));
        List<Map<String, Object>> ret = new LinkedList<Map<String, Object>>();
        map.forEach( (k,v)->{
            Map<String, Object> tmap = new LinkedHashMap<>();
            tmap.put("target_id", k);

            List<Map<String, Object>> optionList = new LinkedList<>();
            v.stream().forEach(item->{
                Map<String, Object> omap = new LinkedHashMap<>();
                tmap.put("target_name", item.getTname());

                omap.put("options_id", item.getOid());
                omap.put("options_content", item.getOname());
                omap.put("options_weight", item.getScore());
                optionList.add(omap);
            });
            tmap.put("options", optionList);

            ret.add(tmap);
        });

        return ret;
    }

    @Override
    public List<Map<String, Object>> selectOneselfOnlineEvaluation() {
        List<IndexOptionVO> list = questionnaireDao.selectOneselfOnlineEvaluation();
        Map<Long, List<IndexOptionVO>> map = list.stream().collect(Collectors.groupingBy(IndexOptionVO::getTid));
        List<Map<String, Object>> ret = new LinkedList<Map<String, Object>>();
        map.forEach( (k,v)->{
            Map<String, Object> tmap = new LinkedHashMap<>();
            tmap.put("target_id", k);

            List<Map<String, Object>> optionList = new LinkedList<>();
            v.stream().forEach(item->{
                Map<String, Object> omap = new LinkedHashMap<>();
                tmap.put("target_name", item.getTname());

                omap.put("options_id", item.getOid());
                omap.put("options_content", item.getOname());
                omap.put("options_weight", item.getScore());
                optionList.add(omap);
            });
            tmap.put("options", optionList);

            ret.add(tmap);
        });

        return ret;
    }
}
