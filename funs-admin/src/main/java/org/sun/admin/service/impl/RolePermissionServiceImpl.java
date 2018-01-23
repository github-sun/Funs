package org.sun.admin.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.RolePermissionService;
import org.sun.dao.RolePermissionDAO;
import org.sun.model.RolePermission;
import org.sun.model.bo.PermissionBO;
import org.sun.model.bo.RolePermissionBO;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	@Override
	public RolePermission getRolePermissionById(int roleId, int permissionId) {
		return rolePermissionDAO.queryById(roleId, permissionId);
	}

	@Override
	public List<RolePermissionBO> getRolePermissionDatas() {
		return rolePermissionDAO.query();
	}

	@Override
	public int addRolePermission(RolePermission model) {
		return rolePermissionDAO.insert(model);
	}

	@Override
	public int removeRolePermissionById(int roleId, int permissionId) {
		return rolePermissionDAO.deleteById(roleId, permissionId);
	}

	@Override
	public int updateRolePermission(RolePermission model) {
		return rolePermissionDAO.update(model);
	}

	@Override
	public Set<String> getPermissions(String username) {
		Set<String> set = new HashSet<>();
		List<PermissionBO> list = rolePermissionDAO.queryPermissions(username);
		if (list == null) {
			return set;
		}
		for (PermissionBO permissionBO : list) {
			set.add(permissionBO.getCode());
		}
		return set;
	}
 
}
