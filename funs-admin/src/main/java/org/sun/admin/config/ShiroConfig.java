package org.sun.admin.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.sun.admin.redis.RedisCacheManager;
import org.sun.admin.redis.RedisSessionDAO;
import org.sun.admin.shiro.AdminFormAuthenticationFilter;
import org.sun.admin.shiro.AdminShiroRealm;
import org.sun.admin.shiro.CustomModularRealmAuthenticator;
import org.sun.admin.shiro.CustomSessionListener;

/**
 * @author sun
 * @date Jan 16, 2018 5:09:58 PM
 * 
 */

@Configuration
public class ShiroConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean(name = "adminShiroRealm")
	public AdminShiroRealm adminShiroRealm() {
		logger.info("===adminShiroRealm()");
		AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
		adminShiroRealm.setAuthenticationCachingEnabled(true);
		adminShiroRealm.setCacheManager(redisCacheManager());// redis权限缓存 默认缓存可注释此行
		adminShiroRealm.setCredentialsMatcher(adminHashedCredentialsMatcher());
		return adminShiroRealm;
	}

	@Bean(name = "redisCacheManager")
	public RedisCacheManager redisCacheManager() {
		logger.info("===redisCacheManager()");
		return new RedisCacheManager();
	}

	@Bean(name = "redisSessionDAO")
	public RedisSessionDAO redisSessionDAO() {
		logger.debug("ShiroConfiguration.redisSessionDAO()");
		return new RedisSessionDAO();
	}
	
    @Bean(name = "customSessionListener")
    public CustomSessionListener customSessionListener(){
        logger.debug("ShiroConfiguration.customSessionListener()");
        return new CustomSessionListener();
    }

	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager() {
		logger.debug("ShiroConfiguration.defaultWebSessionManager()");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// 用户信息必须是序列化格式，要不创建用户信息创建不过去，此坑很大，
		sessionManager.setSessionDAO(redisSessionDAO());// 如不想使用REDIS可注释此行
		Collection<SessionListener> sessionListeners = new ArrayList<>();
		sessionListeners.add(customSessionListener());
		sessionManager.setSessionListeners(sessionListeners);
		// 单位为毫秒（1秒=1000毫秒） 3600000毫秒为1个小时
		sessionManager.setSessionValidationInterval(3600000 * 12);
		// 3600000 milliseconds = 1 hour
		sessionManager.setGlobalSessionTimeout(3600000 * 12);
		// 是否删除无效的，默认也是开启
		sessionManager.setDeleteInvalidSessions(true);
		// 是否开启 检测，默认开启
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// 创建会话Cookie
		Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		cookie.setName("WEBID");
		cookie.setHttpOnly(true);
		sessionManager.setSessionIdCookie(cookie);
		return sessionManager;
	}

	@Bean(name = "adminHashedCredentialsMatcher")
	public HashedCredentialsMatcher adminHashedCredentialsMatcher() {
		logger.info("===adminHashedCredentialsMatcher()");
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，当于 m比如散列两次，相d5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean(name = "authenticationStrategy")
	public AuthenticationStrategy authenticationStrategy() {
		logger.info("===authenticationStrategy()");
		return new FirstSuccessfulStrategy();
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManage() {
		logger.info("===getDefaultWebSecurityManage()");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		Map<String, Object> shiroAuthenticatorRealms = new HashMap<>();
		shiroAuthenticatorRealms.put("adminShiroRealm", adminShiroRealm());

		Collection<Realm> shiroAuthorizerRealms = new ArrayList<Realm>();
		shiroAuthorizerRealms.add(adminShiroRealm());

		CustomModularRealmAuthenticator customModularRealmAuthenticator = new CustomModularRealmAuthenticator();
		customModularRealmAuthenticator.setDefinedRealms(shiroAuthenticatorRealms);
		customModularRealmAuthenticator.setAuthenticationStrategy(authenticationStrategy());
		securityManager.setAuthenticator(customModularRealmAuthenticator);
		ModularRealmAuthorizer customModularRealmAuthorizer = new ModularRealmAuthorizer();
		customModularRealmAuthorizer.setRealms(shiroAuthorizerRealms);
		securityManager.setAuthorizer(customModularRealmAuthorizer);
		// 注入缓存管理器;
		securityManager.setCacheManager(redisCacheManager());
		securityManager.setSessionManager(defaultWebSessionManager());
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		logger.info("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 增加自定义过滤
		Map<String, Filter> filters = new HashMap<>();
		filters.put("admin", new AdminFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		/**
		 * anon（匿名） org.apache.shiro.web.filter.authc.AnonymousFilter authc（身份验证）
		 * org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 * authcBasic（http基本验证）
		 * org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter logout（退出）
		 * org.apache.shiro.web.filter.authc.LogoutFilter noSessionCreation（不创建session）
		 * org.apache.shiro.web.filter.session.NoSessionCreationFilter perms(许可验证)
		 * org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter port（端口验证）
		 * org.apache.shiro.web.filter.authz.PortFilter rest (rest方面)
		 * org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter roles（权限验证）
		 * org.apache.shiro.web.filter.authz.RolesAuthorizationFilter ssl （ssl方面）
		 * org.apache.shiro.web.filter.authz.SslFilter member （用户方面）
		 * org.apache.shiro.web.filter.authc.UserFilter user
		 * 表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
		 */

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		// filterChainDefinitionMap.put("/**/login", "anon");
		// filterChainDefinitionMap.put("/**/logout", "logout");
		// filterChainDefinitionMap.put("/**/reg", "anon");
		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/admin/**", "admin");

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		// shiroFilterFactoryBean.setLoginUrl("/member/login");
		// 登录成功后要跳转的链接
		// shiroFilterFactoryBean.setSuccessUrl("/member/index");
		// 未授权界面;
		// shiroFilterFactoryBean.setUnauthorizedUrl("/console/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        logger.debug("ShiroConfiguration.lifecycleBeanPostProcessor()");
        return new LifecycleBeanPostProcessor();
    }

    @ConditionalOnMissingBean
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        logger.debug("ShiroConfiguration.getDefaultAdvisorAutoProxyCreator()");
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    
    @Bean(name="authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        logger.debug("ShiroConfiguration.authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
