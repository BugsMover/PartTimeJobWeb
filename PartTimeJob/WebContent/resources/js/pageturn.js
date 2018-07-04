//加载翻页的玩意
function pageTurnDiv(){
	var pageCount = parseInt($("input[name='pageCount']").val());
	var currentPage = parseInt($("input[name='pageNo']").val());
	var divHtml = '<button class="layui-btn layui-btn-primary" onclick="pageTurn(-1)"><i class="layui-icon">&#xe603;</i></button>'
	var btn_choose = "layui-btn layui-btn-disabled";
	
	if(pageCount < 8){
		for(var i=1; i<=pageCount; i++){
			var btn_current = "layui-btn layui-btn-primary"
			if(i == currentPage + 1){
				btn_current = btn_choose;
			}
			divHtml += "<button class='" + btn_current + "' onclick='pageTurn("+i+")'>"+i+"</button>";
		}
	}else{
		for(var i=1; i<=5; i++){
			var btn_current = "layui-btn layui-btn-primary"
			if(i == currentPage + 1){
				btn_current = btn_choose;
			}
			divHtml += "<button class='" + btn_current + "' onclick='pageTurn("+i+")'>"+i+"</button>";
		}
		divHtml += "<button class='layui-btn layui-btn-primary' onclick=''>...</button>";
		var btn_current = "layui-btn layui-btn-primary"
		if(i == currentPage + 1){
			btn_current = btn_choose;
		}
		divHtml += "<button class='" + btn_current + "' onclick='pageTurn("+pageCount+")'>"+pageCount+"</button>";

	}
	divHtml += '<button class="layui-btn layui-btn-primary" onclick="pageTurn(0)"><i class="layui-icon">&#xe602;</i></button>'
	$("#pageTurnDiv").append(divHtml);
}

//翻页
function pageTurn(type){
	var currentPage = parseInt($("input[name='pageNo']").val());
	var pageCount = parseInt($("input[name='pageCount']").val());
	if(type == -1){
		//上
		currentPage -= 1;
		if(currentPage < 0){
			layer.msg('没有了呀~'); 
			return;
		}
	}else if(type == 0){
		//下
		currentPage += 1;
		if(currentPage >= pageCount){
			layer.msg('没有了呀~'); 
			return;
		}
	}else{
		//数据库的页数从0开始
		currentPage = type - 1;
	}
	location.href = "coreServlet?bizCode=1&pageNo="+ currentPage;
}