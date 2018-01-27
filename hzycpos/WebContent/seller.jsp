<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	iframe{width:100%;height:652px;}
</style>
<script type="text/javascript" src="jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	function ajaxSubmit(){
		var name = document.getElementById("name").value;
		var date = document.getElementById("date").value;
		var imgs=[];//存储图片链接
		 //为文件上传添加change事件
		var fileM=document.querySelector("#file");
		//获取文件对象，files是文件选取控件的属性，存储的是文件选取控件选取的文件对象，类型是一个数组
	    var fileObj=fileM.files[0];
	    //创建formdata对象，formData用来存储表单的数据，表单数据时以键值对形式存储的。
	    var formData=new FormData();
	    formData.append('file',fileObj);
	    formData.append('name',name);
	    formData.append('date',date);
	    var ajax=new XMLHttpRequest();
	    //发送POST请求
	    ajax.open("POST","<%=basePath%>seller/addSeller",true);
	    ajax.send(formData);
	    ajax.onreadystatechange=function(){ 
	    	if (ajax.readyState == 4) {
	    		var data= ajax.responseText;
				if (data == 1) {
					alert("添加成功！");
					document.getElementById("iframe1").style.display = "block";
				} else {
					alert("商店名和照片不能为空！");
				}
	    		
		  		
	 	 }};
	}
</script>
</head>
<body>
	<p style="color:#f00">商家页面</p>
	<form action="" id="tijiao">
		请输入商店名称：<input type="text" name="name" id="name">
		请输入商店logo：<input type="file" name="picture" id="file">
		请选择试用截止日期(可以不选)：<input type="date" id="date">
		<input type="button" value="提交" onclick="ajaxSubmit()">
	</form>
	<hr>
	<iframe src="login.html" id="iframe1" style="display: none;"></iframe>
</body>
</html>