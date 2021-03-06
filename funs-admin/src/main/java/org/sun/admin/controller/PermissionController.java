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
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.Permission;
import org.sun.model.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@Api(value="",tags={"权限操作接口"})
@RestController
@RequestMapping("/admin")
public class PermissionController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private PermissionService permissionService;
	
	@ApiOperation(value="根据id获取权限列表", notes="")
	@ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Integer")
	@RequiresPermissions("permission:list.id")
	@GetMapping("/permission/{id}")
	public ResponseResult getPermissionById(@PathVariable("id") Integer id) {
		logger.info("===getPermissionById id "+id);
		Permission model = permissionService.getPermissionById(id);
		return ResponseResultUtils.success(model);
	}
	
	@ApiOperation(value="获取所有权限列表", notes="")
	@RequiresPermissions("permission:list.all")
	@GetMapping("/permission")
	public ResponseResult getPermissionDatas() {
		List<Permission> list = permissionService.getPermissionDatas();
		logger.info("===getPermissionDatas "+list);
		return ResponseResultUtils.success(list);
	}
	
	@ApiOperation(value="添加权限", notes="")
	@ApiImplicitParam(name = "model", value = "权限信息", required = true, dataType = "Permission")
	@RequiresPermissions("permission:add")
	@PostMapping("/permission")
	public ResponseResult addPermission(@RequestBody Permission model) {
		Date date = new Date();
		model.setCreateDate(date);
		model.setUpdateDate(date);
		permissionService.addPermission(model);
		logger.info("===addPermission　"+model.toString());
		return model.getId() > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@ApiOperation(value="根据用户id删除该权限", notes="")
	@ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Integer")
	@RequiresPermissions("permission:remove.id")
	@DeleteMapping("/permission/{id}")
	public ResponseResult removePermission(@PathVariable("id") Integer id) {
		logger.info("===removePermission　"+id);
		return permissionService.removePermissionById(id) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@ApiOperation(value="修改权限", notes="")
	@ApiImplicitParam(name = "model", value = "权限信息", required = true, dataType = "Permission")
	@RequiresPermissions("permission:update")
	@PutMapping("/permission")
	public ResponseResult updatePermission(@RequestBody Permission model) {
		logger.info("===updatePermission　"+model.toString());
		model.setUpdateDate(new Date());
		int result = permissionService.updatePermission(model);
		logger.info("===updatePermission　"+model.toString() + " result "+result);
		return result > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
}
