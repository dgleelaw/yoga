package com.woniuxy.yogasystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.woniuxy.yogasystem.filter.FileTypeInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FileTypeInterceptor()).addPathPatterns("/**");
	}
}
