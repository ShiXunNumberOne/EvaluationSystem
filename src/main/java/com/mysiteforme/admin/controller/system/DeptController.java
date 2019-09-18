package com.mysiteforme.admin.controller.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.util.LayerData;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;


@Controller
@RequestMapping("admin/system/dept")
public class DeptController extends BaseController {
    @GetMapping("list")
    @SysLog("跳转班级列表页面")
    public String list(){
        return "admin/system/dept/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<Dept> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Dept> clazzLayerData = new LayerData<>();
        EntityWrapper<Dept> clazzEntityWrapper = new EntityWrapper<>();
        if(!map.isEmpty()){
            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                clazzEntityWrapper.like("login_name", keys).or().like("tel", keys).or().like("email", keys);
            }
        }
        Page<Dept> clazzPage = deptService.selectPage(new Page<>(page,limit),clazzEntityWrapper);
        clazzLayerData.setCount(clazzPage.getTotal());
        clazzLayerData.setData(clazzPage.getRecords());
        return  clazzLayerData;
    }

		 
}
