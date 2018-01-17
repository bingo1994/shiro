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
<center>
<h4>用户修改</h4>

<form action="modify" method="post">
<table>
<tr>
<td>用户编号：</td>
<td><input type="text" name="user_id" value="${user.user_id }" readonly="readonly"></td>
</tr>

<tr>
<td>登陆账号：</td>
<td><input type="text" name="account" value="${user.account }" readonly="readonly"></td>
</tr>

<tr>
<td>登陆密码：</td>
<td><input type="text" name="password" value="${user.password }"></td>
</tr>

<tr>
<td>用户姓名：</td>
<td><input type="text" name="user_name" value="${user.user_name }"></td>
</tr>

<tr>
<td>用户性别：</td>
<td>
<c:choose>
<c:when test="${user.user_sex=='男' }">
<input type="radio" value="男" checked name="user_sex">男&nbsp;
<input type="radio" value="女" name="user_sex">女
</c:when>
<c:otherwise>
<input type="radio" value="男" name="user_sex">男&nbsp;
<input type="radio" value="女"  checked name="user_sex">女
</c:otherwise>
</c:choose>
</td>
</tr>

<tr>
<td>用户年龄：</td>
<td><input type="text" name="user_age" value="${user.user_age }"></td>
</tr>

<tr>
<td>用户地址：</td>
<td><input type="text" name="user_address" value="${user.user_address }"></td>
</tr>

<tr></tr>	
<tr></tr>

<tr align="center">
<td colspan="2"><input type="submit" value="保存"></td>
</tr>
</table>

</form>
</center>
</body>
</html>