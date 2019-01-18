	//封装token
function setToken(Params){
	var token=JSON.parse(unescape(getCookie("token")));
	var newParams = $.extend(Params, token);
	return newParams;
}
function setTokenWH(){
	var Params={}
	var newParams = setToken(Params);
	var g_userId = newParams.g_userId;//使用点的方式
	var g_token = newParams.g_token;//使用点的方式
	var g_time = newParams.g_time;//使用点的方式
	var ss="?g_userId="+g_userId+"&g_token="+g_token+"&g_time="+g_time;
	return ss;
}

//js写入Cookie代码:
function SetCookie(name, value)
{
    //定义一天
    var days = 1;
    var exp = new Date();
    //定义的失效时间，
    exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);  
    //写入Cookie  ，toGMTstring将时间转换成字符串。
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString;
  
 
}
//js读取Cookie代码

function getCookie(name)
{          //匹配字段
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
         
    if(arr=document.cookie.match(reg))
 
        return (arr[2]);
    else
        return null;
}

//onclick事件
function displayindex(){
		tankuang("300","正在跳转页面")
		window.location.href=url+"/index.html"
	}
function displayTally(){
	tankuang("300","正在跳转页面")
	window.location.href=url+"/page/tally.html"
}
	
//公共
function tankuang(pWidth,content)
{    
    $("#msg").remove();
    var html ='<div id="msg" style="position:fixed;top:50%;width:100%;height:30px;line-height:30px;margin-top:-15px;"><p style="background:#000;opacity:0.8;width:'+ pWidth +'px;color:#fff;text-align:center;padding:10px 10px;margin:0 auto;font-size:12px;border-radius:4px;">'+ content +'</p></div>'
            $("body").append(html);
            var t=setTimeout(next,2000);
            function next()
            {
                $("#msg").remove();
                
            }
}

//浮点
function fdChar(strs){   
	var pattern = /[1-9]\d*.\d*|0.\d*[1-9]\d*/;
	return pattern.test(strs);
}
//是否含有中文（也包含日文和韩文）
function isChineseChar(strs){   
	var pattern = /[\u4e00-\u9fa5]/;
   return pattern.test(strs);
}
//同理，是否含有全角符号的函数
function isFullwidthChar(str){
   var pattern = /[\uFF00-\uFFEF]/;
   return pattern.test(strs);
} 

