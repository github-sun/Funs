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
import org.sun.model.admin.Role;
import org.sun.model.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@Api(value="",tags={"角色操作接口"})
@RestController
@RequestMapping("/admin")
public class RoleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value="根据id获取角色列表", notes="")
	@ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Integer")
	@RequiresPermissions("role:list.id")
	@GetMapping("/role/{id}")
	public ResponseResult getRoleById(@PathVariable("id") Integer id) {
		logger.info("===getRoleById id "+id);
		Role model = roleService.getRoleById(id);
		return ResponseResultUtils.success(model);
	}
	
	@ApiOperation(value="获取所有角色列表", notes="")
	@RequiresPermissions("role:list.all")
	@GetMapping("/role")
	public ResponseResult getRoleDatas() {
		List<Role> list = roleService.getRoleDatas();
		logger.info("===getRoleDatas "+list);
		return ResponseResultUtils.success(list);
	}
	
	@ApiOperation(value="添加角色", notes="")
	@ApiImplicitParam(name = "model", value = "角色信息", required = true, dataType = "Role")
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
	
	@ApiOperation(value="根据用户id删除该角色", notes="")
	@ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Integer")
	@RequiresPermissions("role:remove.id")
	@DeleteMapping("/role/{id}")
	public ResponseResult removeRole(@PathVariable("id") Integer id) {
		logger.info("===removeRole　"+id);
		return roleService.removeRoleById(id) > 0 ? ResponseResultUtils.success() : ResponseResultUtils.failed();
	}
	
	@ApiOperation(value="修改角色", notes="")
	@ApiImplicitParam(name = "model", value = "角色信息", required = true, dataType = "Role")
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
