<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
</head>
<body>
	<h1>修改用户</h1>
	<form action="user_update.action" method="post">
		
		<input type="hidden" name="uid" value="${user.uid }">
		用户：<input type="text" name="uname" value="${user.uname }"><br>
		邮箱：<input type="text" name="email" value="${user.email }"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>