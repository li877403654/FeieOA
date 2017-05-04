<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发信箱</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    	$(function(){
    		//parent.location.reload();
    		//window.open(document.all.right.src,'right');
    	});
    </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 显示消息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
	<div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
		<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 信息内容 </DIV>  -->
	</div>
	
	<!-- 内容显示 -->
	<div class="ItemBlockBorder" style="margin: 15px;">
		<div class="ItemBlock">
			<table cellpadding="0" cellspacing="0" class="mainForm" style="font-size: 14px;"> 
				<tr height="25px"><td>级　别　：<c:if test='${email.place == 1}'>重要</c:if>
											   <c:if test='${email.place == 0}'>普通</c:if></td></tr>
				<tr height="25px"><td>发件人　：${email.user1.uname }</td></tr>
				<tr height="25px"><td>收件人　：${email.user.uname }</td></tr>
				<tr height="25px"><td>发送时间：<fmt:formatDate value="${email.godate}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
				<tr height="25px"><td>标　题　：${email.etitle }</td></tr>
				<tr><td style="border-top: 1px dotted blue; padding-top: 15px;">
						${email.etext }
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="InputDetailBar">
		<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
	</div>
</div>
 
</body>
</html>