<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>职位列表</title>
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" /> 职位管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align="CENTER" valign="MIDDLE" id="TableTitle">
					<td width="200px">职位名称</td>
					<td width="300px">担任员工姓名</td>
					<td width="300px">职位说明</td>
					<td>相关操作</td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData">
				<c:forEach items="${list }" var="post">
					<tr class="TableDetail1 template">
						<th>${post.pname}&nbsp;</th>
						<th>
						<c:forEach items="${post.users}" var="user">
							${user.uname }
						</c:forEach>
						
						</th>
						<th>${post.description}&nbsp;</th>
						<th>
							<c:forEach items="${MyPri}" var="mypri">
										<c:if test="${mypri.name=='职位删除'}">
											<a onClick="return delConfirm()" href="post_deletePost.action?pid=${post.pid }">删除</a>
										</c:if>
										<c:if test="${mypri.name=='职位修改'}">
											<a href="post_updatePostUI.action?pid=${post.pid }">修改</a>
										</c:if>
										<c:if test="${mypri.name=='设置权限'}">	
											 <a href="post_setPrivilegeUI.action?pid=${post.pid }">设置权限</a></th>
										</c:if>
							</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		 <div id="TableTail">
			<div id="TableTail_inside">	
				<c:forEach items="${MyPri}" var="mypri">
										<c:if test="${mypri.name=='职位添加'}">
				<a href="post_addPostUI.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
										</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
