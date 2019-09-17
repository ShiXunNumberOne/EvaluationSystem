package com.mysiteforme.admin.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Course;
import org.apache.ibatis.annotations.Param;



public interface CourseDao extends BaseMapper<Course> {
	 public List<Course> getAllCourseByFilter(@Param("filter") String filter, @Param("before") int before, @Param("after") int after);
	  //查询所有课程总数
	 public int getTotalCourse(@Param("filter") String filter);
	 //插入课程
	 public int addCourse(Course course);
	 //删除课程
	 public void deleteCourse(@Param("id") int id);
	//更改课程
	 public void updateCourse(Course course);
}
