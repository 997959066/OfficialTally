	var date = new Date();

	var yearTally = date.getFullYear(); // 获取当前年份(2位)
	var pageCount=0;//26 总页数
	var pageNo=1; //1 当前页数
	var pageSize=10; //10  当前显示条数
	var rowCount=0;//252  总条数
	
	function select() {	
		// 因为一会要用str来拼接，所以要先声明一下
			var Params={}//获取token
			var newParams = setToken(Params);
			$.get(consumeTypeListUrl,newParams,
		 	function(data,status){
				var myArray=data.data;
				var sel = document.getElementById('a');
				var str = "";
				for(var i=0;i<myArray.length;i++){
					str += '<option value=' + myArray[i].id + '>' + myArray[i].typeName + '</option>';
				}
				sel.innerHTML = str;
				$("#a option").eq(0).attr("selected", true);
			})
		}
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
    // 把拼接好的str放到select标签里
			var type;
	////////////////////////////////////////////////////////////////////////////////////////////////
			function qingKuang(){
				$("input:eq(0)").val(""),
				$("input:eq(1)").val(""),
				$("input:eq(2)").val("")
			}
			$("body").on("click",".save",function(){
				var createDate=$("input:eq(0)").val();
				var used=$("input:eq(1)").val();
				var howMuch=$("input:eq(2)").val();
				qingKuang();
				var ty=$("#a").val();
				if(used == null || used == undefined || used == ""){
					return tankuang("300","对不起用途没有写"); 
				}
				if(howMuch == null || howMuch == undefined || howMuch == ""){
					
					return tankuang("300","对不起消费没有写"); 
				}else{
					if(isChineseChar(howMuch)==true || isFullwidthChar == true){
						tankuang("200","对不起请输入正确的钱数")
						$('.find').trigger("click");
					}else{
						var Params={
								createDate:createDate,
								used:used,
								type:ty,
								howMuch:howMuch
								}
						var newParams = setToken(Params);
						$.post(saveUrl,newParams,
								function(data,status){
							if(data.code<=0){
								return tankuang("300",data.msg); 
							}else{
								$('.find').trigger("click");
							}  
							});
					}
				}
				type=null;
			});
			
	//手动选框查询  save后  给type赋值
	var oSelect = $("#a");
	oSelect.on('change', function() {
	 // var checkText = $("#a").find("option:selected").text(); // 获取Select选择的Text文本
	type = $("#a").val();
	});
	//查询
	$("body").on("click",".find",function(){
		pageNo=pageNo;
		pageSize=pageSize;
		createDate = $("input:eq(0)").val();
		used = $("input:eq(1)").val();
		howMuch = $("input:eq(2)").val();
		var Params={
				pageNo:pageNo,
				pageSize:pageSize,
				createDate:createDate,
				used:used,
				type:type,
				howMuch:howMuch
		}
		var newParams = setToken(Params);
		$.get(listByPageUrl,newParams,
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
						var typeName =array[i].typeName;
						if( typeName== null){
							typeName="暂无";
						}
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].createDate.substring(0,10)+"'></td> ");
						tr.append("<td style='display:none'><input  style='display:none' value='"+array[i].createDate.substring(0,19)+"'></td> ");
						tr.append("<td class='w80'><input class='upInput550' readonly='readonly' type='text' value='"+array[i].used+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+array[i].howMuch+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+typeName+"'></td> ");
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
	
	select();//初始化查询下拉类型
	
	//绑定键盘按下事件  
	$(document).keypress(function(e) {  
	 // 回车键事件  
	     if(e.which == 13) {  
	    	   jQuery(".save").click();  
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
					createDate:tr.find("td:eq(2)").find("input").val(),
					used:tr.find("td:eq(3)").find("input").val(),
					howMuch:tr.find("td:eq(4)").find("input").val()
				}
			var newParams = setToken(Params);
			$.post(updateUrl,newParams
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

		var id = $("td").attr("id");
		var Params={
				id:id
		}
		var newParams = setToken(Params);
		$.post(deleteUrl,newParams
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