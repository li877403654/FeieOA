<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />

	<script language="javascript" src="${pageContext.request.contextPath}/script/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		$(function(){
			var fck = new FCKeditor("etext");
			fck.Width = "99%";
			fck.Height = "300px";
			fck.ToolbarSet = "bbs";
			fck.BasePath = "${pageContext.request.contextPath}/script/fckeditor/";
			fck.ReplaceTextarea();
		});

		function openSelectReceiverUI(){
			myShowModalDialog("email_selectReceiverUI.action", 500, 500);
		}
		function draftbox(){
				document.form1.action="email_drafybox.action?uid=${user.uid}";		
		}
    </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 发送消息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <form name="form1" action="email_save.action?uid=${user.uid }" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 信息内容 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<input type="hidden" name="goid" value=${user.uid }></input>
                    <tr>
                        <td width="65px">级别</td>
						<td colspan="2">
							<select name="place">
								<option value="0" <c:if test="email.place == 0">selected="selected"</c:if>>普通　</option>
								<option value="1" <c:if test="email.place == 1">selected="selected"</c:if>>重要　</option>
							</select>
						</td>
                    </tr>
					
					 <tr>
                        <td>标题</td>
                        <td><input type="text" name="etitle" class="InputStyle" style="width:472px;" value="${email.etitle }"/></td>
                    </tr>
                    <tr>
                        <td>内容</td>
                        <td><textarea name="etext" class="TextareaStyle" style="width: 550px; height: 300px;">${email.etext }</textarea></td>
                    </tr>
                    <tr> 
							<td width="100">选择用户</td>
							<td><select name="userstrings" multiple="true" size="10"
								class="SelectStyle">
								<c:forEach items="${ulist }" var="u">
									<h1>${u.uname }</h1>
									<c:if test="${u.uid != user.uid}">
<!-- 							选中		selected="selected" -->
										<option  value="${u.uid }" <c:if test="${u.uid == email.comeid}">selected="selected"</c:if>>┠${u.uname }</option></c:if>
								</c:forEach>
							</select> 按住Ctrl键可以多选或取消选择</td>
					</tr> 
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
			<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/send.png"/>
			<input type="IMAGE" onclick="draftbox()" src="${pageContext.request.contextPath}/style/blue/images/button/saveToDraftBox.png"/>
			<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

</body>
</html>