package com.mysiteforme.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mysiteforme.admin.base.DataEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangl
 * @since 2017-10-31
 */
@TableName("sys_user")
public class User extends DataEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 姓名
     */
	@TableField(value = "nick_name",strategy= FieldStrategy.IGNORED)
	private String nickName;
    /**
     * 密码
     */
	private String password;
    /**
     * shiro加密盐
     */
	private String salt;
    /**
     * 手机号码
     */
	@TableField(strategy= FieldStrategy.IGNORED)
	private String tel;
    /**
     * 邮箱地址
     */
	@TableField(strategy= FieldStrategy.IGNORED)
	private String email;
	
	/**
	 * 账户是否锁定
	 */
	private Boolean locked;
    private Integer dept_id;
    private Integer clazz_id;
	@TableField(strategy= FieldStrategy.IGNORED)
	private String icon;

	@TableField(exist=false)
	private Set<Role> roleLists = Sets.newHashSet();
	@TableField(exist=false)
	private Set<Clazz> clazzs = Sets.newHashSet();
	@TableField(exist=false)
	private Set<Dept> depts = Sets.newHashSet();
	@TableField(exist=false)
	private Set<Menu> menus = Sets.newHashSet();

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getClazz_id() {
		return clazz_id;
	}

	public void setClazz_id(Integer clazz_id) {
		this.clazz_id = clazz_id;
	}

	public Set<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public Set<Dept> getDepts() {
		return depts;
	}

	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@JSONField(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JSONField(serialize=false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set<Role> getRoleLists() {
		return roleLists;
	}

	public void setRoleLists(Set<Role> roleLists) {
		this.roleLists = roleLists;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
}
