package org.sun.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.RoleService;
import org.sun.dao.RoleDAO;
import org.sun.model.admin.Role;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDAO roleDAO;
	

	@Override
	public Role getRoleById(int id) {
		return roleDAO.queryById(id);
	}

	@Override
	public List<Role> getRoleDatas() {
		return roleDAO.queryAll();
	}

	@Override
	public int addRole(Role model) {
		return roleDAO.insert(model);
	}

	@Override
	public int removeRoleById(int id) {
		return roleDAO.deleteById(id);
	}

	@Override
	public int updateRole(Role model) {
		return roleDAO.update(model);
	}

}
