package org.sun.model.admin;

import java.io.Serializable;

/**
* @author sun 
* @date Jan 12, 2018 5:28:17 PM
* 角色权限对照表
*/
public class RolePermission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4457402777734708313L;
	
	//角色id
	private int roleId;
	

	//用户id
	private int permissionId;


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getPermissionId() {
		return permissionId;
	}


	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}


	@Override
	public String toString() {
		return "RolePermission [roleId=" + roleId + ", permissionId=" + permissionId + "]";
	}
}
