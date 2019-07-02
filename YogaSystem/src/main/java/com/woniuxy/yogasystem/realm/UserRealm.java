package com.woniuxy.yogasystem.realm;



import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.woniuxy.yogasystem.pojo.User;
import com.woniuxy.yogasystem.service.UserService;



public class UserRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("授权");
		String account = (String)arg0.getPrimaryPrincipal();
		User user = new User();
		user.setAcc(account);
		User currentUser = userService.login(user);
		Set<String> set = new HashSet<>();
		set.add(String.valueOf(currentUser.getRole()));
		System.out.println(set);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(set);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("认证");
		String account = (String)arg0.getPrincipal();
		User user = new User();
		user.setAcc(account);
		User currentUser = userService.login(user);
		if(currentUser==null){
			return null;
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(currentUser.getAcc(), currentUser.getPwd(),getName());
		return info;
	}

}
