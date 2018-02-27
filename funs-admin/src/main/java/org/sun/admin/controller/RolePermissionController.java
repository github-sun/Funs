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
import org.sun.admin.service.RolePermissionService;
import org.sun.admin.util.AuthorizationUtils;
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.RolePermission;
import org.sun.model.admin.bo.RolePermissionBO;
import org.sun.model.vo.ResponseResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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

	@ApiOperation(value="根据id获取角色权限对照列表", notes="")
	@ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Integer"), @ApiImplicitParam(name = "permissionId", value = "权限ID", required = true, dataType = "Integer")})
	@RequiresPermissions("rolepermission:list.id")
	@GetMapping("/rolepermission/{roleId}/{permissionId}")
	public RolePermission getRolePermissionById(@PathVariable("roleId") Integer roleId,
			@PathVariable("permissionId") Integer permissionId) {
		logger.info("===getRolePermissionById roleId " + roleId + " permissionId " + permissionId);
		return rolePermissionService.getRolePermissionById(roleId, permissionId);
	}

	@ApiOperation(value="获取所有角色权限对照列表", notes="")
	@RequiresPermissions("rolepermission:list.all")
	@GetMapping("/rolepermission")
	public ResponseResult getRolePermissionDatas() {
		List<RolePermissionBO> list = rolePermissionService.getRolePermissionDatas();
		logger.info("===getRolePermissionDatas " + list);
		return ResponseResultUtils.success(list);
	}

	@ApiOperation(value="添加角色权限对照", notes="")
	@ApiImplicitParam(name = "model", value = "角色权限对照信息", required = true, dataType = "RolePermission")
	@RequiresPermissions("rolepermission:add")
	@PostMapping("/rolepermission")
	public ResponseResult addRolePermission(@RequestBody RolePermission model) {
		RolePermission rolePermission = rolePermissionService.getRolePermissionById(model.getRoleId(),
				model.getPermissionId());
		logger.info("===addRolePermission　" + model.toString() + " rolePermission " + rolePermission);
		if (null != rolePermission) {
			return ResponseResultUtils.failed();
		}
		ResponseResult result = rolePermissionService.addRolePermission(model) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
		List<String> authz = rolePermissionService.getUserAuthzByPermission(model.getRoleId(), model.getPermissionId());
		logger.info("===addRolePermission　authz " + authz);
		AuthorizationUtils.clearCachedAuthorizationInfo(authz);
		return result;
	}

	@ApiOperation(value="根据用户id删除该角色权限对照", notes="")
	@ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Integer"), @ApiImplicitParam(name = "permissionId", value = "权限ID", required = true, dataType = "Integer")})
	@RequiresPermissions("rolepermission:remove.id")
	@DeleteMapping("/rolepermission/{roleId}/{permissionId}")
	public ResponseResult removeRolePermission(@PathVariable("roleId") Integer roleId,
			@PathVariable("permissionId") Integer permissionId) {
		logger.info("===removeRolePermission　roleId " + roleId + " permissionId " + permissionId);
		List<String> authz = rolePermissionService.getUserAuthzByPermission(roleId, permissionId);
		ResponseResult result = rolePermissionService.removeRolePermissionById(roleId, permissionId) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
		logger.info("===removeRolePermission　authz " + authz);
		AuthorizationUtils.clearCachedAuthorizationInfo(authz);
		return result;
	}

	@ApiOperation(value="修改角色权限对照", notes="")
	@ApiImplicitParam(name = "model", value = "角色权限对照信息", required = true, dataType = "RolePermission")
	@RequiresPermissions("rolepermission:update")
	@PutMapping("/rolepermission")
	public ResponseResult updateRolePermission(@RequestBody RolePermission model) {
		RolePermission rolePermission = rolePermissionService.getRolePermissionById(model.getRoleId(),
				model.getPermissionId());
		logger.info("===updateRolePermission　" + model.toString() + " rolePermission " + rolePermission);
		if (null != rolePermission) {
			return ResponseResultUtils.failed();
		}
		return rolePermissionService.updateRolePermission(model) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
}
