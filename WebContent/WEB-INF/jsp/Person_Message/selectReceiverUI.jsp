<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<base target="_self" />

	<script type="text/javascript">
		function selectMe( id, name ){
			var win = window.dialogArguments;
			// win.document.forms[0].receiverId.value = id;
			win.document.forms[0].receiverName.value = name;
			window.close();
		}

    </script>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 选择用户 
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
            <tr class="TableDetail1 template">
				<td><a href="javascript:selectMe('${user.id}', '${user.name}')">${user.name}</a>&nbsp;</td>
                <td>${user.department}&nbsp;</td>
                <td>${user.roles}&nbsp;</td>
            </tr>
        </tbody>
	</table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside" style="text-align: center">
            <a href="javascript:window.close();"><img src="${pageContext.request.contextPath}/style/blue/images/button/close.png"/></a>
        </div>
    </div>

</div>
</body>
</html>