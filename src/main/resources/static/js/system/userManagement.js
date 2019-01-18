	var date = new Date();

	var yearTally = date.getFullYear(); // 获取当前年份(2位)
	var pageCount=0;//26 总页数
	var pageNo=1; //1 当前页数
	var pageSize=10; //10  当前显示条数
	var rowCount=0;//252  总条数
	//获取token
	
$(document).ready(function(){
	
	var roleId;
	/* 用jQuery实现 */
	var Params={}
	var newParams = setToken(Params);
	$.get(roleListUrl,newParams,
		 	function(data,status){
				if(data.code<=0){
					return tankuang("300",data.msg); 
				}else{
					var data=data.data;//26
					// 获取select
					var sel = document.getElementById('a');
					// 因为一会要用str来拼接，所以要先声明一下
					var str = "";
					// 遍历循环，将每一项都拼接到str中
					for (var i =0; i<data.length; i++) { // 倒叙
						console.log(data[i]);
						str += '<option value=' + data[i].id + '>' + data[i].roleName+ '</option>';
					}
					// 把拼接好的str放到select标签里
					sel.innerHTML = str;
					
				}
				
				
				//默认选择中 ，并赋值
				$("#a option").eq(0).attr("selected", true); // 控制默认选中 0 第一个
				roleId = $("#a").val();
					///////
				}); 

	//下拉选中
	var oSelect = $("#a");
	oSelect.on('change', function() {
		var checkText = $("#a").find("option:selected").text(); // 获取Select选择的Text
		roleId = $("#a").val();
//		alert(roleId)
		
	});
	
	
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
		qingKuang();
		pageNo=pageNo;
		pageSize=pageSize;
		var userName=$("input:eq(0)").val();
		var loginName=$("input:eq(1)").val();
		var Params={
				pageNo:pageNo,
				pageSize:pageSize,
				userName:userName,
				loginName:loginName
//				roleId:roleId
		}
		var newParams = setToken(Params);
		$.get(userManagementListByPageUrl,newParams,
	 	function(data,status){
			if(data.code<=0){
				return tankuang("300",data.msg); 
			}else{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text' value='"+array[i].userName+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+array[i].roleName+"'></td> ");
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+array[i].loginName+"'></td> ");
						var disFlaName='禁用';
						if(0 == array[i].disabledFlag){
							disFlaName='启用';
						}
						tr.append("<td class='w80'><input class='upInput' readonly='readonly' type='text'  value='"+""+disFlaName/*array[i].disabledFlag*/+"'></td> ");  
						tr.append("<td class='w80'>&nbsp;<input class='updateUserManagement' type='button' id='"+array[i].roleId+"' val='"+array[i].id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class='del' id='del' type='button' val='"+array[i].id+"'></td>");
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
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
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
		$("input:eq(0)").val(""),
		$("input:eq(1)").val("")
	}
	$("body").on("click",".save",function(){
		var userName=$("input:eq(0)").val();
		var loginName=$("input:eq(1)").val();
							qingKuang();//清空
		if(userName == null || userName == undefined || userName == ""){
			return tankuang("300","对不起用用户名称有写"); 
		}
		if(roleId == null || roleId == undefined || roleId == ""){
			return tankuang("300","对不起用户角色没有选择"); 
		}else{
			if(loginName == null || loginName == undefined || loginName == ""){
				tankuang("200","对不起用户账号没有写")
				$('.find').trigger("click");
			}else{
				
				var Params={
						userName:userName,
						roleId:roleId,
						loginName:loginName,
						passWord:1,   //初始密码为  1
						disabledFlag:0  //初始 0 启用
						}
				var newParams = setToken(Params);
				$.post(userManagementSaveUrl,newParams,
						function(data,status){
					if(data.code<=0){
						return tankuang("300",data.msg); 
					}else{
						$('.find').trigger("click");
					}  
					});
			}
		}

	});
	
	 
	//修改
		
//					
	$("body").on("click",".updateUserManagement",function(){
		//当前val 值  
		 sessionStorage.setItem("userManagementUpdateId", $(this).attr("val"));
//		 当前id值 在 input内的
		 sessionStorage.setItem("userManagementUpdateRoleId",$(this).attr("id"));
		$.fancybox({
	    	'href'  : 'userEdit.html',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'userManagement.html';
	        }
	    });			
	});
	
	//删除
	$("body").on("click",".del",function(){
//		var id = document.getElementById("del").value;
//		alert(id)var inputValue = document.getElementById("del").value;
		var Params={
				id:$(this).attr("val")
		}
		var newParams = setToken(Params);
		$.post(userManagementListDeleteUrl,newParams,
			function(data,status){
			if(data.code<=0){
				return tankuang("300",data.msg); 
			}else{
				$('.find').trigger("click");
			}
			}
		);
	});
	
	$('.find').trigger("click");
});