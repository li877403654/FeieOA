<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js"
	charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js"
	charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js"
	charset="utf-8"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
<script type="text/javascript">
	
</script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 用户管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="100">登录名</td>
					<td width="100">姓名</td>
					<td width="100">所属部门</td>
					<td width="200">岗位</td>
					<td>描述</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			
				<c:forEach items="${list }" var="user">
					<tr class="TableDetail1 template">
						<th>${user.loginname}&nbsp;</th>
						<th>${user.uname}&nbsp;</th>
						<th>${user.dept.dname}&nbsp;</th>
						<th>
						<c:forEach items="${user.posts }" var="post">
							${post.pname}&nbsp;
						</c:forEach>	
							
						</th>
						<th>${user.description}&nbsp;</th>
						
						<th>
								<c:forEach items="${MyPri}" var="mypri">
										<c:if test="${mypri.name=='用户删除'}">
											<a onClick="return delConfirm()" href="user_userdelete.action?uid=${user.uid }">删除</a>
										</c:if>
										<c:if test="${mypri.name=='用户修改'}">
											<a href="user_updateUser.action?uid=${user.uid }">修改</a> 
										</c:if>
										<c:if test="${mypri.name=='用户初始化密码'}">
											<a href="user_initializePassword.action?uid=${user.uid }" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</a>
										</c:if>
								</c:forEach>
						</th>
					</tr>
				</c:forEach>
			
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
			<c:forEach items="${MyPri}" var="mypri">
										<c:if test="${mypri.name=='用户添加'}">
											<a href="user_addUser.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
										</c:if>
			</c:forEach>
			</div>
		</div>
	</div>

</body>
</html>
