package org.sun.admin.service;

import java.util.List;

import org.sun.model.AdminRole;
import org.sun.model.bo.AdminRoleBO;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface AdminRoleService {

	AdminRole getAdminRoleById(int adminId, int roleId);

	List<AdminRoleBO> getAdminRoleDatas();

	int addAdminRole(AdminRole model);

	int removeAdminRoleById(int adminId, int roleId);

	int updateAdminRole(AdminRole model);
}
