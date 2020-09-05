package com.sealinkin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sealinkin.bdef.model.Bdef_DefWorkerModel;

/**
 * 验证是否登录
 * @author wangx
 *
 */
public class LoginFilter implements Filter{

	//定义请求过滤常量
	private static final String FILTERD_REQUEST = "@@session_context_filtered_request";
	//定义不需要进行过滤的资源
	private static final String[] INHERENT_ESCAPE_URIS = {"index.jsp","loginAction_login.action","logout.action","odata_ReceiveSeverAction_sendOrderData.action"};

	@Override
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//保证该过滤器在一次请求中只被调用一次
		if(request != null && request.getAttribute(FILTERD_REQUEST) != null){
			chain.doFilter(request, response);
		}else{
			//设置一个请求只被过滤一次
			request.setAttribute(FILTERD_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			Bdef_DefWorkerModel user = (Bdef_DefWorkerModel)httpRequest.getSession().getAttribute("loginUser");
			if(user == null && !isLoginUrl(httpRequest, httpRequest.getRequestURI())){
				if (httpRequest.getHeader("x-requested-with") != null
						&& httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					httpResponse.setHeader("sessionstatus","timeout");
				}				
				return ;
			}
			chain.doFilter(httpRequest, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		
	}
	
	/**
	 * 判断当前URL是否登录之后才能调用
	 * @param request
	 * @param requestUrl
	 * @return
	 */
	private boolean isLoginUrl(HttpServletRequest request , String requestUrl){
		if(request.getContextPath().equalsIgnoreCase(requestUrl) || (request.getContextPath()+"/").equalsIgnoreCase(requestUrl)){
			return true;
		}
		for (String  url : INHERENT_ESCAPE_URIS) {
			url = request.getContextPath()+"/"+url;
			if(requestUrl != null && requestUrl.equals(url)){
				return true;
			}
		}
		return false;
	}

}
