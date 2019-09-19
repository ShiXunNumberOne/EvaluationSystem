package com.mysiteforme.admin.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;


public interface DeptDao extends BaseMapper<Dept> {
	Dept selectDeptByMap(Map<String, Object> map);

	Dept findDeptById(int id);
	int updataDeptById(Dept dept);
	int deleteDeptById(int id);
}
