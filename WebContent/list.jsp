<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function () { 
  $("#sel").bind("change",function(){ 
    if($(this).val()==0){
      return; 
    } 
    else{
      var value=$("#thediv").text($(this).val());
     // alert($(this).val());
      window.location.href = "user_list.action?did="+$(this).val();
    } 
  }); 
});
</script> 
</head>
<body>

	<h1>user集合</h1>
	<select id="sel" name="uname"  value="${unamevalue}">
		<option value="all">-请选择-</option>
		<option value="z" <c:if test="${'z' eq unamevalue}">selected</c:if>>人事部</option>
		<option value="2">研发部</option>
		<option value="3">财务部</option>
		<option value="4">。。部</option>
	</select>
	<table border="1">
		<tr>
			<td>用户名</td>
			<td>邮箱</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list }" var="user">

			<tr>
				<td>${user.uname }</td>
				<td>${user.email }</td>
				<td><a onClick="return delConfirm()"
					href="user_delete.action?uid=${user.uid }">删除</a> <a
					href="user_updateUI.action?uid=${user.uid }">修改</a></td>
			</tr>




		</c:forEach>
	</table>
	<h1>增加用户</h1>
	<form action="user_save.action" method="post">

		用户：<input type="text" name="uname"><br> 邮箱：<input
			type="text" name="email"><br> <input type="submit"
			value="提交">
	</form>
	<a href="email_come.action?comeid=3">aaaaa</a>
</body>
</html>