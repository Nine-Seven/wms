package com.sealinkin.mina.listerner;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sealinkin.mina.server.RfServer;
@SuppressWarnings("unused")
public class SpringListerner extends ContextLoaderListener  {
	
	/**
	 * Spring启动做初始化操作
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		Logger log = Logger.getLogger(SpringListerner.class);
		log.info("Spring启动做初始化");
		//1.获取上下文
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		//2.开启RF服务
		try {
			RfServer rfServer = new RfServer();
			rfServer.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Spring初始化完成.");
	}
}
