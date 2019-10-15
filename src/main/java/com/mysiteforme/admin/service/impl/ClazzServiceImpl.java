package com.mysiteforme.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.ClazzDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl  extends ServiceImpl<ClazzDao, Clazz> implements ClazzService {

	@Override
	public Clazz saveClazz(Clazz clazz) {
		baseMapper.insert(clazz);
		return findClazzById(clazz.getId());
	}

	@Override
	public Clazz findClazzById(int id) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		Clazz c = baseMapper.selectClazzByMap(map);
		return c;
	}

	@Override
	public List<HashMap> findClazzByDeptId(int dept_id) {
		return baseMapper.findClazzByDept(dept_id);
	}

	@Override
	public List<Clazz> selectAll() {
		EntityWrapper<Clazz> wrapper = new EntityWrapper<>();
		List<Clazz> clazzList = baseMapper.selectList(wrapper);
		return clazzList;
	}

	@Override
	public Clazz selectClazzByMap(int deptId) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("deptId", deptId);
		Clazz c= baseMapper.selectClazzByMap(map);
		return c;
	}

	@Override
	public int updataClazzById(Clazz clazz) {
		int c = baseMapper.updataClazzById(clazz);
		return c;
	}

	@Override
	public int deleteClazzById(int id) {
		int c = baseMapper.deleteClazzById(id);
		return c;
	}

	@Override
	public int getCountByName(String name) {
		EntityWrapper<Clazz> wrapper = new EntityWrapper<>();
		wrapper.eq("status",1);
		wrapper.eq("name",name);
		return baseMapper.selectCount(wrapper);
	}


}
