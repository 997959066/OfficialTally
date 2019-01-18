$(document).ready(function() {
		/*
		 * 提交注册信息
		 */
		$("#submitbutton").click(function() {
			var userName = $('#userNameId').val();
			var loginName = $('#loginNameId').val();
			var passWord = $('#passWordId').val();
			var phone = $('#phoneId').val();
			var Params={
					userName:userName,
					loginName:loginName,
					passWord:passWord,
					phone:phone,
					disabledFlag:0,
					roleId:2
			}
		var newParams = setToken(Params);
		$.post(registeredUserUrl,newParams
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

