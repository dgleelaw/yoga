/*package com.woniuxy.yogasystem.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class ApplicationLis implements ServletContextListener {
	
	private 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听application");
		
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
	
	
	public void payoff(){
		final long timeInterval = 259200000;  
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    
                    try {  
                        Thread.sleep(timeInterval);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread = new Thread(runnable);  
        thread.start();  
	}
}
*/