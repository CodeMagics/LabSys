$(document).ready(function (){
	onload();
	$("#addTask").click(function(){
		window.location.href="publishInfo.html";
	});
});

function  showOnePageAnnouncement(page,type){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/taskController/ShowList.do',
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
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.taskId+")'>"+val.taskTitle+"</a></td><td>"+val.taskDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.taskId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.taskId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.taskId+")'>"+val.taskTitle+"</a></td><td>"+val.taskDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.taskId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
						+"</td></tr>";
					}
				});
				$("#announcements").empty().append(content);
			}else{
				content="暂无任务";;
				$("#announcements").empty().append(content);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
}

function showDetail(id){
	window.location.href = "seeTaskDetail.html?id="+id;
}

function modify(id){
	window.location.href = "modifyInfo.html?id="+id;
}


function deleteOne(id){
	if(window.confirm('你确定要删除吗？')){
		
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/taskController/Delete.do',
			async : false,
			data : {
				id:id
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
		onload();
		
        return true;
     }else{
        return false;
    }
}

function onload(){
	
	var type=0;
	var page=1;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/taskController/ShowList.do',
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
					
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.taskId+")'>"+val.taskTitle+"</a></td><td>"+val.taskDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.taskId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.taskId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						num=num%5+1;
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.taskId+")'>"+val.taskTitle+"</a></td><td>"+val.taskDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.taskId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
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
				content="暂无任务";;
				$("#announcements").empty().append(content);
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
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	if (url.indexOf("type") == -1) {
		theRequest = null;
	}
	return theRequest;
}