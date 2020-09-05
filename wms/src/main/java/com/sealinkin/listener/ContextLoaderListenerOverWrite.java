package com.sealinkin.listener;

import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sealinkin.bdef.service.IWms_DefErrorService;
import com.sealinkin.comm.service.IWmsJobConfigService;
import com.sealinkin.mina.server.RfServer;

public class ContextLoaderListenerOverWrite extends ContextLoaderListener
{

	/**
	 * Spring启动做初始化操作
	 */
	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		super.contextInitialized(event);
		Logger log = Logger.getLogger("");
		log.info("Spring启动做初始化");
		// 1.获取上下文
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		try
		{
			// 服务器启动时候去数据库加载
			//loadWmsDefError
			IWms_DefErrorService wmsDefErrorServiceImpl = (IWms_DefErrorService)applicationContext.getBean("wmsDefErrorServiceImpl");//bean的名称
			wmsDefErrorServiceImpl.loadWmsDefError();
			
			// 服务器启动时候去调用后台定时调用存储过程（类似JOB）
			IWmsJobConfigService wmsJobConfigServiceImpl = (IWmsJobConfigService)applicationContext.getBean("wmsJobConfigImpl");//bean的名称
			//wmsJobConfigServiceImpl.tscjobConfig();
			
			RfServer sf = new RfServer();
			sf.startServer();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		log.info("Spring初始化完成.");
	}

}
