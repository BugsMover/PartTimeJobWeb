<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>输入新的密码</title>
</head>
<style type="text/css">

#nav{ border:3px solid pink;
	text-align:center;
    padding:20px;
    width:400px;
    margin-top:50px;
    margin-right:auto;
    margin-bottom:50px;
    margin-left:auto;
  
}
</style>
<script type="text/javascript">
function check() {
	return checkchangepassword() && checkrechangepassword();
}

function checkchangepassword() {
	var password = document.getElementById('changepassword');
	var passwordspan = document.getElementById('changepasswordspan');
	var pattern = /^\w{6,16}$/; //密码格式正则表达式；密码六位到十六位
	if (password.value.length == 0) {
		passwordspan.innerHTML = "密码不能为空！"
		return false;
	} else if (!pattern.test(password.value)) {
		passwordspan.innerHTML = "密码不规范！"
		return false;
	} else {
		passwordspan.innerHTML = "OK"
		return true;
	}
}
function checkrechangepassword() {
	var repassword = document.getElementById('rechangepassword');
	var password = document.getElementById('changepassword');
	var repasswordspan = document.getElementById('rechangepasswordspan');
	if (repassword.value.length == 0) {
		repasswordspan.innerHTML = "不能为空！"
		return false;
	} else if (password.value != repassword.value) {
		repasswordspan.innerHTML = "上下密码不正确！"
		return false;
	} else {
		repasswordspan.innerHTML = "OK"
		return true;
	}
}
</script>
<body style="background: url(./img/01.jpg);">
<div id= "nav">
      用户：<%=request.getSession().getAttribute("findname") %>,请输入新的密码：<br>
 <form action="changeservlet" method="post" onsubmit="return check()">
         <span style="color:red"><%if(request.getAttribute("changeerror")==null){
		     %><%=""%><% 
	         }else{%><%=request.getAttribute("changeerror")%><%}%></span><br>
密&nbsp;&nbsp;&nbsp;码:<input type="password" id="changepassword" name="changepassword" maxlength="16" size="16" onBlur="checkchangepassword()" required placeholder="请输入至少6到16位密码">
      <span id="changepasswordspan"></span><br>
  确认密码:<input type="password" id="rechangepassword" name="rechangepassword" maxlength="16" size="16" onBlur="checkrechangepassword()" required placeholder="请再输入一遍密码">
      <span id="rechangepasswordspan"></span><br>
      <input type="submit" value="提交"><input type="reset" value="重置">
</form>
</div>

</body>
</html>