+function load(){
	var s = document.createElement('script');
	s.setAttribute("src", "../static/js/jquery-1.12.4.min.js");
	document.head.appendChild(s);
	//('<script src="/fms/js/jquery-1.12.4.min.js"/>');
}()

function auth(){
	var user = $("#auth-form input[name='user']").val();
	var passwd = $("#auth-form input[name='passwd']").val();
	$.get('/fms/auth',{'user': user, 'passwd': passwd}, function(r){
		// alert(JSON.stringify(r))
		$("#auth-key").html('<span style="padding-left:80px">' + r.data +"</span>")
	})
}

function download(){
	var s = $("#dl-form input[name='s']").val();
	$.get('/fms/download',{'s': s}, function(resp,status,req){
		var disp = req.getResponseHeader('Content-Disposition');
		if (disp && disp.search('attachment') != -1) {  //判断是否为文件
			window.open('/fms/download?s=' + s)
		} else {
			$("#dl-result").html('<span style="padding-left:0px">' + JSON.stringify(resp) +"</span>")
		}
		//console.log(JSON.stringify(r))
	})
}

function upload(){
	var files = $("#auth-form input[name='files']").get(0);
	var auth = $("#auth-form input[name='auth']").val();
	
	var fileObj = document.getElementsByName("files")[0].files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择文件");
        return;
    }
	var formFile = new FormData();
    //formFile.append("action", "UploadVMKImagePath");  
    formFile.append("files", fileObj); //加入文件对象
	$.ajax({
		url:'/fms/upload/filesUpload',
		 data: formFile,
         type: "post",
         dataType: "json",
         cache: false,//上传文件无需缓存
         processData: false,//用于对data参数进行序列化处理 这里必须false
         contentType: false, //必须 
		success: function(r){
			// alert(JSON.stringify(r))
			$("#result-code").html('<pre  class="brush: js;" >' + JSON.stringify(r) + '</pre>');
			if(SyntaxHighlighter)
				SyntaxHighlighter.all();
		}
	});
}