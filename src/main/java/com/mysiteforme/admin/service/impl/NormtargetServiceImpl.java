package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mysiteforme.admin.dao.MenuDao;
import com.mysiteforme.admin.dao.NormtargetDao;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Normtarget;
import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.mysiteforme.admin.service.MenuService;
import com.mysiteforme.admin.service.NormtargetService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class NormtargetServiceImpl extends ServiceImpl<NormtargetDao, Normtarget> implements NormtargetService {

    @Cacheable(value = "allNormtarget",key = "'allNormtarget_isShow_'+#map['isShow'].toString()",unless = "#result == null or #result.size() == 0")
    @Override
    public List<Normtarget> selectAllNormtargets(Map<String, Object> map) {
        return baseMapper.getNormtargets(map);
    }

    @Override
    public List<ZtreeVO> showTreeNormtarget() {
        EntityWrapper<Normtarget> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort_code",false);
        wrapper.eq("is_show",true);
        List<Normtarget> totalMenus = baseMapper.selectList(wrapper);
        List<ZtreeVO> ztreeVOs = Lists.newArrayList();
        return getZTree(null,totalMenus,ztreeVOs);
    }


    @Override
    public List<Normtarget> getShowNormtargetByUser(int id) {
        return null;
    }

    @Override
    public void saveOrUpdateNormtarget(Normtarget normtarget) {

    }

    @Override
    public int getCountByName(String name) {
        return 0;
    }
    /**
     * 递归拉取菜单树的数据
     */
    private  List<ZtreeVO> getZTree(ZtreeVO tree,List<Normtarget> total,List<ZtreeVO> result){
        Long pid = tree == null?null:tree.getId();
        List<ZtreeVO> childList = Lists.newArrayList();
        for (Normtarget n : total){
            if(pid == n.getPid()) {
                ZtreeVO ztreeVO = new ZtreeVO();
                ztreeVO.setId(n.getId());
                ztreeVO.setName(n.getName());
                ztreeVO.setPid(pid);
                childList.add(ztreeVO);
                getZTree(ztreeVO,total,result);
            }
        }
        if(tree != null){
            tree.setChildren(childList);
        }else{
            result = childList;
        }
        return result;
    }

}
