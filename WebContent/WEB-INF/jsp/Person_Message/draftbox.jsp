l<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script type="text/javascript">
    function gotoPageNum(num,uid){
    //	alert("email_box.action?uid="+uid+"&startnum="+num+"&box=waste");
    	location.href ="email_box.action?uid="+uid+"&startnum="+num+"&box=draftbox";
    }
    function goNum(uid){
    	var a = $(".inputStyle").val();
    	alert("email_box.action?uid="+uid+"&startnum="+a+"&box=waste");
    	location.href ="email_box.action?uid="+uid+"&startnum="+a+"&box=draftbox";
    }
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 草稿箱
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
				<td width="28px" style="color:red;"> ！</td>
                <td>标题</td>
				<td width="100px">收件人 </td>
				<td>编辑时间</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData">
        <c:forEach items="${email }" var="email">
        	 <tr  align=center class="TableDetail1 template">
            	<td style=color:red; width="50px">
            	<c:if test='${email.place == 1}'>重要</c:if>
            	<c:if test='${ email.place == 0}'>普通</c:if>&nbsp;</td>
                <td><a href="email_show.action?eid=${email.eid }">${email.etitle}</a>&nbsp;</td>
				<td>${email.user.uname}&nbsp;</td>
				<td><fmt:formatDate value="${email.godate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
                <td><a href="email_out1.action?eid=${email.eid}">打开</a>
					<a onClick="return delConfirm();" href="email_delete.action?eid=${email.eid }&box=draftbox">删除</a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
        </div>
    </div>
</div>

<!--分页信息-->
<div id=PageSelectorBar>
	<div id=PageSelectorMemo>
		页次：${num }/${sumnum}页 &nbsp;
		每页显示：30条 &nbsp;
		总记录数：${sumemail }条
	</div>
	<div id=PageSelectorSelectorArea>
		<!--
		<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage2.png"/>
		-->
		<a href="email_box.action?uid=${user.uid}&startnum=1&box=draftbox" title="首页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/></a>
		
		<c:forEach  begin="1" end="${sumnum }"  var="v" varStatus="vs">
        	<c:if test="${num != vs.count}"><span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPageNum(${vs.count},${email[0].comeid});">${vs.count}</span></c:if>
        	<c:if test="${num == vs.count}"><span class="PageSelectorNum PageSelectorSelected">${vs.count}</span></c:if>
		</c:forEach>
		
		<!--
		<IMG SRC="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage2.png"/>
		-->
		<a href="email_box.action?uid=${user.uid}&startnum=1&box=draftbox" title="尾页" style="cursor: hand;"><img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/></a>
		
		转到：
		<input onFocus="this.select();" maxlength="2" class="inputStyle" type="text" value="${num}" name="currPage" tabindex="0"/>
		<input type="submit" name="goBtn" value="Go" onClick="goNum(${user.uid})" class="MiddleButtonStyle" />
	</div>
</div>
</body>
</html>