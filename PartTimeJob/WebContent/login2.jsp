<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<style type="text/css">
#nav{
	line-heigh:26px;
    padding:20px;
    width:400px;
    margin-top:50px;
    margin-right:auto;
    margin-bottom:50px;
    margin-left:auto;
    border:3px solid pink;
	text-align:center;
}
</style>
<script type="text/javascript">
        var xmlHttp;
                if(window.XMLHttpRequest){
                	xmlHttp = new XMLHttpRequest();
                }else{
                	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }	
        
    
		function refImg(){
			document.getElementById("Kaptcha").src="<%=basePath%>Kaptcha.jpg?data="+Math.random();
		}
		
		function check(){	
			return checkyzmCallback() && checkname() && checkpass();
		}
		
		function checkyzm(yzminput){
			//createXMLHttp();
			xmlHttp.open("POST","YzmCheckServlet?kaptcha="+yzminput);
			xmlHttp.onreadystatechange = checkyzmCallback;
			xmlHttp.send(null);
			document.getElementById("yzmspan").innerHTML = "验证中..."
		}
		function checkyzmCallback(){
			if(xmlHttp.readyState ==4 ){
				if(xmlHttp.status ==200){
					var text = xmlHttp.responseText;
					//window.alert(text);
					if(text == "yes"){
						document.getElementById("yzmspan").innerHTML = "OK"
						return true;
					}else if(text == "no"){
						document.getElementById("yzmspan").innerHTML = "验证码错误！"
						return false;
					}
				}
			}
		}
		function checkname(){
			var name = document.getElementById('name');
			var namespan = document.getElementById('namespan')
			var pattern =/^[\u4e00-\u9fff\w]{3,16}$/;
			if(name.value.length==0){
				namespan.innerHTML = "用户名不能为空！"
				return false;
			}else if(!pattern.test(name.value)){
				namespan.innerHTML = "请输入3到16位的用户名！"
				return false;
			}else{
				namespan.innerHTML = "OK"
				return true;
			}
		}
		function checkpass(){
			var pass = document.getElementById('pass');
			var passspan = document.getElementById('passspan')
			var pattern = /^\w{6,16}$/;
			if(pass.value.length==0){
				passspan.innerHTML = "密码不能为空！"
				return false;
			}else if(!pattern.test(pass.value)){
				passspan.innerHTML = "请输入6到16位的密码！"
				return false;
			}else{
				passspan.innerHTML = "OK"
				return true;
			}
		}
		
</script>
<body style="background: url(./img/01.jpg);">
<div id="nav">
 <div style="text-align: center;">
     <form action="loginservlet" method="post" onsubmit="return check()">
     <table>
 <tr>
             用户名：<input type="text" id="name" name="name" maxlength="16" size="16" onBlur="checkname()" required
             value="<%if(request.getAttribute("name")==null){
		     %><%=""%><% 
	         }else{%><%=request.getAttribute("name")%><%}%>" autoComplete="Off" placeholder="请输入3-16位用户名">
             <span id="namespan"></span><br/>

<tr>    
               密&nbsp;码：<input type="password" id="pass" name="pass" size="17" maxlength="16" onblur = "checkpass()" required placeholder="请输入至少6到16位密码">
               <span id="passspan"></span><br>
         验证码：<input type = "text" id="yzminput" name="yzminput" onBlur="checkyzm(this.value)" size="4" maxlength="4" required autoComplete="Off" >
         <span id="yzmspan"></span><br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <img id="Kaptcha" src="<%=basePath%>Kaptcha.jpg" onclick="refImg()" ><a href="javascript:void(0)" onclick="refImg()">看不清，点击刷新</a><br>
         <span style="color:red"><%if(request.getAttribute("error")==null){%>
                             <%=""%><%}else{%>
                             <%=request.getAttribute("error")%><%}%></span><br>
</table>                                                       
         
          <input type="submit" value="提交">
          <input type="button" onclick="window.location.href='register.jsp'" value="注册">
          <input type="button" value="忘记/修改密码" onclick="window.location.href='findword.jsp'">
     </form>
 </div>
 </div>
</body>
</html>