package org.sun.admin.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
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
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.Admin;
import org.sun.model.vo.ResponseResult;

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
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@RequiresPermissions("admin:list.id")
	@GetMapping("/admin/{id}")
	public ResponseResult getAdminById(@PathVariable("id") Integer id) {
		logger.info("===getAdminById id "+id);
		Admin model = adminService.getAdminById(id);
		return ResponseResultUtils.success(model);
	}
	
	@RequiresPermissions("admin:list.all")
	@GetMapping("/admin")
	public ResponseResult getAdminDatas() {
		List<Admin> list = adminService.getAdminDatas();
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		logger.info("===getAdminDatas "+list + " sessions "+sessions);
		return ResponseResultUtils.success(list);
	}
	
	@RequiresPermissions("admin:add")
	@PostMapping("/admin")
	public ResponseResult addAdmin(@RequestBody Admin model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		adminService.addAdmin(model);
		logger.info("===addAdmin　"+model.toString());
		return model.getId() > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@RequiresPermissions("admin:remove.id")
	@DeleteMapping("/admin/{id}")
	public ResponseResult removeAdmin(@PathVariable("id") Integer id) {
		logger.info("===removeAdmin　"+id);
		return adminService.removeAdminById(id) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@RequiresPermissions("admin:update")
	@PutMapping("/admin")
	public ResponseResult updateAdmin(@RequestBody Admin model) {
		logger.info("===updateAdmin　"+model.toString());
		model.setUpdateDate(new Date());
		int result = adminService.updateAdmin(model);
		logger.info("===updateAdmin　"+model.toString() + " result "+result);
		return result > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
}
