package org.sun.admin.service;

import java.util.List;

import org.sun.model.RolePermission;
import org.sun.model.bo.AdminRoleBO;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface RolePermissionService {

	RolePermission getRolePermissionById(int roleId, int permissionId);

	List<AdminRoleBO> getRolePermissionDatas();

	int addRolePermission(RolePermission model);

	int removeRolePermissionById(int roleId, int permissionId);

	int updateRolePermission(RolePermission model);
}
