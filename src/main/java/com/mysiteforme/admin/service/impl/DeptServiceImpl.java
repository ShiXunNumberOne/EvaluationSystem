package com.mysiteforme.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.DeptDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl  extends ServiceImpl<DeptDao, Dept> implements DeptService {

	@Override
	public Dept saveDept(Dept dept) {
		baseMapper.insert(dept);
		return findDeptById(dept.getId());
	}

	@Override
	public Dept findDeptById(int id) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		Dept c= baseMapper.selectDeptByMap(map);
		return c;
	}

	@Override
	public List<Dept> selectAll() {
		EntityWrapper<Dept> wrapper = new EntityWrapper<>();
		List<Dept> deptList = baseMapper.selectList(wrapper);
		return deptList;
	}

	@Override
	public Dept selectDeptByMap(int deptId) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("deptId", deptId);
		Dept c= baseMapper.selectDeptByMap(map);
		return c;
	}

	@Override
	public int updataDeptById(Dept dept) {
		int c = baseMapper.updataDeptById(dept);
		return c;
	}

	@Override
	public int deleteDeptById(int id) {
		int c = baseMapper.deleteDeptById(id);
		return c;
	}


}
