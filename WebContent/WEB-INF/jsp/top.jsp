<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Top</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="style/blue/top.css" />
	<script language="JavaScript" src="script/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
				a();
		});
		function a(){
			$('#news').fadeTo('slow', 0.1, function() {
				b();
			});
		}
		function b(){
			$('#news').fadeTo('slow', 1, function() {
				a();
			});
		}
		
	</script>
</head>

<body class="PageBody" style="margin: 0">
 
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">Feie OA</font> 
			<!--<img border="0" src="style/blue/images/logo.png" />-->
        </div>
		
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="style/images/top/user.gif" /> 您好：<b>${user.uname }</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">
            	<a href="user_user.action" target="right">
					<img border="0" width="13" height="14" src="style/images/top/user_setup.gif" /> 个人设置
				</a>
			</div>
			<div id="Head1Right_Time"></div>
		</div>
		
        <div id="Head1Right_SystemButton">
            <a target="_parent" href="home_logout.action">
				<img width="78" height="20" alt="退出系统" src="style/blue/images/top/logout.gif" />
			</a>
        </div>
		
        <div id="Head1Right_Button">
            <a target="desktop" href="index.jsp">
				<img width="65" height="20" alt="显示桌面" src="style/blue/images/top/desktop.gif" />
			</a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                <li><a target="desktop" href="javascript:void(0)">
						<img border="0" width="11" height="13" src="style/images/top/msg.gif" /> 消息
						<span id="msg"></span>
					</a>
				</li>
                <li class="Line"></li>
                <li><a target="right"  href="email_box.action?uid=${user.uid }&startnum=1&box=inbox">
						<img <c:if test="${newsnum != 0}"> id="news" </c:if> border="0" width="16" height="11" src="style/images/top/mail.gif" /> 邮件
						<span id="mail">
										<c:if test="${newsnum != 0}">(${newsnum })</c:if> </span>
					</a>
				</li>
                <li class="Line"></li>
				  <!-- 是否有待审批文档的提示1，数量 -->
<!--                 <li><a href="Flow_Formflow/myTaskList.html" target="desktop"> -->
<!--                 		<img border="0" width="12" height="14" src="style/images/top/wait.gif" />  -->
<!--                 		待办事项（<span id="wait" class="taskListSize">1</span>） -->
<!--                 	</a> -->
<!--                 </li> -->
				  
                <!-- 是否有待审批文档的提示2，提示审批 -->
<!--                 <li id="messageArea">您有 1 个待审批文档，请及时审批！★★★★★</li> -->
            </ul>
        </div>
   			<marquee style="WIDTH: 100%;" onMouseOver="this.stop()" onMouseOut="this.start()" 
				scrollamount=1 scrolldelay=30 direction=left>
				<b>嘉伟的第一个网页</b>
			</marquee>
		</div>
	</div>

</body>
</html>

