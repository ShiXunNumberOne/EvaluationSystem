package com.mysiteforme.admin.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Course;
import com.mysiteforme.admin.util.LayerData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("admin/system/course")
public class CourseController extends BaseController {

    @GetMapping("list")
    @SysLog("跳转班级列表页面")
    public String list(){
        return "admin/system/course/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<Course> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                 ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Course> clazzLayerData = new LayerData<>();
        EntityWrapper<Course> clazzEntityWrapper = new EntityWrapper<>();
        if(!map.isEmpty()){
            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                clazzEntityWrapper.like("login_name", keys).or().like("tel", keys).or().like("email", keys);
            }
        }
        Page<Course> clazzPage = courseService.selectPage(new Page<>(page,limit),clazzEntityWrapper);
        clazzLayerData.setCount(clazzPage.getTotal());
        clazzLayerData.setData(clazzPage.getRecords());
        return  clazzLayerData;
    }


}
