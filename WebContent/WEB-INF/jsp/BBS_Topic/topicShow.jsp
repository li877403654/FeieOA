<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>查看主题：新手发帖</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
<%-- 	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script> --%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
	<script language="javascript" src="${pageContext.request.contextPath}/script/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("reply.content");
			fck.Width = "90%";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${pageContext.request.contextPath}/script/fckeditor/";
			fck.ReplaceTextarea();
			
			$(".title").click(function(){
				var text = $(this).text();
				$("input[name=reply.title]").val("回复:"+text);
			});
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 查看主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<a href="forum_list.action">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="forum_forumShow.action?forum.id=${superpage.topic.forum.id}">${superpage.topic.title }</a>
			<font class="MenuPoint"> &gt;&gt; </font>
			帖子阅读
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
		
		<div class="ForumPageTableBorder dataContainer">
		
			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：新手发帖</b></td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
<%-- 						<a href="moveUI.html"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />移动到其他版块</a> --%>
						<c:forEach items="${MyPri}" var="mypri">
							<c:if test="${mypri.name=='设置精华帖'}"><a href="${mypri.url}&id=${superpage.id}&forum.id=${superpage.forum.id}" onClick="return confirm('要把本主题设为精华吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_hot.gif" />精华</a></c:if>
							<c:if test="${mypri.name=='置顶主题帖'}"><a href="${mypri.url}&id=${superpage.id}&forum.id=${superpage.forum.id}" onClick="return confirm('要把本主题设为置顶吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_top.gif" />置顶</a></c:if>
							<c:if test="${mypri.name=='设置普通帖'}"><a href="${mypri.url}&id=${superpage.id}&forum.id=${superpage.forum.id}" onClick="return confirm('要把本主题设为普通吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />普通</a></c:if>
						</c:forEach>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
				<div class="template">
			<c:forEach items="${superpage.replies}" var="reply" varStatus="i">
					<table border="0" cellpadding="0" cellspacing="1" width="100%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
								<!--作者头像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110" src="${reply.user.imgname}" 
										onerror="this.onerror=null; this.src='${reply.user.imgname}" />
								</div>
								<!--作者名称-->
								<div class="AuthorName">${reply.user.uname}</div>
							</td>
							<td align="center">
								<ul class="TopicFunc">
									<!--操作列表-->
									<li class="TopicFuncLi">
										<a class="detail" href="forum_replyUI.action?id=${reply.id }"><img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</a>
										<c:forEach items="${MyPri}" var="mypri">
											<c:if test="${mypri.name=='编辑帖子'}"><a class="detail" href="${mypri.url}?id=${reply.id}"><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</a></c:if>
											<c:if test="${mypri.name=='删除帖子'}"><a class="detail" href="${mypri.url }?id=${reply.id}&topic.id=${superpage.topic.id}" onClick="return confirm('确定要删除本帖吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</a></c:if>
										</c:forEach>
									</li>
									<!-- 文章表情与标题 -->
									<li class="TopicSubject">
										<c:if test="${reply.faceIcon!=0}"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face${reply.faceIcon}.gif"/></c:if>
										<span class="title">${reply.title}</span>
									</li>
								</ul>
							</td>
						</tr>
						<tr><!-- 文章内容 -->
							<td valign="top" align="center">
								<div class="Content">${reply.content}</div>
							</td>
						</tr>
						<tr><!--显示楼层等信息-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height:18px;"><font color=#C30000>[${(superpage.pageNo-1)*superpage.pageNum+i.count}楼]</font>
										<fmt:formatDate value="${reply.recoverytime }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
										<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
			</c:forEach>
			</div>
			
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
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
				<a onclick="gotoPageNum(${superpage.pageCount})" title="尾页" style="cursor: hand;">
					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>

				转到：
				<select> 
					<c:forEach begin="1" step="1" end="${superpage.pageCount }" var="i">
						<option value="${i}" onclick="gotoPageNum('${i}')">${i}</option> 
					</c:forEach>
				</select> 
			</div>
		</div>

		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
			
	<!--快速回复-->
	<div class="QuictReply">
	<form action="forum_topicShow.action" id="pageForm" method="post" >
		<input type="hidden" name="id" value="${superpage.id }">
		<input type="hidden" name="forum.id" value="${superpage.id }">
		<input type="hidden" name="reply.user.uid" value="${user.uid }">
		<input type="hidden" name="reply.topic.id" value="${superpage.id }">
		<div style="padding-left: 3px;">
			<table border="0" cellspacing="1" width="98%" cellpadding="5" class="TableStyle">
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>标题</b></td>
					<td class="no_color_bg">
						<input type="text" name="reply.title" class="InputStyle" style="width:90%"/>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td width="50px" class="Deep"><b>表情</b></td>
					<td class="no_color_bg"><div class="InputContent">
						<!-- 显示表情符号 -->
						<!--现在在设计单选框(radio)和复选框(checkbox)时都会给选择文字加上label增大选择范围，以提高用户体验。
							但在给单独的图片加label时无法成功。
							解决方法是：给图片img标签加上disabled即可。-->
						<table cellpadding="0" border="0" cellspacing="0">
							<tr>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="1" id="r_1"/>
									<label for="r_1"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face1.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="2" id="r_2"/>
									<label for="r_2"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face2.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="3" id="r_3"/>
									<label for="r_3"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face3.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="4" id="r_4"/>
									<label for="r_4"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face4.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="5" id="r_5"/>
									<label for="r_5"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face5.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="6" id="r_6"/>
									<label for="r_6"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face6.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="7" id="r_7"/>
									<label for="r_7"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face7.gif" disabled="true" align="absmiddle"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="8" id="r_8"/>
									<label for="r_8"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face8.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="9" id="r_9"/>
									<label for="r_9"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face9.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="10" id="r_10"/>
									<label for="r_10"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face10.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="11" id="r_11"/>
									<label for="r_11"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face11.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="12" id="r_12"/>
									<label for="r_12"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face12.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="13" id="r_13"/>
									<label for="r_13"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face13.gif" align="absmiddle" disabled="true"/></label>
								</td>
								<td width="50" style="border-bottom:0 solid #ffffff">
									<input type="radio" name="reply.faceIcon" value="14" id="r_14"/>
									<label for="r_14"><img width="19" height="19" src="${pageContext.request.contextPath}/style/images/face/face14.gif" align="absmiddle" disabled="true"/></label>
								</td>
							</tr>
						</table>	
					</td>
				</tr>
				<tr class="Tint" height="200">
					<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
					<td valign="top" class="no_color_bg">
						<textarea name="reply.content" style="width: 95%; height: 300px"></textarea>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td class="no_color_bg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
</div>

<div class="Description">
	说明：<br />
	1，主帖只在第一页显示。<br />
	2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br />
	3，删除主帖，就会删除所有的跟帖（回复）。<br />
</div>

</body>
</html>
