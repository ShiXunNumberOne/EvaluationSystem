package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Normtarget;
import com.mysiteforme.admin.entity.VO.NtreeVO;
import com.mysiteforme.admin.entity.VO.ShowMenu;
import com.mysiteforme.admin.entity.VO.ZtreeVO;

import java.util.List;
import java.util.Map;


public interface NormtargetService extends IService<Normtarget> {
    List<Normtarget> selectAllNormtargets(Map<String,Object> map);

    List<NtreeVO> showTreeNormtarget();

    List<Normtarget> getShowNormtargetByUser(int id);
    Normtarget QueryNormtargetId();
    void saveOrUpdateNormtarget(Normtarget normtarget);
    void updateNormtarget(Normtarget normtarget);
    int getCountByName(String name);

}
