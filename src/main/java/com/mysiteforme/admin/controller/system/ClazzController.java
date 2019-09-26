package com.mysiteforme.admin.controller.system;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.entity.Role;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("admin/system/clazz")
public class ClazzController extends BaseController {

	@GetMapping("list")
	@SysLog("跳转班级列表页面")
	public String list(){
		return "admin/system/clazz/list";
	}

	@GetMapping("details")
	@SysLog("跳转班级详情页面")
	public String details(String clazz_id){
		ServletRequestAttributes servletReqAttr =
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletReqAttr.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("clazzidDetails" ,clazz_id );
		return "admin/system/clazz/details";
	}


	@GetMapping("classList")
	@ResponseBody
	public HashMap queryClassList(){
		ServletRequestAttributes servletReqAttr =
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletReqAttr.getRequest();
		HttpSession session = request.getSession();
		Object clazzId = session.getAttribute("clazzidDetails");
		List<HashMap> clazzList = userService.selectUserinClazz(clazzId.toString());

		HashMap map = new HashMap();
		map.put("code",0000);
		map.put("msg","Ok");
		map.put("count",clazzList.size());
		map.put("data",clazzList);

		return  map;

	}

	@PostMapping("list")
	@ResponseBody
	public LayerData<Clazz> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
								@RequestParam(value = "limit",defaultValue = "10")Integer limit,
								ServletRequest request){
		Map map = WebUtils.getParametersStartingWith(request, "s_");
		LayerData<Clazz> clazzLayerData = new LayerData<>();
		EntityWrapper<Clazz> clazzEntityWrapper = new EntityWrapper<>();
		if(!map.isEmpty()){
			String keys = (String) map.get("key");
			if(StringUtils.isNotBlank(keys)) {
				clazzEntityWrapper.like("name", keys).or().like("code", keys).or().like("dept_id", keys);
			}
		}
		Page<Clazz> clazzPage = clazzService.selectPage(new Page<>(page,limit),clazzEntityWrapper);
		clazzLayerData.setCount(clazzPage.getTotal());
		clazzLayerData.setData(setDeptToClazz(clazzPage.getRecords()));
		return  clazzLayerData;
	}
    private List<Clazz> setDeptToClazz(List<Clazz> clazzs) {
        for (Clazz c : clazzs) {
            if (c.getId() != null && c.getId() != 0) {
                Dept d = deptService.selectById(c.getDeptId());
                if (StringUtils.isBlank(d.getName())) {
                    d.setName(d.getName());
                }
                c.setDept(d);
            }
        }
        return clazzs;
    }
    @GetMapping("add")
    @SysLog("跳转班级添加页面")
    public String add(){
        return "admin/system/clazz/add";
    }

	@GetMapping("queryDept")
    @ResponseBody
	public HashMap  queryDepartments(){
        HashMap result = new HashMap();
		List<Dept> deptList = deptService.selectAll();
        result.put("code",0);
        result.put("msg","");
        result.put("count",deptList.size());
        result.put("data",deptList);
		return result;
	}
	@GetMapping("queryClazz")
	@ResponseBody
	public HashMap  queryClazzs(int dept_id){
		HashMap result = new HashMap();
		List<HashMap> clazzList = clazzService.findClazzByDeptId(dept_id);
		result.put("code",0);
		result.put("msg","");
		result.put("count",clazzList.size());
		result.put("data",clazzList);
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
	@SysLog("删除班级数据(单个)")
	public RestResponse delete(@RequestParam(value = "id",required = false)int id){
		if(id<=0){
			return RestResponse.failure("参数错误");
		}
		Clazz clazz =clazzService.findClazzById(id) ;
		if(clazz == null){
			return RestResponse.failure("班级不存在");
		}
		clazzService.deleteClazzById(id);
		return RestResponse.success();
	}
}
