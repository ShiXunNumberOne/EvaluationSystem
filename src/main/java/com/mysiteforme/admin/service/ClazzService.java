package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Clazz;

import java.util.HashMap;
import java.util.List;



public interface ClazzService extends IService<Clazz> {

	Clazz saveClazz(Clazz clazz);
	Clazz findClazzById(int id);
	List<HashMap> findClazzByDeptId(int dept_id);
	List<Clazz> selectAll();
	Clazz selectClazzByMap(int deptId);
	int updataClazzById(Clazz clazz);
	int deleteClazzById(int id);
	int getCountByName(String name);

}
