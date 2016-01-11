var copyright = "<span style=text-align:center>QQ在线交流：<a target=blank href='tencent://message/?uin=3076134635&Site=yige.org&Menu=yes'><img border='0' SRC='' alt='点击这里给我发消息'></a>" +
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线信息反馈：<a href='userFeedBack.html'><img src='' alt='点击这里在线留言'></img></a></span>"
	+"<p>系统版权由西南科技大学学生工作处（部）开发</p><p>版权所有&nbsp;&nbsp;&nbsp;©2014&nbsp;&nbsp;&nbsp;西南科技大学学生工作部（处）</p>"
	+ "<ul class=\"footer-links\"><li>当前版本：&nbsp;&nbsp; v1.18 &nbsp;&nbsp;&nbsp;系统更新时间：2014年09月16日&nbsp;&nbsp;&nbsp;&nbsp;西南科技大学学生资助系统</li>"
	+ "<li class='muted'>·</li></ul>"
	+"友情链接：<a href='http://xsc.swust.edu.cn/'>西南科技大学学生工作处（部）</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://xsc.swust.edu.cn/plus/list.php?tid=36'>勤工助学贷款办公室</a>";
var pareid;
var pareName;
var funid;
var funName;
var funUrl;
$(document).ready(
		function() {
			$("#copyright").empty().append(copyright);
			
			// 获取用户权限
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				url : '/LabSys/userController/showAuthorityList.do',
				async : false,
				data : {

				},
				dataType : 'json',
				success : function(msg) {
					if (msg.result == true) {
						if (msg.user == null) {
							alert("用户已经退出，请重新登录！");
							window.location.href = "login.html";
						} else {
							onload();
							$("#current_user").empty().append(msg.user.userAccount);
						}
						
						var navigation = "";
						var nav1 = "";
						//alert(msg.functionList[0].funcName);
						for(i=0;i<msg.functionList.length;i++)
							{
							
							navigation+= "<div class='panel panel-default'>" + "<div class='panel-heading'>"
							+ "<h4 class='panel-title'>" + "<a href='" + msg.functionList[i].funcUrl + "'"  + ">" 
							+ msg.functionList[i].funcName + "</a>"
							+ "</h4>" + "</div>";
							
							nav1 += "<a href='" + msg.functionList[i].funcUrl + "'"  + ">" 
							+ msg.functionList[i].funcName + "</a>";
							
							}
						$("#navigation").empty().append(navigation);
							
						
					} else {
						alert(msg.message);
						window.location.href = "login.html";
					}
				},
				error : function(msg) {
					alert("网络超时！");
				}
			});
			onload();

			$("#inputPassword1").val("");// 上面出现的当前密码清空

			$("#changePWD").click(function() {
				var currentpassword = $("#inputPassword1").val();
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					url : '/LabSys/userController/checkPassword.do',
					async : false,
					data : {

					},
					dataType : 'json',
					success : function(msg) {
						if (msg.result == true) {
							var id = msg.user.userId;
							/*
							 * 如果修改密码后继续用session的user， 不退出登陆再次进行修改密码的话，它的当前密码还是
							 * 默认的是修改前的密码，所以用了showUser方法 重新掉用数据库里的密码与输入的当前密码对比
							 * 看是否输入正确
							 */
							$.ajax({
								type : "post",
								contentType : "application/x-www-form-urlencoded;charset=UTF-8",
								url : '/LabSys/userController/showUser.do',
								async : false,
								data : {
									id : id,
								},
								dataType : 'json',
								success : function(msg) {
									if (msg.result == true) {

										if (msg.user.userPassword == currentpassword) {
											var password = $("#inputPassword2").val();
											if (check() != false) {

												$.ajax({
													type : "post",
													contentType : "application/x-www-form-urlencoded;charset=UTF-8",
													url : '/LabSys/userController/editPassword.do',
													async : false,
													data : {
														password : password,
													},
													dataType : 'json',
													success : function(msg) {
														if (msg.result == true) {

															alert("修改成功！");
															window.location.href="login.html";
														} else {
															alert(msg.message);
														}
													},
													error : function(msg) {
														alert("网络超时！");
													}

												});
											}else{
												$('#changePassword').modal('show');
											}
										} else {
											alert("输入的当前密码不正确！");
										$('#changePassword').modal('show');
										}
									} else {
										alert(msg.message);
									}

								},
								error : function(msg) {
									alert("网络超时！");
								}
							});

						} else {
							alert(msg.message);
						}
					},
					error : function(msg) {
						alert("网络超时！");
					}
				});
				$("#inputPassword1").val("");
				$("#inputPassword2").val("");
				$("#inputPassword3").val("");
				

			});

			// 用户退出
			$("#logout").click(function() {
				// 响应退出事件
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					url : '/LabSys/userController/logout.do',
					async : false,
					data : {

					},
					dataType : 'json',
					success : function(msg) {
						if (msg.result) {
							window.location.href = "login.html";
						} else {
							alert("退出出错！");
						}
					},
					error : function(msg) {
						alert("网络超时！！！！");
					}
				});
			});
			
			//显示导航
			showLocation();
		});//document结束
   function showLocation() {
	if(pareid!=null&&funid!=null)
	{
		var location = "<a href='index.html'>首页</a>&gt;"+pareName+"&gt;"+"<a href="+funUrl+">"+funName+"</a>";
		$("#location").empty().append(location);
	}
	
}
function check() {
	var newPassword = $("#inputPassword2").val();
	var sureNewPWD = $("#inputPassword3").val();

	if (newPassword == "") {
		alert("新密码不能空");
		return false;
	}
	if (sureNewPWD == "") {
		alert("确认密码不能空");
		return false;
	}
	if (sureNewPWD != newPassword) {
		alert("两次输入的密码不一致");
		return false;
	}
}


function GetRequest1() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	if (url.indexOf("pareid") == -1) {
		theRequest = null;
	}
	return theRequest;
}

function onload(){
	
	var type=0;
	var page=1;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/noticeController/ShowList.do',
		async : false,
		data : {
			page:page,
			type:type
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){		
				var content="";
				var type=0;
				if(msg.pageList.length>5){
					msg.pageList.length=5;
				}
				for(var i=0;i<msg.pageList.length;i++){
					content+="<li><a href='seeNoticeDetail.html?id="
						+msg.pageList[i].noticeId+" '><span class='glyphicon glyphicon-folder-close'></span>"
						+msg.pageList[i].noticeTitle+"</a>"
						+"</li>";
					
				}
			
			  	$("#firstNotice").empty().append(content);
				
			}else{
				alert(msg.message);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
	
	$("#moreNotice").attr("href","showNotice.html");
}
