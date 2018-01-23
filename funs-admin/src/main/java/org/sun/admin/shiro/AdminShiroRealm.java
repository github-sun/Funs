package org.sun.admin.shiro;

import java.util.Iterator;

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
import org.sun.admin.service.AdminService;
import org.sun.admin.service.PermissionService;
import org.sun.admin.service.RolePermissionService;
import org.sun.model.Admin;

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
	 * 认证信息 Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
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
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(),
				new MSimpleByteSource(admin.getSalt().getBytes()), admin.getUsername());
		return authenticationInfo;
	}

	/**
	 * 此方法调用 hasRole,hasPermission的时候才会进行回调.
	 *
	 * 权限信息.(授权): 1、如果用户正常退出，缓存自动清空； 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。 （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例， 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 * 
	 * @param principals
	 * @return
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
	 * 清空当前用户权限信息
	 */
	public void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
