package org.sun.admin.shiro;

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
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.sun.admin.service.AdminService;
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


    /**
     * 认证信息
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("===认证:doGetAuthenticationInfo()");
        String username = (String)token.getPrincipal();
        Admin userInfo = adminService.getAdminByUsername(username);
        if(userInfo == null){
            throw new UnknownAccountException();
        }
        
        if (userInfo.getState() == 1) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException();
        }
       /*
        * 获取权限信息:这里没有进行实现，
        * 请自行根据UserInfo,Role,Permission进行实现；
        * 获取之后可以在前端for循环显示所有链接;
        */
        //userInfo.setPermissions(userService.findPermissions(member));

        //账号判断;

        //加密方式;
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                new MSimpleByteSource(userInfo.getSalt().getBytes()),
                userInfo.getUsername()  //realm name
        );

        return authenticationInfo;
    }


    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws AuthenticationException{
//       /*
//        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
//        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
//        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
//        * 缓存过期之后会再次执行。
//        */
//        logger.info("后台权限校验-->AdminShiroRealm.doGetAuthorizationInfo()");
//
    	logger.info("===授权:doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Admin userInfo  = (Admin)principals.getPrimaryPrincipal();
//        Set<String> menus = null;
//        if(userInfo.getIsSystem() == 1) {
//            menus = menuService.getAllMenuCode();
//        }else{
//            menus = menuService.findMenuCodeByUserId(userInfo.getUid());
//        }
//        authorizationInfo.setStringPermissions(menus);
        return authorizationInfo;
    }

    /**
     * 清空当前用户权限信息
     */
	public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
