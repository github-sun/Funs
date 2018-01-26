package org.sun.admin.service;

import java.util.List;

import org.sun.model.admin.Role;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface RoleService {

	Role getRoleById(int id);

	List<Role> getRoleDatas();

	int addRole(Role model);

	int removeRoleById(int id);

	int updateRole(Role model);

}
