package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.NormitemDao;
import com.mysiteforme.admin.entity.Normitem;
import com.mysiteforme.admin.service.NormitemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormitemServiceImpl extends ServiceImpl<NormitemDao, Normitem> implements NormitemService {

    @Override
    public Normitem saveNormitem(Normitem normitem) {
        baseMapper.insert(normitem);
        return null;
    }

    @Override
    public int insertNormitems(List<Normitem> list) {
        int n =baseMapper.insertNormitemsForeach(list);
        return n;
    }
}
