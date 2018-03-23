<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<%
	Dictionary dict = (Dictionary)request.getAttribute("dict");
%>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
</head>
<body>
<form action="" class="lay-form" method="post" >
		 ID <input type="text" >
		 字典类别代码 <input type="text" >
		  字典代码	 <input type="text" >
		  字典名称 <input type="text" >
		  排序 <input type="text" >
		  是否可用 <input type="text" >
  </form>
</body>
</html>