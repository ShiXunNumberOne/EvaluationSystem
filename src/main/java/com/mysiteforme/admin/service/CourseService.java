package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.entity.Course;

import java.util.List;


public interface CourseService extends IService<Course> {

	Course saveCourse(Course course);
	Course findCourseById(int id);
	List<Course> selectAll();
	Course selectCourseByMap(int deptId);
	int updataCourseById(Course course);
	int deleteCourseById(int id);

}
