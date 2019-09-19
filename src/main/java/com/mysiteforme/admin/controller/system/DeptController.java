package com.mysiteforme.admin.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;


@Controller
@RequestMapping("admin/system/dept")
public class DeptController extends BaseController {
    @GetMapping("list")
    @SysLog("跳转学院列表页面")
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
    @GetMapping("add")
    @SysLog("跳转班级添加页面")
    public String add(){
        return "admin/system/dept/add";
    }

    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增班级数据")
    public RestResponse add(@RequestBody  Dept dept){
        if(StringUtils.isBlank(dept.getName())){
            return RestResponse.failure("班级名不能为空");
        }
        deptService.saveDept(dept);
        if(dept.getId() == null || dept.getId() == 0){
            return RestResponse.failure("保存班级信息出错");
        }
        return RestResponse.success();
    }
    @GetMapping("edit")
    public String edit(int id, Model model){
        Dept dept =deptService.findDeptById(id);
        model.addAttribute("dept",dept);
        System.out.println(dept.getId());
        return "admin/system/dept/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存班级编辑数据")
    public RestResponse edit(@RequestBody  Dept dept){
        if(dept.getId() == 0 || dept.getId() == null){
            return RestResponse.failure("班级ID不能为空");
        }
        deptService.updataDeptById(dept);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除对战数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)int id){
        if(id<=0){
            return RestResponse.failure("参数错误");
        }
        Dept dept = deptService.findDeptById(id);
        if(dept == null){
            return RestResponse.failure("学院不存在");
        }
		deptService.deleteDeptById(id);
        return RestResponse.success();
    }
		 
}
