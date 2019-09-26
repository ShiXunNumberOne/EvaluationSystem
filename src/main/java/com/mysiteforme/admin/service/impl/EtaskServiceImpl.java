package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.EtaskDao;
import com.mysiteforme.admin.entity.Etask;
import com.mysiteforme.admin.service.EtaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EtaskServiceImpl  extends ServiceImpl<EtaskDao, Etask> implements EtaskService {

    @Override
    public Etask saveEtask(Etask etask) {
        baseMapper.insert(etask);
        return findEtaskById(etask.getId());
    }

    @Override
    public Etask findEtaskById(int id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("id", id);
        Etask c= baseMapper.selectEtaskByMap(map);
        return c;
    }

    @Override
    public List<Etask> selectAll() {
        EntityWrapper<Etask> wrapper = new EntityWrapper<>();
        List<Etask> etaskList = baseMapper.selectList(wrapper);
        return etaskList;
    }



    @Override
    public int updataEtaskById(Etask etask) {
        int c = baseMapper.updataEtaskById(etask);
        return c;
    }

    @Override
    public int deleteEtaskById(int id) {
        int c = baseMapper.deleteEtaskById(id);
        return c;
    }


}
