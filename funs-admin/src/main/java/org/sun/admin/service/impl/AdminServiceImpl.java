package org.sun.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.AdminService;
import org.sun.dao.AdminDAO;
import org.sun.dao.AdminRoleDAO;
import org.sun.dao.RoleDAO;
import org.sun.model.Admin;
import org.sun.model.AdminRole;
import org.sun.model.Role;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private AdminRoleDAO adminRoleDAO;

	@Override
	public Admin getAdminById(int id) {
		return adminDAO.queryById(id);
	}

	@Override
	public List<Admin> getAdminDatas() {
		return adminDAO.queryAll();
	}

	@Override
	public int addAdmin(Admin model) {
		return adminDAO.insert(model);
	}

	@Override
	public int removeAdminById(int id) {
		return adminDAO.deleteById(id);
	}

	@Override
	public int updateAdmin(Admin model) {
		return adminDAO.update(model);
	}

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

	@Override
	public AdminRole getAdminRoleById(int adminId, int roleId) {
		return adminRoleDAO.queryById(adminId, roleId);
	}

	@Override
	public List<AdminRole> getAdminRoleDatas() {
		return adminRoleDAO.queryAll();
	}

	@Override
	public int addRole(AdminRole model) {
		return adminRoleDAO.insert(model);
	}

	@Override
	public int removeAdminRoleById(int adminId, int roleId) {
		return adminRoleDAO.deleteById(adminId, roleId);
	}

	@Override
	public int updateAdminRole(AdminRole model) {
		return adminRoleDAO.update(model);
	}

}
