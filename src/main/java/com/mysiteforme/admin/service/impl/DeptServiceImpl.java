package com.mysiteforme.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.DeptDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

	@Override
	
	/**
	 * 查询所有组织
	 */
	public List<Dept> getDepts() {
		// TODO Auto-generated method stub
		return baseMapper.getDepts();
	}

	
	/**
	 * 根据组织id查询所有信息
	 */
	@Override
	public Dept getDeptById(int id) {
		// TODO Auto-generated method stub
		return baseMapper.getDeptById(id);
	}

	
	/**
	 * 新增组织
	 */
	@Override
	public void insertDept(Dept dept) {
		// TODO Auto-generated method stub
		dept.setStatus(1);;
		baseMapper.insertDept(dept);
		
	}

	/**
	 * 删除上级机构并删除他的子孙
	 */
	@Override
	public void deleteDept(int id) {
		List<Dept> m3=sel(id);
		for(Dept m4:m3){
			baseMapper.deleteDept(m4.getId());
		}
		baseMapper.deleteDept(id);
	}
	List<Dept> m = new ArrayList<Dept>();
	private  List<Dept> sel(int id){
		List<Dept> m1 = baseMapper.selDeptByPid(id);
		for(Dept m2:m1){
			m.add(m2);
			sel(m2.getId());	
		}
		return m1;
	}

	
	/**
	 * 修改组织
	 */
	@Override
	public void updateDept(Dept dept) {
		// TODO Auto-generated method stub
		baseMapper.updateDept(dept);
	}

	
	/**
	 * 查询系别
	 */
	@Override
	public List<Dept> getLastDepts() {
		// TODO Auto-generated method stub
		return baseMapper.getLastDepts();
	}

	
	
	/**
	 * 恢复组织
	 */
	@Override
	public void recoverDept(int id) {
		// TODO Auto-generated method stub
		baseMapper.recoverDept(id);
	}

	/**
	 * 查询所有二级学院
	 */
	@Override
	public List<Dept> getSecondDepts() {
		// TODO Auto-generated method stub
		return null;
	}

}
