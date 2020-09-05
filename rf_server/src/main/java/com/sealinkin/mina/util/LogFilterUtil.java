package com.sealinkin.mina.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.sealinkin.process.impl.CaseProcessServiceImpl;
import com.sealinkin.util.ReadSysConfig;
import com.sealinkin.util.StringUtil;

/**
 * ????????????????
 * @author ??
 *
 */
public class LogFilterUtil {

	private static final Logger logger = Logger.getLogger(CaseProcessServiceImpl.class);
	private static final String filterIP =  ReadSysConfig.getBundByKey("filter-ip");
	private static final String second = ReadSysConfig.getBundByKey("second");
	
	/**
	 * ??????
	 * @param message
	 * @param code
	 * @param ip
	 */
	public static void outputLog(String type,Integer code, String ip){
		
		if(!StringUtil.isEmpty(filterIP))
		{
			String ipList[]=filterIP.split(",");
			for(int i=0; i<ipList.length;i++){
				if(ipList[i].equalsIgnoreCase(ip)){
					logger.info(type+" ******????IP??"+ip+"****?????????"+code.toString());
				}		
			}		
		}		
	}
	
	//????second??д?????
	public static void logWithOverTime(Date start,Date end,String ip,Integer code){	
		Double time=(end.getTime()-start.getTime())/1000.0;
		if(time>Double.parseDouble(second)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 			
			logger.info("????IP??"+ip+",???????"+ format.format(start)+",?????????"+code.toString()+",?????????"+time.toString()+"??");
		}
	}
}
