package org.sun.admin.service;

import java.util.List;

import org.sun.model.Admin;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface AdminService {

	Admin getAdminByUsername(String username);
	
	Admin getAdminById(int id);

	List<Admin> getAdminDatas();

	int addAdmin(Admin model);

	int removeAdminById(int id);

	int updateAdmin(Admin model);
	
	boolean isSuperUser(String username);

}
