<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户信息</title>
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
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#sel").bind(
						"change",
						function() {

							//alert($(this).val());
// 							window.location.href = "dept_addUserUI.action?did="
// 									+ $(this).val();

						});
			});
</script>
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
				用户信息
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id=MainArea>
		<form action="dept_addUser.action" method="post">


			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					员工增加
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="100">所属部门</td>
							<td><select name="did" class="SelectStyle" id="sel">
									<option value="0" selected="selected">请选择部门</option>
									<c:forEach items="${dept }" var="dept">
										<option value="${dept.did }" <c:if test='${mydid == dept.did}'>selected="selected"</c:if>>┠${dept.dname
											}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td width="100">员工</td>
							<td><select name="userstrings" multiple="true" size="10"
								class="SelectStyle">
									<c:forEach items="${alluser }" var="user">
										<option <c:if test="${fn:contains(useruids,user.uid)}">selected="selected"</c:if> value="${user.uid }">┠${user.uname }</option>
									</c:forEach>
							</select> 按住Ctrl键可以多选或取消选择</td>
						</tr>
					</table>
				</div>
			</div>
			<%-- <c:if test="${fn:contains(poststrings,post.pid)}">selected="selected"</c:if> --%>
			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image"
					src="${pageContext.request.contextPath}/style/images/save.png" />
				<a href="javascript:history.go(-1);"><img
					src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</form>
	</div>



</body>
</html>
