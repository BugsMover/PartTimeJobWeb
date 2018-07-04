<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="myTag" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pojonews.title}</title>
<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"><!-- 响应式布局 -->
<style type="text/css">
.content p {
	padding: 10px 0; 
}

.content img{
	max-width:762px;
	overflow:hidden;
}
</style>
</head>
<body style="padding-bottom:50px;">
	<a name="mao"></a>
	<div class="news" style="width:762px; margin:0 auto; margin-top:30px;">
		<h1>${news.title}</h1>
		<div style="font-size:14px; color:#666; line-height:12px; padding-top:30px; padding-bottom:30px;"><i class="layui-icon" style="font-size: 15px; color: #FF8C69;">&#xe60e;</i> ${myTag:formatDate(news.pudate)} &nbsp;&nbsp;${news.author} &nbsp;&nbsp;<span style="color: #20B2AA;">${news.type}</span></div>
		<div class="content" style="color:#666; width:762px; letter-spacing:2px; font-size:15px; line-height:30px; font-family:'微软雅黑'; ">
			${news.content}
		</div>
		<p style="color:#666; border-bottom:1px solid #999; margin-top:20px; padding-bottom:10px;">阅读 &nbsp;${news.click}</p>
		<div class="btnTurn" style="margin-top:30px;">
			<c:if test="${news.pre != 0}">
				<button style="font-size:10px; line-height:32px; height:32px; float:left;" class="layui-btn layui-btn-radius layui-btn-primary" onclick="pageTurn(${news.pre})">上一篇：${news.preTitle}</button>
			</c:if>
			<c:if test="${news.next != 0}">
				<button style="font-size:10px; line-height:32px; height:32px; float:right;" class="layui-btn layui-btn-radius layui-btn-primary" onclick="pageTurn(${news.next})">下一篇：${news.nextTitle}</button>
			</c:if>
		</div>
	</div>
	
	<div class="discuss" style="width:762px; margin:0 auto; margin-top:140px;">
		<p style="color:#666; font-size:14px;"><i class="layui-icon" style="font-size: 15px; color: #20B2AA;">&#xe642;</i>留言</p>
		<div class="layui-form-item layui-form-text" style="margin-top:10px;">
			<textarea id="discuss" name="discuss" style="display: none;"></textarea>
	    </div>
	    <button style="float:right;" id="dis-btn" class="layui-btn layui-btn-primary" onclick="discuss(0,${news.id},${news.id})">提交</button>
	    <div style="width:100%; margin-top:80px;">
    	    <c:forEach items="${news.list}" var="discuss" varStatus="status">
	    	    	<div style="padding-top:20px; padding-bottom:10px;">
		    	    	<div style="width:100%; height:30px;"><label style="color:#666; font-size:14px; float:left">${discuss.userId}</label> <span style="color:#666; font-size:14px; float:right; cursor:pointer;" id="agreespan${discuss.id}" onclick="agree(${discuss.id}, 1, 1)"><i id="agreei${discuss.id}" class="layui-icon" style="font-size: 15px; color: #20B2AA;">&#xe6c6;</i> <label>${discuss.agree}</label></span></div>
		    	    	<div style="width:100%;">${discuss.content}</div>
		    	    	<div style="width:100%; font-size:14px; color:#999; margin-top:5px;"><span>${myTag:formatMD(discuss.pudate)}</span><span style="float:right;"><a href="javascript:;"  onclick="reply(${discuss.id})" style="color:#999;">回复</a></span></div>
		    	    	<c:forEach items="${discuss.list}" var="discusssub" varStatus="statussub">
			    	    	<div style="margin-left:50px; margin-top:30px; border-left:5px solid #999; padding-left:10px;">
			    	    		<div style="width:100%; height:30px;">
			    	    			<label style="color:#666; font-size:14px; float:left">${discusssub.userId} @${discuss.userId}</label>
			    	    			<span style="color:#666; font-size:14px; float:right; cursor:pointer;" id="agreespansub${discusssub.id}" onclick="agree(${discusssub.id}, 1, 2)"><i id="agreeisub${discusssub.id}" class="layui-icon" style="font-size: 15px; color: #20B2AA;">&#xe6c6;</i> <label>${discusssub.agree}</label></span>
			    	    		</div>
		    	    			<div style="width:100%;">${discusssub.content}</div>
		    	    			<div style="width:100%; font-size:14px; color:#999; margin-top:5px;"><span>${myTag:formatMD(discusssub.pudate)}</span></div>
			    	    	</div>
		    	    	</c:forEach>
		    	</div>
		    	<div id="replydiv${discuss.id}" hidden>
		    		<textarea id="discusssub" name="discusssub${discuss.id}" class="layui-textarea"></textarea>
		    		<div style="width:100%; height:50px;">
		    			<button style="float:right; margin-top:15px;" id="dis-btn" class="layui-btn layui-btn-primary" onclick="discuss(1,${news.id},${discuss.id})">提交</button>
		    		</div>
		    </div>
    	    </c:forEach>
    	    <p style="text-align:center; color:#FF8C69; font-size:14px; margin-top:30px; margin-bottom:10px;">-&nbsp;&nbsp;<&nbsp;以上为筛选评论&nbsp;>&nbsp;&nbsp;-</p>
	    </div>
	</div>	
	
	<!-- 返回首页 -->
	<div id="maodiv" style="width:50px; height:100px; position:fixed; top:500px; right:10%; display:none;" >
		<div style="width:50px; height:50px;"><a href="#mao" ><i class="layui-icon layui-icon-face-smile" style="font-size: 50px; color: #1E9FFF;">&#xe604;</i></a></div>
		<div style="width:50px; height:50px; margin-top:20px; padding-left:7px;"><a href="coreServlet?bizCode=7" ><i class="layui-icon layui-icon-face-smile" style="font-size: 32px; color: #FF8C69;">&#xe633;</i></a></div>
	</div>

<script type="text/javascript" src="resources/layui/layui.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.1.0.min.js"></script>
<script>
	var layedit;
	var index;
	$(function(){
		//编辑器部分
		layui.use('layedit', function(){
			layedit = layui.layedit;
		  	//创建富文本
		  	index = layedit.build('discuss',{
		  		tool: ['left', 'center', 'right', '|', 'face'],
			  	height: 130,
			  	width: 720
		  	}); 
		});
	});
	
	
	//翻页
	function pageTurn(id){
		location.href = "coreServlet?bizCode=4&id="+ id;
	}
	
	//评论
	function discuss(type, newsId, id){
		$("#dis-btn").attr("disabled","true");
		var content = "";
		if(type == '0'){
			content = layedit.getContent(index);
		}else{
			content = $("textarea[name='discusssub" + id + "']").val();
		}
		if(content == ""){
			layer.msg("你想说的是什么呢？");
			$("#dis-btn").removeAttr("disabled");
			return;
		}
		$.ajax({
			dataType:'json',
			data	:{'content':content, 'type':type, 'id':id},
			url		:"coreServlet?bizCode=5",
			async 	: true,
			success	:function(data){
				location.href = "coreServlet?bizCode=4&id="+ newsId;
			}
		});
	}
	
	//点赞
	function agree(id, type, subType){
		if(subType=='1'){
			count = $("#agreespan"+id +" label").text();
		}else{
			count = $("#agreespansub"+id +" label").text();
		}
		$.ajax({
			url : 'coreServlet?bizCode=6',
			dataType : 'json',
			data : {'id':id, 'type':type, 'count':count},
			method : 'post',
			async : false,
			success : function(data){
				if(subType=='1'){
					$("#agreespan"+id +" label").text(data.count);
				}else{
					$("#agreespansub"+id +" label").text(data.count);
				}
				if(data.agree == '0'){
					if(subType=='1'){
						$("#agreei"+id).css("color","#20B2AA");
					}else{
						$("#agreeisub"+id).css("color","#20B2AA");
					}
				}else{
					if(subType=='1'){
						$("#agreei"+id).css("color","#FF8C69");
					}else{
						$("#agreeisub"+id).css("color","#FF8C69");
					}
				}
			}
		});
	}
	
	
	$(window).scroll(function(){  
		if ($(window).scrollTop() >  $(window).height() || $(window).scrollTop() == $(document).height() - $(window).height()) {
			$("#maodiv").css("display","block");
		}else if ($(window).scrollTop() < $(window).height()){
			$("#maodiv").css("display","none");
		}
	});  
	
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
	  var element = layui.element;
	});
	
	//弹出评论框
	function reply(id){
		$("#replydiv"+id).animate({
		      height:'toggle'
	    });
	}
</script> 
</body>
</html>