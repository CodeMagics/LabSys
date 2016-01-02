var copyright = "<span style=text-align:center>QQ在线交流：<a target=blank href='tencent://message/?uin=3076134635&Site=yige.org&Menu=yes'><img border='0' SRC='../images/qq.gif' alt='点击这里给我发消息'></a>" +
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线信息反馈：<a href='userFeedBack.html'><img src='../images/feedback.jpg' alt='点击这里在线留言'></img></a></span>"
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
			var Request = new Object();
			Request = GetRequest1();
			if (Request != null) {
				pareid = Request['pareid'];
				funid = Request['funid'];
			}
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
							$("#current_user").empty().append(msg.user.userName);
						}
						
						var navigation = "";
						//alert(msg.functionList[0].funcName);
						for(i=0;i<msg.functionList.length;i++)
							{
							navigation+= "<div class='panel panel-default'>" + "<div class='panel-heading'>"
							+ "<h4 class='panel-title'>" + "<a  data-toggle='collapse' data-toggle='collapse'"
							+ "data-parent='#accordion' href='#collapse" +msg.functionList[i].funcName + "'>" + msg.functionList[i].funcName + "</a>"
							+ "</h4>" + "</div>";
							
							
							}
						$("#navigation").empty().append(navigation);
						$.each(msg.functionList, function(key, val) {
							
							var nav = ""; 
							var nav1 = "";
							var nav2 = "";
							nav = "<div class='panel panel-default'>" + "<div class='panel-heading'>"
									+ "<h4 class='panel-title'>" + "<a  data-toggle='collapse' data-toggle='collapse'"
									+ "data-parent='#accordion' href='#collapse" + val.funcId + "'>" + val.funcName + "</a>"
									+ "</h4>" + "</div>";
							if (pareid != null || pareid != "") {
								
								if (val.id == pareid)
								{
								pareName=val.name;
								nav += "<div id='collapse" + val.funcId + "' class='panel-collapse in'>";
								}
								else
									nav += "<div id='collapse" + val.funcId + "' class='panel-collapse collapse'>";
							} else
								nav += "<div id='collapse" + val.funcId + "' class='panel-collapse collapse'>";
							nav += "<div class='panel-body'>"
									+ "<ul class='nav nav-pills nav-stacked' style='max-width: 300px;'>";
							$.each(val.columns, function(k, v) {
								if (funid != null || funid != "") {
									if (funid == v.funcId)
									{
										
										funName = v.funcName;funUrl = v.funcUrl;
										nav1 += "<li style='background-color:#E6E6FA'><a href='" + v.funcUrl + "'"  + ">" + "<b id='li"+ v.funcId+"'>"
												+ v.funcName + "</b>" + "</a></li>";
									}
									else
										nav1 += "<li><a href='" + v.funcUrl + "'  id='li" + v.funcId + "'>"
												+ v.funcName + "</a></li>";
								} else
									nav1 += "<li><a href='" + v.funcUrl + "'  id='li" + v.funcId + "'>" + v.funcName
											+ "</a></li>";
							});
							nav2 = "</ul>" + "</div>" + "</div>" + "</div>"; 
							navigation += nav + nav1 + nav2;
						});
						$("#navigation").empty().append(navigation);
						if (msg.user.userType == 1) {
							
						}
					} else {
						alert(msg.message);
						window.location.href = "login.html";
					}
				},
				error : function(msg) {
					alert("网络超时！");
				}
			});

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
													url : '/zzxt/userController/editPassword.do',
													async : false,
													data : {
														password : password,
													},
													dataType : 'json',
													success : function(msg) {
														if (msg.result == true) {

															alert("修改成功！");
															$('#changePassword').modal('hide');
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
				//$('#changePassword').modal('hide');

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
