package org.sun.model.bo;

import java.io.Serializable;

/**
* @author sun 
* @date Jan 16, 2018 11:18:45 AM
* 
*/
public class AdminRoleBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8655126515048202835L;

	//用户id
	private int adminId;
	
	//角色id
	private int roleId;
	
	private String username;
	
	private String rolename;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
