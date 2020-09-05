package com.sealinkin.comm.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;


/**HTTP请求响应
 * 
 */
public class HttpService {
	
	
	/**设置响应模式*/
	public static void setRespsonse(){
		ServletActionContext.getResponse().setContentType("text/html");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
	}
	/**
	 * 根据角色信息 判断 用户类型
	 * 0  个人用户
	 * 1  企业用户
	 * @return
	 */
	/*public static Integer getUserType(){
		Integer userType = getUserFromSession().getF033n1001();
		Integer result = 0;
		switch (userType) {
		case 0:
			result = 0;
			break;
		case 1 :
			result = 1;
			break;
		case 2 :
			result = 1;
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}*/
	
	/**获取会话ID*/
	public static String getSessionId(){
		return ServletActionContext.getRequest().getSession().getId();
	}
	
	/**获取实际地址*/
	public static String getIpAddr() {   
	    String ip = ServletActionContext.getRequest().getHeader("x-forwarded-for");   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = ServletActionContext.getRequest().getHeader("Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = ServletActionContext.getRequest().getRemoteAddr();   
	    }   
	    return ip;   
	}
	
	public static String getUserSign(){
		//获得请求地址
		String address = HttpService.getIpAddr();
		//获得请求ID
		String sessionId = HttpService.getSessionId();
		String key = sessionId+"|"+address;
		
		return key;
	}
	
	/*public static AuthUserBo getUserFromSession(){
		AuthUserBo ub=null;
		if(getSessionValue("gybaoUser")!=null){
			ub=(AuthUserBo)getSessionValue("gybaoUser");
		}else{
			ub=new AuthUserBo();
		}
		return ub;
	}
	public static String getUseridFromSession(){
		AuthUserBo ub=getUserFromSession();
		if(ub!=null){
		return ub.getUserserial().toString();
		}
		else return null;
	}*/
	
	public static void pushToSession(String key,Object value){
		 ActionContext.getContext().getSession().put(key, value);
	}
	public static void removeToSession(String key){
		 ActionContext.getContext().getSession().remove(key);
	}
	public static Object getSessionValue(String key){
		return ActionContext.getContext().getSession().get(key);
	}
	
	public static String getParamter(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}
	
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	public static ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	public static PrintWriter  getWriter()throws Exception{
		return ServletActionContext.getResponse().getWriter();
	}
	public void sendMsg(String content) throws IOException{
		
	}
		
	public static void doApplication(){
		ActionContext context=ActionContext.getContext(); 
		Map<String,Object> applicationMap=context.getApplication();
		if(applicationMap.get("i")==null){
			applicationMap.put("i", "1111111111111111111111111111111");
		}
	}
	
	public static String ckBrowse(String s){
		try{
			if (ServletActionContext.getRequest().getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
				s = new String(s.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器
			}else if(ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
				s = URLEncoder.encode(s, "UTF-8");//IE浏览器
			}else{
				s = URLEncoder.encode(s, "UTF-8");//IE浏览器
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		return s;
	}
	
}
