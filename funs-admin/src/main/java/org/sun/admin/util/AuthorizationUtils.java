package org.sun.admin.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sun.admin.shiro.AdminShiroRealm;

/**
* @author sun 
* @date Jan 24, 2018 10:54:06 AM
* 
*/
public class AuthorizationUtils {

	private static Logger logger = LoggerFactory.getLogger(AuthorizationUtils.class); 
	
	/**
	 * 清除当前用户的权限缓存
	 */
	public static void clearCachedAuthorizationInfo(){  
		DefaultWebSecurityManager manager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();  
		ModularRealmAuthorizer mAuthorizer = (ModularRealmAuthorizer) manager.getAuthorizer();
	    AdminShiroRealm realm = (AdminShiroRealm) mAuthorizer.getRealms().iterator().next();  
	    realm.clearCachedAuthorizationInfo();  
	}  
	
	/**
	 * 清除指定用户的权限缓存
	 * @param username
	 */
	public static void clearCachedAuthorizationInfo(List<String> usernameList){  
		DefaultWebSecurityManager manager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();  
		ModularRealmAuthorizer mAuthorizer = (ModularRealmAuthorizer) manager.getAuthorizer();
	    AdminShiroRealm realm = (AdminShiroRealm) mAuthorizer.getRealms().iterator().next(); 
	    realm.clearCachedAuthorizationInfo(usernameList);
	} 
}
