$(document).ready(function (){
	onload();
	$("#addUser").click(function(){
		
	});
	
	$("#Query").click(function(){
		var page=1;
		var type=$('#type option:selected') .val();
		alert(type);
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
					$.each(msg.pageList,function(key,val){
						content+="<tr><td>"+type+"</td><td><a href='#' onclick='showDetail("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
						"<span class='form-group'><button onclick='deleteOne("+val.userId+")' type='button' class='btn btn-success btn-xs' style='display:inline-block;margin-right: 10px;'>删除</button></span>"+
						"<span class='form-group'><button onclick='Reset("+val.userAccount+")' type='button' class='btn btn-success btn-xs'>重置密码</button></span>"
						+"</td></tr>";
					});
					$("#announcements").empty().append(content);
					$("#announcementList").paginate({
						count 		: msg.pageCount,
						start 		: 1,
						display     : 10,
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
	});
	
	
	
});

function  showOnePageAnnouncement(page,type){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/userController/ShowList.do',
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
				$.each(msg.pageList,function(key,val){
					type=(type)%5+1;
					if(msg.user.userType==3){
						 content+="<tr><td>"+type+"</td><td><a href='#' onclick='showDetail("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
							"<span class='form-group'><button onclick='deleteOne("+val.userId+")' type='button' class='btn btn-success btn-xs' style='display:inline-block;margin-right: 10px;'>删除</button></span>"+
							"<span class='form-group'><button onclick='Reset("+val.userAccount+")' type='button' class='btn btn-success btn-xs'>重置密码</button></span>"
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
	window.location.href = "seeuserDetail.html?id="+id;
}



function deleteOne(id){
	if(window.confirm('你确定要删除吗？')){
		alert(id);
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/userController/Delete.do',
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

function Reset(account){
	
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/userController/resetPassword.do',
			async : false,
			data : {
				account:account
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					alert("重置成功！");
				}else{
					alert(msg.message);
					
				}
			},error: function(msg){
			    alert("网络超时！");
			}
		});
		onload();
		
    
}


function onload(){
	
	var type='0';
	var page=1;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/userController/ShowList.do',
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
				$.each(msg.pageList,function(key,val){
					if(msg.user.userType==3){
					   type=type%5+1;
					   content+="<tr><td>"+type+"</td><td><a href='#' onclick='showDetail("+val.userId+")'>"+val.userAccount+"</a></td><td>"+val.userRealname+"</td><td>"+
						"<span class='form-group'><button onclick='deleteOne("+val.userId+")' type='button' class='btn btn-success btn-xs' style='display:inline-block;margin-right: 10px;'>删除</button></span>"+
						"<span class='form-group'><button onclick='Reset("+val.userAccount+")' type='button' class='btn btn-success btn-xs'>重置密码</button></span>"
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
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	if (url.indexOf("type") == -1) {
		theRequest = null;
	}
	return theRequest;
}