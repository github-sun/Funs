package org.sun.admin.service;

import java.util.List;
import java.util.Set;

import org.sun.model.admin.Permission;

/**
 * @author sun
 * @date Jan 15, 2018 1:40:19 PM
 * 
 */
public interface PermissionService {

	Permission getPermissionById(int id);

	List<Permission> getPermissionDatas();

	int addPermission(Permission model);

	int removePermissionById(int id);

	int updatePermission(Permission model);
	
	Set<String> getPermissions();

}
