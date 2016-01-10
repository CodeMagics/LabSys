//
//
//
//							
//		
//
//
//function showInformation() {
//	$
//			.ajax({
//				type : "post",
//				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
//				url : '/zzxt/informationController/showDeatilInformation.do',
//				async : false,
//				data : {
//
//				},
//				dataType : 'json',
//				success : function(msg) {
//					if (msg.result == true) {
//						if (msg.studentinfo.stinIseditable == 0) {
//							$("#infoDeatil :input").attr("disabled", true);
//						}
//
//						$("#politicState").empty().val(
//								msg.studentinfo.stinPoliticstate);
//						$("#bankNumber").empty().val(
//								msg.studentinfo.stinBanknumber);
//						if (msg.studentinfo.stinSex == "男") {
//							document.getElementById("man").selected = "selected";
//						} else {
//							document.getElementById("woman").selected = "selected";
//						}
//						$("#phone").empty().val(msg.studentinfo.stinPhone);
//						$("#QQ").empty().val(msg.studentinfo.stinQq);
//						$("#email").empty().val(msg.studentinfo.stinEmail);
//						if (msg.studentinfo.stinResidence == "城镇") {
//							document.getElementById("chengzhen").selected = "selected";
//						} else {
//							document.getElementById("nongcun").selected = "selected";
//						}
//						$("#population").empty().val(
//								msg.studentinfo.stinPopulation);
//						$("#allIncomes").empty().val(
//								msg.studentinfo.stinAllincomes);
//						$("#mailCode").empty()
//								.val(msg.studentinfo.stinMailcode);
//						$("#incomeType").val(msg.studentinfo.stinIncometype);
//						$("#department").empty().val(
//								msg.studentinfo.stinDepartment);
//						originDeatil = msg.studentinfo.stinAreadetailorigin;
//						if (msg.city != null && msg.province != null
//								&& msg.county != null && msg.town != null) {
//							$("#city").val(msg.city);
//							$("#province").val(msg.province);
//							$("#city").val(msg.city);
//							$("#county").val(msg.county);
//							$("#town").val(msg.town);
//						}
//						if(msg.studentinfo.stinAreadeatilhome=="未填写"){
//							msg.studentinfo.stinAreadeatilhome="";
//						}
//						$("#vallige").val(msg.studentinfo.stinAreadeatilhome);
//						msg.studentinfo.stinPoliticstate = checkValue(msg.studentinfo.stinPoliticstate);
//						msg.studentinfo.stinPhone = checkValue(msg.studentinfo.stinPhone);
//						msg.studentinfo.stinEmail = checkValue(msg.studentinfo.stinEmail);
//						msg.studentinfo.stinResidence = checkValue(msg.studentinfo.stinResidence);
//						msg.studentinfo.stinPopulation = checkValue(msg.studentinfo.stinPopulation);
//						msg.studentinfo.stinAllincomes = checkValue(msg.studentinfo.stinAllincomes);
//						msg.studentinfo.stinIncometype = checkValue(msg.studentinfo.stinIncometype);
//						msg.studentinfo.stinMailcode = checkValue(msg.studentinfo.stinMailcode);
//						msg.studentinfo.stinDepartment = checkValue(msg.studentinfo.stinDepartment);
//						msg.studentinfo.stinQq = checkValue(msg.studentinfo.stinQq);
//						msg.studentinfo.stinAreadetailorigin = checkValue(msg.studentinfo.stinAreadetailorigin);
//						msg.studentinfo.stinBanknumber = checkValue(msg.studentinfo.stinBanknumber);
//						var stuDeatilInfo = "<tr>" + "<th>政治面貌</th>" + "<td>"
//								+ msg.studentinfo.stinPoliticstate + "</td>"
//								+ "<th>银行卡号</th>" + "<td>"
//								+ msg.studentinfo.stinBanknumber + "</td>"
//								+ "<th>性别</th>" + "<td>"
//								+ msg.studentinfo.stinSex + "</td>" + "</tr>"
//								+ "<tr>" + "<th>电话号码</th>" + "<td>"
//								+ msg.studentinfo.stinPhone + "</td>"
//								+ "<th>邮箱</th>" + "<td>"
//								+ msg.studentinfo.stinEmail + "</td>"
//								+ "<th>户口类型</th>" + "<td>"
//								+ msg.studentinfo.stinResidence + "</td>"
//								+ "</tr>" + "<tr>" + "<th>人口数量</th>" + "<td>"
//								+ msg.studentinfo.stinPopulation +"人"+ "</td>"
//								+ "<th>家庭月总收入</th>" + "<td>"
//								+ msg.studentinfo.stinAllincomes +"元"+ "</td>"
//								+ "<th>收入来源</th>" + "<td>"
//								+ msg.studentinfo.stinIncometype + "</td>"
//								+ "</tr>" + "<tr>" + "<th>邮政编码</th>" + "<td>"
//								+ msg.studentinfo.stinMailcode + "</td>"
//								+ "<th>家庭详细地址</th>" + "<td>" + msg.HomeAddress
//								+ "</td>" + "<th>系</th>" + "<td>"
//								+ msg.studentinfo.stinDepartment + "</td>"
//								+ "</tr>" + "<tr>" + "<th>QQ号码</th>" + "<td>"
//								+ msg.studentinfo.stinQq + "</td>"
//								+ "<th>生源地详细地址</th>" + "<td colspan=\"3\">"
//								+ msg.studentinfo.stinAreadetailorigin
//								+ "</td>" + "</tr>";
//						$("#studentIfo").empty().append(stuDeatilInfo);
//
//					}
//				},
//				error : function(msg) {
//					alert("网络超时！wrong");
//				}
//			});
//};
//
//
//function check() {
////
////		if (checkPhone() && checkEmail())
////			return true;
////		else
////			return false;
//	return true;
//	
//}
//
//
//
//function checkPhone() {
//	if (!document.getElementById
//			|| !document.createTextNode)
//		return false;
//	var phone = document.getElementById("phone");
//	var value = phone.value;
//	if (!value || value == null) {
//		alert("请填写电话号码！");
//		return false;
//	}
//	var pattern1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
//	var pattern2 = /^0?(13[0-9]|15[0-9]|18[0-9]|14[57])[0-9]{8}$/;
//	flag1 = pattern1.test(value);
//	flag2 = pattern2.test(value);
//	if (flag1 == true || flag2 == true) {
//		return true;
//	} else {
//		alert("电话号码不正确！");
//		return false;
//	}
//}
//
//function checkEmail() {
//	if (!document.getElementById
//			|| !document.createTextNode)
//		return false;
//	var email = document.getElementById("email");
//	var value = email.value;
//	if (!value || value == null) {
//		alert("请填写邮箱！");
//		return false;
//	}
//	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
//	flag = pattern.test(value);
//	if (flag) {
//		return true;
//	} else {
//		alert("邮箱格式不正确！");
//		return false;
//	}
//}
//
//
