	var date = new Date();

	var yearTally = date.getFullYear(); // 获取当前年份(2位)
	var pageCount=0;//26 总页数
	var pageNo=1; //1 当前页数
	var pageSize=10; //10  当前显示条数
	var rowCount=0;//252  总条数
	//获取token
	
$(document).ready(function(){
	//分页操作
	$("body").on("click",".pageX",function(){
		var pageCountId = $("#pageCountId").val();   //赋值新的pageNo
		 pageNo = $("#pageNextId").val();   //下一页赋值新的pageNo
		 if(parseInt(pageNo) == parseInt(1)){
			 pageNo =2;
		 }
		 if(parseInt(pageNo)>=parseInt(pageCountId)){
			 tankuang("300","没有下一页了"); 
		 } 
			 $('.find').trigger("click");
		 
	});
	$("body").on("click",".pageXX",function(){
		var pageCountId = $("#pageCountId").val();   //赋值新的pageNo
		pageNo = $("#pageShangId").val();   //上一页赋值新的pageNo
		if(parseInt(pageNo)==parseInt(0)){
			tankuang("300","没有上一页了"); 
		} 
		$('.find').trigger("click");
		
	});

	//查询
	$("body").on("click",".find",function(){
		pageNo=pageNo;
		pageSize=pageSize;
		var roleName=$("input:eq(0)").val();
		var Params={
				roleName:roleName,
				pageNo:pageNo,
		     pageSize:pageSize
		}
		var newParams = setToken(Params);
		$.get(roleListByPageUrl,newParams,
	 	function(data,status){
			if(data.code<=0){
				return tankuang("300",data.msg); 
			}else{
				//删除上回查到的
				$("tbody").children().remove();
				var pageCount=data.data.pageCount;//26
				var pageNo=data.data.pageNo; //1
				var pageSize=data.data.pageSize; //10
				var rowCount=data.data.rowCount;//252
				
				var array=data.data.list;
				//文本设置
					var tr;
					for(var i=0;i<array.length;i++){
						$("#tbodyId").append("<tr></tr>");
						tr = $("#tbodyId").find("tr:eq("+i+")");
						tr.append("<td class='w40' id='"+array[i].id+"'>"+(i+1)+"</td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].roleName+"'></td> ");
						(array[i].functionName!=null) ? a=array[i].functionName : a="无功能";
						tr.append("<td class='w500'><input class='upInputFunction' readonly='readonly' type='text'  value='"+""+a+"'></td> ");
						tr.append("<td class='w80'>&nbsp;<input class='update' type='button' id='updatess' val='"+array[i].id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class='del' type='button' val='"+array[i].id+"'></td>");
					}
					var pageNoS;
					if(parseInt(pageNo) >parseInt(0)){
						pageNoS=Number(pageNo) - Number(1); 
					}else{
						pageNoS=parseInt(0); 
					}
					var trs;
					trs = $("#tbodyIds").append("<tr></tr>");
					trs.append("<td class='w80'><button class='pageXX' id='pageShangId' value='"+pageNoS+"' >上一页</button></td>");
					var pageNoA;
					pageNoA=Number(pageNo) + Number(1);  //下一页 +1
					trs.append(" <input style='display:none;' id='pageCountId' value='"+pageCount+"' type='text'>");
					trs.append("<td class='w80'><button class='pageX' id='pageNextId' value='"+pageNoA+"' >下一页</button></td>");
			}
				
				///////
			}
		); 
		
	});
	
	
	
	//回车键事件 
	//绑定键盘按下事件  
	$(document).keypress(function(e) {  
	 // 回车键事件  
	     if(e.which == 13) {  
//	    	 alert("回车")
	    	   jQuery(".save").click();  
	     }  
	}); 
	
	function qingKuang(){
		$("input:eq(0)").val("") 
	}
	
	$("body").on("click",".save",function(){
		var roleName=$("input:eq(0)").val();
							qingKuang();//清空
	 
			if(roleName == null || roleName == undefined || roleName == ""){
				tankuang("200","角色名没有写啊，大哥")
				$('.find').trigger("click");
			}else{
				
				var Params={
						roleName:roleName
						}
				var newParams = setToken(Params);
				$.post(roleAppendUrl,newParams,
						function(data,status){
					if(data.code<=0){
						return tankuang("300",data.msg); 
					}else{
						$('.find').trigger("click");
					}  
					});
			}

	});
	
	 
	//修改
		
//					
	$("body").on("click",".update",function(){
		 sessionStorage.setItem("roleManagementUpdateId", $(this).attr("val"));
		$.fancybox({
	    	'href'  : 'roleEdit.html',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'roleManagement.html';
	        }
	    });			
	});
	
	//删除
	$("body").on("click",".del",function(){
		var id = $(this).attr("val");
//		alert(id)
		var Params={
				id:id
		}
		var newParams = setToken(Params);
		$.post(roleDeleteUrl,newParams
			,
			function(data,status){
			if(data.code<=0){
				return tankuang("300",data.msg); 
			}else{
				$(".operateMessage").text(data);
				$('.find').trigger("click");
			}
			}
		);
	});
	
	$('.find').trigger("click");
});