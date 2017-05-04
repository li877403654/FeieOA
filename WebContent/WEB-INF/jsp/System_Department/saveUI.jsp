<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>部门设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/PageUtils.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/DemoData.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/DataShowManager.js"
	charset="utf-8"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
</head>
<body>

	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				部门信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<form action="dept_add.action" method="post">
			<div class="ItemBlock_Title1">
				<!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td>上级部门</td>
							<td>
								<select name="dpid" class="SelectStyle">
									<option value="" selected="selected">请选择上级部门部门</option>
									<c:forEach items="${deptList}" var="dept">
										<option value="${dept.did }" <c:if test="${dept.did==dpid }"> selected="selected" </c:if>>${dept.dname }</option>
									</c:forEach>
								</select>
							</td>
							</tr>
						<tr>
							<td>部门名称</td>
							<td><input type="text" name="dname" class="InputStyle" /> *</td>
						</tr>
						<!-- <tr>
							<td>职能说明</td>
							<td><textarea name="description" class="TextareaStyle"></textarea></td>
						</tr> -->

						<tr>
							<td width="100">部门管理人员</td>
							<td><select name="deptuid" class="SelectStyle">
									<option value="0" selected="selected">请选择部门主管</option>
									
									<c:forEach items="${uname }" var="user">
									<option value="${user.uid }">┠${user.uname }</option>
									
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>职能说明</td>
							<td><textarea name="dcomment" rows="3" cols="20"></textarea></td>
						</tr>
					</table>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</form>
	</div>

	<div class="Description">
		说明：<br /> 1，上级部门的列表是有层次结构的（树形）。<br />
		2，如果是修改：上级部门列表中不能显示当前修改的部门及其子孙部门。因为不能选择自已或自已的子部门作为上级部门。<br />
	</div>

</body>
</html>
