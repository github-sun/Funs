package org.sun.admin.shiro;

import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.sun.admin.contacts.Contacts;
import org.sun.admin.service.AdminService;
import org.sun.admin.service.PermissionService;
import org.sun.admin.service.RolePermissionService;
import org.sun.model.admin.Admin;

/**
 * @author sun
 * @date Jan 16, 2018 2:32:55 PM
 * 
 */
public class AdminShiroRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminService adminService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RolePermissionService rolePermissionService;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("===认证:doGetAuthenticationInfo()");
		String username = (String) token.getPrincipal();
		Admin admin = adminService.getAdminByUsername(username);
		if (admin == null) {
			throw new UnknownAccountException();
		}

		if (admin.getState() == 1) {
			throw new LockedAccountException();
		}
		
		String authz = Contacts.AUTHZ + username;
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authz, admin.getPassword(),
				new MSimpleByteSource(admin.getSalt().getBytes()), admin.getUsername());
		return authenticationInfo;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthenticationException {
		logger.info("===授权:doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = "";
		Iterator<String> it = principals.getRealmNames().iterator();
		if (it.hasNext()) {
			username = it.next();
		}
		if (adminService.isSuperUser(username)) {
			logger.info("===授权:doGetAuthorizationInfo() is super");
			authorizationInfo.setStringPermissions(permissionService.getPermissions());
			return authorizationInfo;
		}
		authorizationInfo.setStringPermissions(rolePermissionService.getPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 清空当前用户认证缓存信息
	 */
	public void clearCachedAuthenticationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthenticationInfo(principals);
	}
	
	/**
	 * 通过用户列表清除认证缓存信息
	 */
	public void clearCachedAuthenticationInfo(List<String> usernameList) {
		String name = getName();
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		for (String string : usernameList) {
			principals.add(string, name);
		}
		super.clearCachedAuthenticationInfo(principals);
	}
	
	/**
	 * 清空当前用户权限缓存信息
	 */
	public void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 通过用户列表清除权限信息
	 */
	public void clearCachedAuthorizationInfo(List<String> usernameList) {
		String name = getName();
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		for (String string : usernameList) {
			principals.add(string, name);
		}
		super.clearCachedAuthorizationInfo(principals);
	}
	
	/**
	 * 清空当前用户的认证和权限缓存信息
	 */
	public void clearCache() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCache(principals);
	}
	
	/**
	 * 通过用户列表清除认证和权限信息
	 */
	public void clearCache(List<String> usernameList) {
		String name = getName();
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		for (String string : usernameList) {
			principals.add(string, name);
		}
		super.clearCache(principals);
	}
}
