$(document).ready(function (){
	onload();
	$("#addSummary").click(function(){
		window.location.href="publishSummaryInfo.html";
	});
});

function  showOnePageAnnouncement(id,page){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/summaryController/showList.do',
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
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.sumId+")'>"+val.sumTitle+"</a></td><td>"+val.sumDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.sumId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.sumId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						num=num%5+1;
						$("#addsummary").hide();
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.sumId+")'>"+val.sumTitle+"</a></td><td>"+val.sumDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.sumId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
						+"</td></tr>";
					}
				});
				$("#announcements").empty().append(content);
			}else{
				content="暂无总结";;
				$("#announcements").empty().append(content);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
}

function showDetail(id){
	window.location.href = "seeSummaryDetail.html?id="+id;
}

function modify(id){
	window.location.href = "publishSummaryInfo.html?id="+id;
}


function deleteOne(id){
	if(window.confirm('你确定要删除吗？')){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/LabSys/summaryController/deletesummary.do',
			async : false,
			data : {
				summaryId:id
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
	
	var page=1;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/summaryController/showList.do',
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
						
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.sumId+")'>"+val.sumTitle+"</a></td><td>"+val.sumDate+"</td><td>"+
						"<div class='form-group'><button onclick='modify("+val.sumId+")' type='button' class='btn btn-warning btn-xs'>修改</button></div>"+
						"<div class='form-group'><button onclick='deleteOne("+val.sumId+")' type='button' class='btn btn-danger btn-xs'>删除</button></div>"
						+"</td></tr>";
					}else{
						$("#addSummary").hide();
						num=num%5+1;
						content+="<tr><td>"+num+"</td><td><a href='#' onclick='showDetail("+val.sumId+")'>"+val.sumTitle+"</a></td><td>"+val.sumDate+"</td><td>"+
						"<div class='form-group'><button onclick='showDetail("+val.sumId+")' type='button' class='btn btn-success btn-xs'>查看详细信息</button></div>"
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
				content="暂无总结";;
				$("#announcements").empty().append(content);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
}


function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest=null ;

	if (url.indexOf("?") != -1) {
		theRequest={};
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest=new Object();
			theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
		}
	}
	return theRequest;
}