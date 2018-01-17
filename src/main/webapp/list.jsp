<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript">
function delUser(uid){
	if(confirm("您是否确定要删除这条数据")){
		location.href="delete?uid="+uid;
	}else{
		return false;
	}
}

</script>
</head>
<body>
	
	<center>
	<h4>用户列表</h4>
	
	<table cellpadding="0" width="100%" cellspacing="0" border="1">
	<tr>
	<td  align="center">编号</td>
	<td align="center">登陆账号</td>
	<td align="center" >密码</td>
	<td align="center">姓名</td>
	<td align="center">性别</td>
	<td align="center">年龄</td>
	<td align="center">地址</td>
	<td align="center">操作</td>
	</tr>
	
	<c:forEach items="${list }" var="user">
	<tr>
	<td align="center">${user.user_id }</td>
	<td align="center">${user.account }</td>
	<td align="center">${user.password }</td>
	<td align="center">${user.user_name }</td>
	<td align="center">${user.user_sex }</td>
	<td align="center">${user.user_age }</td>
	<td align="center">${user.user_address }</td>
	<td align="center">
<shiro:hasPermission name="user:update">
<a href="user?uid=${user.user_id }">修改</a>
</shiro:hasPermission>&nbsp;&nbsp;
<shiro:hasPermission name="user:delete">
<a href="#" onclick="delUser(${user.user_id})">删除</a>
</shiro:hasPermission>
</td>
	</tr>
	
	</c:forEach>
	</table>
	</center>
	
</body>
</html>