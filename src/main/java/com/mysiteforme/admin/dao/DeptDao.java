package com.mysiteforme.admin.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Dept;


public interface DeptDao extends BaseMapper<Dept> {
	
	
	List<Dept> getDepts();             //查询所有的组织
	
	Dept getDeptById(int id);          //根据组织ID查询所属组织
	void insertDept(Dept dept);          //新增组织
	void deleteDept(int id);           //禁用组织
	void updateDept(Dept dept);           //修改组织
	List<Dept> getLastDepts();           //查询系部
	List<Dept> getSecondDepts();        //查询所有二级学院
	List<Dept> selDeptByPid(int pid);   //根据上级ID查询所有子id
	void recoverDept(int id);            //恢复
}
