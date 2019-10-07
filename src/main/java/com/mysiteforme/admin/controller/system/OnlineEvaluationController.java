package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/system/onlineEvaluation")
public class OnlineEvaluationController extends BaseController {
    @GetMapping("student/list")
    @SysLog("跳转列表")
    public String list(Model model){
        return "admin/system/onlineEvaluation/student/list";
    }
}
