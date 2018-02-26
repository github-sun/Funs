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

/**
* @author sun 
* @date Jan 15, 2018 2:23:58 PM
* 
*/

@RestController
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	

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
