<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
.h{
font-weight: normal;
}
#h{
	padding:15px;
    text-align: justify;
    line-height:30px;
}
</style>
</head>
<body>
<center><h1 class="h">详细描述</h1></center>
<%	request.setCharacterEncoding("utf-8");
	String username =new String(request.getParameter("code").getBytes("iso8859-1"),"utf-8"); %>
<h4 class="h" id="h"><%=username %></h4>
</body>
</html>