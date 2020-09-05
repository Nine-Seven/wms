<%@page import="com.sealinkin.util.ContextUtil"%>
<%@page import="com.mchange.v2.c3p0.ComboPooledDataSource"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.io.*,java.util.*,net.sf.jasperreports.engine.*"
	errorPage=""%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="com.sealinkin.bdef.model.Bdef_DefWorkerModel"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.sealinkin.util.SpringUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.jdbc.datasource.DriverManagerDataSource" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>打印预览</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%
		  request.setCharacterEncoding("gb2312");  
		  response.setContentType("text/html;charset=UTF-8");
          Connection conn = null; 
          Map params=null;          
          String reportFilePath=null;
		  File reportFile=null;
	      	      
		  try { 
		  	 String sheetid =(String)request.getParameter("sheetid");//打印单号
		  	 String reportType =(String)request.getParameter("reportType");//打印类型
		  	 Bdef_DefWorkerModel workerModel=(Bdef_DefWorkerModel)request.getSession().getAttribute("loginUser");
		  	 String wareHouseNo =workerModel.getWarehouseNo();//仓别
		  	 
		  	 //获取打印报表名称
		  	 reportFilePath = getReportFilePath(reportType);
		  	 
		  	 //打印参数
		  	 params = new HashMap(); 
			 params.clear();
			 params.put("sourceNo", sheetid);
			 params.put("wareHouseNo", wareHouseNo);	
			 		
			 //获取打印文件			 		 
			 ServletContext context = this.getServletConfig().getServletContext();
			 reportFile = new File(context.getRealPath(reportFilePath));
			 
			 conn=getConnection();
			 
			 JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());	
			 JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport,params,conn);
		  	 
		  	 if (jasperPrint != null){
				 byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),params,conn);  
				 response.setContentType("application/pdf"); 
				 response.setContentLength(bytes.length); 
				 ServletOutputStream ouputStream = response.getOutputStream(); 
				 ouputStream.write(bytes,0,bytes.length); 
				 ouputStream.flush(); 
				 ouputStream.close();	
				 
				 ouputStream=null;
				 response.flushBuffer();
				 out.clear();
				 out = pageContext.pushBody();   	    
			 }
			 conn.close();
		  	 
		      /*Map map = new HashMap();
		      map = request.getParameterMap();
		      for(Iterator iter = map.entrySet().iterator();iter.hasNext();){ 
		        Map.Entry element = (Map.Entry)iter.next(); 
		        Object strKey = element.getKey();
		        Object strObj = element.getValue();
			 }*/		      				
		 }catch(Exception e){
	        e.printStackTrace();
	     }finally{
			try{
				if (conn!=null)
					conn.close();
			}catch(Exception e){
			
		    }
		}	
 %>
 //获取打印报表
 <%! 	
 	String getReportFilePath(String reportType)
	{
		String reportFilePath="";
		if("盘点初盘单".equals(reportType))
		{
			reportFilePath="system/app/view/print/firstStock.jasper";
		}else if("拣货单(分播)".equals(reportType))
		{
			reportFilePath="system/app/view/print/jianhuodanfenbo.jasper";
		}else if("分播单".equals(reportType))
		{
			reportFilePath="system/app/view/print/fenbo.jasper";
		}else if("拣货单(摘果)".equals(reportType))
		{
			reportFilePath="system/app/view/print/jianhuodanzhaiguo.jasper";
		}else
		{
			reportFilePath="system/app/view/print/firstStock.jasper";
		}	
	   return reportFilePath;
	} %>
	
//连接
 <%! 	
 	Connection getConnection()
	{
		Connection conn=null;
		try{
			/* // 方法一：读取配置文件
			String resource = "/resources/jdbc.properties";
			Properties props = loadProperties(resource);
	
			String jdbcUrl = props.getProperty("jdbc.url.value");
			String driverClass = props.getProperty("driverClass");
			String username = props.getProperty("jdbc.user.value");
			String password = props.getProperty("jdbc.password.value"); 
			
			Class.forName(driverClass);
			conn =DriverManager.getConnection(jdbcUrl,username,password); */
						
			//方法二 
			WebApplicationContext acontext =WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			
			ComboPooledDataSource dataSource = (ComboPooledDataSource)acontext.getBean("dataSource");  //读取DataSource
			
			String jdbcUrl = new String(dataSource.getJdbcUrl());  //读取Url并赋值
			String username = new String(dataSource.getUser());  //读取Username并赋值
			String password = new String(dataSource.getPassword());  
			String driverClass = new String(dataSource.getDriverClass());
			Class.forName(driverClass);
			conn =DriverManager.getConnection(jdbcUrl,username,password);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return conn;  	     
	} %>	
	
	//连接
 <%! 	
    /**
	 * 加载配置文件
	 * 
	 * @param resource
	 * @return
	 */
	private Properties loadProperties(String resource) {
		InputStream inputStream = null;
		try {			
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			Properties props = new Properties();
			props.load(inputStream);
			return props;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	} %>
  </body>
</html>
