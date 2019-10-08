package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.service.OnlineEvaluationService;
import com.mysiteforme.admin.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/system/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @RequestMapping("student/GoOnlineEvaluation")
    @SysLog("跳转列表")
    public String GoOnlineEvaluation(){
        return "admin/system/onlineEvaluation/student/onlineEvaluation";
    }
    @RequestMapping("colleague/GoOnlineEvaluation")
    @SysLog("跳转列表")
    public String colleagueList(){
        return "admin/system/onlineEvaluation/colleague/onlineEvaluation";
    }
    @RequestMapping("oneself/GoOnlineEvaluation")
    @SysLog("跳转列表")
    public String oneselfList(){
        return "admin/system/onlineEvaluation/oneself/onlineEvaluation";
    }
    @ResponseBody
    @RequestMapping("selectOnlineEvaluation")
    public Map selectOnlineEvaluation(){
        Map result=new HashMap();
        List<Map<String, Object>> online = questionnaireService.selectOnlineEvaluation();
        result.put("data",online);
        return result;
    }
    @ResponseBody
    @RequestMapping("selectColleagueOnlineEvaluation")
    public Map selectColleagueOnlineEvaluation(){
        Map result=new HashMap();
        List<Map<String, Object>> online = questionnaireService.selectColleagueOnlineEvaluation();
        result.put("data",online);
        return result;
    }
    @ResponseBody
    @RequestMapping("selectOneselfOnlineEvaluation")
    public Map selectOneselfOnlineEvaluation(){
        Map result=new HashMap();
        List<Map<String, Object>> online = questionnaireService.selectOneselfOnlineEvaluation();
        result.put("data",online);
        return result;
    }
}
