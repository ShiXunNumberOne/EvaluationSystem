package com.mysiteforme.admin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.ClazzDao;
import com.mysiteforme.admin.dao.UserDao1;
import com.mysiteforme.admin.entity.Clazz;
import com.mysiteforme.admin.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl  extends ServiceImpl<ClazzDao, Clazz> implements ClazzService {

	
	/**
	 * 查询所有班级并分页
	 */
	@Override
	public List<Clazz> getClazzs(Clazz clazz, int before, int after) {
		// TODO Auto-generated method stub
		return baseMapper.getClazzs(clazz,before,after);
	}

	/**
	 * 新增班级
	 */
	@Override
	public void insertClazz(Clazz clazz) {
		clazz.setStatus(1);
		baseMapper.insertClazz(clazz);
		
	}

	/**
	 * 修改班级
	 */
	@Override
	public void updateClazz(Clazz clazz) {
		// TODO Auto-generated method stub
		baseMapper.updateClazz(clazz);
	}

	
	/**
	 * 删除班级
	 */
	@Override
	public void deleteClazzs(String cidlist) {
		// TODO Auto-generated method stub
		String[] ls=cidlist.split("<cid>");
		for(String cid:ls){
			if(cid!=null&&!"".equals(cid)){

				baseMapper.deleteClazzs(cid);
				
			}
		}
	}

	/**
	 * 查看班级详情
	 */
	@Override
	public Clazz getClazzById(int id) {
		// TODO Auto-generated method stub
		return baseMapper.getClazzById(id);
	}

	/**
	 * 查询所有班级（用户下拉框显示）
	 */
	@Override
	public List<Clazz> queryClazzs() {
		// TODO Auto-generated method stub
		return baseMapper.queryClazzs();
	}

	
	/**
	 * 查询班级总数
	 */
	@Override
	public int getTotalClazz() {
		// TODO Auto-generated method stub
		return baseMapper.getTotalClazz();
	}

	
	/**
	 * 恢复班级
	 */
	@Override
	public void recoverClazz(int id) {
		// TODO Auto-generated method stub
		baseMapper.recoverClazz(id);
	}

	

}
