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
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.AdminRole;
import org.sun.model.admin.bo.AdminRoleBO;
import org.sun.model.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@Api(value="",tags={"用户角色对照操作接口"})
@RestController
@RequestMapping("/admin")
public class AdminRoleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	@ApiOperation(value="根据id获取用户角色对照列表", notes="")
	@ApiImplicitParams({@ApiImplicitParam(name = "adminId", value = "用户ID", required = true, dataType = "Integer"), @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Integer")})
	@RequiresPermissions("adminrole:list.id")
	@GetMapping("/adminrole/{adminId}/{roleId}")
	public AdminRole getAdminRoleById(@PathVariable("adminId") Integer adminId,@PathVariable("roleId") Integer roleId) {
		logger.info("===getAdminRoleById adminId "+adminId + " roleId "+roleId);
		return adminRoleService.getAdminRoleById(adminId, roleId);
	}
	
	@ApiOperation(value="获取所有用户角色对照列表", notes="")
	@RequiresPermissions("adminrole:list.all")
	@GetMapping("/adminrole")
	public ResponseResult getAdminRoleDatas() {
		List<AdminRoleBO> list = adminRoleService.getAdminRoleDatas();
		logger.info("===getAdminRoleDatas "+list);
		return ResponseResultUtils.success(list);
	}
	
	@ApiOperation(value="添加用户角色", notes="")
	@ApiImplicitParam(name = "model", value = "用户角色对照信息", required = true, dataType = "AdminRole")
	@RequiresPermissions("adminrole:add")
	@PostMapping("/adminrole")
	public ResponseResult addAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminRoleService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===addAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null != adminRole) {
			return ResponseResultUtils.failed();
		}
		return adminRoleService.addAdminRole(model) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@ApiOperation(value="根据用户id删除该用户角色对照", notes="")
	@ApiImplicitParams({@ApiImplicitParam(name = "admin_id", value = "用户ID", required = true, dataType = "Integer"), @ApiImplicitParam(name = "role_id", value = "角色ID", required = true, dataType = "Integer")})
	@RequiresPermissions("adminrole:remove.id")
	@DeleteMapping("/adminrole/{admin_id}/{role_id}")
	public ResponseResult removeAdminRole(@PathVariable("admin_id") Integer adminId,@PathVariable("role_id") Integer roleId) {
		logger.info("===removeAdminRole　admin_id "+adminId + " role_id "+roleId);
		return adminRoleService.removeAdminRoleById(adminId, roleId) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@ApiOperation(value="修改用户角色对照", notes="")
	@ApiImplicitParam(name = "model", value = "用户角色对照信息", required = true, dataType = "AdminRole")
	@RequiresPermissions("adminrole:update")
	@PutMapping("/adminrole")
	public ResponseResult updateAdminRole(@RequestBody AdminRole model) {
		AdminRole adminRole = adminRoleService.getAdminRoleById(model.getAdminId(), model.getRoleId());
		logger.info("===updateAdminRole　"+model.toString() + " adminRole " +adminRole);
		if (null != adminRole) {
			return ResponseResultUtils.failed();
		}
		return adminRoleService.updateAdminRole(model) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
}
