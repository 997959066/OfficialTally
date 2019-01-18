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
		year = $("input:eq(0)").val();
		month = $("input:eq(1)").val();
		money = $("input:eq(2)").val();
		source = $("input:eq(3)").val();
		var Params={
				pageNo:pageNo,
				pageSize:pageSize,
				year:year,
				 month :month,
				 money :money,
				 source:source
		}
		var newParams = setToken(Params);
		$.get(incomeDetailsUrl,newParams,
	 	function(data,status){
			if(data.code<=0){
				return tankuang("300",data.msg); 
			}else{
				$(".findMessage").text(data);
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
						tr.append("<td class='w40'><input class='upInput' readonly='readonly' type='text' value='"+array[i].year+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].month+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].money+"'></td> ");
						tr.append("<td class='w80'><input class='upInput550' readonly='readonly' type='text'  value='"+""+array[i].source+"'></td> ");
						tr.append("<td class='w80'>&nbsp;<input class='update' type='button' val='"+array[i].id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class='del' type='button' val='"+array[i].id+"'></td>");
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
	
	function qing(){
		$("#incomeSource").val("");
		$("#incomeYear").val("");
		$("#incomeMonth").val("");
		$("#incomeMoney").val("");
	}
	$("body").on("click",".save",function(){
		var source=$("#incomeSource").val()
		var year=$("#incomeYear").val()
		var month=$("#incomeMonth").val()
		var money=$("#incomeMoney").val()
		qing();
		if(source == null || source == undefined || source == ""){
			return tankuang("300","对不起来源没有写"); 
		}
		if(year == null || year == undefined || year == ""){
			return tankuang("300","对不起年没有写"); 
		}
		if(month == null || month == undefined || month == ""){
			
			return tankuang("300","对不起月没有写"); 
		}else{
			if(isChineseChar(money)==true || isFullwidthChar == true){
				tankuang("200","对不起请输入正确的钱数")
			}else{
				var Params={
						source:source,
						year:year,
						month:month,
						money:money
						}
				var newParams = setToken(Params);
				
				$.post(saveIncomeUrl,newParams,
						function(data,status){
							if(data.code == 1){
//								$('.find').trigger("click");
								tankuang("200",year+"年"+month+"月,收入来源为："+source+"  :"+money+"元,保存成功")
								$('.find').trigger("click");
							}
//							$(".operateMessage").text(data);//前端显示json结果
						}
					);
			}
		}

	});
	
	 
	//修改
	$("body").on("click",".update",function(){
		var tr = $(this).parent().parent();
		
		if(typeof(tr.find("td:eq(1)").find("input").attr("readOnly"))!="undefined"){
			tr.children("td").each(function(){
				$(this).find(".upInput").removeAttr("readOnly","readOnly");
				$(this).find(".upInput").css("border","1px solid");
				
			});
		}else{
			tr.children("td").each(function(){
				$(this).find(".upInput").attr("readOnly","readOnly");
				$(this).find(".upInput").css("border","none");
				
			});
			
			var Params={
					id:$(this).attr("val"),
					year:tr.find("td:eq(1)").find("input").val(),
					month:tr.find("td:eq(2)").find("input").val(),
					money:tr.find("td:eq(3)").find("input").val(),
					source:tr.find("td:eq(4)").find("input").val()
				}
			var newParams = setToken(Params);
			$.post(updateIncomeUrl,newParams
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
		}
	});
	
	//删除
	$("body").on("click",".del",function(){

		var id =$(this).attr("val");
		var Params={
				id:id
		}
		var newParams = setToken(Params);
		$.post(deleteIncomeUrl,newParams
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