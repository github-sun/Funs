package org.sun.model.admin.bo;

import java.io.Serializable;

/**
 * @author sun
 * @date Jan 16, 2018 11:18:45 AM
 * 
 */
public class RolePermissionBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135092379174615076L;

	// 用户id
	private int roleId;

	// 角色id
	private int permissionId;

	private String rolename;

	private String permissionname;
	
	private String permissioncode;

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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public String getPermissioncode() {
		return permissioncode;
	}

	public void setPermissioncode(String permissioncode) {
		this.permissioncode = permissioncode;
	}

	@Override
	public String toString() {
		return "RolePermissionBO [roleId=" + roleId + ", permissionId=" + permissionId + ", rolename=" + rolename
				+ ", permissionname=" + permissionname + "]";
	}

}
