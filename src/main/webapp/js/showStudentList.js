$(document).ready(function (){
	onload();
	
//	$("#Query").click(function(){
//		var page=1;
//		var type=$('#type option:selected') .val();
//		alert(type);
//		$.ajax({
//			type : "post",
//			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
//			url : '/LabSys/userController/SelectByType.do',
//			async : false,
//			data : {
//				page:page,
//				type:type
//				
//			},
//			dataType : 'json',
//			success : function(msg) {
//				var num=0;
//				if(msg.result ==true){		
//					var content="";
//					$.each(msg.pageList,function(key,val){
//						num=num%5+1;
//						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
//						"<span class='form-group list'><button onclick='deleteOne("+val.userId+")' type='button' class='btn btn-success btn-xs' >查看详情</button></span>"+
//						"<span class='form-group zs-list'><button onclick='Reset("+val.userAccount+")' type='button' class='btn btn-success btn-xs'>查看计划</button></span>"
//						+"<span class='form-group zs-list'><button onclick='Reset("+val.userAccount+")' type='button' class='btn btn-success btn-xs'>查看总结</button></span>"
//						+"</td></tr>";
//					});
//					alert(content);
//					$("#announcements").empty().append(content);
//					$("#announcementList").paginate({
//						count 		: msg.pageCount,
//						start 		: 1,
//						display     : 10,
//						border					: true,
//						border_color			: '#fff',
//						text_color  			: '#fff',
//						background_color    	: 'rgb(66,139,202)',
//						border_hover_color		: '#ccc',
//						text_hover_color  		: '#000',
//						background_hover_color	: '#fff', 
//						images					: false,
//						mouse					: 'press',
//						onChange     			: function(page){
//							showOnePageAnnouncement(page,type);
//						}
//					});
//				}else{
//					alert(msg.message);
//				}
//			},error: function(msg){
//			    alert("网络超时！");
//			}
//		});
//	});
	
	
	
});

function  showOnePageAnnouncement(page,type){

	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/userController/SelectByType.do',
		async : false,
		data : {
			page:page,
			type:type
		},
	
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){						
				var content="";
				var num=0;
				$.each(msg.pageList,function(key,val){
					num=(num)%5+1;
					if(msg.user.userType==2){
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showStudentDetaile("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
						"<span class='form-group zs-list'><button onclick='showStudentDetaile("+val.userId+")' type='button' class='btn btn-success btn-xs' >查看详情</button></span>"+
						"<span class='form-group zs-list'><button onclick='showStuPlan("+val.userId+")' type='button' class='btn btn-success btn-xs'>查看计划</button></span>"
						+"<span class='form-group zs-list'><button onclick='showStuSumma("+val.userId+")' type='button' class='btn btn-success btn-xs'>查看总结</button></span>"
						+"</td></tr>";
					 
					}
				});
				$("#announcements").empty().append(content);
			}else{
				alert(msg.message);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
}

function showStuPlan(id){

	window.location.href="showPlans.html?id="+id;
	
}

function showStuSumma(id){
	window.location.href="showSummary.html?id="+id;
}

function showStudentDetaile(id){
	window.location.href="studentDetailInfo.html?id="+id;
}




function onload(){
  
	var type='1';
	var page=1;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/userController/SelectByType.do',
		async : false,
		data : {
			page:page,
			type:type
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){		
				var content="";
				var num=0;
				$.each(msg.pageList,function(key,val){
					if(msg.user.userType==2){
					   num=num%5+1;
					   
					   content+="<tr><td>"+num+"</td><td><a href='#' onclick='showStudentDetaile("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
						"<span class='form-group zs-list'><button onclick='showStudentDetaile("+val.userId+")' type='button' class='btn btn-success btn-xs' >查看详情</button></span>"+
						"<span class='form-group zs-list'><button onclick='showStuPlan("+val.userId+")' type='button' class='btn btn-success btn-xs'>查看计划</button></span>"
						+"<span class='form-group zs-list'><button onclick='showStuSumma("+val.userId+")' type='button' class='btn btn-success btn-xs'>查看总结</button></span>"
						+"</td></tr>";
					 
					 
					}
				});
				$("#announcements").empty().append(content);
				$("#announcementList").paginate({
					count 		: msg.pageCount,
					start 		: 1,
					display     : 5,
					border					: true,
					border_color			: '#fff',
					text_color  			: '#fff',
					background_color    	: 'rgb(66,139,202)',
					border_hover_color		: '#ccc',
					text_hover_color  		: '#000',
					background_hover_color	: '#fff', 
					images					: false,
					mouse					: 'press',
					onChange     			: function(page){
					showOnePageAnnouncement(page,type);
					}
				});
			}else{
				alert(msg.message);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
}


function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();

	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
		}
	}
	
	return theRequest;
}