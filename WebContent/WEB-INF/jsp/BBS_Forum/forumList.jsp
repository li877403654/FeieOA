<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>论坛</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
<%-- 	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script> --%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
	</script>
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 论坛 </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
	<center>
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<!--表头-->
				<tr align="center" valign="middle">
					<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
					<td width="200" class="ForumPageTableTitle">主题数</td>
					<td width="200" class="ForumPageTableTitle">文章数</td>
					<td width="270" class="ForumPageTableTitle">最后发表的主题</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr height="3"><td colspan="9"></td></tr>
				<c:forEach items="${forumList}" var="forum">
				<!--版面列表-->
				<tbody class="dataContainer">
						<tr height="78" align="center">
							<td width="3"></td>
							<td width="75" class="ForumPageTableDataLine">
								<img src="${pageContext.request.contextPath}/style/images/forumpage3.gif" />
							</td>
							<td class="ForumPageTableDataLine">
								<ul class="ForumPageTopicUl">
									<li class="ForumPageTopic"><a class="ForumPageTopic" href="forum_forumShow.action?id=${forum.id}&forum.id=${forum.id}">${forum.name}</a></li>
									<li class="ForumPageTopicMemo">${forum.description}</li>
								</ul>
							</td>
							<td class="ForumPageTableDataLine"><b>${forum.topicCount}</b></td>
							<td class="ForumPageTableDataLine"><b>${forum.replyCount}</b></td>
							<td class="ForumPageTableDataLine">
								<c:forEach items="${forum.topics}" var="topic">
										<ul class="ForumPageTopicUl">
											<li><font color="#444444">┌ 主题：</font> 
												<a class="ForumTitle" href="${pageContext.request.contextPath}/BBS_Topic/topicShow.html">${topic.title}</a>
											</li>
											<li><font color="#444444">├ 作者：</font> ${topic.user.uname}</li>
											<li><font color="#444444">└ 时间：</font><fmt:formatDate value="${topic.creationtime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
										</ul>
								</c:forEach>
							</td>
							<td width="3"></td>
						</tr>
				</tbody>
				</c:forEach>
				<!-- 版面列表结束 -->
				
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
	</center>
</div>
</body>
</html>