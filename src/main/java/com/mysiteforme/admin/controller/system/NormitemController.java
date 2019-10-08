package com.mysiteforme.admin.controller.system;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Normitem;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/system/normitem")
public class NormitemController  extends BaseController {

//    @GetMapping("add")
//    @SysLog("跳转班级添加页面")
//    public String add(){
//        return "admin/system/clazz/add";
//    }
    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增选项数据")
    public RestResponse add(@RequestBody List<Normitem> normitems){
//        if(StringUtils.isBlank(normitems.getName())){
//            return RestResponse.failure("班级名不能为空");
//        }
        System.out.printf(normitems.toString());
//        if(clazz.getDepts() == null || clazz.getDepts() .size()==1){
//            return  RestResponse.failure("请选择学院");
//        }

//        clazzService.saveClazz(clazz);
        normitemService.insertNormitems(normitems);
//        if(clazz.getId() == null || clazz.getId() == 0){
//            return RestResponse.failure("保存班级信息出错");
//        }
        return RestResponse.success();
    }
}
