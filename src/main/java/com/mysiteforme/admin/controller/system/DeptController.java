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
    @GetMapping("add")
    @SysLog("跳转班级添加页面")
    public String add(){
        return "admin/system/clazz/add";
    }

    @GetMapping("queryDept")
    @ResponseBody
    public HashMap queryDepartments(){
        HashMap result = new HashMap();
        List<Dept> deptList = deptService.selectAll();
        result.put("code",0);
        result.put("msg","");
        result.put("count",deptList.size());
        result.put("data",deptList);
        return result;
    }
    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增班级数据")
    public RestResponse add(@RequestBody  Clazz clazz){
        if(StringUtils.isBlank(clazz.getName())){
            return RestResponse.failure("班级名不能为空");
        }
        if(clazz.getDepts() == null || clazz.getDepts() .size()==1){
            return  RestResponse.failure("请选择学院");
        }

        clazzService.saveClazz(clazz);
        if(clazz.getId() == null || clazz.getId() == 0){
            return RestResponse.failure("保存班级信息出错");
        }
        return RestResponse.success();
    }
    @GetMapping("edit")
    public String edit(int id, Model model){
        Clazz clazz =clazzService.findClazzById(id);
        model.addAttribute("clazz",clazz);
        System.out.println(clazz.getName()+clazz.getCode());
        return "admin/system/clazz/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存班级编辑数据")
    public RestResponse edit(@RequestBody  Clazz clazz){
        if(clazz.getId() == 0 || clazz.getId() == null){
            return RestResponse.failure("班级ID不能为空");
        }
        clazzService.updataClazzById(clazz);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除对战数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)int id){
        if(id<=0){
            return RestResponse.failure("参数错误");
        }
        Clazz clazz = null;
        if(clazz == null){
            return RestResponse.failure("对战不存在");
        }
//		clazzService;
        return RestResponse.success();
    }
		 
}
