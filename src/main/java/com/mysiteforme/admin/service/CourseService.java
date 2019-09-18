package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Course;

import java.util.List;


public interface CourseService extends IService<Course> {
     //查询所有课程
	 public List<Course> getAllCourselistByFilter(String filter, int before, int after);
	 //查询课程总数
	 public int getTotalCourse(String filter); 
	 //根据书名查询
	 //public List<Course> getAllCourselistBynamelist(String name);
	 //插入课程
	 public String addCourselist(Course course);
	 //删除课程
	 public void deleteCourselist(int id);
	 //更改课程
	 public  void updateCourselist(Course course);
}
