package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.List;

public class Dept {
	private Integer id;
	private String name;
	private Integer sort_code;
	private Integer pid;
	private Integer status;

	@TableField(exist = false)
	private List<Clazz> clazz;

	@TableField(exist = false)
	private List<User> user;
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<Clazz> getClazz() {
		return clazz;
	}
	public void setClazz(List<Clazz> clazz) {
		this.clazz = clazz;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort_code() {
		return sort_code;
	}
	public void setSort_code(Integer sort_code) {
		this.sort_code = sort_code;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
