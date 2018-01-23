package org.sun.admin.controller;

import java.util.Date;
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
import org.sun.admin.service.PermissionService;
import org.sun.model.Permission;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
@RequestMapping("/admin")
public class PermissionController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("permission:list.id")
	@GetMapping("/permission/{id}")
	public Permission getPermissionById(@PathVariable("id") Integer id) {
		logger.info("===getPermissionById id "+id);
		return permissionService.getPermissionById(id);
	}
	
	@RequiresPermissions("permission:list.all")
	@GetMapping("/permission")
	public List<Permission> getPermissionDatas() {
		List<Permission> list = permissionService.getPermissionDatas();
		logger.info("===getPermissionDatas "+list);
		return list;
	}
	
	@RequiresPermissions("permission:add")
	@PostMapping("/permission")
	public int addPermission(@RequestBody Permission model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		permissionService.addPermission(model);
		logger.info("===addPermission　"+model.toString());
		return model.getId();
	}
	
	@RequiresPermissions("permission:remove.id")
	@DeleteMapping("/permission/{id}")
	public int removePermission(@PathVariable("id") Integer id) {
		logger.info("===removePermission　"+id);
		return permissionService.removePermissionById(id);
	}
	
	@RequiresPermissions("permission:update")
	@PutMapping("/permission")
	public int updatePermission(@RequestBody Permission model) {
		logger.info("===updatePermission　"+model.toString());
		model.setUpdateDate(new Date());
		int result = permissionService.updatePermission(model);
		logger.info("===updatePermission　"+model.toString() + " result "+result);
		return result;
	}
	
}
