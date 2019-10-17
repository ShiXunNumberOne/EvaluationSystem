package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.entity.ScoreSum;
import com.mysiteforme.admin.service.OnlineEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping("selectIfEvaluation")
    public Map selectIfEvaluation(Long gradeds,Integer course_id,Integer etask_id){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        Map result = new HashMap();
        List<HashMap> users = onlineEvaluationService.selectIfEvaluation(MySysUser.id(), gradeds,course_id,etask_id);
        if (users.size()>0){
            result.put("data",1);
        }else{
            result.put("data",0);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("selectIfStartEvaluation")
    public Map  selectIfStartEvaluation(Integer batch_Id){
        Map result = new HashMap();
        List<Etask> batches = onlineEvaluationService.selectIfStartEvaluation(batch_Id);
        if(batches.get(0).getStatus()==1){
            result.put("data",1);
        }else{
            result.put("data",0);
        }
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
    @ResponseBody
    @RequestMapping("OnlineEvaluationFraction")
    public Map OnlineEvaluationFraction(String optionsAll_id ,Long earnedId,Integer questionnaireId,Integer courses_id,String answers,String target_name_id){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        HttpSession session = request.getSession();

        String a[]=optionsAll_id.split(",");
        String t[]=target_name_id.split(",");
        Map result =new HashMap();
        float sum = 0;
        for (int i=0;i<a.length;i++){
            String options = a[i];
            String targets_id = t[i];
            int options_id = Integer.parseInt(options);
            int target_id = Integer.parseInt(targets_id);
//            ScoreSum scoreSum = new ScoreSum();
            List<ScoreSum> score = onlineEvaluationService.StudentOnlineEvaluationFraction(options_id,target_id);
            sum = sum + score.get(0).getScore();
        }
        Float score = Float.valueOf(sum);
        System.out.println(sum);
        if(onlineEvaluationService.insertOnlineEvaluation(MySysUser.id(),earnedId,questionnaireId,courses_id,score)){
            result.put("data",1);
        }else{
            result.put("data",0);
        }

        return result;
    }
}
