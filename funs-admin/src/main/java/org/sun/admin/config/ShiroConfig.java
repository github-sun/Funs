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
import org.sun.admin.shiro.MModularRealmAuthenticator;
import org.sun.admin.shiro.MSessionListener;

/**
 * @author sun
 * @date Jan 16, 2018 5:09:58 PM
 * 
 */

@Configuration
public class ShiroConfig {
	
	public static final String DEFAULT_SESSION_ID_NAME = "SHIRO_JSESSIONID";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean(name = "adminShiroRealm")
	public AdminShiroRealm adminShiroRealm() {
		logger.info("===adminShiroRealm()"); 
		AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
		adminShiroRealm.setCachingEnabled(true);   
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
		logger.debug("===redisSessionDAO()");
		return new RedisSessionDAO();
	}
	
    @Bean(name = "customSessionListener")
    public MSessionListener customSessionListener(){
        logger.debug("===customSessionListener()");
        return new MSessionListener();
    }

	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager() {
		logger.debug("===defaultWebSessionManager()");
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
		Cookie cookie = new SimpleCookie(DEFAULT_SESSION_ID_NAME);
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

		MModularRealmAuthenticator customModularRealmAuthenticator = new MModularRealmAuthenticator();
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
		logger.info("===shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 增加自定义过滤
		Map<String, Filter> filters = new HashMap<>();
		filters.put("admin", new AdminFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		filterChainDefinitionMap.put("/admin/**", "admin");
		filterChainDefinitionMap.put("/login*", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        logger.debug("===lifecycleBeanPostProcessor()");
        return new LifecycleBeanPostProcessor();
    }

    @ConditionalOnMissingBean
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        logger.debug("===getDefaultAdvisorAutoProxyCreator()");
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
    
    @Bean(name="authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        logger.debug("===authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
