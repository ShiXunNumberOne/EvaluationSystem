package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.service.OnlineEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/system/onlineEvaluation")
public class OnlineEvaluationController extends BaseController {
    @Autowired
    private OnlineEvaluationService onlineEvaluationService;
    @GetMapping("student/list")
    @SysLog("跳转列表")
    public String list(Model model){
        return "admin/system/onlineEvaluation/student/list";
    }
    @GetMapping("oneself/list")
    @SysLog("跳转列表")
    public String oneselfList(Model model){
        return "admin/system/onlineEvaluation/oneself/list";
    }
    @GetMapping("colleague/list")
    @SysLog("跳转列表")
    public String solleagueList(Model model){
        return "admin/system/onlineEvaluation/colleague/list";
    }
    @ResponseBody
    @RequestMapping("selectStudentEvaluation")
    public Map selectStudentEvaluation(){
        Map result = new HashMap();
        List<HashMap> student = onlineEvaluationService.selectStudentEvaluation(MySysUser.id());
        result.put("code",0);
        result.put("msg","");
        result.put("count",student.size());
        result.put("data",student);
        return result;
    }

    @ResponseBody
    @RequestMapping("selectBatchName")
    public  Map selectBatchName(){
        Map result = new HashMap();
        List<Etask> batches = onlineEvaluationService.selectBatchName();
        result.put("data",batches);
        return result;
    }

    @ResponseBody
    @RequestMapping("selectBatchIdStudentEvaluation")
    public Map selectBatchIdStudentEvaluation(int batch_id){
        Map result = new HashMap();
        List<HashMap> student = onlineEvaluationService.selectBatchIdStudentEvaluation(MySysUser.id(),batch_id);
        result.put("code",0);
        result.put("msg","");
        result.put("count",student.size());
        result.put("data",student);
        return result;
    }
    @ResponseBody
    @RequestMapping("selectColleagueEvaluation")
    public Map selectColleagueEvaluation(){
        Map result = new HashMap();
        List<HashMap> users = onlineEvaluationService.selectColleagueEvaluation(MySysUser.id());
        result.put("code",0);
        result.put("msg","");
        result.put("count",users.size());
        result.put("data",users);
        return result;
    }

    @ResponseBody
    @RequestMapping("selectBatchIdColleagueEvaluation")
    public Map selectBatchIdColleagueEvaluation(int batch_id){
        Map result = new HashMap();
        List<HashMap> users = onlineEvaluationService.selectBatchIdColleagueEvaluation(MySysUser.id(),batch_id);
        result.put("code",0);
        result.put("msg","");
        result.put("count",users.size());
        result.put("data",users);
        return result;
    }
    @ResponseBody
    @RequestMapping("selectOneselfEvaluation")
    public Map selectOneselfEvaluation(){
        Map result = new HashMap();
        List<HashMap> users = onlineEvaluationService.selectOneselfEvaluation(MySysUser.id());
        result.put("code",0);
        result.put("msg","");
        result.put("count",users.size());
        result.put("data",users);
        return result;
    }
    @ResponseBody
    @RequestMapping("selectBatchIdOneselfEvaluation")
    public Map selectBatchIdOneselfEvaluation(int batch_id){
        Map result = new HashMap();
        List<HashMap> users = onlineEvaluationService.selectBatchIdOneselfEvaluation(MySysUser.id(),batch_id);
        result.put("code",0);
        result.put("msg","");
        result.put("count",users.size());
        result.put("data",users);
        return result;
    }
}
