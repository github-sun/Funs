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
import org.sun.admin.service.RoleService;
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.Role;
import org.sun.model.vo.ResponseResult;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
@RequestMapping("/admin")
public class RoleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private RoleService roleService;
	
	@RequiresPermissions("role:list.id")
	@GetMapping("/role/{id}")
	public ResponseResult getRoleById(@PathVariable("id") Integer id) {
		logger.info("===getRoleById id "+id);
		Role model = roleService.getRoleById(id);
		return ResponseResultUtils.success(model);
	}
	
	@RequiresPermissions("role:list.all")
	@GetMapping("/role")
	public ResponseResult getRoleDatas() {
		List<Role> list = roleService.getRoleDatas();
		logger.info("===getRoleDatas "+list);
		return ResponseResultUtils.success(list);
	}
	
	@RequiresPermissions("role:add")
	@PostMapping("/role")
	public ResponseResult addRole(@RequestBody Role model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		roleService.addRole(model);
		logger.info("===addRole　"+model.toString());
		return model.getId() > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@RequiresPermissions("role:remove.id")
	@DeleteMapping("/role/{id}")
	public ResponseResult removeRole(@PathVariable("id") Integer id) {
		logger.info("===removeRole　"+id);
		return roleService.removeRoleById(id) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@RequiresPermissions("role:update")
	@PutMapping("/role")
	public ResponseResult updateRole(@RequestBody Role model) {
		logger.info("===updateRole　"+model.toString());
		model.setUpdateDate(new Date());
		int result = roleService.updateRole(model);
		logger.info("===updateRole　"+model.toString() + " result "+result);
		return result > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}

}
