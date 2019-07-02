package com.woniuxy.yogasystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration//文件上传的配置
public class SpringMvcConfig {
	public CommonsMultipartResolver createResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxInMemorySize(4096);
		commonsMultipartResolver.setMaxUploadSize(1024000);
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setResolveLazily(true);
		return commonsMultipartResolver;

	}
}
