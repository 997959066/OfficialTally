$(document).ready(function() {
	//做为弹框  取session中的id
	var functionIdArray;
	var roleManagementUpdateId=sessionStorage.getItem("roleManagementUpdateId");
	var Params={id:roleManagementUpdateId}
	var newParams = setToken(Params);
	$.get(roleGetUrl,newParams ,
			function(data,status){
	if(data.code<=0){
		return tankuang("300",data.msg); 
	}{
				var data=data.data;
    ////////////////////////////////////////////////////////////////////////////////////////		
				$("#roleNameId").val(data.roleName);
				
				//获取角色已有功能
				if(data.functionName!=null){
				var functionIdString=data.functionName;
		var	functionIdArray = functionIdString.split(",");
				}
		var listFunction=data.listFunctionByRoleId;
		//渲染已有的功能就勾选
		var chkhtml = [];
		for (var i = 0; i < listFunction.length; i++) {
			var sa=""; 
			if(data.functionName!=null){
				if(isInArray3(functionIdArray,listFunction[i].id) ==true){
					 sa="checked='checked'";
				 }
			}
		chkhtml.push('<input type="checkbox"   '+sa+' name="chackBoxName" value="'+listFunction[i].id+'"/><label>'+listFunction[i].resourceName+'</label>');
		}
		$("#checkId").html(chkhtml.join(''));
		
	////////////////////////////////////////////////////////////////////////////////////////
	}
			}
	); 
	

	
	
	function isInArray3(functionIdArray,value){
		var dddd=false;
		for (var i = 0; i < functionIdArray.length; i++) {
			if(parseInt(functionIdArray[i])==parseInt(value)){
				dddd=true;
		} 
	}
		return dddd;
	}	/*
		 * 提交
		 */
		$("#submitbutton").click(function() {
			//获取chckBox
			  obj = document.getElementsByName("chackBoxName");
//			    check_val = [];
			    var check_valList = "";
			    for(k in obj){
			        if(obj[k].checked)
			        	check_valList = obj[k].value + ","+check_valList;
//			            check_val.push(obj[k].value);
			    }
			    check_valList = check_valList.substring(0, check_valList.lastIndexOf(','));  
			var roleId = roleManagementUpdateId;
			var roleName = $('#roleNameId').val();
			var Params={
					id:roleId,
					roleName:roleName,
					functionName:check_valList
			}
		var newParams = setToken(Params);
		$.post(roleUpdateUrl,newParams
			,
			function(data,status){
				 tankuang("300",data.msg); 
				 window.parent.$.fancybox.close();
			});
		});
	 
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
		
		var result = 'null';
		if(result =='success'){
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		}
	});

