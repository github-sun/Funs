package org.sun.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.AdminRoleService;
import org.sun.dao.admin.AdminRoleDAO;
import org.sun.model.admin.AdminRole;
import org.sun.model.admin.bo.AdminRoleBO;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class AdminRoleServiceImpl implements AdminRoleService{
	
	@Autowired
	private AdminRoleDAO adminRoleDAO;


	@Override
	public AdminRole getAdminRoleById(int adminId, int roleId) {
		return adminRoleDAO.queryById(adminId, roleId);
	}

	@Override
	public List<AdminRoleBO> getAdminRoleDatas() {
		return  adminRoleDAO.query();
	}

	@Override
	public int addAdminRole(AdminRole model) {
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
