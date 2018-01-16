package org.sun.model;

import java.io.Serializable;

/**
* @author sun 
* @date Jan 12, 2018 5:28:17 PM
* 用户角色对照表
*/
public class AdminRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -979800794844188158L;

	//用户id
	private int adminId;
	
	//角色id
	private int roleId;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "AdminRole [adminId=" + adminId + ", roleId=" + roleId + "]";
	}
}
