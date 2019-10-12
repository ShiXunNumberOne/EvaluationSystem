package com.mysiteforme.admin.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Normtarget;
import com.mysiteforme.admin.entity.VO.NtreeVO;
import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/system/normtarget")
public class NormtargetController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NormtargetController.class);

    @GetMapping("list")
    @SysLog("跳转菜单列表")
    public String list(Model model){
        return "admin/system/normtarget/test";
    }

    @PostMapping("tree")
    @ResponseBody
    public RestResponse tree(){
        List<NtreeVO> ntreeVOs = normtargetService.showTreeNormtarget();
        LOGGER.info(JSONObject.toJSONString(ntreeVOs));
        return RestResponse.success().setData(ntreeVOs);
    }

    @PostMapping("treelist")
    @ResponseBody
    public RestResponse treelist(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("pid",null);
        map.put("isShow",false);
        return RestResponse.success().setData(normtargetService.selectAllNormtargets(map));
    }

    @GetMapping("add")
    public String add(@RequestParam(value = "pid",required = false) Integer pid, Model model){
        if(pid != null){
            Normtarget normtarget = normtargetService.selectById(pid);
            Normtarget id = normtargetService.QueryNormtargetId();
            model.addAttribute("parentNormtarget",normtarget);
            model.addAttribute("normtargetId",id);
        }
        return "admin/system/normtarget/add";
    }

    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增菜单数据")
    public RestResponse add(Normtarget normtarget){
        if(StringUtils.isBlank(normtarget.getName())){
            return RestResponse.failure("指标名称不能为空");
        }
        if(normtargetService.getCountByName(normtarget.getName())>0){
            return RestResponse.failure("指标已存在");
        }
        if(normtarget.getPid()==null){
            normtarget.setLevel(1);
            Object o = normtargetService.selectObj(Condition.create().setSqlSelect("max(sort)").isNull("pid"));
            int sort = 0;
            if(o != null){
                sort =  (Integer)o +10;
            }
            normtarget.setSort(sort);
        }else{
            Normtarget parentNormtarget = normtargetService.selectById(normtarget.getPid());
            if(parentNormtarget==null){
                return RestResponse.failure("指标类别不存在");
            }
            normtarget.setLevel(parentNormtarget.getLevel()+1);
            Object o = normtargetService.selectObj(Condition.create()
                    .setSqlSelect("max(sort)")
                    .eq("pid",normtarget.getPid()));
            int sort = 0;
            if(o != null){
                sort =  (Integer)o +10;
            }
            normtarget.setSort(sort);
        }
        normtargetService.saveOrUpdateNormtarget(normtarget);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id,Model model){
        Menu menu = menuService.selectById(id);
        model.addAttribute("menu",menu);
        return "admin/system/menu/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑菜单数据")
    public RestResponse edit(Menu menu){
        if(menu.getId() == null){
            return RestResponse.failure("菜单ID不能为空");
        }
        if (StringUtils.isBlank(menu.getName())) {
            return RestResponse.failure("菜单名称不能为空");
        }
        Menu oldMenu = menuService.selectById(menu.getId());
        if(!oldMenu.getName().equals(menu.getName())) {
            if(menuService.getCountByName(menu.getName())>0){
                return RestResponse.failure("菜单名称已存在");
            }
        }
        if (StringUtils.isNotBlank(menu.getPermission())) {
            if(!oldMenu.getPermission().equals(menu.getPermission())) {
                if (menuService.getCountByPermission(menu.getPermission()) > 0) {
                    return RestResponse.failure("权限标识已经存在");
                }
            }
        }
        if(menu.getSort() == null){
            return RestResponse.failure("排序值不能为空");
        }
        menuService.saveOrUpdateMenu(menu);
        return RestResponse.success();
    }

    @RequiresPermissions("sys:menu:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除菜单")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(id == null){
            return RestResponse.failure("菜单ID不能为空");
        }
        Menu menu = menuService.selectById(id);
        menu.setDelFlag(true);
        menuService.saveOrUpdateMenu(menu);
        return RestResponse.success();
    }

    @PostMapping("isShow")
    @ResponseBody
    public RestResponse isShow(@RequestParam(value = "id",required = false)Long id,
                               @RequestParam(value = "isShow",required = false)String isShow){
        if(id == null){
            return RestResponse.failure("菜单ID不能为空");
        }
        if(isShow == null){
            return RestResponse.failure("设置参数不能为空");
        }else{
            if(!"true".equals(isShow) && !"false".equals(isShow)){
                return RestResponse.failure("设置参数不正确");
            }
        }
        Boolean showFalg = Boolean.valueOf(isShow);
        Menu menu = menuService.selectById(id);
        menu.setIsShow(showFalg);
        menuService.saveOrUpdateMenu(menu);
        return RestResponse.success();

    }

}
