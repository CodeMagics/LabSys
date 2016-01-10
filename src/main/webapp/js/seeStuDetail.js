$(document).ready(function() {
	

});

window.onload=function(){

	
	ShowPersonalInfo();
	$("#upadte").click(function(){
	
	});
	$("#saveIfo").click(function(){
		
		save();
	});

	
}




function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
			}
		}
		if(url.indexOf("id")==-1)
		{
		theRequest = null;
		}
	return theRequest;
}

function checkValue(str) {
	if (str == null) {
		str = "未填写";
	}
	return (str);
}


function ShowInfo() {
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/studentController/readStudentByStudNumber.do',
		async : false,
		data : {

		},
		dataType : 'json',
		success : function(msg) {
		
			if (msg.result == true) {
				
				$("#studClass").html(msg.student.studClass);// 班级
				$("#name").html(msg.student.userRealname);// 姓名
				$("#num").html(msg.student.studNum);// 学号
				$("#major").html(msg.student.studMajor);// 专业
				$("#phone").html(msg.student.userPhone);// 电话
				$("#email").html(msg.student.userEMail);// 邮箱

				
			
			} else {
				alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}


function ShowPersonalInfo() {
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/studentController/readStudentByStudNumber.do',
		async : false,
		data : {

		},
		dataType : 'json',
		success : function(msg) {
		
			if (msg.result == true) {
				alert(msg.student.studMajor);
				$(".personalStuClass").html(msg.student.studClass);// 班级
				$(".personalStuName").html(msg.student.userRealname);// 姓名
				$(".personalStuNumber").html(msg.student.studNum);// 学号
				$(".personalStuDepartment").html(msg.student.studMajor);// 专业
				$(".personalStuPhone").html(msg.student.userPhone);// 电话
				$(".personalStuEmail").text(msg.student.userEMail);// 邮箱

				
			
			} else {
				alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}

function save(){
var name = $("#name").val();
var num = $("#num").val();
var major = $("#major").val();
var studClass = $("#studClass").val();
var email = $("#email").val();
var phone = $("#phone").val();



		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/studentController/updateStudent.do',
			async : false,
			data : {
				name:name,
				num : num,
				major:major,
				studClass:studClass,
				email : email,
				phone : phone

			},
			dataType : 'json',
			success : function(msg) {
				if (msg.result == true) {
					
					alert(msg.message);
					window.location.href = 'studentDetailInfo.html';
					
				} else {
					alert("保存失败！");
				}
			},
			error : function(msg) {
				alert("网络超时！");
			}
		});
 
}