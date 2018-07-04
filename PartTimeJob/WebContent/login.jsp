<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"><!-- 响应式布局 -->
</head>
<body>
	<div style="width:500px; height:300px; margin:0 auto; margin-top:200px;">
		<form class="layui-form" action="coreServlet?bizCode=1" method="post">
			<div class="layui-form-item">
			  <label class="layui-form-label" style="width:0px; padding:0;"></label>
			  <div class="layui-input-block" style="margin-left:0px;">
			    <input type="text" name="username" required  lay-verify="required" placeholder="昵称" autocomplete="off" class="layui-input">
			  </div>
			</div>
			<div class="layui-form-item">
			  <label class="layui-form-label" style="width:0px; padding:0;"></label>
			  <div class="layui-input-block" style="margin-left:0px;">
			    <input type="password" name="password" required lay-verify="required" placeholder="通关口令" autocomplete="off" class="layui-input">
			  </div>
			</div>
			<div class="layui-form-item">
			  <label class="layui-form-label" style="width:0px; padding:0;"></label>
			  	<div class="layui-input-block" style="margin-left:0px;">
			    <button class="layui-btn" lay-submit lay-filter="formDemo" style="width:100%">咻！~</button>
			  </div>
			</div>
		</form>
	</div>
<script type="text/javascript" src="resources/layui/layui.js"></script>
</body>
</html>