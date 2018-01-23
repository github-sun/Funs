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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sun.admin.service.RoleService;
import org.sun.model.Role;

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
	
	@GetMapping("/role/{id}")
	public Role getRoleById(@PathVariable("id") Integer id) {
		logger.info("===getRoleById id "+id);
		return roleService.getRoleById(id);
	}
	
	@GetMapping("/role")
	public List<Role> getRoleDatas() {
		List<Role> list = roleService.getRoleDatas();
		logger.info("===getRoleDatas "+list);
		return list;
	}
	
	@PostMapping("/role")
	public int addRole(@RequestBody Role model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		roleService.addRole(model);
		logger.info("===addRole　"+model.toString());
		return model.getId();
	}
	
	@DeleteMapping("/role/{id}")
	public int removeRole(@PathVariable("id") Integer id) {
		logger.info("===removeRole　"+id);
		return roleService.removeRoleById(id);
	}
	
	@PutMapping("/role")
	public int updateRole(@RequestBody Role model) {
		logger.info("===updateRole　"+model.toString());
		model.setUpdateDate(new Date());
		int result = roleService.updateRole(model);
		logger.info("===updateRole　"+model.toString() + " result "+result);
		return result;
	}

}
