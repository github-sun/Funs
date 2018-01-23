package org.sun.admin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sun.admin.service.AdminRoleService;
import org.sun.model.AdminRole;
import org.sun.model.bo.AdminRoleBO;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
@RequestMapping("/admin")
public class AdminRoleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	@RequiresPermissions("adminrole:list.id")
	@GetMapping("/adminrole/{adminId}/{roleId}")
	public AdminRole getAdminRoleById(@PathVariable("adminId") Integer adminId,@PathVariable("roleId") Integer roleId) {
		logger.info("===getAdminRoleById adminId "+adminId + " roleId "+roleId);
		return adminRoleService.getAdminRoleById(adminId, roleId);
	}
	
	@RequiresPermissions("adminrole:list.all")
	@GetMapping("/adminrole")
	public List<AdminRoleBO> getAdminRoleDatas() {
		List<AdminRoleBO> list = adminRoleService.getAdminRoleDatas();
		logger.info("===getAdminRoleDatas "+list);
		return list;
	}
	
	@RequiresPermissions("adminrole:add")
	@PostMapping("/adminrole")
	public int addAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminRoleService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===addAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null == adminRole) {
			return adminRoleService.addAdminRole(model);
		}
		return 0;
	}
	
	@RequiresPermissions("adminrole:remove.id")
	@DeleteMapping("/adminrole/{admin_id}/{role_id}")
	public int removeAdminRole(@PathVariable("admin_id") Integer adminId,@PathVariable("role_id") Integer roleId) {
		logger.info("===removeAdminRole　admin_id "+adminId + " role_id "+roleId);
		return adminRoleService.removeAdminRoleById(adminId, roleId);
	}
	
	@RequiresPermissions("adminrole:update")
	@PutMapping("/adminrole")
	public int updateAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminRoleService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===updateAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null == adminRole) {
			return adminRoleService.updateAdminRole(model);
		}
		return 0;
	}
}
