package com.mysiteforme.admin.controller.system;

import com.baomidou.mybatisplus.plugins.Page;
import com.mchange.lang.IntegerUtils;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.VO.Schedules;
import com.mysiteforme.admin.entity.VO.SchedulesVO;
import com.mysiteforme.admin.service.SchedulesService;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping("admin/system/teach")
public class SchedulesController extends BaseController {
    @GetMapping("list")
    @SysLog("跳转系统成绩列表页面")
    public String list(){
        return "admin/system/teach/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<SchedulesVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                       @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                       ServletRequest request){
        LayerData<SchedulesVO> userLayerData = new LayerData<>();
        Page<SchedulesVO> matchesPage = schedulesService.queryTeachOutMapByPage(new Page<>(page, limit));
        userLayerData.setCount(matchesPage.getTotal());
        userLayerData.setData(matchesPage.getRecords());
        return  userLayerData;
    }
    @GetMapping("add")
    @SysLog("跳转授课添加页面")
    public String add(){
        return "admin/system/teach/add";
    }
    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增班级数据")
    public RestResponse add(@RequestBody Schedules schedules){
        if(schedules.getCourses() == null || schedules.getCourses() .size()==1){
            return RestResponse.failure("请选择课程");
        }
        if(schedules.getClazzs() == null || schedules.getClazzs() .size()==1){
            return  RestResponse.failure("请选择班级");
        }
        if(schedules.getUsers() == null || schedules.getUsers() .size()==1){
            return  RestResponse.failure("请选择老师");
        }

        schedulesService.saveSchedules(schedules);
        if(schedules.getId() == null || schedules.getId() == 0){
            return RestResponse.failure("保存授课信息出错");
        }
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(int id, Model model){
        Schedules schedules =schedulesService.findSchedulesById(id);
        model.addAttribute("schedules",schedules);
        System.out.println(schedules.getUsers());
        return "admin/system/teach/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存授课编辑数据")
    public RestResponse edit(@RequestBody  Schedules schedules){
        if(schedules.getId() == 0 || schedules.getId() == null){
            return RestResponse.failure("ID不能为空");
        }
        schedulesService.updataSchedulesById(schedules);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除授课数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)int id){
        if(id<=0){
            return RestResponse.failure("参数错误");
        }
        Schedules schedules =schedulesService.findSchedulesById(id) ;
        if(schedules == null){
            return RestResponse.failure("授课信息不存在");
        }
        schedulesService.deleteSchedulesById(id);
        return RestResponse.success();
    }
}
