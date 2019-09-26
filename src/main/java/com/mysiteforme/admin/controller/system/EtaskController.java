package com.mysiteforme.admin.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mchange.lang.IntegerUtils;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/system/etask")
public class EtaskController extends BaseController {
    @GetMapping("list")
    @SysLog("跳转批次管理列表页面")
    public String list(){
        return "admin/system/etask/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<Etask> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                 ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Etask> etaskLayerData = new LayerData<>();
        EntityWrapper<Etask> etaskEntityWrapper = new EntityWrapper<>();
        if(!map.isEmpty()){
            String keys = (String) map.get("key");
            if(StringUtils.isNotBlank(keys)) {
                etaskEntityWrapper.like("name", keys);
            }
        }
        Page<Etask> etaskPage = etaskService.selectPage(new Page<>(page,limit),etaskEntityWrapper);
        etaskLayerData.setCount(etaskPage.getTotal());
        etaskLayerData.setData(setUserToEtask(etaskPage.getRecords()));
        return  etaskLayerData;
    }
    private List<Etask> setUserToEtask(List<Etask> etasks) {
        for (Etask e : etasks) {
            if (e.getId() != null && e.getId() != 0) {
                User u = userService.selectById(e.getCreateuserId());
                if (org.apache.commons.lang3.StringUtils.isBlank(u.getNickName())) {
                    u.setNickName(u.getNickName());
                }
               e.setUser(u);
            }
        }
        return etasks;
    }
    @GetMapping("add")
    @SysLog("跳转批次添加页面")
    public String add(Model model){
        model.addAttribute("uid", MySysUser.id());
        return "admin/system/etask/add";
    }

    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增批次数据")
    public RestResponse add(@RequestBody  Etask etask){

        if(StringUtils.isBlank(etask.getName())){
            return RestResponse.failure("批次名不能为空");
        }
        etaskService.saveEtask(etask);
        if(etask.getId() == null || etask.getId() == 0){
            return RestResponse.failure("保存批次信息出错");
        }
        return RestResponse.success();
    }
    @GetMapping("edit")
    public String edit(int id, Model model){
        Etask etask =etaskService.findEtaskById(id);
        model.addAttribute("etask",etask);
        System.out.println(etask.getId()+""+etask.getEndData());
        return "admin/system/etask/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存批次编辑数据")
    public RestResponse edit(@RequestBody  Etask etask){
        if(etask.getId() == 0 || etask.getId() == null){
            return RestResponse.failure("批次ID不能为空");
        }
        etaskService.updataEtaskById(etask);
        return RestResponse.success();
    }
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除对战数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)int id){
        if(id<=0){
            return RestResponse.failure("参数错误");
        }
        Etask etask = etaskService.findEtaskById(id);
        if(etask == null){
            return RestResponse.failure("批次不存在");
        }
        etaskService.deleteEtaskById(id);
        return RestResponse.success();
    }

}
