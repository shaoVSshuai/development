<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<title>Insert title here</title>
</head>
<body>
	<div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 首页信息管理 >> 就业信息维护  
	</div>
	<iframe src="<%=basePath%>courseCon/courseDisplay.hzyc" name="result" style="width:1130px;height:450px;margin-top:20px">
	
	</iframe>
</body>
</html>