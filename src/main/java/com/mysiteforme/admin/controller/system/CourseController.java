package com.mysiteforme.admin.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Course;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("admin/system/course")
public class CourseController extends BaseController {

    @GetMapping("list")
    @SysLog("跳转课程列表页面")
    public String list(){
        return "admin/system/course/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<Course> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                 ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Course> courseLayerData = new LayerData<>();
        EntityWrapper<Course> courseEntityWrapper = new EntityWrapper<>();
        if(!map.isEmpty()){
            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                courseEntityWrapper.like("name", keys).or().like("code", keys).or().like("dept_id", keys);
            }
        }
        Page<Course> coursePage = courseService.selectPage(new Page<>(page,limit),courseEntityWrapper);
        courseLayerData.setCount(coursePage.getTotal());
        courseLayerData.setData(coursePage.getRecords());
        return  courseLayerData;
    }
//    private List<Course> setDeptToCourse(List<Course> courses) {
//        for (Course c : courses) {
//            if (c.getId() != null && c.getId() != 0) {
//                Dept d = deptService.selectById(c.getDeptId());
//                if (StringUtils.isBlank(d.getName())) {
//                    d.setName(d.getName());
//                }
//                c.setDept(d);
//            }
//        }
//        return courses;
//    }
    @GetMapping("add")
    @SysLog("跳转课程添加页面")
    public String add(){
        return "admin/system/course/add";
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
    @SysLog("保存新增课程数据")
    public RestResponse add(@RequestBody  Course course){
        if(StringUtils.isBlank(course.getName())){
            return RestResponse.failure("课程名不能为空");
        }
//        if(course.getDepts() == null || course.getDepts() .size()==1){
//            return  RestResponse.failure("请选择学院");
//        }

        courseService.saveCourse(course);
        if(course.getId() == null || course.getId() == 0){
            return RestResponse.failure("保存课程信息出错");
        }
        return RestResponse.success();
    }
    @GetMapping("edit")
    public String edit(int id, Model model){
        Course course =courseService.findCourseById(id);
        model.addAttribute("course",course);
        System.out.println(course.getName()+course.getCode());
        return "admin/system/course/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存课程编辑数据")
    public RestResponse edit(@RequestBody  Course course){
        if(course.getId() == 0 || course.getId() == null){
            return RestResponse.failure("课程ID不能为空");
        }
        courseService.updataCourseById(course);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除课程数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)int id){
        if(id<=0){
            return RestResponse.failure("参数错误");
        }
        Course course =courseService.findCourseById(id) ;
        if(course == null){
            return RestResponse.failure("课程不存在");
        }
        courseService.deleteCourseById(id);
        return RestResponse.success();
    }
}
