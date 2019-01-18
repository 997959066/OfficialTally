$(function(){
	
	//注册弹框
	$("#login_ret").click(function() {
			$.fancybox({
	    	'href'  : 'page/system/register.html',
	    	'width' : 300,
	        'height' : 190,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = 'login.html';
	        }
	    });	
	});
	
	  //进入登录界面后，判断cookie中是否有帐号密码，如果有就自动填充
		  //获取cookie
		  var cusername = getCookie('loginName');
		  if(cusername != "" && cusername != null){
		    $("#name").val(cusername);
		  }
	  
	/*回车事件*/
	//回车键事件 
	//绑定键盘按下事件  
	$(document).keypress(function(e) {  
	 // 回车键事件  
	     if(e.which == 13) {  
//	    	 alert("回车")
	    	   jQuery(".login_btnD").click();  
	     }  
	}); 
	
	
	    $("input:button").click(function() {
	    	 var loginName = $("#name").val();
	    	 var passWord = $("#pwd").val();
			$.post(loginUrl,
					{
				loginName:loginName,
					passWord:passWord,
					},
					function(data,status){
							 if(data.code==1){
								 //将信息存在Cookie中
								 SetCookie("userId", data.data.id);
								 SetCookie("userName", data.data.userName);
								 SetCookie("faceImage", data.data.faceImage);
								 SetCookie("loginName", data.data.loginName);
								 //token 
								 SetCookie("token", data.data.token);
								 sessionStorage.setItem("userId", data.data.id);
								 sessionStorage.setItem("faceImage",data.data.faceImage);
								 //跳转到index页面
								displayindex();
							 }else{
								 tankuang("300","账号或密码错误！！！")
							 }
					}
				);
	      
	    });
	})
	
