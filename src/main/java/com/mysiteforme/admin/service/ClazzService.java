package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Clazz;

import java.util.List;



public interface ClazzService extends IService<Clazz> {
	
	
	/**
	 * 查询所有班级并分页
	 * @param clazz
	 * @param before
	 * @param after
	 * @return
	 */
	List<Clazz> getClazzs(Clazz clazz, int before, int after);
	
	/**
	 * 查询班级总数
	 * @return
	 */
	int getTotalClazz();
	
	/**
	 * 查询班级（用户下拉框）
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
	 * @param
	 */
	void deleteClazzs(String cidlist);
	
	/**
	 * 恢复班级
	 * @param
	 */
	void recoverClazz(int id);
	
	
	/**
	 * 查看班级详情
	 * @param id
	 * @return
	 */
	Clazz getClazzById(int id);
}
