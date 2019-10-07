package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mysiteforme.admin.dao.MenuDao;
import com.mysiteforme.admin.dao.NormtargetDao;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Normtarget;
import com.mysiteforme.admin.entity.VO.NtreeVO;
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

//    @Cacheable(value = "allNormtarget",unless = "#result == null or #result.size() == 0")
    @Override
    public List<Normtarget> selectAllNormtargets(Map<String, Object> map) {
        return baseMapper.getNormtargets(map);
    }

    @Override
    public List<NtreeVO> showTreeNormtarget() {
        EntityWrapper<Normtarget> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort",true);
        wrapper.eq("is_show",true);
        List<Normtarget> totalMenus = baseMapper.selectList(wrapper);
        List<NtreeVO> ntreeVO = Lists.newArrayList();
        return getZTree(null,totalMenus,ntreeVO);
    }


    @Override
    public List<Normtarget> getShowNormtargetByUser(int id) {
        return null;
    }

    @Override
    public Normtarget QueryNormtargetId() {
        Normtarget n=  baseMapper.QueryNormtargetId();
        return n;

    }
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateNormtarget(Normtarget normtarget) {
        insert(normtarget);

    }

    @Override
    public void updateNormtarget(Normtarget normtarget) {
        updateById(normtarget);
    }

    @Override
    public int getCountByName(String name) {

        EntityWrapper<Normtarget> wrapper = new EntityWrapper<>();
        wrapper.eq("status",1);
        wrapper.eq("name",name);
        return baseMapper.selectCount(wrapper);
    }
    /**
     * 递归拉取指标树的数据
     */
    private  List<NtreeVO> getZTree(NtreeVO tree, List<Normtarget> total, List<NtreeVO> result){
        Long pid = tree == null?null:tree.getId();
        List<NtreeVO> childList = Lists.newArrayList();
        for (Normtarget n : total){
            if(pid == n.getPid()) {
                NtreeVO ntreeVO = new NtreeVO();
                ntreeVO.setId(n.getId());
                ntreeVO.setName(n.getName());
                ntreeVO.setPid(pid);
                childList.add(ntreeVO);
                getZTree(ntreeVO,total,result);
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
