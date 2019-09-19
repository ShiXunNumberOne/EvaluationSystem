package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;

import java.util.List;


public interface DeptService extends IService<Dept> {

	Dept saveDept(Dept dept);
	Dept findDeptById(int id);
	List<Dept> selectAll();
	Dept selectDeptByMap(int deptId);
	int updataDeptById(Dept dept);
	int deleteDeptById(int id);

}
