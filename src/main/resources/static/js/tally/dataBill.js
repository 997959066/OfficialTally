	var date = new Date();
	var yearTally = date.getFullYear(); // 获取当前年份(2位)
$(document).ready(function(){
	//每月消费总数
	theMonthlyConsumption(yearTally);
	function theMonthlyConsumption(yearTally){
		var Params={year:yearTally}
			var newParams = setToken(Params);
	$.get(theMonthlyConsumptionUrl,newParams ,
				function(data,status){
		if(data.code<=0){
			return tankuang("300",data.msg); 
		}{
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
				}
		); 
}
		
		//当前全数据导出
		$("body").on("click",".yearAllExport",function(){
			tankuang("200","正在导出全年数据，请稍后。。。")
			var Params={}
			var newParams = setToken(Params);
			var g_userId = newParams.g_userId;//使用点的方式
			var g_token = newParams.g_token;//使用点的方式
			var g_time = newParams.g_time;//使用点的方式
			document.location.href = yearAllExport+"?g_userId="+g_userId+"&g_token="+g_token+"&g_time="+g_time;
		});
		
			$("#execlUpload").click(function () {
				
				tankuang("200","正在上传。。。")
				$("#imgWait").show();
				var formData = new FormData();
				formData.append("myfile", document.getElementById("file1").files[0]);  
				//获取token  url形式
				var s=setTokenWH();
				$.ajax({
					url: execlUploadUrl+s,
					type: "POST",
					data: formData,
					contentType: false,
					processData: false,
					success: function (data) {
						if(data.code == 1){
							tankuang("200","恭喜您，上传成功")
						}else{
							return tankuang("300",data.msg); 
						}
					},
					error: function () {
						alert("上传失败！");
						$("#imgWait").hide();
					}
				});
			});
			
			
			//时间段消费
			$("body").on("click",".theTotalAmount",function(){
				
				
				var startTime = $("#used").val();
				 var startTime = $("#startTime").val();
				 var endTime = $("#endTime").val();
					var Params={startTime:startTime,endTime:endTime}
					var newParams = setToken(Params);
				$.get(theTotalAmountUrl,newParams,
			 	function(data,status){
					if(data.code<=0){
						return tankuang("300",data.msg); 
					}{
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
						theMonthlyConsumption(yearTally);
//						$('.find').trigger("click");
					}
					}
				); 
			});
			
			//回车键事件 
			//绑定键盘按下事件  
			$(document).keypress(function(e) {  
			 // 回车键事件  
			     if(e.which == 13) {  
//			    	 alert("回车")
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
//										$('.find').trigger("click");
										tankuang("200",year+"年"+month+"月,收入来源为："+source+"  :"+money+"元,保存成功")
										theMonthlyConsumption(yearTally);
									}
//									$(".operateMessage").text(data);//前端显示json结果
								}
							);
					}
				}

			});
			
			
});