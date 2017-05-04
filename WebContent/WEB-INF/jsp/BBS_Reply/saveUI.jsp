<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>帖子回复</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />

	<script language="javascript" src="${pageContext.request.contextPath}/script/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("reply.content");
			fck.Width = "99%";
			fck.Height = "100%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${pageContext.request.contextPath}/script/fckeditor/";
			//fck.Config['SkinPath'] = "${pageContext.request.contextPath}/scriipt/fckeditoreditor/skins/office2003/";
			//fck.Config['SkinPath'] = "http://bbs.itcast.cn:80/widgets/fckeditor/editor/skins/office2003/";
			fck.ReplaceTextarea();
		});
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 帖子回复
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
<form action="forum_topicShow.action" method="post" style="margin: 0; padding: 0;">
	<div id="PageHead"></div>
		<div class="ItemBlock_Title1">
			<div width=85% style="float:left">
				<font class="MenuPoint"> &gt; </font>
				<a href="forum_list.action">论坛</a>
				<font class="MenuPoint"> &gt; </font>
				<a href="forum_forumShow.action?id=${reply.topic.forum.id }">${reply.topic.title }</a>
				<font class="MenuPoint"> &gt;&gt; </font>
				帖子回复
			</div>
		</div>
		<div class="ItemBlockBorder">
			<table border="0" cellspacing="1" cellpadding="1" width="100%" id="InputArea">
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">帖子主题</div></td>
					<td class="InputAreaBg"><div class="InputContent">${reply.topic.title }</div></td>
				</tr>
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">标题</div></td>
					<td class="InputAreaBg"><div class="InputContent">
						<input type="text" name="reply.title" class="InputStyle" style="width:100%" value="${reply.title }"/></div>
					</td>
				</tr>
				<tr>
					<td class="InputAreaBg" height="30"><div class="InputTitle">表情</div></td>
					<td class="InputAreaBg"><div class="InputContent">
						<!-- 显示表情符号 -->
						<!--现在在设计单选框(radio)和复选框(checkbox)时都会给选择文字加上label增大选择范围，以提高用户体验。
							但在给单独的图片加label时无法成功。
							解决方法是：给图片img标签加上disabled即可。-->
						<table cellpadding="0" border="0" cellspacing="0">
							<tr>
								<c:forEach begin="1" step="1" end="14" var="i">
									<td width="50" style="border-bottom:0 solid #ffffff">
										<input type="radio" name="reply.faceIcon" value="${i }" id="r_${i }" <c:if test="${i==reply.faceIcon }">checked="checked"</c:if>/>
										<label for="r_${i }"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face${i }.gif" align="absmiddle" disabled="true"/></label>
									</td>
								</c:forEach>
							</tr>
						</table></div>
					</td>
				</tr>
				<tr height="240">
					<td class="InputAreaBg"><div class="InputTitle">内容${reply.topic.forum.id }</div></td>
					<td class="InputAreaBg"><div class="InputContent"><textarea name="reply.content">${reply.content}</textarea></div></td>
				</tr>
				<tr height="30">
					<td class="InputAreaBg" colspan="2" align="center">
						<input type="hidden" name="reply.topic.id" value= ${reply.topic.id }>
						<input type="hidden" name="id" value= ${reply.topic.id }>
						<input type="hidden" name="reply.topic.forum.id" value= ${reply.topic.forum.id }>
						<input type="hidden" name="reply.user.uid" value="${user.uid }">
						<input type="hidden" name="reply.id" value="${reply.id }">
						<c:if test="${reply.content!=null}"><input type="hidden" name="sql" value="update"></c:if>
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
						<a href="reply_updateReplyUI.action"><img src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"/></a>
					</td>
				</tr>
			</table>
		</div>
</form>
</div>

<div class="Description">
	说明：<br />
	
</div>

</body>
</html>