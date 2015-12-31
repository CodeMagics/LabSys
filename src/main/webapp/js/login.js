$(document).ready(function (){
	createCode();
	document.onkeydown = function(e){    
	    var ev = document.all ? window.event : e;  
	    if(ev.keyCode==13) {    // 如（ev.ctrlKey && ev.keyCode==13）为ctrl+Center 触发  
	        //要处理的事件  
	    	document.getElementById("login").click(); //调用登录按钮的登录事件  
	    }
	  };
	
	$("#login").click(function(){
		var password=$("#password").val();
		var loginname=$("#loginname").val();
		if(check()){
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					url : '/LabSys/userController/login.do',
					async : false,
					data : {
						password:password,
						loginname:loginname
					},
					dataType : 'json',
					success : function(msg) {
						if(msg.result ==true){						
							window.location.href="index.html";
						}else{
							alert(msg.message);
						}
					},error: function(msg){
					    alert("网络超时！");
					}
				});
		}
	});
});

function check(){
	if(validate){
		if($("#username").val()==""){
			alert("请填写用户名！");
			return false;
		}
		else if($("#password").val()==""){
			alert("请填写密码！");
			return false;
		}
		else if(validate ()){
			return true;
		}
	}
}

var code ; //在全局 定义验证码  
function createCode()  
{   
	$("#identyCode").val("");
	//alert("++++++++++");
  code = "";  
  var codeLength = 4;//验证码的长度  
  var checkCode = document.getElementById("checkCode");  
  var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的  
     
  for(var i=0;i<codeLength;i++)
  {
  var charIndex = Math.floor(Math.random()*36);
  code +=selectChar[charIndex];
  }  
 
  if(checkCode)  
  {  
    checkCode.className="code";  
    checkCode.value = code;  
  };  
}  

function validate (){
	  
	  var inputCode = document.getElementById("identyCode").value.toUpperCase();   
	  if(inputCode.length <=0)   
	  {   
	      alert("请输入验证码！");
	      return false;
	  }   
	  else if(inputCode != code )   //不区分大小写
	  {   
	     alert("验证码输入错误！");
	     $("#identyCode").empty().val("");
	     createCode();//刷新验证码   
	     return false;
	  }   
	  else if(inputCode == code) 
	  {   
		 return true;
	  }   

	  }
