<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<title>【${superpage.forum.name}】中的主题列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
<%-- 	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script> --%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
		$(function(){
		});
		function gotoPageNum(pageNO){
			//动态添加一个隐藏输入框，传递页码
			var a = pageNO;
			$("#pageForm").append('<input type="hidden" value='+a+' name="pageNo"/>');
			$("#pageForm").submit();
		}
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 【${superpage.forum.name }】主题列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<form name="form1" id="pageForm" action="forum_forumShow.action?id=${superpage.forum.id}" method="post">
<input type="hidden" name="forum.id" value="${superpage.forum.id}">
<div id="MainArea">
	<div id="PageHead"></div>
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="forum_list.action">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			${superpage.forum.name }
			<span style="margin-left:30px;">
				<c:forEach items="${MyPri}" var="mypri">
					<c:if test="${mypri.name=='添加主题'}">
						<a href="${mypri.url }?forum.id=${superpage.forum.id}">
							<img align="absmiddle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/>
						</a>
					</c:if>
				</c:forEach>
			</span>
		</div>
		
		<div class="ForumPageTableBorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">主题</td>
					<td width="130" class="ForumPageTableTitle">作者</td>
					<td width="100" class="ForumPageTableTitle">回复数</td>
					<td width="130" class="ForumPageTableTitle">最后回复</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<c:forEach items="${superpage.topics}" var="topic">
					<tbody class="dataContainer">
						<tr height="35" id="d0" class="template">
							<td></td>
							<td class="ForumTopicPageDataLine" align="center"><img src="${pageContext.request.contextPath}/style/images/topicType_${topic.type}.gif" /></td>
							<td class="Topic"><a class="Default" href="forum_topicShow.action?id=${topic.id }&forum.id=${superpage.forum.id}">${topic.title}</a></td>
							<td class="ForumTopicPageDataLine">
								<ul class="ForumPageTopicUl">
									<li class="Author">${topic.user.uname }</li>
									<li class="CreateTime"><fmt:formatDate value="${topic.creationtime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
								</ul>
							</td>
							<td class="ForumTopicPageDataLine Reply" align="center"><b>${topic.replyCount}</b></td>
							<td class="ForumTopicPageDataLine">
								<ul class="ForumPageTopicUl">
									<li class="Author">${topic.reply.user.uname }</li>
									<li class="CreateTime"><fmt:formatDate value="${topic.reply.recoverytime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
								</ul>
							</td>
							<td></td>
						</tr>
						</tbody>
				</c:forEach>
					<!--主题列表结束-->	
						
					<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td><select name="recommend">
									<option value="0" <c:if test="${superpage.recommend==0}">selected</c:if>>全部主题</option>
									<option value="1" <c:if test="${superpage.recommend==1}">selected</c:if>>全部精华贴</option>
									<!--
									<option value="2">当天的主题</option>
									<option value="3">本周的主题</option>
									<option value="4">本月的主题</option>
									-->
								</select>
								<select name="rankcount">
									<option value="0" <c:if test="${superpage.rankcount==0}">selected</c:if>>默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
									<option value="1" <c:if test="${superpage.rankcount==1}">selected</c:if>>按最后更新时间排序</option>
									<option value="2" <c:if test="${superpage.rankcount==2}">selected</c:if>>按主题发表时间排序</option>
									<option value="3" <c:if test="${superpage.rankcount==3}">selected</c:if>>按回复数量排序</option>
								</select>
								<select name="elevator">
									<option value="0" <c:if test="${superpage.elevator==0}">selected</c:if>>降序</option>
									<option value="1" <c:if test="${superpage.elevator==1}">selected</c:if>>升序</option>
								</select>
								<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	</center>
</div>

<!--分页信息-->
<div id=PageSelectorBar>
			<div id=PageSelectorMemo>
				页次：${superpage.pageNo }/${superpage.pageCount }页 &nbsp;
				每页显示：${superpage.pageNum }条 &nbsp;
				总记录数：${superpage.pageSum }条
			</div>
			<div id=PageSelectorSelectorArea>
				<!--
				<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
				-->
				<a onclick="gotoPageNum(1)" title="首页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
				
				<c:forEach begin="1" step="1" end="${superpage.pageCount }" var="i">
				<c:if test="${i==superpage.pageNo }">
					<span class="PageSelectorNum PageSelectorSelected">${i}</span>
				</c:if>
				<c:if test="${i!=superpage.pageNo }">
					<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum('${i}');">${i}</span>
				</c:if>
				</c:forEach>
				<a onclick="gotoPageNum('${superpage.pageCount}')" title="尾页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>

				转到：
				<select> 
					<c:forEach begin="1" step="1" end="${superpage.pageCount }" var="i">
						<option value="${i}" onclick="('${i}')">${i}</option> 
					</c:forEach>
				</select> 
			</div>
		</div>
</form>
<div class="Description">
	说明：<br />
	1，主题默认按最后更新的时间降序排列。最后更新时间是指主题最后回复的时间，如果没有回复，就是主题发表的时间。<br />
	2，帖子有普通、置顶、精华之分。置顶贴始终显示在最上面，精华贴用不同的图标标示。<br />
</div>

</body>
</html>
