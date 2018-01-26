package org.sun.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sun.admin.service.AdminService;
import org.sun.admin.util.Utils;
import org.sun.dao.admin.AdminDAO;
import org.sun.model.admin.Admin;

/**
* @author sun 
* @date Jan 15, 2018 2:18:48 PM
* 
*/

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public Admin getAdminByUsername(String username) {
		return adminDAO.queryByUsername(username);
	}
	
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
		Utils.encryptPassword(model);
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
	public boolean isSuperUser(String username) {
		return adminDAO.querySuperByUsername(username) == 1 ? true : false;
	}

}
