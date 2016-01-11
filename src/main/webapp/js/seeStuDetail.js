$(document).ready(function() {
	

});

window.onload=function(){

	
	ShowPersonalInfo();
	$("#upadte").click(function(){

		UpdateShowPersonalInfo();
	
	});
	$("#saveIfo").click(function(){
		
		save();
	});

	
}




function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = null;

	if (url.indexOf("?") != -1) {
		theRequest={};
		var str = url.substr(1);
		strs = str.split("&");
		
		for ( var i = 0; i < strs.length; i++) {
			
			theRequest.studId = strs[i].split("=")[1];
			
		}
	}
	
	return theRequest;
}

function checkValue(str) {
	if (str == null) {
		str = "未填写";
	}
	return (str);
}




function ShowPersonalInfo() {
	var Request = GetRequest();
	
    var Userid=-1;
   
	if (Request != null) {
		Userid = Request.studId;
	}

    var id=Userid;
    
	
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/studentController/readStudentByStudNumber.do',
		async : false,
		data : {
          id:id
		},
		dataType : 'json',
		success : function(msg) {
		    
			if (msg.result == true) {
				if(msg.user.userType==2){
					
					
					
				}
			
				$(".personalStuClass").html(msg.student.studClass);// 班级
				$(".personalStuName").html(msg.student.userRealname);// 姓名
				$(".personalStuNumber").html(msg.student.studNum);// 学号
				$(".personalStuDepartment").html(msg.student.studMajor);// 专业
				$(".personalStuPhone").html(msg.student.userPhone);// 电话
				$(".personalStuEmail").html(msg.student.userEMail);// 邮箱

				
			
			} else {
				alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}

function UpdateShowPersonalInfo() {
	var Request = GetRequest();
	
    var Userid=-1;
   
	if (Request != null) {
		Userid = Request.studId;
	}

    var id=Userid;
    
	
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/studentController/readStudentByStudNumber.do',
		async : false,
		data : {
          id:id
		},
		dataType : 'json',
		success : function(msg) {
		    
			if (msg.result == true) {
				if(msg.user.userType==2){
					
					
					
				}
			
				$(".personalStuClass").val(msg.student.studClass);// 班级
				$(".personalStuName").val(msg.student.userRealname);// 姓名
				$(".personalStuNumber").val(msg.student.studNum);// 学号
				$(".personalStuDepartment").val(msg.student.studMajor);// 专业
				$(".personalStuPhone").val(msg.student.userPhone);// 电话
				$(".personalStuEmail").val(msg.student.userEMail);// 邮箱

				
			
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


