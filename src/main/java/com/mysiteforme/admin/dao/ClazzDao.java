package com.mysiteforme.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Clazz;



public interface ClazzDao extends BaseMapper<Clazz> {

	Clazz  selectClazzByMap(Map<String, Object> map);
	List<HashMap>  findClazzByDept(int dept_id);
	Clazz findClazzById(int id);
	int updataClazzById(Clazz clazz);
	int deleteClazzById(int id);
	
}
