package com.mysiteforme.admin.entity;

public class Course {
	private Integer id;
	private String name;
	private String code;
	private String note;

	private Integer status;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}



}
