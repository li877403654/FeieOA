<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bottom</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="style/blue/statusbar.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		setInterval("online()",3000);
		window.onbeforeunload = onbeforeunload_handler;
			
	});
	function onbeforeunload_handler(){
		$.post("closeAll.action",null,function(data){
		});
		
	};
	function online(){
		$.post("getOnLineUser.action",null,function(data){
			$("#onlineUserNum").text(data);
		});
	}
</script>
</head>
<body style="margin: 0">

	<div id="StatusBar">
		<div id="Online">
			在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人
				<c:forEach items="${MyPri}" var="mypri">
					<c:if test="${mypri.name=='查看在线名单'}"> 
						<span class="OnlineView"><a target="right" href="onLineUserList.action">[查看在线名单]</a>
						</span>
					</c:if>
				</c:forEach>
				
		</div>
		
		<div id="DesktopText">
			<a href="javascript:void(0)"><img border="0"
				src="style/images/top/text.gif" />便笺</a> <span id=TryoutInfo></span> <span
				id="Version"> <a href="javascript:void(0)"> <img
					border="0" width="11" height="11" src="style/images/top/help.gif" />
					<img border="0" width="40" height="11"
					src="style/blue/images/top/version.gif" />
			</a>
			</span>
		</div>
	</div>

</body>
</html>
