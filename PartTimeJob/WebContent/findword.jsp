<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回/修改密码</title>
</head>
<style type="text/css">
body{
   line-heigh:15px;
   
}
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
     var xmlHttp;
     function createXMLHttp() {
		if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}else{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
     
     function check(){
    	 return checkfindnameCallback() && checkfindphonenumber() && checkfindemail() && checkfindyzmCallback();
     }
     
     function checkfindname(findname) {
    	 var findnamespan = document.getElementById('findnamespan');
 		createXMLHttp();
 		xmlHttp.open("POST", "CheckServlet?username=" + findname);
 		xmlHttp.onreadystatechange = checkfindnameCallback;
 		xmlHttp.send(null);
 		findnamespan.innerHTML = "正在验证...";
 	}

 	function checkfindnameCallback() {
 		if (xmlHttp.readyState == 4) {
 			if (xmlHttp.status == 200) {
 				var text = xmlHttp.responseText;
 				if (text == "true") {
 					//用户已经存在
 					document.getElementById("findnamespan").innerHTML = "OK!"
 					return true;
 				} else if(text == "false") {
 					document.getElementById("findnamespan").innerHTML = "用户不存在!"
 					return false;
 				} else if(text == "length") {
 					document.getElementById("findnamespan").innerHTML = "用户名不规范，请输入3-16位用户名！"
 					return false;
 				}
 			}
 		}
 	}
 	
 	function checkfindphonenumber() {
		var phonenumber = document.getElementById('findphonenumber');
		var phonenumberspan = document.getElementById('findphonenumberspan');
		var pattern = /^1[34578]\d{9}$/; //验证手机号码正则表达式
		if (!pattern.test(phonenumber.value)) {
			phonenumberspan.innerHTML = "手机号码不规范"
			return false;
		} else {
			phonenumberspan.innerHTML = "OK"
			return true;
		}
	}
 	
	function checkfindemail() {
		var email = document.getElementById('findemail');
		var emailspan = document.getElementById('findemailspan');
		var pattern = /^[1-9a-zA-Z_]\w*@[a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/; //验证邮箱正则表达式
		if (!pattern.test(email.value)) {
			emailspan.innerHTML = "E-mail不规范"
			return false;
		} else {
			emailspan.innerHTML = "OK"
			return true;
		}
	}
 	
	function refimg() {
		//验证码切换
		document.getElementById("findyzmimg").src = "<%=basePath%>Kaptcha.jpg?data="+Math.random();
    	}
	
	 function checkfindyzm(findyzm){
   	  createXMLHttp();
   	  xmlHttp.open("POST","YzmCheckServlet?kaptcha="+findyzm);
		  xmlHttp.onreadystatechange = checkfindyzmCallback;
		  xmlHttp.send(null);
		  document.getElementById("findyzmspan").innerHTML = "验证中..."
     }
     function checkfindyzmCallback(){
		  if(xmlHttp.readyState == 4){
			  if(xmlHttp.status ==200){
				  var text = xmlHttp.responseText;
				 // window.alert(text); 
				  if(text =="yes"){//验证码相同
					 document.getElementById("findyzmspan").innerHTML = "OK"
					 return true ;
				  }else if(text =="no"){ 
					  document.getElementById("findyzmspan").innerHTML = "验证码错误！"
					  return false ;
				  }
			    }
			  }
     }
	
</script>
<body style="background:url(./img/01.jpg);">
<div id="nav">
     请输入要找回/修改的用户名，邮箱，电话<br>
  <form action="findservlet"  method="post" onsubmit="return check()">
  <span style="color:red"><%if(request.getAttribute("finderror")==null){
		     %><%=""%><% 
	         }else{%><%=request.getAttribute("finderror")%><%}%></span><br>
      用户名:<input type="text" id="findname" name="findname" onblur="checkfindname(this.value)" maxlength="16" required autoComplete="Off" placeholder="请输入3-16位用户名">
        <span id="findnamespan"></span><br>
      邮箱：<input type="email" id="findemail" name="findemail" onblur="checkfindemail()" required autoComplete="Off" placeholder="请输入正确的邮箱地址">
      <span id="findemailspan"></span><br>
      电话：<input type="number" id="findphonenumber" name="findphonenumber" onblur="checkfindphonenumber()" maxlength="11" required autoComplete="Off" placeholder="请输入11位手机号码">
      <span id="findphonenumberspan"></span><br>
   验证码：<input type="text" id="findyzm" name="findyzm" size="4" maxlength="4" onblur="checkfindyzm(this.value)" required autoComplete="Off" >
       <span id="findyzmspan"></span><br>
       <img id="findyzmimg" src="<%=basePath%>Kaptcha.jpg" onclick="refimg()">
       <a href="javascript:void(0)" onclick="refimg()">看不清，点击刷新！</a><br>
      <input type="submit" value="提交"><input type="reset" value="重置">
      <input type="button" value="返回" onclick="javascript:history.back(1)">
  
  </form>
  </div>
</body>
</html>