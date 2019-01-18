$(document).ready(function() {
	//做为弹框  取session中的id
	
	var userManagementUpdateId=sessionStorage.getItem("userManagementUpdateId");
	var userManagementUpdateRoleId=sessionStorage.getItem("userManagementUpdateRoleId");
	var Params={
			id:userManagementUpdateId,
			roleId:userManagementUpdateRoleId
	}
	var newParams = setToken(Params);
	$.get(userManagementGetUrl,newParams ,
			function(data,status){
	if(data.code<=0){
		return tankuang("300",data.msg); 
	}{
				var data=data.data;
    ////////////////////////////////////////////////////////////////////////////////////////		
				$("#userNameId").val(data.userName);
				$("#loginNameId").val(data.loginName);
				/*$("#passWordId").val(data.passWord);2018.12.23增加md5加密*/
				$("#phoneId").val(data.phone);
				
				var str = "";
				var sel = document.getElementById('disabledFlagId');
				var m='';
				var s='';
//				
				if(data.disabledFlag==0){
					m="selected='selected'";
				}else{
					s="selected='selected'";
				}
					str += '<option value=0 '+m+'>启用</option>';
					str += '<option value=1 '+s+'>禁用</option>';
				sel.innerHTML = str;
				
				
				// 获取select
				var userRoleId = document.getElementById('userRoleId');
				var userRoleIdstr = "";
				userRoleIdstr += '<option selected value=' + data.roleId + '>' + data.roleName + '</option>';

				
				//当前用户不在的角色
				var roleList=data.roleList;
				for(var i=0;i<roleList.length;i++){
					userRoleIdstr += '<option value=' + roleList[i].id + '>' + roleList[i].roleName + '</option>';
				}
				// 把拼接好的str放到select标签里
				userRoleId.innerHTML = userRoleIdstr;
	////////////////////////////////////////////////////////////////////////////////////////
	}
			}
	); 
	
		/*
		 * 提交
		 */
		$("#submitbutton").click(function() {
//			var id = $('#userId').val();
			var userName = $('#userNameId').val();
			var loginName = $('#loginNameId').val();
			var passWord = $('#passWordId').val();
			var phone = $('#phoneId').val();
			var disabledFlag = $('#disabledFlagId').val();
			
//			 var checkText = $("#userRoleId").find("option:selected").text(); // 获取Select选择的Text
			  var options=$("#userRoleId option:selected"); //获取选中的项
//			  alert(options.val()); //拿到选中项的值
//			  alert(options.text()); //拿到选中项的文本
			  
			  
			  
			
			var Params={
					id:userManagementUpdateId,
					 userName:userName,
					loginName:loginName,
					passWord:passWord,
					phone:phone,
					disabledFlag,disabledFlag,
					roleId:options.val(),
					beforeRoleId:userManagementUpdateRoleId //将之前roleId传过去 以便更改
			}
		var newParams = setToken(Params);
		$.post(userManagementListUpdateUrl,newParams
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

