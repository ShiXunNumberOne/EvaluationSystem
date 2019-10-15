package com.mysiteforme.admin.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.CourseDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Course;
import com.mysiteforme.admin.entity.Dept;
import com.mysiteforme.admin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl  extends ServiceImpl<CourseDao, Course> implements CourseService {

	@Override
	public Course saveCourse(Course course) {
		baseMapper.insert(course);
		return findCourseById(course.getId());
	}

	@Override
	public Course findCourseById(int id) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("id", id);
		Course c= baseMapper.selectCourseByMap(map);
		return c;
	}

	@Override
	public List<Course> selectAll() {
		EntityWrapper<Course> wrapper = new EntityWrapper<>();
		List<Course> courseList = baseMapper.selectList(wrapper);
		return courseList;
	}

	@Override
	public Course selectCourseByMap(int note) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("note", note);
		Course c= baseMapper.selectCourseByMap(map);
		return c;
	}

	@Override
	public int updataCourseById(Course course) {
		int c = baseMapper.updataCourseById(course);
		return c;
	}

	@Override
	public int deleteCourseById(int id) {
		int c = baseMapper.deleteCourseById(id);
		return c;
	}

	@Override
	public int getCountByName(String name) {
		EntityWrapper<Course> wrapper = new EntityWrapper<>();
		wrapper.eq("status",1);
		wrapper.eq("name",name);
		return baseMapper.selectCount(wrapper);
	}


}