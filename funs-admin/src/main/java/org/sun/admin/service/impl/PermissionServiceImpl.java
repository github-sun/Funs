package org.sun.admin.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.PermissionService;
import org.sun.dao.PermissionDAO;
import org.sun.model.admin.Permission;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	@Override
	public Permission getPermissionById(int id) {
		return permissionDAO.queryById(id);
	}

	@Override
	public List<Permission> getPermissionDatas() {
		return permissionDAO.queryAll();
	}

	@Override
	public int addPermission(Permission model) {
		return permissionDAO.insert(model);
	}

	@Override
	public int removePermissionById(int id) {
		return permissionDAO.deleteById(id);
	}

	@Override
	public int updatePermission(Permission model) {
		return permissionDAO.update(model);
	}

	@Override
	public Set<String> getPermissions() {
		Set<String> set = new HashSet<>();
		List<Permission> list = permissionDAO.queryAll();
		if (list == null) {
			return set;
		}
		for (Permission permission : list) {
			set.add(permission.getCode());
		}
		return set;
	}

}
