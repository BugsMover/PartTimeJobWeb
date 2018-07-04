<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成功</title>
</head>
<body style="background:url(./img/01.jpg);">
<% response.setHeader("Refresh","3;URL=login2.jsp"); %>
 <div style="text-align: center;line-height: 450px">
 账户：<%=request.getAttribute("findname") %>,密码修改成功。3秒后自动跳转到登录界面,或者
<a href="login2.jsp">点击这里!</a>
</div>
</body>
</html>