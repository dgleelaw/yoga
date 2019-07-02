package com.woniuxy.yogasystem.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RolesFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//1.获取subject对象
		Subject currentUser = SecurityUtils.getSubject();
		
		//2.获取[]配置的角色信息
		String[] roles = (String[])mappedValue;
		
		//3.判断
		if(roles!=null&&roles.length!=0) {
			for (String role : roles) {
				//判断当前用户是否是该角色
				if(currentUser.hasRole(role)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
