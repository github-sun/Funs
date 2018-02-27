package org.sun.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sun.admin.contacts.Contacts;
import org.sun.admin.enums.ResponseResultCode;
import org.sun.admin.shiro.AuthenticationToken;
import org.sun.admin.util.ResponseResultUtils;
import org.sun.model.admin.Admin;
import org.sun.model.vo.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@Api(value="",tags={"登录接口"})
@RestController
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	

	@ApiOperation(value="登录", notes="")
	@ApiImplicitParam(name = "model", value = "用户信息", required = true, dataType = "Admin")
	@PostMapping("/login")
	public ResponseResult login(@RequestBody Admin model) {
		logger.debug("===login "+model.toString());
		int result = 1;
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new AuthenticationToken(model.getUsername(), model.getPassword(), true);
		try {
			subject.login(token);
			subject.getSession().setAttribute(Contacts.SESSION_SUBJECT, model.getUsername());
			result = 0;
		} catch (Exception e) {
			logger.error("===login token exception "+e);
		}
		return result == 0 ? ResponseResultUtils.success() : ResponseResultUtils.warn(ResponseResultCode.LOGIN_ERROR);
	}
}
