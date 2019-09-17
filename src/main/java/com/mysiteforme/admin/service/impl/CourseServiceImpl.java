package com.mysiteforme.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.CourseDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Course;
import com.mysiteforme.admin.service.CourseService;
import org.springframework.stereotype.Service;

@Service("CourseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

	@Override
	public List<Course> getAllCourselistByFilter(String filter, int before, int after) {
		List<Course> courses=baseMapper.getAllCourseByFilter(filter, before, after);
		return courses;
	}
	
	//查询course表的所有条数
	@Override
	public int getTotalCourse(String filter) {
		// TODO Auto-generated method stub
    	return baseMapper.getTotalCourse(filter);
	}
	// 实现插入课程
	@Override
	public String  addCourselist(Course course) {
		int courseNum=baseMapper.addCourse(course);
		   if(courseNum>=1){
			   return "success";
		   }else{
			   return "false";
		   }	
	}
	//删除课程
	@Override
	public void deleteCourselist(int id) {
		baseMapper.deleteCourse(id);
	}
	//更改课程
		@Override
		public void updateCourselist(Course course) {
			// TODO Auto-generated method stub
			baseMapper.updateCourse(course);
		}
}
