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
		logger.debug("===getUserById id "+id);
		return adminService.getAdminById(id);
	}
	
	@GetMapping("/admin")
	public List<Admin> getAdminDatas() {
		logger.debug("===getUsers");
		return adminService.getAdminDatas();
	}
	
	@PostMapping("/admin")
	public int addAdmin(@RequestBody Admin model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		adminService.addAdmin(model);
		logger.debug("===addAdmin　"+model.toString());
		return model.getId();
	}
	
	@DeleteMapping("/admin/{id}")
	public int removeAdmin(@PathVariable("id") Integer id) {
		logger.debug("===removeAdmin　"+id);
		return adminService.removeAdminById(id);
	}
	
	@PutMapping("/admin")
	public int updateAdmin(@RequestBody Admin model) {
		model.setUpdateDate(new Date());
		int result = adminService.updateAdmin(model);
		logger.debug("===updateAdmin　"+model.toString() + " result "+result);
		return result;
	}
}
