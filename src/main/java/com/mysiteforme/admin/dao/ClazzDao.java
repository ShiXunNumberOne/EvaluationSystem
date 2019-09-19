package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Clazz;
import org.apache.ibatis.annotations.Param;



public interface ClazzDao extends BaseMapper<Clazz> {

	Clazz  selectClazzByMap(Map<String, Object> map);

	Clazz findClazzById(int id);
	int updataClazzById(Clazz clazz);
	int deleteClazzById(int id);
	
}
