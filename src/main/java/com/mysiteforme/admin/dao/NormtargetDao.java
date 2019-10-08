package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Normtarget;
import com.mysiteforme.admin.entity.VO.ShowMenu;

import java.util.List;
import java.util.Map;

public interface NormtargetDao extends BaseMapper<Normtarget> {
    List<Normtarget> showAllNormtargetList(Map map);
    Normtarget QueryNormtargetId();
    List<Normtarget> getNormtargets(Map map);

//    List<ShowMenu> selectShowMenuByUser(Map<String,Object> map);
}
