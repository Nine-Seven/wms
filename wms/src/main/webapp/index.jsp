<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="http://${header['host']}${pageContext.request.contextPath}/" />
    <title>跨境电商仓储系统</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	
	<link rel="stylesheet" type="text/css" href="system/extjs/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="system/extjs/resources/mycss/style.css" /> 
	
	<script type="text/javascript" src="system/extjs/resources/js/ext-all-debug.js"></script>
	<script type="text/javascript" src="system/extjs/locale/ext-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="system/extjs/locale/i18n.js" ></script>
	<script type="text/javascript" src="system/extjs/locale/i18n_prompt.js" ></script>
	<script type="text/javascript" src="system/extjs/locale/reportDefine.js" ></script>
	
	<script type="text/javascript" src="system/util/examples.js" ></script>
	
	<link rel="stylesheet" type="text/css" href="system/extjs/resources/js/content/ux/css/CheckHeader.css" />
	<link href="system/css/login.css" rel="stylesheet" type="text/css" />
	<link href="system/extjs/resources/mycss/example.css" rel="stylesheet" type="text/css" />
	
	
	<!--引入自定义CSS--> 
	<script type="text/javascript" src="system/app.js"></script> 
	<script type="text/javascript" src="system/util/addDate.js"></script>
	<script type="text/javascript" src="system/util/dateFormat.js"></script>
	<script type="text/javascript" src="system/util/common.js"></script>
	<!-- jquery -->
	<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script> -->
	<script type="text/javascript" src="system/jquery/jquery.min.js"></script>
	<!-- util -->
	<script type="text/javascript" src="system/util/enterSkip.js"></script>
	<script type="text/javascript">
	var workSpaceName = '${loginUser.workSpaceName}';
	var workSpaceNo = '${loginUser.workSpaceNo}';
	
		function submitlogin() {
			if(document.getElementById('username').value=="" &&
			document.getElementById('username').value.trim().length==0){
			        document.getElementById('errMsg').innerHTML="请输入用户名！";
			        document.getElementById('username').focus();
			}else if(document.getElementById('passwd').value=="" &&
			document.getElementById('passwd').value.trim().length==0){
			        document.getElementById('errMsg').innerHTML="请输入密码！";
			        document.getElementById('passwd').focus();
			}else{
                Ext.Ajax.request({
                	url:'loginAction_login.action',
                	params:{
                		workerNo:document.getElementById('username').value,
                		pwd:document.getElementById('passwd').value,
                		enterpriseNo:document.getElementById('enterpriseNo').value
                	},
                	success:function(response){
                		var data = Ext.decode(response.responseText);
                		if(!data.isSucc){
                			document.getElementById("errMsg").innerHTML=data.msg;
                		}else{
                			window.location.reload();
                		}
                	}
                });
            }
		}
		document.onkeydown=function(event){ 
	        e = event ? event :(window.event ? window.event : null); 
	        if(e.keyCode==13 && document.getElementById('username')!=null){ 
	          submitlogin();
	        } 
    	}; 

		function resetform() {
			document.getElementById('loginform').reset();
			document.getElementById('errMsg').innerHTML='';
		}
		
		function doLogout(){
		Ext.Msg.confirm('提示','确定注销系统?',function(button){
			if(button=="yes"){
				Ext.Ajax.request({
						url:'authAction_loginOut.action',
						success:function(response){
						 	window.onbeforeunload=null;
							window.location.reload();
						}
					});
			}
		});
		}
		
		$(function () 
		{
    		banBackSpace();
	   	}); 
	   	
	   	function userNameGetFocus()
		{
			if(document.getElementById('username')!=null)
			{
				document.getElementById('username').focus();
			}		    
		}
		window.onload=userNameGetFocus;
	</script>
	</head> 	
	<body> 
	
	<c:if test="${loginUser!=null}">
		<div style="font-size: 14px; font-weight: bold;  font-style: normal; padding: 5px;"></div>
		<input type="hidden" id="workerName" value="${loginUser.workerName}"/>
		<input type="hidden" id="workerNo" value="${loginUser.workerNo}"/>
	 	<input type="hidden" id="warehouseNo" value="${loginUser.warehouseNo}"/>
		<input type="hidden" id="warehouseName" value="${loginUser.warehouseName}"/>
		<input type="hidden" id="enterpriseNo" value="${loginUser.currEnterpriseNo}"/>	
		<input type="hidden" id="node" value=""/>
		<!-- <script>
			window.onbeforeunload=function(){
				return "您确定离开系统吗?";
			};
		</script> -->
	</c:if>
	<c:if test="${loginUser==null}">
	<form id="loginform" >
		<div class="login_main">
			<div style="float:left">
				<img src="system/images/login_left.gif" width="8" height="230"/>
			</div>
			<div class="login_main_content">
				<div class="login_main_logo">
<%--					<img src="system/images/login_bs.png" width="367" height="44" />--%>
					<span width="367" height="44" style="font-size:30px;margin:28%">跨境电商仓储系统</span>
				</div>
				<div class="login_main_field">
				    <div class="login_main_field_content" style="display:none"><!--hkl style="display:none"隐藏企业， style="display:"为显示-->
						企业号　　
						<input type="text" 
							name="userList.enterpriseNo" 
							id="enterpriseNo"
							value="" 
							style="width:145px;" 
							onkeydown="if(event.keyCode==13)return false;" />
					</div>
					<div class="login_main_field_content">
						用户名　　
						<input type="text" 
							name="userList.workerNo" 
							id="username"
							value="" 
							style="width:145px;" 
							onkeydown="if(event.keyCode==13)return false;" />
					</div>
					<div class="login_main_field_content">
						密　码　　
						<input 
							type="password" 
							name="userList.pwd"
							id="passwd" 
							style="width:145px;"
							onkeydown="if ((event.keyCode == 13))return false;" />&nbsp;&nbsp;
						<a href="javascript:submitlogin()">
							<img src="system/images/botton_b.gif" class="img_botton" />
						</a>&nbsp;
						<a href="javascript:resetform()">
							<img src="system/images/botton_a.gif" width="44" height="23" class="img_botton" />
						</a>
					</div>
					<div class="login_main_field_content" style="color:red" id="errMsg">
					        ${msgRes.msg}
					</div>
				</div>
				<div class="login_main_field_copyright"> </div>
			</div>
			<div style="float:left">
				<img src="system/images/login_right.gif" width="8" height="230" />
			</div>
			<div style="float:left"></div>
		</div>
		<!-- <div class="login_bottom">
			<img src="system/images/login_bottom.gif" width="636" height="99" />
		</div> --><!--  屏蔽下方海联天下字样 update by czh 2016.08.05-->
	</form>

	<div id="sbi_camera_button" class="sbi_search"
		style="left:0px;top:0px;position:absolute;width:29px;height:27px;border:none;margin:0px 0px 0px 0px;padding:0px 0px 0px 0px;z-index:2147483647;display:none;">
    </div>
    </c:if>
</body>  
</html> 
