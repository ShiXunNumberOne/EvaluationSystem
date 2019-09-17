package com.mysiteforme.admin.controller.system;

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
import com.mysiteforme.admin.service.ClazzService;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;


@Controller
@RequestMapping("admin/system/clazz")
public class ClazzController extends BaseController {

	@GetMapping("list")
	@SysLog("跳转班级列表页面")
	public String list(){
		return "admin/system/clazz/list";
	}

	@RequiresPermissions("sys:clazz:list")
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
				clazzEntityWrapper.like("login_name", keys).or().like("tel", keys).or().like("email", keys);
			}
		}
		Page<Clazz> clazzPage = clazzService.selectPage(new Page<>(page,limit),clazzEntityWrapper);
		clazzLayerData.setCount(clazzPage.getTotal());
		clazzLayerData.setData(clazzPage.getRecords());
		return  clazzLayerData;
	}


//	@GetMapping("add")
//	public String add(Model model){
//		Map<String,Object> map = Maps.newHashMap();
//		map.put("parentId",null);
//		map.put("isShow",false);
//		List<Menu> menuList = menuService.selectAllMenus(map);
//		model.addAttribute("menuList",menuList);
//		return "admin/system/role/add";
//	}
//
//	@RequiresPermissions("sys:role:add")
//	@PostMapping("add")
//	@ResponseBody
//	@SysLog("保存新增角色数据")
//	public RestResponse add(@RequestBody Role role){
//		if(StringUtils.isBlank(role.getName())){
//			return RestResponse.failure("角色名称不能为空");
//		}
//		if(roleService.getRoleNameCount(role.getName())>0){
//			return RestResponse.failure("角色名称已存在");
//		}
//		roleService.saveRole(role);
//		return RestResponse.success();
//	}
//
//	@GetMapping("edit")
//	public String edit(Long id,Model model){
//		Role role = roleService.getRoleById(id);
//		//StringBuilder menuIds = new StringBuilder();
//		List<Long> menuIds = Lists.newArrayList();
//		if(role != null) {
//			Set<Menu> menuSet = role.getMenuSet();
//			if (menuSet != null && menuSet.size() > 0) {
//				for (Menu m : menuSet) {
//					//menuIds.append(m.getId().toString()).append(",");
//					menuIds.add(m.getId());
//				}
//			}
//		}
//		Map<String,Object> map = Maps.newHashMap();
//		map.put("parentId",null);
//		map.put("isShow",false);
//		List<Menu> menuList = menuService.selectAllMenus(map);
//		model.addAttribute("role",role);
//		model.addAttribute("menuList",menuList);
//		model.addAttribute("menuIds",menuIds);
//		return "admin/system/role/edit";
//	}
//
//	@RequiresPermissions("sys:role:edit")
//	@PostMapping("edit")
//	@ResponseBody
//	@SysLog("保存编辑角色数据")
//	public RestResponse edit(@RequestBody Role role){
//		if(role.getId() == null || role.getId() == 0){
//			return RestResponse.failure("角色ID不能为空");
//		}
//		if(StringUtils.isBlank(role.getName())){
//			return RestResponse.failure("角色名称不能为空");
//		}
//		Role oldRole = roleService.getRoleById(role.getId());
//		if(!oldRole.getName().equals(role.getName())){
//			if(roleService.getRoleNameCount(role.getName())>0){
//				return RestResponse.failure("角色名称已存在");
//			}
//		}
//		roleService.updateRole(role);
//		return RestResponse.success();
//	}
//
//	@RequiresPermissions("sys:role:delete")
//	@PostMapping("delete")
//	@ResponseBody
//	@SysLog("删除角色数据")
//	public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
//		if(id == null || id == 0){
//			return RestResponse.failure("角色ID不能为空");
//		}
//		Role role = roleService.getRoleById(id);
//		roleService.deleteRole(role);
//		return RestResponse.success();
//	}
//
//	@RequiresPermissions("sys:role:delete")
//	@PostMapping("deleteSome")
//	@ResponseBody
//	@SysLog("多选删除角色数据")
//	public RestResponse deleteSome(@RequestBody List<Role> roles){
//		if(roles == null || roles.size()==0){
//			return RestResponse.failure("请选择需要删除的角色");
//		}
//		for (Role r : roles){
//			roleService.deleteRole(r);
//		}
//		return RestResponse.success();
//	}
//

}
