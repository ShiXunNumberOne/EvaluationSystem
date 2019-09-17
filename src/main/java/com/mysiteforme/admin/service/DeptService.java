package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Dept;

import java.util.List;



public interface DeptService extends IService<Dept> {
	
	List<Dept> getDepts();                   //查询所有组织
	Dept getDeptById(int id);          //根据部门ID查询所属组织
	void insertDept(Dept dept);          //新增组织
	void deleteDept(int id);           //删除组织
	void updateDept(Dept dept);           //修改组织
	List<Dept> getLastDepts();           //查询系部
	List<Dept> getSecondDepts();           //查询二级学院
	void recoverDept(int id);            //恢复
	
}
