<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
<center>
<h4>用户添加</h4>

<form action="save" method="post">
<table>

<tr>
<td>登陆账号：</td>
<td><input type="text" name="account"></td>
</tr>

<tr>
<td>登陆密码：</td>
<td><input type="text" name="password"></td>
</tr>

<tr>
<td>用户姓名：</td>
<td><input type="text" name="user_name"></td>
</tr>

<tr>
<td>用户性别：</td>
<td>
<input type="radio" value="男" checked name="user_sex">男&nbsp;
<input type="radio" value="女" name="user_sex">女</td>
</tr>

<tr>
<td>用户年龄：</td>
<td><input type="text" name="user_age"></td>
</tr>

<tr>
<td>用户地址：</td>
<td><input type="text" name="user_address"></td>
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