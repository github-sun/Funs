package org.sun.admin.service;

import java.util.List;
import java.util.Set;

import org.sun.model.RolePermission;
import org.sun.model.bo.RolePermissionBO;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface RolePermissionService {

	RolePermission getRolePermissionById(int roleId, int permissionId);

	List<RolePermissionBO> getRolePermissionDatas();

	int addRolePermission(RolePermission model);

	int removeRolePermissionById(int roleId, int permissionId);

	int updateRolePermission(RolePermission model);
	
	Set<String> getPermissions(String username);
	
	List<String> getUserAuthzByPermission(int roleId, int permissionId);
}
