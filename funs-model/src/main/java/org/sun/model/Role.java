package org.sun.model;

import java.util.Date;

/**
* @author sun 
* @date Jan 12, 2018 5:25:27 PM
* 角色表
*/
public class Role {
	//主键id
	private int id;
	
	//角色名称
	private String rolename;
	
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
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
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
}
