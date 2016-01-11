$(document).ready(function (){
	
	onload();
	$("#addPlan").click(function(){
		window.location.href="publishPlanInfo.html";
	});
});

function  showOnePageAnnouncement(id,page){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/planController/showList.do',
		async : false,
		data : {
			id:id,
			page:page
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){						
				var content="";
				var num=0;
				$.each(msg.pageList,function(key,val){
					num=(num)%5+1;
					if(msg.user.userType==1){
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.planId+")'>"+val.planTitle+"</a></td><td>"+val.planDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.planId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.planId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						num=num%5+1;
						$("#addPlan").hide();
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.planId+")'>"+val.planTitle+"</a></td><td>"+val.planDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.planId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
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

function showDetail(id){
	window.location.href = "seePlanDetail.html?id="+id;
}

function modify(id){
	window.location.href = "modifyPlanInfo.html?id="+id;
}


function deleteOne(id){
	if(window.confirm('你确定要删除吗？')){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/planController/deleteplan.do',
			async : false,
			data : {
				planId:id
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					alert("删除成功！");
				}else{
					alert(msg.message);
					
				}
			},error: function(msg){
			    alert("网络超时！");
			}
		});	
		window.location.reload();
		
        return true;
     }else{
  
        return false;
    }
}

function onload(){
	var page=1;
	var Request = GetRequest();
    var Userid=-1;
	if (Request != null) {
		Userid = Request['id'];
	}
	var id=Userid;
	
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/planController/showList.do',
		async : false,
		data : {
			id:id,
			page:page
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){		
				var content="";
				var num=0;
				$.each(msg.pageList,function(key,val){
					if(msg.user.userType==1){
						num=num%5+1;
						
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.planId+")'>"+val.planTitle+"</a></td><td>"+val.planDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.planId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.planId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						$("#addPlan").hide();
						num=num%5+1;
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.planId+")'>"+val.planTitle+"</a></td><td>"+val.planDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.planId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
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
						showOnePageAnnouncement(id,page);
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
	var theRequest = null;

	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
		}
	}
	return theRequest;
}