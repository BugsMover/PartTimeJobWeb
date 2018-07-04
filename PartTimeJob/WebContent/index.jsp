<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="myTag" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兼职网</title>
<link rel="stylesheet" type="text/css" href="resources/layui/css/layui.css"><!-- 响应式布局 -->
<link rel="stylesheet" type="text/css" href="resources/css/common.css"><!-- 公共样式 -->
<link rel="stylesheet" type="text/css" href="resources/css/index.css"><!-- 主页样式 -->

</head>
<body>
用户：<%=request.getSession().getAttribute("name")%> &nbsp; <a href="logoutservlet">注销</a>
	<!-- 这是新闻系统的首页 -->
	<input type="hidden" name="pageCount" value="${pageCount}" >
	<input type="hidden" name="pageNo" value="${pageNo}">
	<!-- 主体部分 -->
	<div class="row logo" >
		<!-- 搜索框 -->
		<div class="search-part">
			<form method="post" class="search" action="coreServlet?bizCode=7" >
				<input type="hidden" name="type">
	    		<input class="text" type="text" name="keyWord" placeholder="搜索一下" value="${keyWord}">
	    		<input class="button" value="搜索" type="submit">
	  		</form>
		</div>
	</div>

	<!-- 兼职部分 -->
	<div class="list">
		<c:forEach var="news" items="${newsList}" varStatus="status">
			<div class="new">&nbsp;
				<div class="content row">
					<div class="imgdiv">
						<a href="coreServlet?bizCode=4&id=${news.id}"><img src="${news.img}"/></a>
					</div>
					<div class="text">
						<a href="coreServlet?bizCode=4&id=${news.id}"><h4>${news.title}</h4></a>
						<p class="p1">${myTag:formatDate(news.pudate)}&nbsp;-&nbsp;<span style="color: #20B2AA;">${news.type}</span>&nbsp;-&nbsp;${news.author}&nbsp;-&nbsp;阅${news.click}</p>
						<p class="p2">${news.content}</p>
					</div>
				</div>
			</div>	
		</c:forEach>
    </div>
	<!-- bootstrap所需js -->
	<script type="text/javascript" src="resources/layui/layui.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//滚动分页
			var flag = 0;
			$(window).scroll(function(){  
				console.log($(window).scrollTop() + "  " + $(document).height() + "  " +$(window).height());
				if ($(window).scrollTop() == $(document).height() - $(window).height()) {
					var pageCount = $("input[name='pageCount']").val();
					var pageNo = $("input[name='pageNo']").val();
					var keyWord = $("input[name='keyWord']").val();
					var type = $("input[name='type']").val();
					if(pageNo < pageCount){
						$.ajax({
							url:"coreServlet?bizCode=7",
							dataType:"json",
							data:{'keyword':keyWord, 'type':type, 'pageNo':parseInt(pageNo)+1},
							success:function(data){
								var html = "";
								$.each(data.newsList, function(i,val){      
									html += '<div class="new">&nbsp';
									html += '	<div class="content row">';
									html += '		<div class="imgdiv">';
									html += '			<a href="coreServlet?bizCode=4&id='+val.id+'"><img src="'+val.img+'"/></a>';
									html += '		</div>';
									html += '		<div class="text">';
									html += '			<a href="coreServlet?bizCode=4&id='+val.id+'"><h4>"'+val.title+'</h4></a>';
									html += '			<p class="p1">'+dateFormat(val.pudate)+'-<span style="color: #20B2AA;">'+val.type+'</span>-'+val.author+'-阅'+val.click+'</p>';
									html += '			<p class="p2">"'+val.content+'</p>';
									html += '		</div>';
									html += '	</div>';
									html += '</div>	';
								});
								
								$(".new").last().after(html);
								$("input[name='pageNo']").val(parseInt(pageNo) + 1);
							}
						});
					}else if(pageNo == pageCount && flag == 0){
						flag ++;
						var html = '<p style="text-align:center; color:#FF8C69; font-size:14px; margin-top:10px; margin-bottom:10px;">-没有更多了-</p>';
						$(".new").last().after(html);
					}
				}
			});  
		});
		
		function typeTurn(opt, type){
			if(opt == 1){
				$("input[name='type']").val(type);
			}else{
				$("input[name='type']").val("");
			}
		}
		
		function showlog(){
			$(".logdiv").animate({
			      height:'toggle'
		    });
		}
		
		function dateFormat(pdate){
			return pdate.substring(0,4) + "-" + pdate.substring(4,6) + "-" + pdate.substring(6,8);
		}
	</script>
</body>
</html>