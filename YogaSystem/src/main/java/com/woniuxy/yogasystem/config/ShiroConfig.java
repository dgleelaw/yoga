package com.woniuxy.yogasystem.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woniuxy.yogasystem.filter.RolesFilter;
import com.woniuxy.yogasystem.realm.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	@Bean
	public CredentialsMatcher matcher(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashIterations(1024);
		matcher.setHashAlgorithmName("MD5");
		return matcher;
	}
	
	//创建realm
	@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		UserRealm userRealm = new UserRealm();
		//userRealm.setCredentialsMatcher(matcher);
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthenticationCacheName("authentication");
		userRealm.setAuthorizationCachingEnabled(true);
		userRealm.setAuthorizationCacheName("authorization");
		return userRealm;
	}
		
	//创建安全管理器
	@Bean
	public SecurityManager securityManager(UserRealm realm,EhCacheManager manager){
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
		defaultSecurityManager.setRealm(realm);
		defaultSecurityManager.setCacheManager(manager);
		return defaultSecurityManager;
	}
	
	//创建shiro过滤器
	@Bean
	public ShiroFilterFactoryBean shFactoryBean(SecurityManager manager){
		//配置安全管理器
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		
		//自定义过滤
		Map<String, Filter> filterMap = new HashMap<>();
		filterMap.put("or", new RolesFilter());
		bean.setFilters(filterMap);
		//配置登录页面
		bean.setLoginUrl("/html/login.html");
		//无权限
		bean.setUnauthorizedUrl("/html/error.html");
		
		//设置过滤器链
		Map<String, String> map = new LinkedHashMap<>();
			
		//静态资源	
		map.put("/css/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/headimg/**", "anon");
		map.put("/js/**", "anon");
		map.put("/images/**", "anon");
		map.put("/veneusimg/**", "anon");
		
		//html
		map.put("/html/login.html", "anon");
		map.put("/html/register.html", "anon");
		map.put("/html/_header.html", "anon");
		map.put("/html/findvenues.html", "anon");
		map.put("/html/reset.html", "anon");
		map.put("/html/findcoach.html", "anon");
		map.put("/html/messagelogin.html", "anon");
		
		
		//controller
		map.put("/order/notify_url", "anon");
		map.put("/user/login", "anon");
		map.put("/user/register", "anon");
		map.put("/user/getcode", "anon");
		map.put("/user/checkacc", "anon");
		map.put("/index", "anon");
		map.put("/trainee/findCoachMsg", "anon");
		map.put("/coach/findVenues", "anon");
		map.put("/venues/find", "authc,or[0,1,3,4]");
		map.put("/trainee/findCoachDetailMsg", "authc,or[0,2,3,4]");
		map.put("/user/reset", "anon");
		map.put("/user/meslogin", "anon");
		
		
		
		map.put("/logout", "logout");
		map.put("/**", "authc");
		System.out.println(map);
		bean.setFilterChainDefinitionMap(map);
		
		return bean;
	}
	
	//缓存
	@Bean
	public EhCacheManager cacheManager(){
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	
	
	@Bean
	public ShiroDialect shiroDialect() {
	    return new ShiroDialect();
	} 
	
	
	
	
}
