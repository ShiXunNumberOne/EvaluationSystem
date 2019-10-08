package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Normitem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NormitemService extends IService<Normitem> {
    Normitem saveNormitem(Normitem normitem);
    int insertNormitems(@Param("list") List<Normitem> list);
}
