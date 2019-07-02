package com.woniuxy.yogasystem.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableWebSocket //启用websocket
@ComponentScan("com.woniuxy.yogasystem.handler")
@ComponentScan("com.woniuxy.yogasystem.controller")
@ComponentScan("com.woniuxy.yogasystem.service.imp")
@MapperScan("com.woniuxy.yogasystem.dao")
@ComponentScan("com.woniuxy.yogasystem.config")
@ServletComponentScan(value={"com.woniuxy.yogasystem.listener","com.woniuxy.yogasystem.filter"})
public class YogaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogaSystemApplication.class, args);
	}
	@Bean // 在容器中创建bean对象，在WebSocketUtil中需要用到的RemoteEndpoint对象
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
