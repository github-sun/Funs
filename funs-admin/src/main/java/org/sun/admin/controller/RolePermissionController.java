package org.sun.admin.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sun.admin.service.RolePermissionService;
import org.sun.model.RolePermission;
import org.sun.model.bo.RolePermissionBO;

/**
 * @author sun
 * @date Jan 15, 2018 2:23:58 PM
 * 
 */

@RestController
@RequestMapping("/admin")
public class RolePermissionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RolePermissionService rolePermissionService;

	@GetMapping("/rolepermission/{roleId}/{permissionId}")
	public RolePermission getRolePermissionById(@PathVariable("roleId") Integer roleId,
			@PathVariable("permissionId") Integer permissionId) {
		logger.info("===getRolePermissionById roleId " + roleId + " permissionId " + permissionId);
		return rolePermissionService.getRolePermissionById(roleId, permissionId);
	}

	@GetMapping("/rolepermission")
	public List<RolePermissionBO> getRolePermissionDatas() {
		List<RolePermissionBO> list = rolePermissionService.getRolePermissionDatas();
		logger.info("===getRolePermissionDatas " + list);
		return list;
	}

	@PostMapping("/rolepermission")
	public int addRolePermission(@RequestBody RolePermission model) {
		RolePermission rolePermission = rolePermissionService.getRolePermissionById(model.getRoleId(),
				model.getPermissionId());
		logger.info("===addRolePermission　" + model.toString() + " rolePermission " + rolePermission);
		if (null == rolePermission) {
			return rolePermissionService.addRolePermission(model);
		}
		return 0;
	}

	@DeleteMapping("/rolepermission/{roleId}/{permissionId}")
	public int removeRolePermission(@PathVariable("roleId") Integer roleId,
			@PathVariable("permissionId") Integer permissionId) {
		logger.info("===removeRolePermission　roleId " + roleId + " permissionId " + permissionId);
		return rolePermissionService.removeRolePermissionById(roleId, permissionId);
	}

	@PutMapping("/rolepermission")
	public int updateRolePermission(@RequestBody RolePermission model) {
		RolePermission rolePermission = rolePermissionService.getRolePermissionById(model.getRoleId(),
				model.getPermissionId());
		logger.info("===updateRolePermission　" + model.toString() + " rolePermission " + rolePermission);
		if (null == rolePermission) {
			return rolePermissionService.updateRolePermission(model);
		}
		return 0;
	}
}
