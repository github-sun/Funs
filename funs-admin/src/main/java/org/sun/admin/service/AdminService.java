package org.sun.admin.service;

import java.util.List;

import org.sun.model.Admin;
import org.sun.model.AdminRole;
import org.sun.model.Role;
import org.sun.model.bo.AdminRoleBO;

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

	Role getRoleById(int id);

	List<Role> getRoleDatas();

	int addRole(Role model);

	int removeRoleById(int id);

	int updateRole(Role model);

	AdminRole getAdminRoleById(int adminId, int roleId);

	List<AdminRoleBO> getAdminRoleDatas();

	int addAdminRole(AdminRole model);

	int removeAdminRoleById(int adminId, int roleId);

	int updateAdminRole(AdminRole model);
}
