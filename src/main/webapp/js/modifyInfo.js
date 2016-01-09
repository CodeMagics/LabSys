var id=1;
$(document).ready(function (){
	var Request = new Object();
	Request = GetRequest();

	if (Request != null) {
		id = Request['id'];
	}

	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/taskController/findAnnouncementsById.do',
		async : false,
		data : {
			id:id
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				var title=msg.detail.title;
				var content=msg.detail.content;
				var type1=msg.detail.type;
				$("#title").val(title);
				$("#content").val(content);
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					url : '/zzxt/announcementController/showAllannouncementType.do',
					async : false,
					data : {
						
					},
					dataType : 'json',
					success : function(msg) {
						if(msg.result ==true){
							var type="";
							$.each(msg.type,function(key,val){
								if(val.antpName==type1){
									type+="<option selected='selected' value='"+val.antpId+"'>"+val.antpName+"</option>";
								}else{
									type+="<option value='"+val.antpId+"'>"+val.antpName+"</option>";
								}
							});
							$("#type").empty().append(type);
						}else{
							alert(msg.message);
						}
					},error: function(msg){
					    alert("网络超时！");
					}
				});
			}else{
				alert(msg.message);
			}
		},error: function(msg){
		    alert("网络超时！");
		}
	});
	
	$("#save").click(function(){
		if(check()){
			var title=$("#title").val().trim();
			var content=$("#content").val();
			var type=$('#type option:selected') .val();//选中的值$("type").val();
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				url : '/zzxt/announcementController/updateAnnouncement.do',
				async : false,
				data : {
					id:id,
					title:title,
					content:content,
					type:type
				},
				dataType : 'json',
				success : function(msg) {
					if(msg.result ==true){
						alert("发布成功！");
						window.location.href="showAnnouncements.html";
					}else{
						alert(msg.message);
					}
				},error: function(msg){
				    alert("网络超时！");
				}
			});
		}
	});
});



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
	if (url.indexOf("id") == -1) {
		theRequest = null;
	}
	return theRequest;
}

function check(){
	if($("#title").val().trim()==""){
		var tip="<div class='alert alert-danger alert-dismissible fade in' role='alert'>"+
      "<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>×</span><span class='sr-only'>Close</span></button>"+
	    "标题不能为空！</div>";
		$("#tip").empty().append(tip);
		return false;
	}else if($("#content").val().trim()=="" || $("#content").val().length<10){
		var tip="<div class='alert alert-danger alert-dismissible fade in' role='alert'>"+
	      "<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>×</span><span class='sr-only'>Close</span></button>"+
		    "发布的内容不能为空！请检查您的内容！</div>";
			$("#tip1").empty().append(tip);
		return false;
	}else{
		return true;
	}
};

function cleanTip(){
	$("#tip").empty();
}

function cleanTip1(){
	$("#tip1").empty();
}