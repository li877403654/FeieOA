<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>版块列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
<%-- 	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script> --%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="250px">版块名称</td>
                <td width="300px">版块说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        	<c:forEach items="${forums}" var="forum">
				<tr class="TableDetail1">
					<td>${forum.name}&nbsp;</td>
					<td>${forum.description}&nbsp;</td>
					
					<td>
					<c:forEach items="${MyPri}" var="mypri">
					<c:if test="${mypri.name=='删除版块'}">
						<a onClick="return delConfirm()" href="${mypri.url }?id=${forum.id }">删除</a>
					</c:if>
					<c:if test="${mypri.name=='修改版块'}">
						<a href="${mypri.url }?id=${forum.id}">修改</a>
					</c:if>
					<c:if test="${mypri.name=='上移版块'}">
						<a href="${mypri.url }?id=${forum.id}">上移</a>
					</c:if>
					<c:if test="${mypri.name=='下移版块'}">
						<a href="${mypri.url }?id=${forum.id}">下移</a>
					</c:if>
					</c:forEach>
					</td>
				</tr>
        	</c:forEach>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
        <c:forEach items="${MyPri}" var="mypri">
					<c:if test="${mypri.name=='添加版块'}">
            			<a href="${mypri.url }"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
            		</c:if>
         </c:forEach>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，显示的列表按其sortOrder值升序排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>

</body>
</html>
