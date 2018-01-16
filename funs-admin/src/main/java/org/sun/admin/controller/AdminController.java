package org.sun.admin.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sun.admin.service.AdminService;
import org.sun.model.Admin;
import org.sun.model.AdminRole;
import org.sun.model.Role;
import org.sun.model.bo.AdminRoleBO;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
public class AdminController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable("id") Integer id) {
		logger.info("===getAdminById id "+id);
		return adminService.getAdminById(id);
	}
	
	@GetMapping("/admin")
	public List<Admin> getAdminDatas() {
		List<Admin> list = adminService.getAdminDatas();
		logger.info("===getAdminDatas "+list);
		return list;
	}
	
	@PostMapping("/admin")
	public int addAdmin(@RequestBody Admin model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		adminService.addAdmin(model);
		logger.info("===addAdmin　"+model.toString());
		return model.getId();
	}
	
	@DeleteMapping("/admin/{id}")
	public int removeAdmin(@PathVariable("id") Integer id) {
		logger.info("===removeAdmin　"+id);
		return adminService.removeAdminById(id);
	}
	
	@PutMapping("/admin")
	public int updateAdmin(@RequestBody Admin model) {
		logger.info("===updateAdmin　"+model.toString());
		model.setUpdateDate(new Date());
		int result = adminService.updateAdmin(model);
		logger.info("===updateAdmin　"+model.toString() + " result "+result);
		return result;
	}
	
	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable("id") Integer id) {
		logger.info("===getRoleById id "+id);
		return adminService.getRoleById(id);
	}
	
	@GetMapping("/role")
	public List<Role> getRoleDatas() {
		List<Role> list = adminService.getRoleDatas();
		logger.info("===getRoleDatas "+list);
		return list;
	}
	
	@PostMapping("/role")
	public int addRole(@RequestBody Role model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		adminService.addRole(model);
		logger.info("===addRole　"+model.toString());
		return model.getId();
	}
	
	@DeleteMapping("/role/{id}")
	public int removeRole(@PathVariable("id") Integer id) {
		logger.info("===removeRole　"+id);
		return adminService.removeRoleById(id);
	}
	
	@PutMapping("/role")
	public int updateRole(@RequestBody Role model) {
		logger.info("===updateRole　"+model.toString());
		model.setUpdateDate(new Date());
		int result = adminService.updateRole(model);
		logger.info("===updateRole　"+model.toString() + " result "+result);
		return result;
	}
	
	@GetMapping("/adminrole/{adminId}/{roleId}")
	public AdminRole getAdminRoleById(@PathVariable("adminId") Integer adminId,@PathVariable("roleId") Integer roleId) {
		logger.info("===getAdminRoleById adminId "+adminId + " roleId "+roleId);
		return adminService.getAdminRoleById(adminId, roleId);
	}
	
	@GetMapping("/adminrole")
	public List<AdminRoleBO> getAdminRoleDatas() {
		List<AdminRoleBO> list = adminService.getAdminRoleDatas();
		logger.info("===getAdminRoleDatas "+list);
		return list;
	}
	
	@PostMapping("/adminrole")
	public int addAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===addAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null == adminRole) {
			return adminService.addAdminRole(model);
		}
		return 0;
	}
	
	@DeleteMapping("/adminrole/{admin_id}/{role_id}")
	public int removeAdminRole(@PathVariable("admin_id") Integer adminId,@PathVariable("role_id") Integer roleId) {
		logger.info("===removeAdminRole　admin_id "+adminId + " role_id "+roleId);
		return adminService.removeAdminRoleById(adminId, roleId);
	}
	
	@PutMapping("/adminrole")
	public int updateAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===updateAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null == adminRole) {
			return adminService.updateAdminRole(model);
		}
		return 0;
	}
}
