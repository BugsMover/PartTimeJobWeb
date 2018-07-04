<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"><!-- 响应式布局 -->
</head>
<body>

	<input type="text" name="" value="<c:out value='respdesc'/>">
    <form method="post" action="imageUpload" enctype="multipart/form-data" id="uploadForm">  
       <input type="file" name="upload"/>  
       <button onclick="saveMsg()">提交</button> 
    </form>  
<script src="resources/layui/layui.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.1.0.min.js"></script>
<script>

function saveMsg() {
    var formData = new FormData($("#uploadForm")[0]);
    $.ajax({
        async : true,
        cache : flase,
        type : "post",
        data : formData,
        url : 'imageUpload',
        dataType : 'json',
        contentType: false, //必须
        processData: false, //必须
        success : function() {
            console.log('success');
        },
        error : function(arg1, arg2, arg3) {
            console.log(arg1 + "--" + arg2 + "--" + arg3);
        }
    });
}

</script> 
</body>
</html>