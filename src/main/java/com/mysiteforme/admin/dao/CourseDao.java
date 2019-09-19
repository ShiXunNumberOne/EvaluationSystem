package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Course;
import org.apache.ibatis.annotations.Param;



public interface CourseDao extends BaseMapper<Course> {

	Course  selectCourseByMap(Map<String, Object> map);

	Course findCourseById(int id);
	int updataCourseById(Course course);
	int deleteCourseById(int id);

}
