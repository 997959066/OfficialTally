	var date = new Date();

	var yearTally = date.getFullYear(); // 获取当前年份(2位)
	var pageCount=0;//26 总页数
	var pageNo=1; //1 当前页数
	var pageSize=10; //10  当前显示条数
	var rowCount=0;//252  总条数
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
		 display4 ();
		 pageNo=pageNo;
		    pageSize=pageSize;
		createDate = $("input:eq(0)").val();
		used = $("input:eq(1)").val();
		howMuch = $("input:eq(2)").val();
		$.get(listByPageUrl,
			{
			pageNo:pageNo,
			pageSize:pageSize,
			createDate:createDate,
			used:used,
			howMuch:howMuch,
			},
	 	function(data,status){
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
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].createDate.substring(0,10)+"'></td> ");
						tr.append("<td class='w80'><input class='upInput550' readonly='readonly' type='text' value='"+array[i].used+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+array[i].howMuch+"'></td> ");
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
		$("input:eq(0)").val(""),
		$("input:eq(1)").val(""),
		$("input:eq(2)").val("")
	}
	$("body").on("click",".save",function(){
		var createDate=$("input:eq(0)").val();
		var used=$("input:eq(1)").val();
		var howMuch=$("input:eq(2)").val();
		qingKuang();
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
				$.post(saveUrl,
						{
						createDate:createDate,
						used:used,
						howMuch:howMuch,
						},
						function(data,status){
							if(data.code == 1){
								$('.find').trigger("click");
							}
//							$(".operateMessage").text(data);//前端显示json结果
						}
					);
			}
		}

	});
	
	
	$(function () {
		$("#execlUpload").click(function () {
			tankuang("200","正在上传。。。")
			$("#imgWait").show();
			var formData = new FormData($("#shopId").val());
			formData.append("myfile", document.getElementById("file1").files[0]);  
			$.ajax({
				url: execlUploadUrl,
				type: "POST",
				data: formData,
				contentType: false,
				processData: false,
				success: function (data) {
					if(data.code == 1){
						tankuang("200","恭喜您，上传成功")
					}else{
						alert("上传失败啦 code"+data.code)
					}
				},
				error: function () {
					alert("上传失败！");
					$("#imgWait").hide();
				}
			});
		});
	});
	
	//隐藏表头
	function display4 (){
		  $("#findDivId").attr("style","display:block;");
		  $("#theMonthlyConsumptionDivId").attr("style","display:none;");
		  $("#theDailyConsumptionDivId").attr("style","display:none;");
		  $("#startToendTimeDivId").attr("style","display:none;");
	}
	
	//隐藏表头
	function display1 (){
		  $("#findDivId").attr("style","display:none;");
		  $("#theMonthlyConsumptionDivId").attr("style","display:none;");
		  $("#theDailyConsumptionDivId").attr("style","display:none;");
		  $("#startToendTimeDivId").attr("style","display:block;");
	}
	//时间段消费
	$("body").on("click",".theTotalAmount",function(){
		
//		隐藏
		display1();
		
		 var startTime = $("#startTime").val();
		 var endTime = $("#endTime").val();
		$.get(theTotalAmountUrl,
			{
			startTime:startTime,
			endTime:endTime,
			},
	 	function(data,status){
				$(".findMessage").text(data);
				//删除上回查到的
				$("tbody").children().remove();
				var howMuch=data.data.howMuch;
				var pin1="";
				var pin2="";
				if(startTime!=""){
					pin1="在";
					 pin2="  到   ";
				} 
				var sum="雨哥您"+pin1+startTime+pin2+endTime+" 共消费   "+howMuch+"元";
				//文本设置

				$(".startToendTime").text(sum);
				
//				$('.find').trigger("click");
			}
		); 
	});
	
	//隐藏表头
	function display2 (){
		  $("#findDivId").attr("style","display:none;");
		  $("#theMonthlyConsumptionDivId").attr("style","display:block;");
		  $("#theDailyConsumptionDivId").attr("style","display:none;");
		  $("#startToendTimeDivId").attr("style","display:none;");
	}
	//每月消费总数
	$("body").on("click",".theMonthlyConsumption",function(){
		
//		隐藏
		display2();
		
		$.get(theMonthlyConsumptionUrl,
				{year:yearTally,},
				function(data,status){
					//删除上回查到的
					$("tbody").children().remove();
					var datass=data.data;
//					var datass=JSON.stringify(datas);
					for(var i=0;i<datass.length;i++){
						
						var c = Number(i) + Number(1) 
						var tr;
						var month = datass[i].month; // 月
						var money = datass[i].money; // 月收入
						var spending = datass[i].spending; // 月消费
						var average = datass[i].average; // 月每天平均消费
						if(month ==undefined){
							month=0;
						}
						if(money ==undefined){
							money=0;
						}
						if(spending ==undefined){
							spending=0;
						}
						if(average ==undefined){
							average=0;
						}
						
						$("#theMonthlyConsumption").append("<tr></tr>");
						tr = $("#theMonthlyConsumption").find("tr:eq("+i+")");
						tr.append("<td class='w80'><input class='upInput'   type='text' value='"+ month +"'></td> ");
						tr.append("<td class='w160'><input class='upInput'   type='text' value='"+money +"'></td> ");
						tr.append("<td class='w160'><input class='upInput'   type='text' value='"+spending +"'></td> ");
						tr.append("<td class='w160'><input class='upInput'   type='text' value='"+average +"'></td> ");
					}
					
				}
		); 
	});

	//隐藏表头
	function display7 (){
		  $("#findDivId").attr("style","display:none;");
		  $("#theMonthlyConsumptionDivId").attr("style","display:none;");
		  $("#theDailyConsumptionDivId").attr("style","display:none;");
		  $("#startToendTimeDivId").attr("style","display:block;");
	}
 
	//隐藏表头
	function display3 (){
		  $("#findDivId").attr("style","display:none;");
		  $("#theMonthlyConsumptionDivId").attr("style","display:none;");
		  $("#theDailyConsumptionDivId").attr("style","display:block;");
		  $("#startToendTimeDivId").attr("style","display:none;");
	}
	//每日消费（）
	$("body").on("click",".theDailyConsumption",function(){
		
//		隐藏
		display3();
		
		$.get(summaryListUrl,
				{year:yearTally,},
				function(data,status){
					//删除上回查到的
					$("tbody").children().remove();
					var d=data.data;
					for(var i=0;i<d.length;i++){
						var c = Number(i) + Number(1) 
						var tr;
						var expense=d[i].expense;
						var countDate=d[i].countDate;
						$("#theDailyConsumption").append("<tr></tr>");
						tr = $("#theDailyConsumption").find("tr:eq("+i+")");
						tr.append("<td class='w80'><input class='upInput'   type='text' value='"+ countDate+"'></td> ");
						if(expense ==undefined){
						tr.append("<td class='w160'><input class='upInput250'   type='text' value='"+0+"'></td> ");
						} else{
							tr.append("<td class='w160'><input class='upInput250'   type='text' value='"+expense+"'></td> ");
						}
					}
				}
		); 
	});
	
	//导出明细消费
	$("body").on("click",".exportDetailsOfConsumption",function(){
                    document.location.href = exportDetailsOfConsumption;
	});
	
	//导出每日消费
	$("body").on("click",".exportDailyConsumption",function(){
		document.location.href = exportDailyConsumption;
	});
	//当前全数据导出
	$("body").on("click",".yearAllExport",function(){
		tankuang("200","正在导出全年数据，请稍后。。。")
		document.location.href = yearAllExport;
	});
	
	
	
	
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
			$.post(updateUrl,
				{
					id:$(this).attr("val"),
					createDate:tr.find("td:eq(1)").find("input").val(),
					used:tr.find("td:eq(2)").find("input").val(),
					howMuch:tr.find("td:eq(3)").find("input").val(),
				},
				function(data,status){
					$(".operateMessage").text(data);
					$('.find').trigger("click");
				}
			);
		}
	});
	
	$("body").on("click",".del",function(){
		var id = $("td").attr("id");
		$.post(deleteUrl,
			{
				id:id
			},
			function(data,status){
				$(".operateMessage").text(data);
				$('.find').trigger("click");
			}
		);
	});
	
	$('.find').trigger("click");
});