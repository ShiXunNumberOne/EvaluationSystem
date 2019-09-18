package com.mysiteforme.admin.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Clazz;
import org.apache.ibatis.annotations.Param;



public interface ClazzDao extends BaseMapper<Clazz> {
	/**
	 * 展示所有的班级并分页
	 * @param clazz
	 * @param before 前一页
	 * @param after  后一页
	 * @return
	 */
	List<Clazz> getClazzs(@Param("clazz") Clazz clazz, @Param("before") int before, @Param("after") int after);
	
	
	/**
	 * 查询班级总数
	 * @return
	 */
	int getTotalClazz();
	
	/**
	 * 查询所有班级（用户下拉框）
	 * @return
	 */
	List<Clazz> queryClazzs();
	
	/**
	 * 新增班级
	 * @param clazz
	 */
	void insertClazz(Clazz clazz);    
	
	
	/**
	 * 修改班级
	 * @param clazz
	 */
	void updateClazz(Clazz clazz);
	
	/**
	 * 禁用班级
	 * @param clazz
	 */
	void deleteClazzs(String cidlist);
	
	/**
	 * 恢复班级
	 * @param clazz
	 */
	void recoverClazz(int id);
	
	
	/**
	 * 根据班级id查询班级信息
	 * @param id
	 * @return
	 */
	Clazz getClazzById(int id);
	
}
