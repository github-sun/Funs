package org.sun.model;

import java.util.Date;

/**
* @author sun 
* @date Jan 12, 2018 5:20:08 PM
* 用户表
*/
public class Admin {
	
	//主键
	private int id;
	
	//用户名
	private String username;
	
	//密码
	private String password;
	
	//创建日期
	private Date createDate;
	
	//修改日期
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
