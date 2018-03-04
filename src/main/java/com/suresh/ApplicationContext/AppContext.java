package com.suresh.ApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suresh.AppConfig;

public final class AppContext {
private static ApplicationContext context = null;
	
	private AppContext() {
		
	}
	
	public static ApplicationContext getInstance() {
		if(context == null)
			context = getApplicationInstance();
		return context;
			
	}
	
	private static ApplicationContext getApplicationInstance() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		return context;
	}


}
