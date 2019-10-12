package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Normitem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NormitemDao  extends BaseMapper<Normitem> {
   int insertNormitemsForeach(@Param("list") List<Normitem> list);
}
