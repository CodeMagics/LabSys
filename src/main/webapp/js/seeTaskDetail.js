$(document).ready(function (){
	var Request = new Object();
	Request = GetRequest();

	if (Request != null) {
		id = Request['id'];
	}

	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/LabSys/taskController/SelectById.do',
		async : false,
		data : {
			id:id
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				alert(msg.publisher);
				var detail="";
				detail="<h4 align='center'><b>"+msg.task.taskTitle+"</b></h4>"
								+"<div style='text-align: center'><br /><span>发布人：</span> <span>"+msg.publisher+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
								+"<span>发布时间：</span><span>"+msg.task.taskDate+"</span></div><br />" +
								"<pre>"+msg.task.taskDetails+"</pre>";
				$("#detail").empty().append(detail);
			}else{
				alert(msg.message);
			}
		},error: function(msg){
		    alert("网络超时！");
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