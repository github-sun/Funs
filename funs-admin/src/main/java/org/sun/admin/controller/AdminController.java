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
import org.sun.admin.service.AdminService;
import org.sun.model.Admin;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private AdminService adminService;
	
	@RequiresPermissions("admin:list.id")
	@GetMapping("/admin/{id}")
	public Admin getAdminById(@PathVariable("id") Integer id) {
		logger.info("===getAdminById id "+id);
		return adminService.getAdminById(id);
	}
	
	@RequiresPermissions("admin:list.all")
	@GetMapping("/admin")
	public List<Admin> getAdminDatas() {
		List<Admin> list = adminService.getAdminDatas();
		logger.info("===getAdminDatas "+list);
		return list;
	}
	
	@RequiresPermissions("admin:add")
	@PostMapping("/admin")
	public int addAdmin(@RequestBody Admin model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		adminService.addAdmin(model);
		logger.info("===addAdmin　"+model.toString());
		return model.getId();
	}
	
	@RequiresPermissions("admin:remove.id")
	@DeleteMapping("/admin/{id}")
	public int removeAdmin(@PathVariable("id") Integer id) {
		logger.info("===removeAdmin　"+id);
		return adminService.removeAdminById(id);
	}
	
	@RequiresPermissions("admin:update")
	@PutMapping("/admin")
	public int updateAdmin(@RequestBody Admin model) {
		logger.info("===updateAdmin　"+model.toString());
		model.setUpdateDate(new Date());
		int result = adminService.updateAdmin(model);
		logger.info("===updateAdmin　"+model.toString() + " result "+result);
		return result;
	}
	
}
